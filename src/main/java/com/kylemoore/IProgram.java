package com.kylemoore;

import java.time.LocalDateTime;

public interface IProgram {

    int getOrdinal();
    LocalDateTime getDate();
    String getOpponent();
    TVStation getChannel();

    default String debugString() {
        return  "-- " +
                "ordinal: " + getOrdinal() +
                ", date: " + getDate().toString() +
                ", opponent: " + getOpponent() +
                ", channel: " + getChannel();
    }

}
