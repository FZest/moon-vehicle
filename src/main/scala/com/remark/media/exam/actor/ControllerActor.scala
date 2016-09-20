package com.remark.media.exam.actor

import akka.actor.Actor
import com.remark.media.exam.controller.Controller
import com.remark.media.exam.vehicle.VehicleStatus

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 地面控制中心actor
  */
class ControllerActor(controller: Controller) extends Actor {
  override def receive: Receive = {
    case status: VehicleStatus => {

    }

    case _ => "Wrong message type."
  }
}
