package com.project.luulinhson.ar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.luulinhson.ar.Model.Object.School;
import com.project.luulinhson.ar.R;

import java.util.List;

/**
 * Created by Admin on 6/25/2017.
 */

public class AdapterSchool extends BaseAdapter {

    Context context;
    int layout;
    List<School> schoolList;
    ViewHolder viewHolder;
    View view;

    public AdapterSchool(Context context, int layout, List<School> schoolList){
        this.context = context;
        this.layout = layout;
        this.schoolList = schoolList;
    }

    @Override
    public int getCount() {
        return schoolList.size();
    }

    @Override
    public Object getItem(int position) {
        return schoolList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return schoolList.get(position).getId();
    }

    public class ViewHolder{
        TextView tvSchool;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.tvSchool = (TextView) view.findViewById(R.id.tvShoolList);

            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvSchool.setText(schoolList.get(position).getSchoolName());

        return view;
    }
}
