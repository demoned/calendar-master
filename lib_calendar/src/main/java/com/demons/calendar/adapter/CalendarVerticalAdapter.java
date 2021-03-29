package com.demons.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demons.calendar.R;
import com.demons.calendar.listeners.OnCalendarRangeChooseListener;
import com.demons.calendar.model.CalendarItemModel;
import com.demons.calendar.model.CalendarVerticalConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CalendarVerticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HashMap<String, ArrayList<CalendarItemModel>> mDatas;

    private LayoutInflater layoutInflater;

    private Context context;

    private Calendar currentCalendar;

    private CalendarVerticalConfig mnCalendarVerticalConfig;

    public Date startDate = null;
    public Date endDate = null;

    public CalendarVerticalAdapter(Context context, HashMap<String, ArrayList<CalendarItemModel>> mDatas, Calendar currentCalendar, CalendarVerticalConfig mnCalendarVerticalConfig) {
        this.context = context;
        this.mDatas = mDatas;
        this.currentCalendar = currentCalendar;
        this.mnCalendarVerticalConfig = mnCalendarVerticalConfig;
        layoutInflater = LayoutInflater.from(this.context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.item_calendar_vertical, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            //标题
            Calendar calendarTitle = (Calendar) currentCalendar.clone();
            calendarTitle.add(Calendar.MONTH, position);
            Date titleDate = calendarTitle.getTime();

            //设置标题的格式
            String mnCalendar_titleFormat = mnCalendarVerticalConfig.getMnCalendar_titleFormat();
            SimpleDateFormat sdf = new SimpleDateFormat(mnCalendar_titleFormat);
            myViewHolder.tv_item_title.setText(sdf.format(titleDate));

            //设置标题的颜色
            myViewHolder.tv_item_title.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorTitle());

            //日期数据
            ArrayList<CalendarItemModel> dates = mDatas.get(String.valueOf(position));
            //初始化RecycleerView
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7);
            myViewHolder.recyclerViewItem.setLayoutManager(gridLayoutManager);

            //初始化Adapter
            Calendar calendarItem = (Calendar) currentCalendar.clone();
            calendarItem.add(Calendar.MONTH, position);
            CalendarVerticalItemAdapter mnCalendarVerticalItemAdapter = new CalendarVerticalItemAdapter(context, dates, calendarItem, this, mnCalendarVerticalConfig);
            myViewHolder.recyclerViewItem.setAdapter(mnCalendarVerticalItemAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_item_title;
        private RecyclerView recyclerViewItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_title = (TextView) itemView.findViewById(R.id.tv_item_title);
            recyclerViewItem = (RecyclerView) itemView.findViewById(R.id.recyclerViewItem);
        }
    }

    public OnCalendarRangeChooseListener onCalendarRangeChooseListener;


    public void setOnCalendarRangeChooseListener(OnCalendarRangeChooseListener onCalendarRangeChooseListener) {
        this.onCalendarRangeChooseListener = onCalendarRangeChooseListener;
        notifyDataSetChanged();
    }

    public void notifyChoose() {
        if (this.onCalendarRangeChooseListener != null) {
            if (startDate != null && endDate != null) {
                onCalendarRangeChooseListener.onRangeDate(startDate, endDate);
            }
        }
    }

    public void updateDatas(HashMap<String, ArrayList<CalendarItemModel>> mDatas, Calendar currentCalendar, CalendarVerticalConfig mnCalendarVerticalConfig) {
        this.mDatas = mDatas;
        this.currentCalendar = currentCalendar;
        this.mnCalendarVerticalConfig = mnCalendarVerticalConfig;
        this.startDate = null;
        this.endDate = null;
        notifyDataSetChanged();
    }

}
