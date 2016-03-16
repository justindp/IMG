package main

case class Games(game : Game)

case class Game(id: Double,
                matchType: MatchType.Value,
                events: Seq[MatchEvent])
