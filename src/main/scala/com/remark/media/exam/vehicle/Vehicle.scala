package com.remark.media.exam.vehicle

import scala.util.control.Breaks._

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车
  *
  * @param statusList 月球车每秒的状态信息列表，也就是预置的运行线路
  */
class Vehicle(statusList: List[VehicleStatus]) extends Thread {
  var moving = true

  /**
    * 移动月球车
    */
  override def run() = {
    statusList.foreach(status => {
      println(status)
      Thread.sleep(1000)

      if (!moving) {
        break
      }
    })
  }

  /**
    * 停止移动月球车
    */
  def stopMove() = {
    moving = false
  }
}
