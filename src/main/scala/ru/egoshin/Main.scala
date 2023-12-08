package ru.egoshin

import ru.egoshin.utils.Utils
import ru.egoshin.workflow.{Application, Program}

object Main extends App with Utils {
  implicit val app: Application = new Application

  val argumentsMap = ArgsParser(args)(app).toMap

  println(argumentsMap)
  val program = new Program(argumentsMap)(app)
  program.run()
}
