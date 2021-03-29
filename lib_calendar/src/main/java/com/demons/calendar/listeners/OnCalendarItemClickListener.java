package com.demons.calendar.listeners;

import java.util.Date;

public interface OnCalendarItemClickListener {

    void onClick(Date date);

    void onLongClick(Date date);

}
