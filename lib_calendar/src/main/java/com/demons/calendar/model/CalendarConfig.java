package com.demons.calendar.model;

import android.graphics.Color;


/**
 * @author demons
 */
public class CalendarConfig {
    private boolean mnCalendar_showLunar = true;
    private boolean mnCalendar_showWeek = true;
    private boolean mnCalendar_showTitle = true;
    private int mnCalendar_colorWeek = Color.parseColor("#5E5E5E");
    private int mnCalendar_colorSolar = Color.parseColor("#282828");
    private int mnCalendar_colorLunar = Color.parseColor("#979797");
    private int mnCalendar_colorTodayBg = Color.parseColor("#282828");
    private int mnCalendar_colorOtherMonth = Color.parseColor("#979797");
    private int mnCalendar_colorTodayText = Color.parseColor("#FFFFFF");
    private int mnCalendar_colorTitle = Color.parseColor("#282828");
    private int mnCalendar_colorSelected = Color.parseColor("#dfdfdf");
    private String mnCalendar_titleDateFormat = "yyyy-MM";

    private CalendarConfig() {
    }

    public String getMnCalendar_titleDateFormat() {
        return mnCalendar_titleDateFormat;
    }

    public void setMnCalendar_titleDateFormat(String mnCalendar_titleDateFormat) {
        this.mnCalendar_titleDateFormat = mnCalendar_titleDateFormat;
    }

    public boolean isMnCalendar_showTitle() {
        return mnCalendar_showTitle;
    }

    private void setMnCalendar_showTitle(boolean mnCalendar_showTitle) {
        this.mnCalendar_showTitle = mnCalendar_showTitle;
    }

    public boolean isMnCalendar_showLunar() {
        return mnCalendar_showLunar;
    }

    private void setMnCalendar_showLunar(boolean mnCalendar_showLunar) {
        this.mnCalendar_showLunar = mnCalendar_showLunar;
    }

    public boolean isMnCalendar_showWeek() {
        return mnCalendar_showWeek;
    }

    private void setMnCalendar_showWeek(boolean mnCalendar_showWeek) {
        this.mnCalendar_showWeek = mnCalendar_showWeek;
    }

    public int getMnCalendar_colorWeek() {
        return mnCalendar_colorWeek;
    }

    private void setMnCalendar_colorWeek(int mnCalendar_colorWeek) {
        this.mnCalendar_colorWeek = mnCalendar_colorWeek;
    }

    public int getMnCalendar_colorSolar() {
        return mnCalendar_colorSolar;
    }

    private void setMnCalendar_colorSolar(int mnCalendar_colorSolar) {
        this.mnCalendar_colorSolar = mnCalendar_colorSolar;
    }

    public int getMnCalendar_colorLunar() {
        return mnCalendar_colorLunar;
    }

    private void setMnCalendar_colorLunar(int mnCalendar_colorLunar) {
        this.mnCalendar_colorLunar = mnCalendar_colorLunar;
    }

    public int getMnCalendar_colorTodayBg() {
        return mnCalendar_colorTodayBg;
    }

    private void setMnCalendar_colorTodayBg(int mnCalendar_colorTodayBg) {
        this.mnCalendar_colorTodayBg = mnCalendar_colorTodayBg;
    }

    public int getMnCalendar_colorOtherMonth() {
        return mnCalendar_colorOtherMonth;
    }

    private void setMnCalendar_colorOtherMonth(int mnCalendar_colorOtherMonth) {
        this.mnCalendar_colorOtherMonth = mnCalendar_colorOtherMonth;
    }

    public int getMnCalendar_colorTodayText() {
        return mnCalendar_colorTodayText;
    }

    private void setMnCalendar_colorTodayText(int mnCalendar_colorTodayText) {
        this.mnCalendar_colorTodayText = mnCalendar_colorTodayText;
    }

    public int getMnCalendar_colorTitle() {
        return mnCalendar_colorTitle;
    }

    private void setMnCalendar_colorTitle(int mnCalendar_colorTitle) {
        this.mnCalendar_colorTitle = mnCalendar_colorTitle;
    }

    public int getMnCalendar_colorSelected() {
        return mnCalendar_colorSelected;
    }

    public void setMnCalendar_colorSelected(int mnCalendar_colorSelected) {
        this.mnCalendar_colorSelected = mnCalendar_colorSelected;
    }

    @Override
    public String toString() {
        return "CalendarConfig{" +
                "mnCalendar_showLunar=" + mnCalendar_showLunar +
                ", mnCalendar_showWeek=" + mnCalendar_showWeek +
                ", mnCalendar_showTitle=" + mnCalendar_showTitle +
                ", mnCalendar_colorWeek=" + mnCalendar_colorWeek +
                ", mnCalendar_colorSolar=" + mnCalendar_colorSolar +
                ", mnCalendar_colorLunar=" + mnCalendar_colorLunar +
                ", mnCalendar_colorTodayBg=" + mnCalendar_colorTodayBg +
                ", mnCalendar_colorOtherMonth=" + mnCalendar_colorOtherMonth +
                ", mnCalendar_colorTodayText=" + mnCalendar_colorTodayText +
                ", mnCalendar_colorTitle=" + mnCalendar_colorTitle +
                ", mnCalendar_colorSelected=" + mnCalendar_colorSelected +
                ", mnCalendar_titleDateFormat='" + mnCalendar_titleDateFormat + '\'' +
                '}';
    }

    public static class Builder {
        private CalendarConfig mnCalendarConfig = null;

        public Builder() {
            this.mnCalendarConfig = new CalendarConfig();
        }

        public Builder setMnCalendar_showTitle(boolean mnCalendar_showTitle) {
            this.mnCalendarConfig.setMnCalendar_showTitle(mnCalendar_showTitle);
            return this;
        }

        public Builder setMnCalendar_showLunar(boolean mnCalendar_showLunar) {
            this.mnCalendarConfig.setMnCalendar_showLunar(mnCalendar_showLunar);
            return this;
        }


        public Builder setMnCalendar_showWeek(boolean mnCalendar_showWeek) {
            this.mnCalendarConfig.setMnCalendar_showWeek(mnCalendar_showWeek);
            return this;
        }


        public Builder setMnCalendar_colorWeek(String mnCalendar_colorWeek) {
            this.mnCalendarConfig.setMnCalendar_colorWeek(Color.parseColor(mnCalendar_colorWeek));
            return this;
        }


        public Builder setMnCalendar_colorSolar(String mnCalendar_colorSolar) {
            this.mnCalendarConfig.setMnCalendar_colorSolar(Color.parseColor(mnCalendar_colorSolar));
            return this;
        }


        public Builder setMnCalendar_colorLunar(String mnCalendar_colorLunar) {
            this.mnCalendarConfig.setMnCalendar_colorLunar(Color.parseColor(mnCalendar_colorLunar));
            return this;
        }

        public Builder setMnCalendar_colorTodayBg(String mnCalendar_colorTodayBg) {
            this.mnCalendarConfig.setMnCalendar_colorTodayBg(Color.parseColor(mnCalendar_colorTodayBg));
            return this;
        }


        public Builder setMnCalendar_colorOtherMonth(String mnCalendar_colorOtherMonth) {
            this.mnCalendarConfig.setMnCalendar_colorOtherMonth(Color.parseColor(mnCalendar_colorOtherMonth));
            return this;
        }


        public Builder setMnCalendar_colorTodayText(String mnCalendar_colorTodayText) {
            this.mnCalendarConfig.setMnCalendar_colorTodayText(Color.parseColor(mnCalendar_colorTodayText));
            return this;
        }

        public Builder setMnCalendar_colorTitle(String mnCalendar_colorTitle) {
            this.mnCalendarConfig.setMnCalendar_colorTitle(Color.parseColor(mnCalendar_colorTitle));
            return this;
        }

        public Builder setMnCalendar_TitleDateFormat(String mnCalendar_titleDateFormat) {
            this.mnCalendarConfig.setMnCalendar_titleDateFormat(mnCalendar_titleDateFormat);
            return this;
        }

        public Builder setMnCalendar_colorSelected(String mnCalendar_colorSelected) {
            this.mnCalendarConfig.setMnCalendar_colorSelected(Color.parseColor(mnCalendar_colorSelected));
            return this;
        }

        public CalendarConfig build() {
            return mnCalendarConfig;
        }
    }
}
