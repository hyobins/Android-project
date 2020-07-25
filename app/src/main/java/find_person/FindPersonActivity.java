package find_person;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.meetu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FindPersonActivity extends AppCompatActivity {
    //listview 로 나타내기위해 필요한것
    ListView listview;
    ArrayList<MemberData> list_invitation = new ArrayList<MemberData>();
    MemberDataAdapter adapter;

    //volley 위해 필요한것
    RequestQueue requestQueue;
    String TAG = this.getClass().getSimpleName();

    //기타
    String data1, data2, data3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_person);

        listview = (ListView) findViewById(R.id.listview);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        sendRequest();

        //listView 테스트 구문(추후 삭제)
        list_invitation.add(new MemberData("hyobin", "07/26","https://dbscthumb-phinf.pstatic.net/3329_000_1/20140911225436869_NGLR5V661.jpg/02-007110.jpg?type=m2000_2000_fst_n&wm=Y"));

        adapter = new MemberDataAdapter(FindPersonActivity.this, list_invitation);
        listview.setAdapter(adapter);

    }

    private void sendRequest() {
        String url = "http://10.0.2.2:1337/tests";
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "응답=>" + response.toString());
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject data = (JSONObject) response.get(i);
                                data1 = data.getString("name");
                                data2 = data.getString("date");
                                data3 = data.getString("image");
                                list_invitation.add(new MemberData(data1, data2, data3));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG,"에러=>" + error.getMessage());
                            }
                        }
                        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };
        request.setShouldCache(false);
        requestQueue.add(request);
        Log.d(TAG,"요청 보냄!!!!!");
    }

}