package com.kylemoore

uses com.kylemoore.Schedule
uses org.apache.logging.log4j.Logger
uses org.apache.logging.log4j.LogManager

/**
 * Created with IntelliJ IDEA.
 * User: kmoore
 * Date: 2013/04/20
 * Time: 00:14
 * To change this template use File | Settings | File Templates.
 *
 * Execute with Vark:
 *  aardvark/0.4/bin/vark Run
 */
class ProgGen {

  private static var _logger : Logger as readonly logger

//  static function Main(args : String[]) : void {
//    var msg = "Hi"
//    print("Hello woild!")
//    var s = new Schedule()
//    print(s.renderToString( "wow" ))

    // make new program with properties {title:"Test1", start time:date "Friday, April 19, 2013 7:10:00 PM CST", channel number:"1180", duration:12600, episode:"something"}

//  }

  construct() {
    _logger = LogManager.getLogger("") // root logger
    init()
  }

  private function init() {

    var msg = "Hello worlddd"
    print("Error enabled? ${logger.ErrorEnabled}")
    print("Debug enabled? ${logger.DebugEnabled}")
    print("Warn enabled? ${logger.WarnEnabled}")
    print("Info enabled? ${logger.InfoEnabled}")
    logger.error(msg)
    logger.debug(msg)
    logger.warn(msg)
    logger.info(msg)
  }

}