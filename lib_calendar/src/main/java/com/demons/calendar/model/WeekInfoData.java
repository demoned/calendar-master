package com.demons.calendar.model;

import java.util.Date;

/**
 * 日期数据实体
 *
 * @author demons
 */
public class WeekInfoData {
    private Date weekStart;
    private int week;
    private Date weekEnd;
    private Date startTime;
    private Date endTime;
    private String weeks;
    private int weekNumber;
    private String weeksDate;

    public Date getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(Date weekStart) {
        this.weekStart = weekStart;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Date getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(Date weekEnd) {
        this.weekEnd = weekEnd;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getWeeksDate() {
        return weeksDate;
    }

    public void setWeeksDate(String weeksDate) {
        this.weeksDate = weeksDate;
    }
}
