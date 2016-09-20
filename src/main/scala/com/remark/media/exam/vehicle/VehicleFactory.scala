package com.remark.media.exam.vehicle

import scala.collection.mutable.ListBuffer
import scala.io.Source


/**
  * User: 邓思 
  * Date: 2016-09-19
  *
  * 月球车构建工厂
  */
object VehicleFactory {
  /**
    * 从指定配置文件中读取月球车预置线路，并生成月球车对象列表
    *
    * @param filename 配置文件
    * @return 月球车对象列表
    */
  def buildVehicles(filename: String): List[Vehicle] = {
    val vehicleList = new ListBuffer[Vehicle]()
    val fileInputStream = this.getClass.getClassLoader.getResourceAsStream(filename)

    Source.fromInputStream(fileInputStream).getLines().foreach(line => {
      val statusList = new ListBuffer[VehicleStatus]()
      val statusArray = line.split(" ")
      val vehicleId = statusArray(0)

      statusArray.foreach(statusString => {
        val tokens = statusString.split(",")
        if (tokens.size >= 7) {
          statusList.append(VehicleStatus(vehicleId, VehicleLocation(tokens(0).toDouble, tokens(1).toDouble),
            VehicleLocation(tokens(2).toDouble, tokens(3).toDouble), tokens(4).toDouble,
            tokens(5).toDouble, tokens(6).toDouble))
        }
      })

      vehicleList.append(new Vehicle(statusList.toList))
    })

    vehicleList.toList
  }
}
