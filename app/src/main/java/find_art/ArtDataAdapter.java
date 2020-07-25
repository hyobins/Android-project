package find_art;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meetu.R;

import java.util.ArrayList;

import find_person.MemberData;

public class ArtDataAdapter extends BaseAdapter {
    Context context;
    ArrayList<ArtData> artDataList;

    public ArtDataAdapter(Context context, ArrayList<ArtData> artDataList){
        this.context = context;
        this.artDataList = artDataList;
    }

    @Override
    public int getCount() {
        return artDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return artDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.art_list_row, null);
        }

        ImageView img_art = (ImageView) convertView.findViewById(R.id.img_art);
        Glide.with(context).load(artDataList.get(position).getImage()).into(img_art);

        return convertView;
    }

}
