package com.androidtutorialpoint.androidswipecards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultsActivity extends AppCompatActivity {

    private Button btn_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        btn_details = (Button) findViewById(R.id.btn_showResults);
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
