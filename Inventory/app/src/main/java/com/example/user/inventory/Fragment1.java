package com.example.user.inventory;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;



public class Fragment1 extends Fragment {


    private Button play;
    private Button pause;
    private TextView textView;
    private SeekBar mSeekBar;
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_main, container, false);

        play = (Button) view.findViewById(R.id.play);
        pause = (Button) view.findViewById(R.id.pause);
        mediaPlayer = MediaPlayer.create(getActivity(),R.raw.spring_rain);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        textView = (TextView) view.findViewById(R.id.text);

        Button senlin = (Button) view.findViewById(R.id.senlin);
        Button xiaye = (Button) view.findViewById(R.id.summer_night);
        Button chunyu = (Button) view.findViewById(R.id.spring_rain);
        Button luhuo = (Button) view.findViewById(R.id.stove_fire);
        Button haitan = (Button) view.findViewById(R.id.beach);
        Button clock = (Button) view.findViewById(R.id.clock_second);

        //森林
        senlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                mediaPlayer=MediaPlayer.create(getActivity(),R.raw.forest);
                //获取音乐时间
                int max = mediaPlayer.getDuration();
                mSeekBar.setMax(max);
                textView.setText(formatTime(max));
            }
        });
        //春雨
        chunyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                mediaPlayer=MediaPlayer.create(getActivity(),R.raw.spring_rain);
                //获取音乐时间
                int max = mediaPlayer.getDuration();
                mSeekBar.setMax(max);
                textView.setText(formatTime(max));
            }
        });
        //炉火
        luhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mediaPlayer.reset();
              mediaPlayer=MediaPlayer.create(getActivity(),R.raw.stove_fire);
              //获取音乐时间
                int max = mediaPlayer.getDuration();
                mSeekBar.setMax(max);
                textView.setText(formatTime(max));
            }
        });
        //海滩
        haitan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                mediaPlayer=MediaPlayer.create(getActivity(),R.raw.beach);
                //获取音乐时间
                int max = mediaPlayer.getDuration();
                mSeekBar.setMax(max);
                textView.setText(formatTime(max));
            }
        });
        //夏夜
        xiaye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mediaPlayer.reset();
                    mediaPlayer= MediaPlayer.create(getActivity(),R.raw.summer_night);
                //获取音乐时间
                int max = mediaPlayer.getDuration();
                mSeekBar.setMax(max);
                textView.setText(formatTime(max));
            }
        });

        //时钟
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.clock_second);
                //获取音乐时间
                int max = mediaPlayer.getDuration();
                mSeekBar.setMax(max);
                textView.setText(formatTime(max));
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                //调用run开始更新进度条
                handler.post(run);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(run);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                handler.postDelayed(run, 1000);
            }
        });

        return view;
    }

    Handler handler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            //player.getCurrentPosition()获取音乐的当前进度
            mSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(run, 100);
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

}

