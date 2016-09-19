package com.remark.media.exam.vehicle

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车
  *
  * @param id         月球车ID
  * @param statusList 月球车每秒的状态信息列表，也就是预置的运行线路
  */
class Vehicle(id: String, statusList: List[VehicleStatus]) {
  /**
    * 启动并运行月球车
    */
  def run() = {
    statusList.foreach(status => {
      println(status)

      // Move the moon vehicle
      Thread.sleep(1000)
    })
  }
}
