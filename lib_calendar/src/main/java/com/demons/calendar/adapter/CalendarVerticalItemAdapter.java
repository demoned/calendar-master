package com.demons.calendar.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.demons.calendar.R;
import com.demons.calendar.constant.Constants;
import com.demons.calendar.model.CalendarItemModel;
import com.demons.calendar.model.CalendarVerticalConfig;
import com.demons.calendar.model.Lunar;
import com.demons.calendar.model.WeekInfoData;
import com.demons.calendar.utils.LunarCalendarUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarVerticalItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<CalendarItemModel> mDatas;

    private LayoutInflater layoutInflater;

    private Context context;

    private Calendar currentCalendar;

    private CalendarVerticalAdapter adapter;

    private String now_yyy_mm_dd;
    private Date nowDate = new Date();

    //配置信息
    private CalendarVerticalConfig mnCalendarVerticalConfig;


    public CalendarVerticalItemAdapter(Context context, ArrayList<CalendarItemModel> mDatas, Calendar currentCalendar, CalendarVerticalAdapter adapter, CalendarVerticalConfig mnCalendarVerticalConfig) {
        this.context = context;
        this.mDatas = mDatas;
        this.currentCalendar = currentCalendar;
        this.adapter = adapter;
        this.mnCalendarVerticalConfig = mnCalendarVerticalConfig;
        layoutInflater = LayoutInflater.from(this.context);

        now_yyy_mm_dd = Constants.sdf_yyy_MM_dd.format(nowDate);
        try {
            nowDate = Constants.sdf_yyy_MM_dd.parse(now_yyy_mm_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.item_calendar_vertical_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            CalendarItemModel mnCalendarItemModel = mDatas.get(position);

            Date datePosition = mnCalendarItemModel.getDate();
            Lunar lunar = mnCalendarItemModel.getLunar();

            myViewHolder.itemView.setVisibility(View.VISIBLE);
            myViewHolder.tv_small.setVisibility(View.GONE);
            myViewHolder.iv_bg.setVisibility(View.GONE);
            myViewHolder.tv_small.setText("");
            myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorSolar());
            myViewHolder.tv_small.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorLunar());

            myViewHolder.tvDay.setText(String.valueOf(datePosition.getDate()));

            //不是本月的隐藏
            Date currentDate = currentCalendar.getTime();
            if (datePosition.getMonth() != currentDate.getMonth()) {
                myViewHolder.itemView.setVisibility(View.GONE);
            }

            //阴历的显示
            if (mnCalendarVerticalConfig.isMnCalendar_showLunar()) {
                myViewHolder.tv_small.setVisibility(View.VISIBLE);
                String lunarDayString = LunarCalendarUtils.getLunarDayString(lunar.lunarDay);
                myViewHolder.tv_small.setText(lunarDayString);
            } else {
                myViewHolder.tv_small.setVisibility(View.GONE);
            }

            //判断是不是当天
            String position_yyy_MM_dd = Constants.sdf_yyy_MM_dd.format(datePosition);
            if (now_yyy_mm_dd.equals(position_yyy_MM_dd)) {
                myViewHolder.tvDay.setText("今天");
            }

            //小于今天的颜色变灰
            if (datePosition.getTime() < nowDate.getTime()) {
                myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorBeforeToday());
            }

            //判断是不是点击了起始日期
            if (adapter.startDate != null && adapter.startDate == datePosition) {
                myViewHolder.iv_bg.setVisibility(View.VISIBLE);
                myViewHolder.iv_bg.setBackgroundResource(R.drawable.selected_bg_start);
                myViewHolder.tv_small.setVisibility(View.GONE);
                myViewHolder.tv_small.setText("开始");
                myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                myViewHolder.tv_small.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                //动态修改颜色
                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_bg.getBackground();
                myGrad.setColor(mnCalendarVerticalConfig.getMnCalendar_colorStartAndEndBg());
            }
            if (adapter.endDate != null && adapter.endDate == datePosition) {
                myViewHolder.iv_bg.setVisibility(View.VISIBLE);
                myViewHolder.iv_bg.setBackgroundResource(R.drawable.selected_bg_end);
                myViewHolder.tv_small.setVisibility(View.GONE);
                myViewHolder.tv_small.setText("结束");
                myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                myViewHolder.tv_small.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                //动态修改颜色
                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_bg.getBackground();
                myGrad.setColor(mnCalendarVerticalConfig.getMnCalendar_colorStartAndEndBg());
            }
            if (adapter.startDate != null && adapter.endDate != null) {
                refreshDate(myViewHolder, adapter.startDate, adapter.endDate, datePosition);
            }
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CalendarItemModel mnCalendarItemModel = mDatas.get(position);
                    Date dateClick = mnCalendarItemModel.getDate();
                    if (!mnCalendarVerticalConfig.isMnCalendar_single_choose()) {
                        //必须大于今天
                        if (dateClick.getTime() < nowDate.getTime()) {
                            Toast.makeText(context, "选择的日期必须大于今天", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (adapter.startDate != null && adapter.endDate != null) {
                            adapter.startDate = null;
                            adapter.endDate = null;
                        }
                        if (adapter.startDate == null) {
                            adapter.startDate = dateClick;
                        } else {
                            //判断结束位置是不是大于开始位置
                            long deteClickTime = dateClick.getTime();
                            long deteStartTime = adapter.startDate.getTime();
                            if (deteClickTime <= deteStartTime) {
                                adapter.startDate = dateClick;
                            } else {
                                adapter.endDate = dateClick;
                            }
                        }
                    }
                    if (mnCalendarVerticalConfig.isMnCalendar_single_choose()) {
                        WeekInfoData weekInfo = getWeekInfo(dateClick.getTime());
                        adapter.startDate = weekInfo.getWeekStart();
                        adapter.endDate = weekInfo.getWeekEnd();
                        adapter.weekNumber = weekInfo.getWeekNumber();
                    }
                    adapter.notifyChoose();
                    //刷新
                    notifyDataSetChanged();
                    adapter.notifyDataSetChanged();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDay;
        private TextView tv_small;
        private ImageView iv_bg;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            tv_small = (TextView) itemView.findViewById(R.id.tv_small);
            iv_bg = (ImageView) itemView.findViewById(R.id.iv_bg);
        }
    }

    private void refreshDate(MyViewHolder myViewHolder, Date startDate, Date endDate, Date datePosition) {
        if (datePosition.getTime() >= startDate.getTime() && datePosition.getTime() <= endDate.getTime()) {
            myViewHolder.iv_bg.setVisibility(View.VISIBLE);
            myViewHolder.iv_bg.setBackgroundResource(R.drawable.selected_bg_centre);
            myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
            myViewHolder.tv_small.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
            //动态修改颜色
            GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_bg.getBackground();
            myGrad.setColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeBg());
        }
    }

    public WeekInfoData getWeekInfo(long date) {
        int week;
        WeekInfoData dateData = new WeekInfoData();
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            cal.setTimeInMillis(date);
            // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
            // 获得当前日期是一个星期的第几天
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }
            // 设置一个星期的第一天
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            // 获得当前日期是一个星期的第几天
            int day = cal.get(Calendar.DAY_OF_WEEK);
            // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
            dateData.setWeekStart(cal.getTime());
            String date1 = sdf.format(cal.getTime());
            Date time1 = sdf.parse(date1);
            calendar.setTime(time1);
            int week1 = calendar.get(Calendar.WEEK_OF_YEAR);
            cal.add(Calendar.DATE, 6);
            String date2 = sdf.format(cal.getTime());
            Date time2 = sdf.parse(date2);
            calendar.setTime(time2);
            int week2 = calendar.get(Calendar.WEEK_OF_YEAR);
            week = (week1 >= week2 ? week2 : week1);
            dateData.setWeekNumber(week);
            dateData.setWeekEnd(cal.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dateData;
    }
}
