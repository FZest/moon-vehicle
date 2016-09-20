package com.remark.media.exam.controller

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 地面控制中心操作类型（目前只有输出月球车状态信息一种操作）
  */
object OperateType extends Enumeration {
  type OperateType = Value
  val PRINT = Value
}
