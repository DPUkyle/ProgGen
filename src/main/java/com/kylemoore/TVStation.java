package com.kylemoore;

public enum TVStation {
    ABC7(-1),
    CSN(1741),
    CSNPLUS(1742),
    ESPN(1602),
    ESPN2(1606),
    FOX(1011),
    FS1(1652),
    WCIU(-1),
    WGN(1180),
    WPWR(-1);

    private int _channelNumber;

    private TVStation(int channelNumber) {
        _channelNumber = channelNumber;
    }

    public int getChannelNumber() {
        return _channelNumber;
    }

}
