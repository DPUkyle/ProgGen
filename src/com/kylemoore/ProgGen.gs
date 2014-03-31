package com.kylemoore

uses com.kylemoore.Schedule
uses org.apache.logging.log4j.Logger
uses org.apache.logging.log4j.LogManager
uses java.io.FileInputStream
uses org.apache.poi.hssf.usermodel.HSSFCell
uses org.apache.poi.hssf.usermodel.HSSFDateUtil
uses org.apache.poi.hssf.usermodel.HSSFRow
uses org.apache.poi.hssf.usermodel.HSSFSheet
uses org.apache.poi.hssf.usermodel.HSSFWorkbook
uses sun.nio.ch.ChannelInputStream
uses java.util.concurrent.ScheduledExecutorService
uses com.sun.org.apache.xpath.internal.operations.Variable
uses javax.swing.border.TitledBorder

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
    logger.info("About to load data file")
    var myxls = new FileInputStream("resources/2014_TV_Schedule.xls")
    var wb : HSSFWorkbook = new HSSFWorkbook(myxls)
    var sheet : HSSFSheet = wb.getSheetAt(0)
    var rows : List<HSSFRow> = sheet.rowIterator().toList() as List<HSSFRow>

    var gameNumberIndex : int
    var dateIndex : int
    var opponentIndex : int
    var timeIndex : int
    var channelIndex : int

    //special handling for the header row
    var headerRow : HSSFRow = rows.first()
    var headerCells : List<HSSFCell> = headerRow.cellIterator().toList() as List<HSSFCell>
    headerCells.each( \ cell -> {
      if(cell.isString) {
        switch(cell.StringCellValue) {
          case "GameNumber" : gameNumberIndex = cell.ColumnIndex
                              break
          case "Date"       : dateIndex = cell.ColumnIndex
                              break
          case "Opponent"   : opponentIndex = cell.ColumnIndex
                              break
          case "Time"       : timeIndex = cell.ColumnIndex
                              break
          case "Channel"    : channelIndex = cell.ColumnIndex
                              break
          default           : logger.error("Couldn't find a match for '${cell.StringCellValue}'")
        }
      }
    } )

    //now loop and generate a template for each row's data
    for(i in 1..|rows.Count) {
      var theRow = rows.get(i)
      var gameNumber = theRow.getCell(gameNumberIndex).StringCellValue.toInt() // NumericCellValue as int
      var date = theRow.getCell(dateIndex).StringCellValue
      var opponent = theRow.getCell(opponentIndex).StringCellValue
      var time = theRow.getCell(timeIndex).StringCellValue
      var channel : TVStationEnum = TVStationEnum.valueOf(theRow.getCell(channelIndex).StringCellValue)

      var opponentString = " Cubs " + (opponent.contains("@") ? "at ${opponent.remove("@")}" : opponent)
      var titleString = "MLB Baseball:".concat(opponentString)
      var episodeString = (gameNumber as String).concat(opponentString)
      var dateTimeString = date + " " + time + " CDT"

      if(time != "TBD" and channel != WCIU) {
        print(new Schedule().renderToString(titleString, episodeString, dateTimeString, channel.ChannelNumber as String, "12600"))
      }
    }

  }
}