package com.androidtutorialpoint.androidswipecards;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ResultsActivity extends AppCompatActivity {

    private Button btn_details;
    private ImageView iv_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        btn_details = (Button) findViewById(R.id.btn_showResults);
        iv_home = (ImageView) findViewById(R.id.iv_home);

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
                startActivity(new Intent(ResultsActivity.this, ResultsDetailsActivity.class));
                overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
            }
        });

    }
    @Override
    public void onBackPressed() {
    }
}
