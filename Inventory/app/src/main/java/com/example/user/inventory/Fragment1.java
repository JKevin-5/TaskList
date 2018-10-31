package com.example.user.inventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Task;
import presenter.DataBaseHelper;
import presenter.MyDatabase;

public class Fragment1 extends Fragment {

    int i = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_main, container, false);
        Button play = (Button) view.findViewById(R.id.play);
        Button pause = (Button) view.findViewById(R.id.pause);
        Button stop = (Button) view.findViewById(R.id.Stop);
        CheckBox senlin = (CheckBox) view.findViewById(R.id.senlin);
        CheckBox xiaye = (CheckBox) view.findViewById(R.id.summer_night);
        CheckBox cunyu = (CheckBox) view.findViewById(R.id.spring_rain);
        CheckBox luhuo = (CheckBox) view.findViewById(R.id.stove_fire);
        CheckBox haitan = (CheckBox) view.findViewById(R.id.beach);

        //森林音效状态
        senlin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    i = 1;
                    /*ckOK.setEnabled(false);*/
                }else{
                    i = 0;
                }
            }
        });

        //森林音效状态
        xiaye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    i = 2;
                    /*ckOK.setEnabled(false);*/
                }else{
                    i = 0;
                }
            }
        });

        //森林音效状态
        luhuo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    i = 3;
                    /*ckOK.setEnabled(false);*/
                }else{
                    i = 0;
                }
            }
        });

        //森林音效状态
        haitan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    i = 4;
                    /*ckOK.setEnabled(false);*/
                }else{
                    i = 0;
                }
            }
        });

        //森林音效状态
        cunyu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    i = 5;
                    /*ckOK.setEnabled(false);*/
                }else{
                    i = 0;
                }
            }
        });
        final MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(getActivity(),R.raw.spring_rain);
        // 监听音频播放完的代码，实现音频的自动循环播放

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mediaPlayer.start();//开始播放
                    Toast.makeText(getActivity(), "音乐播放", Toast.LENGTH_SHORT).show();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();//音乐暂停
                Toast.makeText(getActivity(), "音乐暂停", Toast.LENGTH_SHORT).show();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   mediaPlayer.stop();
                   Toast.makeText(getActivity(), "音乐停止", Toast.LENGTH_SHORT).show();


            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });

        return view;
    }
}
