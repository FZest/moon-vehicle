package com.remark.media.exam.controller

import akka.actor.{ActorSystem, Props}
import com.remark.media.exam.actor.ControllerActor
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 地面控制中心
  */
class Controller {
  val system = ActorSystem("controller", ConfigFactory.load.getConfig("controller.system"))

  def start() = {
    val controllerActor = system.actorOf(Props[ControllerActor], "controller")
    system.scheduler.schedule(0 milliseconds, 500 milliseconds, controllerActor, OperateType.PRINT)
  }

  def stop() = {
    system.shutdown()
  }
}
