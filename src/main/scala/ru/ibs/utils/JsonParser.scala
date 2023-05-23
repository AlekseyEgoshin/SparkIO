package ru.ibs.utils

import org.json4s.JValue
import org.json4s.native.JsonMethods.parse

object JsonParser {
  def parseFromString(input: String): JValue = {
    input match {
      case _ if input.nonEmpty => parse(input)
      case _ => throw new IllegalArgumentException("Input value is empty")
    }
  }
}
