package com.remark.media.exam.common

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 定时调度操作类型
  */
object ScheduleType extends Enumeration {
  type ScheduleType = Value

  /**
    * 打印月球车状态信息
    */
  val PRINT = Value

  /**
    * 发送月球车状态信息
    */
  val SEND = Value
}
