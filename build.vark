classpath "com.kylemoore:ProgGen:1.0-SNAPSHOT"

uses gw.vark.annotations.Target
uses gw.lang.Param
uses com.kylemoore.ProgGen

/**
 * Simple build target to instantiate a Gosu class
 */
@Target
function Run() {
  var pg = new ProgGen()
}
