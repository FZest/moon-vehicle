package com.remark.media.exam.controller

import java.text.DecimalFormat

import com.remark.media.exam.vehicle.VehicleLocation

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 在地面控制中心展示的月球状态信息
  *
  * @param id                月球车ID
  * @param reportLocation    月球车的报告位置
  * @param predicateLocation 月球车的预测位置
  * @param direction         月球车的移动方向
  */
case class StatusShow(id: String, reportLocation: VehicleLocation, predicateLocation: VehicleLocation,
                      direction: Double) {
  val formatter = new DecimalFormat("0.00")

  override def toString(): String = {
    val content = new StringBuilder()

    content.append("ID=>")
    content.append(id)
    content.append(", Report Location=>")
    content.append(formatter.format(reportLocation.x))
    content.append(",")
    content.append(formatter.format(reportLocation.y))
    content.append(", Predicate Location=>")
    content.append(formatter.format(predicateLocation.x))
    content.append(",")
    content.append(formatter.format(predicateLocation.y))
    content.append(", Direction=>")
    content.append(formatter.format(Math.toDegrees(direction)))
    content.append("°")

    content.toString()
  }
}
