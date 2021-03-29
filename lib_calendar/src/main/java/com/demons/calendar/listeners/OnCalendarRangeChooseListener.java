package com.demons.calendar.listeners;

import java.util.Date;

/**
 * 垂直方向的范围选取监听
 *
 * @author demons
 */
public interface OnCalendarRangeChooseListener {
    void onRangeDate(Date startDate, Date endDate);
}
