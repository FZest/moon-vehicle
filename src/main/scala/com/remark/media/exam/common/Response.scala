package com.remark.media.exam.common

import com.remark.media.exam.common.ResponseCode.ResponseCode

/**
  * User: 邓思 
  * Date: 2016-09-20
  *
  * 控制中心的响应消息
  *
  * @param code 消息code
  * @param msg  附加描述
  */
case class Response(code: ResponseCode, msg: String = "")
