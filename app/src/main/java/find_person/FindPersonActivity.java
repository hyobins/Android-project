package find_person;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.meetu.R;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class FindPersonActivity extends AppCompatActivity {
    ArrayList<MemberData> datas = new ArrayList<MemberData>();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_person);

        listview = (ListView) findViewById(R.id.listview);

        //이 부분에서, MemberData 안에 db에서 받아온 값을 넣어줄 예정

        datas.add(new MemberData("Hyobin", "24th July", R.drawable.bnj));
        datas.add(new MemberData("Geon", "28th July", R.drawable.ljs));

        MemberDataAdapter adapter = new MemberDataAdapter(getLayoutInflater(), datas);
        listview.setAdapter(adapter);
    }
}