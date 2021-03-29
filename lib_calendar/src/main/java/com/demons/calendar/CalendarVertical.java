package com.demons.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demons.calendar.adapter.CalendarVerticalAdapter;
import com.demons.calendar.listeners.OnCalendarRangeChooseListener;
import com.demons.calendar.model.Lunar;
import com.demons.calendar.model.CalendarItemModel;
import com.demons.calendar.model.CalendarVerticalConfig;
import com.demons.calendar.utils.LunarCalendarUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


/**
 * 垂直方向的日历
 *
 * @author demons
 */
public class CalendarVertical extends LinearLayout {

    private Context context;

    private RecyclerView recyclerViewCalendar;
    private LinearLayout ll_week;
    private TextView tv_week_01;
    private TextView tv_week_02;
    private TextView tv_week_03;
    private TextView tv_week_04;
    private TextView tv_week_05;
    private TextView tv_week_06;
    private TextView tv_week_07;

    private Calendar currentCalendar = Calendar.getInstance();

    private CalendarVerticalConfig mnCalendarVerticalConfig = new CalendarVerticalConfig.Builder().build();
    private CalendarVerticalAdapter mnCalendarVerticalAdapter;
    private HashMap<String, ArrayList<CalendarItemModel>> dataMap;

    private OnCalendarRangeChooseListener onCalendarRangeChooseListener;

    public CalendarVertical(Context context) {
        this(context, null);
    }

    public CalendarVertical(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarVertical(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        initViews();
        initCalendarDatas();
    }


    private void initViews() {
        //绑定View
        View.inflate(context, R.layout.layout_calendar_vertical, this);
        recyclerViewCalendar = (RecyclerView) findViewById(R.id.recyclerViewCalendar);
        ll_week = (LinearLayout) findViewById(R.id.ll_week);
        tv_week_01 = (TextView) findViewById(R.id.tv_week_01);
        tv_week_02 = (TextView) findViewById(R.id.tv_week_02);
        tv_week_03 = (TextView) findViewById(R.id.tv_week_03);
        tv_week_04 = (TextView) findViewById(R.id.tv_week_04);
        tv_week_05 = (TextView) findViewById(R.id.tv_week_05);
        tv_week_06 = (TextView) findViewById(R.id.tv_week_06);
        tv_week_07 = (TextView) findViewById(R.id.tv_week_07);

        //初始化RecycleerView
        recyclerViewCalendar.setLayoutManager(new LinearLayoutManager(context));
    }


    private void initCalendarDatas() {

        //星期栏的显示和隐藏
        boolean mnCalendar_showWeek = mnCalendarVerticalConfig.isMnCalendar_showWeek();
        if (mnCalendar_showWeek) {
            ll_week.setVisibility(View.VISIBLE);
            tv_week_01.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_02.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_03.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_04.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_05.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_06.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_07.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
        } else {
            ll_week.setVisibility(View.GONE);
        }

        //日期集合
        dataMap = new HashMap<>();
        //计算日期
        int mnCalendar_countMonth = mnCalendarVerticalConfig.getMnCalendar_countMonth();
        for (int i = 0; i < mnCalendar_countMonth; i++) {
            int count7 = 0;
            ArrayList<CalendarItemModel> mDatas = new ArrayList<>();
            Calendar calendar = (Calendar) currentCalendar.clone();
            calendar.add(Calendar.MONTH, i);
            //至于当月的第一天
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            //获取当月第一天是星期几
            int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            //移动到需要绘制的第一天
            calendar.add(Calendar.DAY_OF_MONTH, -day_of_week);
            //6*7
            while (mDatas.size() < 6 * 7) {
                Date date = calendar.getTime();
                //阴历计算
                Lunar lunar = LunarCalendarUtils.solarToLunar(date);
                mDatas.add(new CalendarItemModel(date, lunar));
                //包含两个7就多了一行
                if (String.valueOf(calendar.getTime().getDate()).equals("7")) {
                    count7++;
                }
                //向前移动一天
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            if (count7 >= 2) {
                ArrayList<CalendarItemModel> mDatas2 = new ArrayList<>();
                for (int j = 0; j < mDatas.size() - 7; j++) {
                    mDatas2.add(mDatas.get(j));
                }
                mDatas = new ArrayList<>();
                mDatas.addAll(mDatas2);
            }

            dataMap.put(String.valueOf(i), mDatas);
        }

        //设置Adapter
        initAdapter();

    }

    private void initAdapter() {
        if (mnCalendarVerticalAdapter == null) {
            mnCalendarVerticalAdapter = new CalendarVerticalAdapter(context, dataMap, currentCalendar, mnCalendarVerticalConfig);
            recyclerViewCalendar.setAdapter(mnCalendarVerticalAdapter);
        } else {
            mnCalendarVerticalAdapter.updateDatas(dataMap, currentCalendar, mnCalendarVerticalConfig);
        }
        if (onCalendarRangeChooseListener != null) {
            mnCalendarVerticalAdapter.setOnCalendarRangeChooseListener(onCalendarRangeChooseListener);
        }
    }

    /**
     * 设置配置文件
     *
     * @param config
     */
    public void setConfig(CalendarVerticalConfig config) {
        this.mnCalendarVerticalConfig = config;
        initCalendarDatas();
    }


    public void setOnCalendarRangeChooseListener(OnCalendarRangeChooseListener onCalendarRangeChooseListener) {
        this.onCalendarRangeChooseListener = onCalendarRangeChooseListener;
        if (mnCalendarVerticalAdapter != null) {
            mnCalendarVerticalAdapter.setOnCalendarRangeChooseListener(onCalendarRangeChooseListener);
        }
    }

}
