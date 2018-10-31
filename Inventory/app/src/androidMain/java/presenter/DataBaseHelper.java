package presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context){
        super(context,"Note",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL( "create table Note("
                + "id integer  primary key autoincrement,"
                + "title text ,"
                + "content text ,"
                + "time text )");
    }

    //private Context mContext;
    /*
    public DataBaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }
    public static final String CREATE_BOOK = "create table Note("
            + "id integer  primary key autoincrement,"
            + "title text ,"
            + "content text ,"
            + "time text )";

    @Override
    //初次登录
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }
    */
    @Override
    //升级数据库
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
    }
}
