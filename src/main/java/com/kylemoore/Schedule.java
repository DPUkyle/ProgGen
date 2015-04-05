package com.kylemoore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule implements ISchedule {

    private List<IProgram> _schedule = new ArrayList<>();

    @Override
    public List<IProgram> getPrograms() {
        return _schedule;
    }

    @Override
    public void addProgram(int ordinal, LocalDateTime date, String opponent, TVStation channel) {
        _schedule.add(Program.init(ordinal, date, opponent, channel));
    }

}
} //intentional compile fail to test build break notification
