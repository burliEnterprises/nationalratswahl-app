package com.androidtutorialpoint.androidswipecards;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultsDetailsActivity extends AppCompatActivity {

    private Button btn_share;
    private ImageView iv_home;
    private ArrayList<Integer> userinputs;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_details);

        btn_share = (Button) findViewById(R.id.btn_share);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        pieChart = (PieChart) findViewById(R.id.piechart);
        userinputs = (ArrayList<Integer>) getIntent().getSerializableExtra("userinputs");

        iv_home.setOnClickListener(new View.OnClickListener() {
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


        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(10, "SPÖ"));
        entries.add(new PieEntry(20, "Liste Kurz"));
        entries.add(new PieEntry(28, "FPÖ"));
        entries.add(new PieEntry(12, "Die Grünen"));
        entries.add(new PieEntry(25, "NEOS"));
        entries.add(new PieEntry(5, "Liste Pilz"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FE6860"));
        colors.add(Color.parseColor("#72CCCA"));
        colors.add(Color.parseColor("#91CED7"));
        colors.add(Color.parseColor("#CCEBC0"));
        colors.add(Color.parseColor("#FDE1F7"));
        colors.add(Color.parseColor("#ecf0f1"));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(colors);
        set.setSliceSpace(0f);
        set.setDrawValues(true);
        PieData data = new PieData(set);
        // data.setValueTextSize(20f); // <- here
        // data.setValueTextColor(Color.parseColor("#ecf0f1"));

        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(0);
        pieChart.setDrawEntryLabels(false);
        pieChart.invalidate(); // refresh

    }

    @Override
    public void onBackPressed() {
    }
}
