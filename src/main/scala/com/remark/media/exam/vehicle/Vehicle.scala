package com.remark.media.exam.vehicle

import akka.actor.{ActorRef, ActorSystem, Cancellable}
import com.remark.media.exam.common.ScheduleType

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车
  *
  * @param statusList 月球车每秒的状态信息列表，也就是预置的运行线路
  * @param actor      actor ref
  * @param system     actor system
  */
class Vehicle(statusList: List[VehicleStatus], actor: ActorRef, system: ActorSystem) extends Thread {
  val cancellableList = new ListBuffer[Cancellable]

  /**
    * 移动月球车
    */
  override def run() = {
    // 将预置路线发送给vehicle actor
    statusList.foreach(status => {
      actor ! status
    })

    cancellableList.append(system.scheduler.schedule(0 milliseconds, 1000 milliseconds, actor, ScheduleType.SEND))
  }

  /**
    * 停止移动月球车
    */
  def shutdown() = {
    cancellableList.foreach(cancellable => {
      cancellable.cancel()
    })
  }
}
