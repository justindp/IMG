package main


case class MatchEvent(pointsScored: Int,
                      teamScored: Int,
                      team1TotalPoints: Int,
                      team2TotalPoints: Int,
                      timeElapsed: Int,
                      suspect: Boolean = false)
