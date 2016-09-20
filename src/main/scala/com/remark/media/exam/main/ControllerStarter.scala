package com.remark.media.exam.main

import com.remark.media.exam.controller.Controller

/**
  * User: 邓思
  * Date: 2016-09-20
  *
  * 地面控制中心启动器
  */
object ControllerStarter {
  def main(args: Array[String]) {
    new Controller().start()
  }
}
