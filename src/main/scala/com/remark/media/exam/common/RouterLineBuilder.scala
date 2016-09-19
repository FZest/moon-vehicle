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
  */
object RouterLineBuilder {
  def main(args: Array[String]) {
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
    while (timeTotal < timeLimit) {
      if (curLocation == null) {
        curLocation = VehicleLocation(random.nextInt(locationXLimit).toDouble, random.nextInt(locationYLimit).toDouble)
      }
      nextLocation = VehicleLocation(random.nextInt(locationXLimit).toDouble, random.nextInt(locationYLimit).toDouble)

      val speed = random.nextInt(speedLimit).toDouble + 1
      val direction = LocationUtils.calculateDirection(curLocation, nextLocation)
      val time = LocationUtils.calculateTime(curLocation, nextLocation, speed).toInt

      Range(0, time).foreach(i => {
        val location = LocationUtils.predictLocation(curLocation, direction, speed * i)
        if (i == 0 && !first) {
          statusList.add(VehicleStatus(location, nextLocation, direction, speed, direction - preDirection))
        } else {
          statusList.add(VehicleStatus(location, nextLocation, direction, speed, 0.0))
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
