package com.example.user.inventory;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment2 extends Fragment {
    private TextView textView;
    private Button button;
    private int t;
    private int min = 25;
    private int sec = 60;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.clock,container,false);
        textView = (TextView) view.findViewById(R.id.clock_time);
        button = (Button) view.findViewById(R.id.clock_bt);
        t = 0;

        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("设置番茄时间：");
                // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.time_dialog, null);
                // 设置我们自己定义的布局文件作为弹出框的Content
                builder.setView(view1);

                final EditText m = (EditText) view.findViewById(R.id.min);

                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //确定操作的内容
                                Toast.makeText(getActivity(),"点击确定",Toast.LENGTH_SHORT).show();
                                String s1 = m.getText().toString();
                                min = Integer.parseInt(s1);
                                textView.setText(min+":00");
                            }
                        });

                builder.setNegativeButton("算了",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(),"点击取消",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(t==0){
                    t=1;
                    timer.start();
                    Toast.makeText(getActivity(),"开始倒计时",Toast.LENGTH_SHORT).show();
                    button.setText("放弃番茄");
                }else {
                    t=0;
                    timer.cancel();
                    button.setText("开始专注");
                    textView.setText("25:00");
                    Toast.makeText(getActivity(),"番茄钟取消",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
    /**
     * 倒数计时器
     */
    private CountDownTimer timer = new CountDownTimer(min * sec * 1000, 1000) {
        /**
         * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText(formatTime(millisUntilFinished));
        }

        /**
         * 倒计时完成时被调用
         */
        @Override
        public void onFinish() {
            textView.setText("00:00");
        }
    };

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

    /**
     * 取消倒计时
     */
    public void timerCancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void timerStart() {
        timer.start();
    }

}