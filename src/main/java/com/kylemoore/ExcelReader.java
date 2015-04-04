package com.kylemoore;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExcelReader implements IReader {

    private File _file = null;
    private ISchedule _schedule = null;
    private List<TVStation> _blacklist = Arrays.asList(TVStation.ABC7, TVStation.WCIU, TVStation.WPWR);

    public ExcelReader(File file) throws FileNotFoundException {
        _file = file;
        if(!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
    }

    @Override
    public ISchedule read() {
        _schedule = new Schedule();

        try {
            FileInputStream myxls = new FileInputStream(_file);
            HSSFWorkbook wb = new HSSFWorkbook(myxls); //Note, this line may fail if the workbook still has a filter on the columns
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator<? extends Row> rows = sheet.rowIterator();//. .toList() as List<HSSFRow>

            HSSFRow headerRow = (HSSFRow) rows.next();

            final int gameNumberIndex = getIndexOf("GameNumber", headerRow);
            final int dateIndex = getIndexOf("Date", headerRow);
            final int opponentIndex = getIndexOf("Opponent", headerRow);
            final int timeIndex = getIndexOf("Time", headerRow);
            final int channelIndex = getIndexOf("Channel", headerRow);

            rows.remove(); //remove the header

            rows.forEachRemaining( row -> {
                int gameNumber = (int) row.getCell(gameNumberIndex).getNumericCellValue(); //Integer.valueOf( ... .getStringCellValue());
                String date = row.getCell(dateIndex).getStringCellValue();
                String opponent = row.getCell(opponentIndex).getStringCellValue();
                String time = row.getCell(timeIndex).getStringCellValue();
                TVStation channel = TVStation.valueOf(row.getCell(channelIndex).getStringCellValue());

            if(!time.equalsIgnoreCase("TBD") &&
               !_blacklist.contains(channel)) {
                    //convert time from CDT to EDT
                    ZonedDateTime localStartTime = DateUtil.joinDateAndTime(date, time);
                    LocalDateTime adjustedStartTime = DateUtil.asEasternTime(localStartTime).toLocalDateTime();

                    _schedule.addProgram(gameNumber, adjustedStartTime, opponent, channel);
                }
            });


        } catch (IOException e) {
            throw new Error(e.toString());
        }

        return _schedule;
    }

    private int getIndexOf(String header, HSSFRow row) {
        final int[] returnValueHack = new int[1];

        row.cellIterator().forEachRemaining(cell -> {
            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING &&
                cell.getStringCellValue().equalsIgnoreCase(header)) {
                    returnValueHack[0] = cell.getColumnIndex();
                }
        });
        return returnValueHack[0];
    }

}
