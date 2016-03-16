package main

import scala.collection.mutable.ListBuffer
import scala.io.BufferedSource

class MatchListener() extends Validation with IMGStats{
  private final val error = "ERROR"
  val eventList = new ListBuffer[MatchEvent]

  def convertToBinary(line: String): String = {
    try {
      val hex = Integer.parseInt(line.replace("0x", ""), 16)
      val bString = hex.toBinaryString
      val padding = "0"
      var paddingResult = ""

      if (bString.length < 32) {
        val missingChars = 32 - bString.length
        for (i <- 1 to missingChars) paddingResult += padding
      }
      paddingResult + bString
    }
    catch {
      case e: Exception => error
    }
  }

  def readContent(inputStream: BufferedSource): Seq[MatchEvent] = {
    eventList.clear()
    inputStream.getLines().foreach(line => {

      val binaryRep = convertToBinary(line)
      if (binaryRep != error) {

        val pointsScored = Integer.parseInt(binaryRep.takeRight(2), 2)
        val teamScored = Integer.parseInt(binaryRep.substring(binaryRep.length - 3, binaryRep.length - 2), 2)
        val team2Total = Integer.parseInt(binaryRep.substring(binaryRep.length - 11, binaryRep.length - 3), 2)
        val team1Total = Integer.parseInt(binaryRep.substring(binaryRep.length - 19, binaryRep.length - 11), 2)
        val secondsPassed = Integer.parseInt(binaryRep.substring(binaryRep.length - 31, binaryRep.length - 19), 2)

        eventList.+=(MatchEvent(pointsScored,
          teamScored,
          team1Total,
          team2Total,
          secondsPassed,
          if (eventList.size < 2) false
          else validateResults(eventList.tail.reverse.head, pointsScored, team1Total, team2Total, teamScored, secondsPassed)))
      }
    }
    )
    eventList.toSeq
  }

  def process(inputStream: BufferedSource): Game = {
    new Game(1, MatchType.BasketBall, readContent(inputStream))
  }

  def viewAllEvents(): Unit = allEvents().foreach(println(_))

  def viewListOfEvents(number: Int): Unit = getListOfEvents(number).foreach(println(_))

  def viewLastEvent(): Unit = getListOfEvents(1).foreach(println(_))


  //send events to REST API (JSON format) or web services (xml)
  def sendToConsumer() ={}

}
