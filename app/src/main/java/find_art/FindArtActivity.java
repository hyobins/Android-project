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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;

import login.DatabaseOpenHelper;
import login.JoinActivity;
import login.LoginActivity;

public class FindArtActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<ArtData> list_ArtData = new ArrayList<>();
    ArtDataAdapter adapter;

    public static String[] img_urls = {
            "https://dbscthumb-phinf.pstatic.net/3329_000_4/20140914215621381_BBG398BEC.jpg/2010010722040633.jpg?type=m2000_2000_fst&wm=N",
            "https://dbscthumb-phinf.pstatic.net/3329_000_55/20141003212252862_H8SJVPP21.jpg/%EC%9D%B4%EC%9A%B0.jpg?type=m2000_2000_fst&wm=N",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_art);

        listview = (ListView) findViewById(R.id.img_listview);
        for(int i=0;i<img_urls.length;i++){
            list_ArtData.add(new ArtData(img_urls[i]));
        }
        adapter = new ArtDataAdapter(FindArtActivity.this, list_ArtData);
        listview.setAdapter(adapter);

        //listview 버튼클릭 이벤트
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String imgURL = ((ArtData)adapter.getItem(position)).getImage();

                //new Intent(현재 Activity, 시작할 activity)
                Intent intent = new Intent(FindArtActivity.this, DetailActivity.class);

                intent.putExtra("imgURL",imgURL);
                startActivity(intent);
            }
        });


    }


}