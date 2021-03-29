//package com.bojun.calendar_master;
//
//import android.content.Context;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MenuItem;
//
//import com.demons.calendar.CalendarView;
//import com.demons.calendar.listeners.OnCalendarChangeListener;
//import com.demons.calendar.listeners.OnCalendarItemClickListener;
//import com.demons.calendar.model.CalendarConfig;
//import com.demons.calendar.model.CalendarEventModel;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class VerticalActivity extends AppCompatActivity {
//    private Context context;
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//    private static final SimpleDateFormat sdf_yyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
//    private CalendarView mnCalendar;
//
//    //事件集合
//    private ArrayList<CalendarEventModel> mEventDatas = new ArrayList<>();
//
//    @Override
//    protected void onCreate(android.os.Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        context = this;
//
//
//        mnCalendar = (CalendarView) findViewById(R.id.mnCalendar);
//
//        /**
//         * Item点击监听
//         */
//        mnCalendar.setOnCalendarItemClickListener(new OnCalendarItemClickListener() {
//
//            @Override
//            public void onClick(Date date) {
//                //Toast日期----阴历可以自己转:LunarCalendarUtils.solarToLunar(date);
//                ToastUtil.showToast(context, "单击:" + sdf_yyy_MM_dd.format(date));
//
//            }
//
//            @Override
//            public void onLongClick(Date date) {
//                //Toast日期----阴历可以自己转:LunarCalendarUtils.solarToLunar(date);
//                ToastUtil.showToast(context, "长按:" + sdf_yyy_MM_dd.format(date));
//            }
//        });
//
//        /**
//         * 日历改变监听
//         */
//        mnCalendar.setOnCalendarChangeListener(new OnCalendarChangeListener() {
//            @Override
//            public void onPageChange(Date date) {
//                ToastUtil.showToast(context, sdf.format(date));
//            }
//        });
//
//        try {
//            CalendarEventModel mnCalendarEventModel = new CalendarEventModel();
//            mnCalendarEventModel.setEventDate(sdf_yyy_MM_dd.parse("2017-11-22"));
//            mnCalendarEventModel.setEventInfo("班");
//            mnCalendarEventModel.setEventBgColor("#FF00FF");
//            mnCalendarEventModel.setEventTextColor("#FFFFFF");
//            mEventDatas.add(mnCalendarEventModel);
//
//            mnCalendarEventModel = new CalendarEventModel();
//            mnCalendarEventModel.setEventDate(sdf_yyy_MM_dd.parse("2017-11-08"));
//            mnCalendarEventModel.setEventInfo("议");
//            mnCalendarEventModel.setEventBgColor("#FF0000");
//            mnCalendarEventModel.setEventTextColor("#FFFFFF");
//            mEventDatas.add(mnCalendarEventModel);
//
//            mnCalendarEventModel = new CalendarEventModel();
//            mnCalendarEventModel.setEventDate(sdf_yyy_MM_dd.parse("2017-10-29"));
//            mnCalendarEventModel.setEventInfo("假");
//            mnCalendarEventModel.setEventBgColor("#0000FF");
//            mnCalendarEventModel.setEventTextColor("#FFFFFF");
//            mEventDatas.add(mnCalendarEventModel);
//
//            mnCalendarEventModel = new CalendarEventModel();
//            mnCalendarEventModel.setEventDate(sdf_yyy_MM_dd.parse("2018-01-25"));
//            mnCalendarEventModel.setEventInfo("差");
//            mnCalendarEventModel.setEventBgColor("#000000");
//            mnCalendarEventModel.setEventTextColor("#FFFFFF");
//            mEventDatas.add(mnCalendarEventModel);
//            //设置事件
//            mnCalendar.setEventDatas(mEventDatas);
//        } catch (Exception e) {
//
//        }
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_10:
//                //上个月
//                mnCalendar.setLastMonth();
//                break;
//            case R.id.action_11:
//                //下个月
//                mnCalendar.setNextMonth();
//                break;
//            case R.id.action_01:
//                //跳转到当前月份
//                String newDateString = "2017-10";
//                Date date = null;
//                try {
//                    date = sdf.parse(newDateString);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                mnCalendar.setCurrentDate(date);
//                break;
//            case R.id.action_02:
//                //跳转到今天
//                mnCalendar.set2Today();
//                break;
//            case R.id.action_03:
//                //改变样式配置
//                CalendarConfig build = new CalendarConfig.Builder()
//                        //星期的文字颜色
//                        .setMnCalendar_colorWeek("#00ff00")
//                        //阴历的颜色
//                        .setMnCalendar_colorLunar("#FF0000")
//                        //阳历的颜色
//                        .setMnCalendar_colorSolar("#9BCCAF")
//                        //今天的背景色
//                        .setMnCalendar_colorTodayBg("#00FFFF")
//                        //今天的文字颜色
//                        .setMnCalendar_colorTodayText("#000000")
//                        //不是本月的文字颜色
//                        .setMnCalendar_colorOtherMonth("#F1EDBD")
//                        //标题的颜色
//                        .setMnCalendar_colorTitle("#FF0000")
//                        //选中日期的背景色
//                        .setMnCalendar_colorSelected("#FFFF00")
//                        //是否显示阴历
//                        .setMnCalendar_showLunar(true)
//                        //是否显示标题
//                        .setMnCalendar_showWeek(true)
//                        //显示标题的样式
//                        .setMnCalendar_TitleDateFormat("yyyy年MM月")
//                        .build();
//                mnCalendar.setConfig(build);
//                break;
//            case R.id.action_04:
//                //默认配置
//                CalendarConfig buildDefault = new CalendarConfig.Builder().build();
//                mnCalendar.setConfig(buildDefault);
//                break;
//            case R.id.action_05:
//                CalendarConfig build05 = new CalendarConfig.Builder()
//                        .setMnCalendar_showTitle(false)
//                        .build();
//                mnCalendar.setConfig(build05);
//                break;
//            case R.id.action_06:
//                CalendarConfig build06 = new CalendarConfig.Builder()
//                        .setMnCalendar_showWeek(false)
//                        .build();
//                mnCalendar.setConfig(build06);
//                break;
//            case R.id.action_07:
//                CalendarConfig build07 = new CalendarConfig.Builder()
//                        .setMnCalendar_showLunar(false)
//                        .build();
//                mnCalendar.setConfig(build07);
//                break;
//            case R.id.action_12:
//                CalendarConfig build12 = new CalendarConfig.Builder()
//                        .setMnCalendar_TitleDateFormat("MM月yyyy年")
//                        .build();
//                mnCalendar.setConfig(build12);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//}
