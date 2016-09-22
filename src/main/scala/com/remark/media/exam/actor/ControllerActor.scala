package com.remark.media.exam.actor

import akka.actor.Actor
import com.remark.media.exam.common.{LocationUtils, Response, ResponseCode}
import com.remark.media.exam.controller.{OperateType, StatusShow}
import com.remark.media.exam.vehicle.{VehicleLocation, VehicleStatus}

import scala.collection.mutable

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 地面控制中心actor
  */
class ControllerActor extends Actor {
  /**
    * 地面控制中心将接收到的月球车状态信息进行位置预测
    * 然后将结果放置到队列中
    * 一辆月球车对应一个队列
    */
  val queueMap = new mutable.HashMap[String, mutable.Queue[StatusShow]]()

  val preStatusMap = new mutable.HashMap[String, StatusShow]()

  /**
    * 月球车与地面控制中心的通信延迟（秒）
    */
  val delaySeconds = 2

  override def receive: Receive = {
    // 接收来自月球车的状态信息
    case status: VehicleStatus => {
      val direction = status.direction + status.steeringAngle
      val moveDistance = status.speed * delaySeconds
      val maxDistance = LocationUtils.calculateDistance(status.currentLocation, status.destLocation)

      var predicateLocation: VehicleLocation = null
      if (moveDistance >= maxDistance) {
        // 如果移动后刚好超过目的位置，则月球车应该在目的位置停下来
        predicateLocation = status.destLocation
      } else {
        predicateLocation = LocationUtils.predictLocation(status.currentLocation, direction, moveDistance)
      }

      var queue: mutable.Queue[StatusShow] = null
      if (queueMap.get(status.id).isEmpty) {
        queue = new mutable.Queue[StatusShow]()
        queueMap.put(status.id, queue)
      } else {
        queue = queueMap.get(status.id).get
      }

      queue.enqueue(StatusShow(status.id, status.currentLocation, predicateLocation, direction))
      sender ! Response(ResponseCode.OK)
    }

    // 接收定时调度信号，并打印月球车状态信息
    case OperateType.PRINT => {
      val showContent = new StringBuilder()
      val seperator = "\t"

      queueMap.foreach(entry => {
        // 获取月球车ID与队列
        val id = entry._1
        val queue = entry._2

        if (!queue.isEmpty) {
          // 从队列中获取状态信息
          val statusShow = queue.dequeue()
          preStatusMap.put(id, statusShow)

          showContent.append(statusShow)
          showContent.append(seperator)
        } else {
          // 无法从队列中获取状态信息，则将打印上次的状态
          if (!preStatusMap.get(id).isEmpty) {
            showContent.append(preStatusMap.get(id).get)
            showContent.append(seperator)
          }
        }
      })

      println(showContent)
    }

    case _ => sender ! Response(ResponseCode.ERROR, "Wrong message type.")
  }
}
