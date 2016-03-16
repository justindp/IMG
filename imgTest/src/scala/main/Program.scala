package main

import scala.io.Source

object Program extends App {
  val matchListener = new MatchListener()
  val eventList = matchListener.eventList
  run()

  //please ensure scala is set as SRC folder and resources set as RESOURCES...
  //unnecessary problems my build is giving me
  def run(): Unit = {
    val source = Source.fromURL(getClass.getResource("/sample1.txt"))
    matchListener.process(source)

    matchListener.viewAllEvents()
    matchListener.viewLastEvent()
    matchListener.getListOfEvents(4)
    System.exit(0)
  }


}
