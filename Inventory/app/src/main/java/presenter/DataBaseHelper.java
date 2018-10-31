package presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context){
        super(context,"Tasks",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL( "create table Task("
                + "id integer  primary key autoincrement,"
                + "title text ,"
                + "content text ,"
                + "time text ,"
                + "clock integer)"
        );
    }

    @Override
    //升级数据库
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
    }
}
