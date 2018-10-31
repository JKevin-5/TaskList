package com.example.user.inventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Task;
import presenter.DataBaseHelper;
import presenter.MyDatabase;

public class Fragment1 extends Fragment {
    ListView listView;
    private DataBaseHelper dbHelper;
    LayoutInflater layoutInflater;
    ArrayList<Task> taskList;
    MyDatabase myDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_main, container, false);

        listView = (ListView) view.findViewById(R.id.list_view);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.add);
        layoutInflater = getLayoutInflater();

        myDatabase = new MyDatabase(this.getActivity());
        taskList = myDatabase.getarray();
        TaskAdapter arrayAdapter = new TaskAdapter(layoutInflater,taskList);
        listView.setAdapter(arrayAdapter);

        //列表点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){//点击一次事件
                View view1 = getLayoutInflater().inflate(R.layout.dialog, null);
                final EditText editText = (EditText) view1.findViewById(R.id.dialog_task);
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        //.setIcon(R.mipmap.icon)//设置标题的图片
                        .setTitle("半自定义对话框")//设置对话框的标题
                        .setView(view1)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String content = editText.getText().toString();
                                Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();

            }
        });

        //列表长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int position,long id) {
                new AlertDialog.Builder(getActivity()) //弹出一个对话框
                        //.setTitle("确定要删除此便签？")
                        .setMessage("确定要删除此便签？")
                        .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myDatabase.toDelete(taskList.get(position).getId());
                                TaskAdapter myAdapter = new TaskAdapter(layoutInflater,taskList);
                                listView.setAdapter(myAdapter);
                                Intent intent = new Intent(getActivity().getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .create()
                        .show();
                return true;
            }
        });

        //悬浮按钮点击事件

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                //MainActivity.this.finish();

                /*
                Snackbar.make(view, "A blank note has been created ", Snackbar.LENGTH_LONG).setAction("Back", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"Note E has been built",Toast.LENGTH_SHORT).show();
                            }
                        }).show();*/
            }
        });


        return view;
    }

}
