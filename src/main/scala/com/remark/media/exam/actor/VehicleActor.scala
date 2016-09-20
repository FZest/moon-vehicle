package com.remark.media.exam.actor

import akka.actor.{Actor, ActorRef, ActorSelection}
import com.remark.media.exam.common.Response
import com.remark.media.exam.vehicle.VehicleStatus

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 月球车actor
  */
class VehicleActor extends Actor {
  var remoteActor: ActorSelection = null

  var localActor: ActorRef = null

  override def preStart(): Unit = {
    remoteActor = context.actorSelection("akka.tcp://controller@127.0.0.1:10000/user/controller")
    println("The remote server address is: " + remoteActor)
  }

  override def receive: Receive = {
    case status: VehicleStatus => {
      localActor = sender()
      remoteActor ! status
    }

    case response: Response => {
      println("Receive response from controller: " + response)
      localActor ! response
    }

    case _ => "Wrong message type."
  }
}
