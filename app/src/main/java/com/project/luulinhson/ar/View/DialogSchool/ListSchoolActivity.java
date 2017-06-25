package com.project.luulinhson.ar.View.DialogSchool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.luulinhson.ar.Adapter.AdapterSchool;
import com.project.luulinhson.ar.Model.Object.School;
import com.project.luulinhson.ar.Presenter.DialogSchool.GetListSchool;
import com.project.luulinhson.ar.R;

import java.util.List;

public class ListSchoolActivity extends AppCompatActivity implements ViewGetListSchool {

    GetListSchool getListSchool;
    ListView lvSchool;
    List<School> schools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_school);

        lvSchool = (ListView) findViewById(R.id.lvShool);

        getListSchool = new GetListSchool(this);
        getListSchool.GetListSchool(this);

        setTitle("SCHOOL");

        lvSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String schoolName = schools.get(position).getSchoolName();
                int idSchool = schools.get(position).getId();
                Intent intent = new Intent();
                intent.putExtra("school",schoolName);
                intent.putExtra("id",idSchool);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void GetListSchoolSuccess(List<School> schoolList) {
        schools = schoolList;
        AdapterSchool adapterSchool = new AdapterSchool(this,R.layout.custom_layout_list_school,schoolList);
        lvSchool.setAdapter(adapterSchool);
        adapterSchool.notifyDataSetChanged();
    }
}
