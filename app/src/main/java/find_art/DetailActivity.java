package find_art;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.meetu.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import login.LoginActivity;

public class DetailActivity extends AppCompatActivity{
    private Intent intent;
    private ImageView imageView;
    String name_string, date_string;

//    @BindView(R.id.name) EditText name;
//    @BindView(R.id.date) EditText date;
//    @BindView(R.id.btn_enter) Button btn_enter;
    EditText name;
    EditText date;
    Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // FindArtActivity 에서 보낸 imgURL 를 받기위해 getIntent()로 초기화
        intent = getIntent();
        imageView = (ImageView) findViewById(R.id.detailImage);

        Glide.with(this).load(intent.getStringExtra("imgURL")).into(imageView);
        name = (EditText) findViewById(R.id.name);
        date = (EditText) findViewById(R.id.date);
        name_string = name.toString();
        date_string = date.toString();

        btn_enter = (Button) findViewById(R.id.btn_enter);


        btn_enter.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(DetailActivity.this, "로그인성공", Toast.LENGTH_SHORT);
                toast.show();
                postRequest(name_string, date_string, intent.getStringExtra("imgURL"));
                Toast toast2 = Toast.makeText(DetailActivity.this, "로그인성공2", Toast.LENGTH_SHORT);
                toast2.show();
            }
        });

    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_enter:
//                Toast toast = Toast.makeText(DetailActivity.this, "로그인성공", Toast.LENGTH_SHORT);
//                toast.show();
//                postRequest(name_string, date_string, intent.getStringExtra("imgURL"));
//                break;
//            default:
//                break;
//        }
//    }


    private void postRequest(final String name_string, final String date_string, final String url_string){
        Log.i("start","여기까지 실행됨");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:1337/tests";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("에러=>", error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name",name_string);
                params.put("date",date_string);
                params.put("image",url_string);
                return params;
            }
        };
        requestQueue.add(request);

    }
}