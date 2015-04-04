package com.kylemoore

final enum TVStationEnum {
  ABC7(-1),
  CSN(1741),
  CSNPLUS(1742),
  ESPN(1602),
  ESPN2(1606),
  FOX(1011),
  FS1(1652),
  WCIU(-1),
  WGN(1180),
  WPWR(-1)

  private var _channelNumber : int

  private construct(channelNumber : int) {
    _channelNumber = channelNumber
  }

  property get ChannelNumber() : int {
    return _channelNumber
  }

}