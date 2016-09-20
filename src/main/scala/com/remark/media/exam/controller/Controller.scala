package com.remark.media.exam.controller

import akka.actor.{ActorSystem, Cancellable, Props}
import com.remark.media.exam.actor.ControllerActor
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 地面控制中心
  */
class Controller {
  val system = ActorSystem("controller", ConfigFactory.parseResources("controller.system"))

  var cancellable: Cancellable = null

  def start() = {
    val controllerActor = system.actorOf(Props[ControllerActor], "controller")
    cancellable = system.scheduler.schedule(0 milliseconds, 500 milliseconds, controllerActor, OperateType.PRINT)
  }

  def stop() = {
    if (cancellable != null) {
      cancellable.cancel()
    }

    system.shutdown()
  }
}
