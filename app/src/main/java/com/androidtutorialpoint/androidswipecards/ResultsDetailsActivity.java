package com.androidtutorialpoint.androidswipecards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class ResultsDetailsActivity extends AppCompatActivity {

    private Button btn_share;
    private ImageView iv_home;
    private ArrayList<Integer> userinputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_details);

        //btn_share = (Button) findViewById(R.id.btn_share);
        //iv_home = (ImageView) findViewById(R.id.iv_home);
        userinputs = (ArrayList<Integer>) getIntent().getSerializableExtra("userinputs");

        /*iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultsDetailsActivity.this, StartActivity.class));    // works, because i changed it in layout to 0
                overridePendingTransition(R.animator.top_in, R.animator.bottom_out);
            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        */
    }

    @Override
    public void onBackPressed() {
    }
}
