package presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import model.Task;

public class MyDatabase {
    Context context;
    DataBaseHelper myOpenHelper;
    SQLiteDatabase mydatabase;
    public MyDatabase(Context context){
        this.context = context;
        myOpenHelper = new DataBaseHelper(context);
    }

    public ArrayList<Task> getarray(){            //获取listview中要显示的数据
        ArrayList<Task> arr = new ArrayList<Task>();
        ArrayList<Task> arr1 = new ArrayList<Task>();
        mydatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select id,title,time,clock from Task",null);
        cursor.moveToFirst();//指的是查询结果的第一个
        Log.v("Tag",cursor.getColumnIndex("id")+"");
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            int clock = cursor.getInt(cursor.getColumnIndex("clock"));
            Task data = new Task(id, title, time,clock);
            arr.add(data);
            cursor.moveToNext();
        }
        mydatabase.close();
        for (int i = arr.size(); i >0; i--) {
            arr1.add(arr.get(i-1));
        }
        return arr1;
    }


    public Task getTiandCon(int id){           //获取要修改数据（就是当选择listview子项想要修改数据时，获取数据显示在新建页面）
        mydatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor=mydatabase.rawQuery("select title,content from Task where id='"+id+"'" , null);
        cursor.moveToFirst();
        String title=cursor.getString(cursor.getColumnIndex("title"));
        String content=cursor.getString(cursor.getColumnIndex("content"));
        Task data=new Task(title,content);
        mydatabase.close();
        return data;
    }

    public void toUpdate(Task data){           //修改表中数据
        mydatabase = myOpenHelper.getWritableDatabase();
        mydatabase.execSQL(
                "update Task set title='"+ data.getTitle()+
                        "',time='"+data.getTime()+
                        "',content='"+data.getContent() +
                        "' where id='"+ data.getId()+"'");
        mydatabase.close();
    }

    public void toInsert(Task data){           //在表中插入新建的便签的数据
        mydatabase = myOpenHelper.getWritableDatabase();
        mydatabase.execSQL("insert into Task(title,content,time)values('"
                + data.getTitle()+"','"
                +data.getContent()+"','"
                +data.getTime()
                +"')");
        mydatabase.close();
    }

    public void toDelete(int ids){            //在表中删除数据
        mydatabase  = myOpenHelper.getWritableDatabase();
        mydatabase.execSQL("delete from Note where id="+ids+"");
        mydatabase.close();
    }
}
