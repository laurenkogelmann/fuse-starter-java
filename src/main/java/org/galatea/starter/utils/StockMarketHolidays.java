package org.galatea.starter.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public abstract class StockMarketHolidays implements TemporalAdjuster {

  LocalDate today = LocalDate.now();
  int year = today.getYear();
  int month = today.getMonthValue();
  int day = today.getDayOfMonth();

  private final LocalDate newYearsDay = LocalDate.of(today.getYear(), Month.JANUARY, 1);
  private final LocalDate mlkDay = getMlkDay(today.getYear());
  private final LocalDate presidentsDay = getPresidentsDay(today.getYear());
  private final LocalDate goodFriday = getGoodFriday(today.getYear());
  private final LocalDate memorialDay = getMemorialDay(today.getYear());
  private final LocalDate independenceDay = LocalDate.of(today.getYear(), Month.JULY, 4);
  private final LocalDate laborDay = getLaborDay(today.getYear());
  private final LocalDate thanksgivingDay = getThanksgivingDay(today.getYear());
  private final LocalDate christmasDay= LocalDate.of(today.getYear(), Month.DECEMBER, 25);

  public LocalDate getMlkDay(int year){
    LocalDate date = LocalDate.of(year, Month.JANUARY, 1);
    TemporalAdjuster firstMonday = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
    TemporalAdjuster secondMonday = TemporalAdjusters.next(DayOfWeek.MONDAY);
    TemporalAdjuster mlkDay = TemporalAdjusters.next(DayOfWeek.MONDAY);
    return (LocalDate) mlkDay;
  }

  public LocalDate getPresidentsDay(int year){
    LocalDate date = LocalDate.of(year, Month.FEBRUARY, 1);
    TemporalAdjuster firstMonday = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
    TemporalAdjuster secondMonday = TemporalAdjusters.next(DayOfWeek.MONDAY);
    TemporalAdjuster presidentsDay = TemporalAdjusters.next(DayOfWeek.MONDAY);
    return (LocalDate) presidentsDay;
  }

  public LocalDate EasterSunday(int year){
    int Y = year;
    int a = Y % 19;
    int b = Y / 100;
    int c = Y % 100;
    int d = b / 4;
    int e = b % 4;
    int f = (b + 8) / 25;
    int g = (b - f + 1) / 3;
    int h = (19 * a + b - d - g + 15) % 30;
    int i = c / 4;
    int k = c % 4;
    int L = (32 + 2 * e + 2 * i - h - k) % 7;
    int m = (a + 11 * h + 22 * L) / 451;
    int month = (h + L - 7 * m + 114) / 31;
    int day = ((h + L - 7 * m + 114) % 31) + 1;
    return LocalDate.of(today.getYear(), month, day);
  }

  public LocalDate getGoodFriday(int year){
    LocalDate goodFriday = EasterSunday(today.getYear()).minusDays(2);
    return goodFriday;
  }

  public LocalDate getMemorialDay(int year){
    LocalDate date = LocalDate.of(year, Month.MAY, 1);
    TemporalAdjuster memorialDay = TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY);
    return (LocalDate) memorialDay;
  }

  public LocalDate getLaborDay(int year){
    LocalDate date = LocalDate.of(year, Month.SEPTEMBER, 1);
    TemporalAdjuster laborDay = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
    return (LocalDate) laborDay;
  }

  public LocalDate getThanksgivingDay(int year){
    LocalDate date = LocalDate.of(year, Month.NOVEMBER, 1);
    TemporalAdjuster thanksgivingDay = TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY);
    return (LocalDate) thanksgivingDay;
  }

  public boolean isHoliday(LocalDate date){
    return date.equals(newYearsDay) || date.equals(mlkDay) || date.equals(presidentsDay) || date
        .equals(goodFriday) || date.equals(memorialDay)
        || date.equals(independenceDay) || date.equals(laborDay) || date.equals(thanksgivingDay)
        || date.equals(christmasDay);
  }

}
