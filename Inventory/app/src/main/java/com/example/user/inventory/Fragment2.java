package com.example.user.inventory;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment2 extends Fragment {
    private TextView textView;
    private TextView hello;
    private Button button;
    private int t;
    //private int min = 1;
    private int sec = 10;
    //private SeekBar mSeekBar;
    private TextView time_now;
    private TimeCount time;
    private long Count_time;
    private String s;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clock, container, false);
        textView = (TextView) view.findViewById(R.id.clock_time);
        hello = (TextView) view.findViewById(R.id.hello);
        button = (Button) view.findViewById(R.id.clock_bt);
        //mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        time_now = (TextView) view.findViewById(R.id.time_now);
        textView.setText("00:10");

        //获取系统的日期
        Calendar calendar = Calendar.getInstance();
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour>=6&&hour<=12){
            hello.setText("早上");
        }else if(hour>12&&hour<=6){
            hello.setText("下午");
        }else {
            hello.setText("晚上");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        time_now.setText(simpleDateFormat.format(date));


        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("设置番茄时间：");
                // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.time_dialog, null);
                // 设置我们自己定义的布局文件作为弹出框的Content
                builder.setView(view1);

                final EditText m = (EditText) view.findViewById(R.id.min);

                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //确定操作的内容
                                Log.v("Tag","3");
                                s = m.getText().toString();
                                Log.v("Tag",s);
                                textView.setText(s + ":00");
                                Toast.makeText(getActivity(), "点击确定", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.setNegativeButton("算了",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "点击取消", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
                return false;
            }
        });

        time = new TimeCount(sec*1000,1000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t == 0) {
                    t = 1;
                    time.start();
                    Toast.makeText(getActivity(), "开始倒计时", Toast.LENGTH_SHORT).show();
                    button.setText("放弃番茄");
                } else {
                    t = 0;
                    time.cancel();
                    button.setText("开始专注");
                    textView.setText("00:10");
                    Toast.makeText(getActivity(), "番茄钟取消", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }


    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public String formatTime(long millisecond) {
        int minute;//分钟
        int second;//秒数
        minute = (int) ((millisecond / 1000) / 60);
        second = (int) ((millisecond / 1000) % 60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        }else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }
    }


    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            textView.setText("00:00");
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle("番茄时钟")//设置对话框的标题
                    .setMessage("时间到了，休息一下吧！")//设置对话框的内容
                    //设置对话框的按钮
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                            button.setText("开始专注");
                            textView.setText("00:10");
                            t=0;
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            textView.setText(formatTime(millisUntilFinished));
            Count_time = millisUntilFinished;
        }

    }

}
