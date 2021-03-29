package com.demons.calendar.model;

import java.util.Date;

/**
 * 日期数据实体
 *
 * @author demons
 */
public class WeekInfoData {
    private Date weekStart;
    private Date weekEnd;
    private int weekNumber;

    public Date getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(Date weekStart) {
        this.weekStart = weekStart;
    }

    public Date getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(Date weekEnd) {
        this.weekEnd = weekEnd;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }
}
