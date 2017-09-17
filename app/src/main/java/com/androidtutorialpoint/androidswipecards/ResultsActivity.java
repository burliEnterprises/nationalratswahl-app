package com.androidtutorialpoint.androidswipecards;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private Button btn_details;
    private ImageView iv_home;
    private ArrayList<Integer> userinputs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        btn_details = (Button) findViewById(R.id.btn_showResults);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        userinputs = (ArrayList<Integer>) getIntent().getSerializableExtra("userinputs");


        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultsActivity.this, StartActivity.class));    // works, because i changed it in layout to 0
                overridePendingTransition(R.animator.top_in, R.animator.bottom_out);
            }
        });
        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ResultsActivity.this, ResultsDetailsActivity.class);
                x.putExtra("userinputs", userinputs);
                startActivity(x);
                overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
            }
        });

        // 1 - drei minus, 2 - zwei minus, 3 - ein minus, 4 - ein plus, 5 - zwei plus, 6 - drei plus
        // source: https://wahlkabine.at/nationalratswahl-2017/stellungnahmen
        int spo[]  = {3, 3, 6, 5, 1, 1, 5, 2, 5, 5, 5, 5, 2, 1, 5, 5, 1, 6, 2, 5, 5, 3, 3, 3, 5, 5 };
        int ovp[]  = {1, 1, 4, 1, 4, 6, 2, 5, 6, 6, 5, 5, 1, 1, 2, 2, 5, 3, 3, 3, 3, 5, 3, 3, 6, 4 };
        int fpo[]  = {1, 2, 2, 2, 4, 6, 5, 2, 6, 6, 2, 6, 1, 5, 2, 3, 6, 3, 3, 6, 3, 6, 3, 4, 1, 1 };
        int grun[] = {3, 4, 4, 5, 1, 1, 6, 1, 2, 2, 4, 2, 5, 5, 5, 5, 1, 6, 4, 5, 6, 2, 6, 6, 1, 6 };
        int neos[] = {3, 2, 1, 3, 4, 6, 2, 5, 3, 3, 6, 3, 4, 6, 3, 4, 1, 3, 3, 3, 5, 3, 4, 4, 1, 6 };
        int pilz[] = {3, 4, 3, 5, 1, 1, 6, 2, 2, 2, 5, 2, 5, 5, 5, 5, 1, 5, 4, 5, 4, 2, 3, 3, 5, 6 };

    }
    @Override
    public void onBackPressed() {
    }
}
