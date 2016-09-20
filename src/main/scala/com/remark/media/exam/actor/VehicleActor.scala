package com.remark.media.exam.actor

import akka.actor.Actor
import com.remark.media.exam.common.Response

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 月球车actor
  */
class VehicleActor extends Actor {
  override def receive: Receive = {
    case response: Response => {
      println("Receive response from controller: " + response)
    }

    case _ => "Wrong message type."
  }
}
