import Example._

import slick.jdbc.H2Profile.api._
import slick.dbio.DBIOAction
import slick.dbio.NoStream
import slick.dbio.Effect

object Exercise {
  val tableLength = exec(messages.length.result)

  val whereIdEq999 = exec(
    (for {
      m <- messages if m.id === 999L
    } yield m).result
  )

  val halExists = exec(
    messages.filter(_.sender === "HAL").exists.result
  )

  val allContent = exec(
    messages.map(_.content).result
  )

  val allContentWithFor = exec(
    (for(m <- messages) yield m.content).result
  )

  val headAlice = exec(
    messages.filter(_.sender === "Alice").result.headOption
  )

  val nextFive = exec(
    messages.filter(_.sender === "HAL").drop(1).take(5).result
  )

  val startWith = exec(
    messages.filter(_.content startsWith "Open").result
  )

  val like = exec(
    messages.filter(_.content like "%do%").result
  )
}