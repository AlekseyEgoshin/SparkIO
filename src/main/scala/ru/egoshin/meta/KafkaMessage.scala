package ru.egoshin.meta

import java.sql.Timestamp

case class KafkaMessage(
       key: String,
       value: String,
       topic: String,
       partition: Int,
       offset: Long,
       timestamp: Timestamp)