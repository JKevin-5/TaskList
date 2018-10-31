package presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabase {
    Context context;
    DataBaseHelper myOpenHelper;
    SQLiteDatabase mydatabase;
    public MyDatabase(Context context){
        this.context = context;
        myOpenHelper = new DataBaseHelper(context);
    }

    public ArrayList<Notes> getarray(){            //获取listview中要显示的数据
        ArrayList<Notes> arr = new ArrayList<Notes>();
        ArrayList<Notes> arr1 = new ArrayList<Notes>();
        mydatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select id,title,time from Note",null);
        cursor.moveToFirst();//指的是查询结果的第一个
        Log.v("Tag",cursor.getColumnIndex("id")+"");
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            Notes data = new Notes(id, title, time);
            arr.add(data);
            cursor.moveToNext();
        }
        mydatabase.close();
        for (int i = arr.size(); i >0; i--) {
            arr1.add(arr.get(i-1));
        }
        return arr1;
    }

    public ArrayList<Notes> getSearch(String s){
        ArrayList<Notes> arr = new ArrayList<Notes>();
        ArrayList<Notes> arr1 = new ArrayList<Notes>();
        mydatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select id,title,time,content from Note where title like '%"+s+"%'or content like '%"+s+"%'",null);
        cursor.moveToFirst();//指的是查询结果的第一个
        Log.v("Tag",cursor.getColumnIndex("id")+"");
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            Notes data = new Notes(id, title, time);
            arr.add(data);
            cursor.moveToNext();
        }
        mydatabase.close();
        for (int i = arr.size(); i >0; i--) {
            arr1.add(arr.get(i-1));
        }
        return arr1;
    }

    public Notes getTiandCon(int id){           //获取要修改数据（就是当选择listview子项想要修改数据时，获取数据显示在新建页面）
        mydatabase = myOpenHelper.getWritableDatabase();
        Cursor cursor=mydatabase.rawQuery("select title,content from Note where id='"+id+"'" , null);
        cursor.moveToFirst();
        String title=cursor.getString(cursor.getColumnIndex("title"));
        String content=cursor.getString(cursor.getColumnIndex("content"));
        Notes data=new Notes(title,content);
        mydatabase.close();
        return data;
    }

    public void toUpdate(Notes data){           //修改表中数据
        mydatabase = myOpenHelper.getWritableDatabase();
        mydatabase.execSQL(
                "update Note set title='"+ data.getTitle()+
                        "',time='"+data.getTime()+
                        "',content='"+data.getContent() +
                        "' where id='"+ data.getId()+"'");
        mydatabase.close();
    }

    public void toInsert(Notes data){           //在表中插入新建的便签的数据
        mydatabase = myOpenHelper.getWritableDatabase();
        mydatabase.execSQL("insert into Note(title,content,time)values('"
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
