package ru.ibs.meta

case class KafkaMessage(
       key: String,
       value: String,
       topic: String,
       offset: Long,
       partition: Int,
       timestamp: Long)