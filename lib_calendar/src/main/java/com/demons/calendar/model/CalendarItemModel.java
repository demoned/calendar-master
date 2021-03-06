package com.demons.calendar.model;

import java.util.Date;


/**
 * Item 显示日期的Bean : 包含阳历和阴历
 *
 * @author demons
 */
public class CalendarItemModel {

    public CalendarItemModel() {
    }

    public CalendarItemModel(Date date, Lunar lunar) {
        mDate = date;
        mLunar = lunar;
    }

    private Date mDate;
    private Lunar mLunar;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Lunar getLunar() {
        return mLunar;
    }

    public void setLunar(Lunar lunar) {
        mLunar = lunar;
    }

}
