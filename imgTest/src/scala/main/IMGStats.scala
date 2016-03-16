package main

import scala.collection.mutable.ListBuffer


trait IMGStats {
  val eventList: ListBuffer[MatchEvent]

  def allEvents(): Seq[String] = eventList.map(convertEventIntoReadableOutput)


  def getListOfEvents(list: Int): Seq[String] = eventList.takeRight(list).map(convertEventIntoReadableOutput)


  def convertEventIntoReadableOutput(event: MatchEvent): String = {
    "Team " + event.teamScored + " scored " + event.pointsScored + " point(s) at match time " +
      convertTimeElapsedToScoreBoardTime(event.timeElapsed) +
      " Score now is " + event.team1TotalPoints + ":" + event.team2TotalPoints
  }

  def convertTimeElapsedToScoreBoardTime(timeElapsed: Int): String = {
    val seconds = (timeElapsed % 60).toString
    timeElapsed / 60 + ":" + (if (seconds.length < 2) "0" + seconds else seconds)
  }
}
