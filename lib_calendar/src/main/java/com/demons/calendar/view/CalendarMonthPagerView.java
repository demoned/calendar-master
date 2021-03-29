package com.demons.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demons.calendar.R;
import com.demons.calendar.adapter.CalendarAdapter;
import com.demons.calendar.listeners.OnCalendarItemClickListener;
import com.demons.calendar.listeners.OnCalendarSelectedChangeListener;
import com.demons.calendar.model.CalendarConfig;
import com.demons.calendar.model.CalendarEventModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

;

/**
 * @author demons
 */
public class CalendarMonthPagerView extends FrameLayout {

    private Context mContext;
    private RecyclerView recyclerViewCalendarMonth;
    private Calendar currentCalendar;
    //配置信息
    private CalendarConfig mnCalendarConfig = new CalendarConfig.Builder().build();
    //Item点击监听
    private OnCalendarItemClickListener onCalendarItemClickListener;
    private OnCalendarSelectedChangeListener onCalendarSelectedChangeListener;

    private CalendarAdapter mnCalendarAdapter;

    private Calendar mSelectedCalendar;

    //事件集合
    private ArrayList<CalendarEventModel> mEventDatas;

    public CalendarMonthPagerView(@NonNull Context context) {
        this(context, null);
    }

    public CalendarMonthPagerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarMonthPagerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.layout_calendar_month_pager, this);
        recyclerViewCalendarMonth = (RecyclerView) findViewById(R.id.recyclerViewCalendarMonth);

        //初始化RecycleerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 7);
        recyclerViewCalendarMonth.setLayoutManager(gridLayoutManager);
    }

    public void setSelectedCalendar(Calendar mSelectedCalendar) {
        this.mSelectedCalendar = mSelectedCalendar;
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.setSelectedCalendar(this.mSelectedCalendar);
        }
    }

    public void updateSelectedCalendar(Calendar mSelectedCalendar) {
        this.mSelectedCalendar = mSelectedCalendar;
        if (mnCalendarAdapter != null && mSelectedCalendar != null) {
            mnCalendarAdapter.updateSelectedCalendar(this.mSelectedCalendar);
        }
    }

    public void setCurrentCalendar(Calendar calendar, CalendarConfig mnCalendarConfig, ArrayList<CalendarEventModel> mEventDatas) {
        this.currentCalendar = calendar;
        this.mEventDatas = mEventDatas;
        this.mnCalendarConfig = mnCalendarConfig;
        initAdapter();
    }

    public void updateEventDatas(ArrayList<CalendarEventModel> mEventDatas) {
        this.mEventDatas = mEventDatas;
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.updateEventDatas(this.mEventDatas);
        }
    }

    public void updateConfig(CalendarConfig mnCalendarConfig) {
        this.mnCalendarConfig = mnCalendarConfig;
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.updateConfig(this.mnCalendarConfig);
        }
    }

    private void initAdapter() {
        //计算日期
        ArrayList<Date> mDatas = new ArrayList<>();
        Calendar calendar = (Calendar) currentCalendar.clone();

        //至于当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //获取当月第一天是星期几
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //移动到需要绘制的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -day_of_week);

        //6*7
        while (mDatas.size() < 6 * 7) {
            mDatas.add(calendar.getTime());
            //向前移动一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        //设置Adapter
        mnCalendarAdapter = new CalendarAdapter(mContext, mDatas, mEventDatas,currentCalendar, mSelectedCalendar, mnCalendarConfig);
        recyclerViewCalendarMonth.setAdapter(mnCalendarAdapter);
        mnCalendarAdapter.setOnCalendarItemClickListener(new OnCalendarItemClickListener() {
            @Override
            public void onClick(Date date) {
                if (onCalendarItemClickListener != null) {
                    onCalendarItemClickListener.onClick(date);
                }
            }

            @Override
            public void onLongClick(Date date) {
                if (onCalendarItemClickListener != null) {
                    onCalendarItemClickListener.onLongClick(date);
                }
            }
        });
        mnCalendarAdapter.setOnCalendarSelectedChangeListener(new OnCalendarSelectedChangeListener() {
            @Override
            public void onSelectedChange(Calendar selectedCalendar) {
                if (onCalendarSelectedChangeListener != null) {
                    onCalendarSelectedChangeListener.onSelectedChange(selectedCalendar);
                }
            }
        });
    }

    /**
     * 设置点击事件
     *
     * @param onCalendarItemClickListener
     */
    public void setOnCalendarItemClickListener(OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
    }

    public void setOnCalendarSelectedChangeListener(OnCalendarSelectedChangeListener onCalendarSelectedChangeListener) {
        this.onCalendarSelectedChangeListener = onCalendarSelectedChangeListener;
    }

}
