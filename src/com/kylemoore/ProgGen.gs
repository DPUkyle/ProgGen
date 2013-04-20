package com.kylemoore

uses com.kylemoore.Schedule
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

  static function Main(args : String[]) : void {
    var msg = "Hi"
    print("Hello woild!")
    var s = new Schedule()
    print(s.renderToString( "wow" ))


    // make new program with properties {title:"Test1", start time:date "Friday, April 19, 2013 7:10:00 PM CST", channel number:"1180", duration:12600, episode:"something"}

  }

}