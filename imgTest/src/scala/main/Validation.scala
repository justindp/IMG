package main

import scala.collection.mutable.ListBuffer

trait Validation {
//this can be expanded
  def validateResults(previousEvent: MatchEvent,
                      pointsScored: Int,
                      team1Total: Int,
                      team2Total: Int,
                      teamScored: Int,
                      secondsPassed: Int): Boolean = {
    if ((pointsScored == 0) ||
      (previousEvent.team1TotalPoints - team1Total > 0 || team1Total - previousEvent.team1TotalPoints > 3) ||
      (previousEvent.team2TotalPoints - team2Total > 0 || team2Total - previousEvent.team2TotalPoints > 3) ||
      (teamScored == 1 && (team2Total - previousEvent.team2TotalPoints != pointsScored)) ||
      (teamScored == 0 && (team1Total - previousEvent.team1TotalPoints != pointsScored)) ||
      (secondsPassed < previousEvent.timeElapsed))
      true
    else false
  }

}
