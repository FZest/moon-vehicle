package com.remark.media.exam.common

import com.remark.media.exam.vehicle.VehicleLocation

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 位置计算相关的工具对象
  */
object LocationUtils {
  /**
    * 计算两个位置之间的直线距离
    *
    * @param srcLocation  源位置
    * @param destLocation 目的位置
    * @return 直线距离
    */
  def calculateDistance(srcLocation: VehicleLocation, destLocation: VehicleLocation): Double = {
    val deltaX = Math.abs(srcLocation.x - destLocation.x)
    val deltaY = Math.abs(srcLocation.y - destLocation.y)
    Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2))
  }

  /**
    * 计算从源位置向目的位置的移动方向
    *
    * @param srcLocation  源位置
    * @param destLocation 目的位置
    * @return 移动方向
    */
  def calculateDirection(srcLocation: VehicleLocation, destLocation: VehicleLocation): Double = {
    val deltaX = destLocation.x - srcLocation.x
    val deltaY = destLocation.y - srcLocation.y
    return Math.atan(deltaY / deltaX)
  }

  /**
    * 计算从源位置移动到目的位置所需的时间（秒）
    *
    * @param srcLocation  源位置
    * @param destLocation 目的位置
    * @param speed        每秒移动的距离
    * @return 移动所需时间（秒）
    */
  def calculateTime(srcLocation: VehicleLocation, destLocation: VehicleLocation, speed: Double): Double = {
    return calculateDistance(srcLocation, destLocation) / speed
  }

  /**
    * 预测移动指定距离后的位置
    *
    * @param curLocation  当前位置
    * @param direction    移动方向
    * @param moveDistance 沿着指定方向移动的距离
    * @return 移动后的位置
    */
  def predictLocation(curLocation: VehicleLocation, direction: Double, moveDistance: Double): VehicleLocation = {
    VehicleLocation(curLocation.x + moveDistance * Math.cos(direction), curLocation.y + moveDistance * Math.sin(direction))
  }
}
