package ru.ibs

import ru.ibs.utils.Utils
import ru.ibs.workflow.{Application, Program}

object Main extends App with Utils {
  implicit val app: Application = new Application

  val argumentsMap = ArgsParser(args)(app).toMap

  println(argumentsMap)
  val program = new Program(argumentsMap)(app)
  program.run()
}
