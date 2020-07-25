package find_person;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.meetu.R;

import java.util.ArrayList;

public class MemberDataAdapter extends BaseAdapter {
    Context context;
    ArrayList<MemberData> invitation;

    public MemberDataAdapter(Context context, ArrayList<MemberData> invitation){
        this.context = context;
        this.invitation = invitation;
    }

    @Override
    public int getCount() {
        return invitation.size();
    }

    @Override
    public Object getItem(int position) {
        return invitation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_row, null);
        }

        TextView text_name = (TextView) convertView.findViewById(R.id.text_name);
        TextView text_date = (TextView) convertView.findViewById(R.id.text_date);
        ImageView img_art = (ImageView) convertView.findViewById(R.id.img_art);

        text_name.setText(invitation.get(position).getName()+"님의 초대");
        text_date.setText(invitation.get(position).getDate());
        //img_art.setText(invitation.get(position).getImage());
        Glide.with(context).load(invitation.get(position).getImage()).into(img_art);
        //img_art.setText(invitation.get(position).getImage());

        return convertView;
    }
}
