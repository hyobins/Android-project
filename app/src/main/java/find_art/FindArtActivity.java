package find_art;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meetu.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import login.DatabaseOpenHelper;
import login.JoinActivity;
import login.LoginActivity;

public class FindArtActivity extends AppCompatActivity {

    EditText data1;
    EditText data2;
    EditText data3;

    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_art);

        data1 = (EditText) findViewById(R.id.artId);
        data2 = (EditText) findViewById(R.id.name);
        data3 = (EditText) findViewById(R.id.date);

        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PHPRequest request = new PHPRequest("http://192.168.64.2/my_project/insert.php");
                    String result = request.PhPtest(String.valueOf(data1.getText()), String.valueOf(data2.getText()), String.valueOf(data3.getText()));
                    Log.i("PHPRequest", "여기");
                    if (result.equals("1")) {
                        Toast.makeText(getApplication(), "들어감", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(), "안 들어감", Toast.LENGTH_SHORT).show();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}