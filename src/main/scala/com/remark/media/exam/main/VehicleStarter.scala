package com.remark.media.exam.main

import akka.actor.ActorSystem
import com.remark.media.exam.vehicle.VehicleFactory
import com.typesafe.config.ConfigFactory

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车启动器
  */
object VehicleStarter {
  def main(args: Array[String]) {
    val system = ActorSystem("vehicle", ConfigFactory.parseResources("vehicle.system"))
    val vehicleList = VehicleFactory.buildVehicles("vehicle.lines", system)
    vehicleList.foreach(vehicle => {
      vehicle.start()
    })
  }
}
