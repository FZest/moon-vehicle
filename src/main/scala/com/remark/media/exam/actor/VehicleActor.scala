package com.remark.media.exam.actor

import akka.actor.{Actor, ActorSelection}
import com.remark.media.exam.common.{Response, ResponseCode, ScheduleType}
import com.remark.media.exam.vehicle.VehicleStatus

import scala.collection.mutable

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 月球车actor
  */
class VehicleActor extends Actor {
  val queue = new mutable.Queue[VehicleStatus]()

  var controllerActor: ActorSelection = null

  override def preStart(): Unit = {
    controllerActor = context.actorSelection("akka.tcp://controller@127.0.0.1:10000/user/controller")
  }

  override def receive: Receive = {
    // 将预置路线中的各个状态信息存储到队列
    case status: VehicleStatus => {
      queue.enqueue(status)
    }

    // 接收来自控制中心的响应消息
    case response: Response => {
      //      println(response)
    }

    // 处理定时调度信号，并从队列中取出状态信息发送给控制中心
    case ScheduleType.SEND => {
      controllerActor ! queue.dequeue()
    }

    case _ => sender ! Response(ResponseCode.ERROR, "Wrong message type.")
  }
}
