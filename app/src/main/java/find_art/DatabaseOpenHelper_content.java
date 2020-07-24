package find_art;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper_content extends SQLiteOpenHelper {
    public static final String tableName2 = "Content";
    public DatabaseOpenHelper_content(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        Log.i("tag","Content db생성");
        createTable(db2);
    }

    //DB가 최근것을 반영하도록
    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVer, int newVer) {
//        //버전 바뀌면 예전 버전의 테이블 삭제
//        db2.execSQL("DROP TABLE IF EXISTS " + tableName2);
//        onCreate(db2);
    }

    private void createTable(SQLiteDatabase db2) {
        String sql =
                "CREATE TABLE " + tableName2 + "(art text, writer text, content text)";
        try{
            db2.execSQL(sql);
            Log.i("tag","Content Table Created");
        }catch (SQLException e){
        }
    }

    public void insertContent(SQLiteDatabase db2, String art, String writer, String content){
        db2.beginTransaction();
        try {
            String sql = "INSERT INTO " + tableName2 + "(art, writer, content)" + "values('" + art + "', '" + writer + "' , '" + content+ "')";
            db2.execSQL(sql);
            db2.setTransactionSuccessful();
            Log.i("tag","글쓰기성공");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db2.endTransaction();
        }
    }




}
