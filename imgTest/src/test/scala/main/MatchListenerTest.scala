package main

import org.scalatest.{Tag, FunSuite}

import scala.io.Source


class MatchListenerTest extends FunSuite {
  //    val temp = new main.MatchReader()
//  val t = new MatchReader()

  test("Check to see if we can read the file", IntegrationTest) {
    val resource = Source.fromURL(getClass.getResource("/sample1.txt"))
    assertResult(true)(resource.size > 2)
  }

  test("Test the 1st sample input", IntegrationTest) {
    val resource = Source.fromURL(getClass.getResource("/sample1.txt"))
    val newReader = new MatchListener()
    val newMatch: Game = newReader.process(resource)
    assertResult(true)(newMatch.events.size > 2)
  }

  test("Test the 2nd sample input", IntegrationTest) {
    val resource = Source.fromURL(getClass.getResource("/sample2.txt"))
    val newReader = new MatchListener()
    val newMatch: Game = newReader.process(resource)
    assertResult(true)(newMatch.events.size > 2)
  }

  test("conversion test", UnitTest) {
    val hex = "0x781002"
    val newReader = new MatchListener()
    val integerVal = newReader.convertToBinary(hex)

    val pointsScored = Integer.parseInt(integerVal.takeRight(2), 2)
    val teamScored = integerVal.substring(integerVal.length - 3, integerVal.length - 2)
    val team2Total = integerVal.substring(integerVal.length - 11, integerVal.length - 3)
    val team1Total = integerVal.substring(integerVal.length - 19, integerVal.length - 11)
    val secondsPassed = Integer.parseInt(integerVal.substring(integerVal.length - 31, integerVal.length - 19), 2)

    assertResult(true)(teamScored === 0)
    assertResult(true)(pointsScored === 2)
    assertResult(true)(team2Total === 0)
    assertResult(true)(team1Total === 2)
    assertResult(true)(secondsPassed === 15)

  }
}

object IntegrationTest extends Tag("IntegrationTest")
object UnitTest extends Tag("UnitTest")
