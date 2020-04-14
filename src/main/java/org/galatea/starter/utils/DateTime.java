package org.galatea.starter.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateTime {

  LocalDate today = LocalDate.now();
  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  StockMarketHolidays stockMarketHolidays;

  public static boolean isWeekend(LocalDate today){
    if(today.getDayOfWeek() == DayOfWeek.SATURDAY || today.getDayOfWeek() == DayOfWeek.SUNDAY){
      return true;
    } else {
      return false;
    }
  }

  public List<LocalDate> getDatesNeededForStockData(int days) {

    int validDays = 0;
    LocalDate nthDay = today;
    List<LocalDate> allStockDates = new ArrayList<>();

    // starting with today, count valid days until count = number of days, and return that date
    for (LocalDate date = today; validDays <= days; date.minusDays(1)) {
      if((!isWeekend(date)) && (!stockMarketHolidays.isHoliday(date))){ //and if date is not a holiday
        validDays++;
        nthDay = date;
        allStockDates.add(nthDay);
      }
    }
    return allStockDates;
  }

}
