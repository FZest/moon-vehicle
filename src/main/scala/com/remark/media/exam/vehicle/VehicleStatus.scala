package com.remark.media.exam.vehicle

import java.text.DecimalFormat

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车的状态信息
  *
  * @param id              月球车ID
  * @param currentLocation 当前位置
  * @param destLocation    目标移动位置
  * @param direction       移动方向
  * @param speed           移动速度
  * @param steeringAngle   转向角度
  */
case class VehicleStatus(id: String, currentLocation: VehicleLocation, destLocation: VehicleLocation,
                         direction: Double, speed: Double, steeringAngle: Double = 0.0) {
  val formatter = new DecimalFormat("0.00")

  val separator = ","

  override def toString(): String = {
    val content = new StringBuilder()
    content.append(id)
    content.append(separator)
    content.append(formatter.format(currentLocation.x))
    content.append(separator)
    content.append(formatter.format(currentLocation.y))
    content.append(separator)
    content.append(formatter.format(destLocation.x))
    content.append(separator)
    content.append(formatter.format(destLocation.y))
    content.append(separator)
    content.append(formatter.format(direction))
    content.append(separator)
    content.append(formatter.format(speed))
    content.append(separator)
    content.append(formatter.format(steeringAngle))
    return content.toString()
  }
}
