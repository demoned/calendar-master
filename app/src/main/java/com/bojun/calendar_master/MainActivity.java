package com.bojun.calendar_master;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demons.calendar.CalendarVertical;
import com.demons.calendar.listeners.OnCalendarRangeChooseListener;
import com.demons.calendar.model.CalendarVerticalConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author demons
 */
public class MainActivity extends AppCompatActivity {

    private Context context;

    private CalendarVertical mnCalendarVertical;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mnCalendarVertical = (CalendarVertical) findViewById(R.id.mnCalendarVertical);
        /**
         *  自定义设置相关
         */
        CalendarVerticalConfig mnCalendarVerticalConfig = new CalendarVerticalConfig.Builder()
                .setMnCalendar_showWeek(false)                   //是否显示星期栏
                .setMnCalendar_single_choose(true)
                .setMnCalendar_showLunar(false)                  //是否显示阴历
                .setMnCalendar_colorRangeBg("#DEF5E2")        //区间中间的背景颜色
                .setMnCalendar_colorStartAndEndBg("#DEF5E2")    //开始结束的背景颜色
                .setMnCalendar_year(2021)
                .build();
        mnCalendarVertical.setConfig(mnCalendarVerticalConfig);
        /**
         * 区间选取完成监听
         */
        mnCalendarVertical.setOnCalendarRangeChooseListener(new OnCalendarRangeChooseListener() {
            @Override
            public void onRangeDate(Date startDate, Date endDate, Integer weekNumber) {
                String startTime = sdf.format(startDate);
                String endTime = sdf.format(endDate);
                Toast.makeText(context, "开始日期:" + startTime + ",结束日期:" + endTime + "、第" + weekNumber + "周", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_other, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_01:

                /**
                 *  自定义设置相关
                 */
                CalendarVerticalConfig mnCalendarVerticalConfig = new CalendarVerticalConfig.Builder()
                        .setMnCalendar_showWeek(false)                   //是否显示星期栏
                        .setMnCalendar_showLunar(false)                  //是否显示阴历
                        .setMnCalendar_colorWeek("#B07219")             //星期栏的颜色
                        .setMnCalendar_titleFormat("yyyy-MM")           //每个月的标题样式
                        .setMnCalendar_colorTitle("#FF0000")            //每个月标题的颜色
                        .setMnCalendar_colorSolar("#ff0fc7")            //阳历的颜色
                        .setMnCalendar_colorLunar("#00ff00")            //阴历的颜色
                        .setMnCalendar_colorBeforeToday("#F1EDBD")      //今天之前的日期的颜色
                        .setMnCalendar_colorRangeBg("#9930C553")        //区间中间的背景颜色
                        .setMnCalendar_colorRangeText("#000000")        //区间文字的颜色
                        .setMnCalendar_colorStartAndEndBg("#258C3E")    //开始结束的背景颜色
                        .build();
                mnCalendarVertical.setConfig(mnCalendarVerticalConfig);
                break;
            case R.id.action_02:
                //隐藏星期
                CalendarVerticalConfig mnCalendarVerticalConfig2 = new CalendarVerticalConfig.Builder()
                        .setMnCalendar_showWeek(false)
                        .build();
                mnCalendarVertical.setConfig(mnCalendarVerticalConfig2);
                break;
            case R.id.action_03:
                //隐藏阴历
                CalendarVerticalConfig mnCalendarVerticalConfig3 = new CalendarVerticalConfig.Builder()
                        .setMnCalendar_showLunar(false)
                        .build();
                mnCalendarVertical.setConfig(mnCalendarVerticalConfig3);
                break;
            case R.id.action_04:
                //恢复默认
                CalendarVerticalConfig mnCalendarVerticalConfig4 = new CalendarVerticalConfig.Builder().build();
                mnCalendarVertical.setConfig(mnCalendarVerticalConfig4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}