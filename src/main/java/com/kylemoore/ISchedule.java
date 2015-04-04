package com.kylemoore;

import java.time.LocalDateTime;
import java.util.List;

public interface ISchedule {

    List<IProgram> getPrograms();
    void addProgram(int ordinal, LocalDateTime date, String opponent, TVStation channel);

}
