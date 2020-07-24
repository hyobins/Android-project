package login;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String tableName = "Users";

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("tag","db생성(최초)");
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
    }

    private void createTable(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + tableName + "(id text, pw text)";
        try{
            db.execSQL(sql);
        }catch (SQLException e){
        }
    }


    public void insertUser(SQLiteDatabase db, String id, String pw){
        Log.i("tag","회원가입시 실행");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName + "(id, pw)" + "values('" + id + "', '" + pw + "')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
}
