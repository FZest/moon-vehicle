package com.remark.media.exam.actor

import akka.actor.Actor
import com.remark.media.exam.common.LocationUtils
import com.remark.media.exam.controller.{OperateType, StatusShow}
import com.remark.media.exam.vehicle.VehicleStatus

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
    * 然后将结果放置到此队列中
    * 以便定时任务输出各个月球车的状态信息
    */
  val queue = new mutable.Queue[StatusShow]()

  /**
    * 月球车与地面控制中心的通信延迟（秒）
    */
  val delaySeconds = 2

  override def receive: Receive = {
    // 接收来自月球车的状态信息
    case status: VehicleStatus => {
      val direction = status.direction + status.steeringAngle
      val moveDistance = status.speed * delaySeconds
      val predicateLocation = LocationUtils.predictLocation(status.currentLocation, direction, moveDistance)

      queue.enqueue(StatusShow(status.id, status.currentLocation, predicateLocation, direction))
    }

    // 接收定时调度信号，并打印月球车状态信息
    case OperateType.PRINT => {
      if (!queue.isEmpty) {
        println(queue.dequeue())
      }
    }

    case _ => "Wrong message type."
  }
}
