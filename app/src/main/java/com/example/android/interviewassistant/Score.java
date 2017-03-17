package com.example.android.interviewassistant;

/**
 * Created by usman on 14/03/2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import com.example.android.interviewassistant.DbHelper.DbHelper;
import com.example.android.interviewassistant.Model.Ranking;
import com.example.android.interviewassistant.Common.CustomAdapter;


public class Score extends AppCompatActivity {
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lstView = (ListView)findViewById(R.id.lstRanking);
        DbHelper db = new DbHelper(this);
        List<Ranking> lstRanking = db.getRanking();
        if(lstRanking.size() > 0)
        {
            CustomAdapter adapter = new CustomAdapter(this,lstRanking);
            lstView.setAdapter(adapter);
        }
    }
}
