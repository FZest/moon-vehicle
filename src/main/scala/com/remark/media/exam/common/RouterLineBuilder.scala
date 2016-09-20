package com.remark.media.exam.common

import java.util

import com.remark.media.exam.vehicle.{VehicleLocation, VehicleStatus}

import scala.collection.JavaConversions._
import scala.util.Random

/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车线路构造器
  * 为了方便构造线路数据，假设月球车在每一个线段上是均速移动的，每个线段上的移动速度不一样
  */
object RouterLineBuilder {
  def main(args: Array[String]) {
    // 线路随机构造限制
    val locationXLimit = 100
    val locationYLimit = 100
    val speedLimit = 5
    val timeLimit = 20 * 60

    var timeTotal = 0
    val random = new Random()
    val statusList = new util.ArrayList[VehicleStatus]()

    var curLocation: VehicleLocation = null
    var nextLocation: VehicleLocation = null
    var preDirection: Double = 0.0
    var first = true

    // 每次循环构造一个线段（当前位置->目的位置）
    while (timeTotal < timeLimit) {
      // 随机生成目的位置
      if (curLocation == null) {
        curLocation = VehicleLocation(random.nextInt(locationXLimit).toDouble, random.nextInt(locationYLimit).toDouble)
      }
      nextLocation = VehicleLocation(random.nextInt(locationXLimit).toDouble, random.nextInt(locationYLimit).toDouble)

      // 随机生成移动速度与方向
      val speed = random.nextInt(speedLimit).toDouble + 1
      val direction = LocationUtils.calculateDirection(curLocation, nextLocation)

      // 根据当前位置、目的位置与移动速度计算移动所需时间
      val time = LocationUtils.calculateTime(curLocation, nextLocation, speed).toInt

      // 构造此线段上每一秒的位置
      Range(0, time).foreach(i => {
        val location = LocationUtils.predictLocation(curLocation, direction, speed * i)
        if (i == 0 && !first) {
          statusList.add(VehicleStatus("Vehicle_1", location, nextLocation, direction, speed, direction - preDirection))
        } else {
          statusList.add(VehicleStatus("Vehicle_1", location, nextLocation, direction, speed, 0.0))
        }
      })

      timeTotal = timeTotal + time
      curLocation = nextLocation
      preDirection = direction
      first = false
    }

    val routerLine = new StringBuilder()
    val separator = " "
    statusList.toList.foreach(status => {
      routerLine.append(status).append(separator)
    })
    println(routerLine)
  }
}
