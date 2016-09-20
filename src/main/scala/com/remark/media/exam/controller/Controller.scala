package com.remark.media.exam.controller

import akka.actor.{ActorSystem, Props}
import com.remark.media.exam.actor.ControllerActor
import com.typesafe.config.ConfigFactory

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 地面控制中心
  */
class Controller {
  val system = ActorSystem("controller", ConfigFactory.load.getConfig("controller.system"))

  def start() = {
    system.actorOf(Props[ControllerActor], "controller")
  }

  def stop() = {
    system.shutdown()
  }
}
