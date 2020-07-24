package find_art;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meetu.R;

import login.DatabaseOpenHelper;
import login.JoinActivity;
import login.LoginActivity;

public class FindArtActivity extends AppCompatActivity {
    int version = 1;
    DatabaseOpenHelper_content helper_content;
    SQLiteDatabase database2;

    EditText artE;
    EditText writerE;
    EditText contentE;

    Button btnWrite;

    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_art);

        artE = (EditText) findViewById(R.id.art);
        writerE = (EditText) findViewById(R.id.writer);
        contentE = (EditText) findViewById(R.id.content);

        btnWrite = (Button) findViewById(R.id.btnWrite);

        helper_content = new DatabaseOpenHelper_content(FindArtActivity.this, DatabaseOpenHelper_content.tableName2, null, version);
        database2 = helper_content.getWritableDatabase();

        btnWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String art = artE.getText().toString();
                String writer = writerE.getText().toString();
                String content = contentE.getText().toString();

                sql = "SELECT * FROM " + DatabaseOpenHelper_content.tableName2;
                cursor = database2.rawQuery(sql, null);

                helper_content.insertContent(database2,art,writer,content);
                finish();
            }
        });

    }
}