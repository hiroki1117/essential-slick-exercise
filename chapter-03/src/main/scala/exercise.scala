import Example._

import slick.jdbc.H2Profile.api._
import slick.dbio.DBIOAction
import slick.dbio.NoStream
import slick.dbio.Effect

object Exercise {
  val updateSenderAndContents: DBIOAction[Int, NoStream, Effect.Write] = messages.map(m => (m.sender, m.content)) += ("HAL" , "Sample Contents")

  val conversation = List(
      Message("Bob","HiAlice"),
      Message("Alice","HiBob"),
      Message("Bob","Areyousurethisissecure?"),
      Message("Alice","Totally,whydoyouask?"),
      Message("Bob","Oh,nothing,justwondering."),
      Message("Alice","Tenwastoomanymessages"),
      Message("Bob","Icoulddowithasleep"),
      Message("Alice","Let'sjusttotothepoint"),
      Message("Bob","Okayokay,noneedtobetetchy."),
      Message("Alice","Humph!")
      )

  val returningId = messages returning messages.map(_.id)

  val insertConversation = returningId ++= conversation

  val deleteLikeSorry = messages.filter(_.content like "%sorry%").delete

  val rebootLoopUsingForComprehension = (for{
      message <- messages if message.sender === "HAL"
  } yield (message.sender, message.content)).update("Hal 9000", "Rebooting")

  val deleteHalsFirstTwoMessages = messages.filter{
    _.id in messages.
      filter (_.sender === "HAL").
      sortBy (_.id.asc).
      map (_.id).
      take(2)
  }.delete
}