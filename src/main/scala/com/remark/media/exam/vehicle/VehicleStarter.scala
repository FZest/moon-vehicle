package com.remark.media.exam.vehicle

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车启动器
  */
object VehicleStarter {
  def main(args: Array[String]) {
    val vehicleList = VehicleFactory.buildVehicles("vehicle")
    vehicleList.foreach(vehicle => {
      vehicle.run()
    })
  }
}
