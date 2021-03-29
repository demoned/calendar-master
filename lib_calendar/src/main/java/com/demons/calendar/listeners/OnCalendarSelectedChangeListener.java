package com.demons.calendar.listeners;

import java.util.Calendar;


public interface OnCalendarSelectedChangeListener {

    /**
     * 选中的时间的改变
     *
     * @param selectedCalendar
     */
    void onSelectedChange(Calendar selectedCalendar);

}
