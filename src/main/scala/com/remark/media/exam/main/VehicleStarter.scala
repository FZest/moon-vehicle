package com.remark.media.exam.main

import com.remark.media.exam.vehicle.VehicleFactory

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车启动器
  */
object VehicleStarter {
  def main(args: Array[String]) {
    val vehicleList = VehicleFactory.buildVehicles("vehicle.lines")
    vehicleList.foreach(vehicle => {
      vehicle.start()
    })
  }
}
