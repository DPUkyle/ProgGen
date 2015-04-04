package com.kylemoore;

import java.util.Date;

public interface IProgram {

    int getChannelNumber();
    Date getDate();
    String getOpponent();
    TVStations getChannel();

}
