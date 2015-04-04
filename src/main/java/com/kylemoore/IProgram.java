package com.kylemoore;

import java.time.LocalDateTime;

public interface IProgram {

    int getOrdinal();
    LocalDateTime getDate();
    String getOpponent();
    TVStation getChannel();

}
