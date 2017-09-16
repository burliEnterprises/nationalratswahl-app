package com.androidtutorialpoint.androidswipecards;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StartActivity extends AppCompatActivity {

    private Button btn_go;
    private TextView tv_mehrErfahren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btn_go = (Button) findViewById(R.id.btn_go);
        tv_mehrErfahren = (TextView) findViewById(R.id.tv_mehrErfahren);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
            }
        });

        tv_mehrErfahren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wahlkabine.at/nationalratswahl-2017"));
                startActivity(browserIntent);
            }
        });



    }
}
