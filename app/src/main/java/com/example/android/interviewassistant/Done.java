package com.example.android.interviewassistant;

/**
 * Created by usman on 14/03/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.android.interviewassistant.DbHelper.DbHelper;
import com.example.android.interviewassistant.Model.Ranking;




public class Done extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore, txtResultQuestion;
    ProgressBar progressBarResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        DbHelper db = new DbHelper(this);


        txtResultScore = (TextView) findViewById(R.id.txtTotalScore);
        txtResultQuestion = (TextView) findViewById(R.id.txtTotalQuestion);
        progressBarResult = (ProgressBar) findViewById(R.id.doneProgressBar);
        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//get data from playin
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");

            //Update 2.0
            int playCount = 0;
            if(totalQuestion == 5) // EASY MODE
            {
                playCount = db.getPlayCount(1);
                playCount++;
                db.updatePlayCount(1,playCount); // Set PlayCount ++
            }
            else if(totalQuestion == 8) // MEDIUM MODE
            {
                playCount = db.getPlayCount(1);
                playCount++;
                db.updatePlayCount(2,playCount); // Set PlayCount ++
            }
            else if(totalQuestion == 10) // HARD MODE
            {
                playCount = db.getPlayCount(2);
                playCount++;
                db.updatePlayCount(2,playCount); // Set PlayCount ++
            }
            else if(totalQuestion == 12) // HARDEST MODE
            {
                playCount = db.getPlayCount(2);
                playCount++;
                db.updatePlayCount(2,playCount); // Set PlayCount ++
            }

            double subtract = ((5.0/(float)score)*100)*(playCount-1); //-1 because we playCount++ before we calculate result
            double finalScore = score - subtract;

            txtResultScore.setText(String.format("SCORE : %.1f (-%d)%%", finalScore,5*(playCount-1)));
            txtResultQuestion.setText(String.format("PASSED : %d/%d", correctAnswer, totalQuestion));

            progressBarResult.setMax(totalQuestion);
            progressBarResult.setProgress(correctAnswer);

            //save score
            db.insertScore(finalScore);
        }
    }
}

