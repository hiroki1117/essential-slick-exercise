import Example._

import slick.jdbc.H2Profile.api._
import slick.dbio.DBIOAction
import slick.dbio.NoStream
import slick.dbio.Effect

object Exercise {
  def insertMessage: DBIOAction[Int,NoStream,Effect.Write] = messages += Message("Dave", "What if I say 'Pretty Please'")

  def filterByDave = messages.filter(_.sender === "Dave")
}