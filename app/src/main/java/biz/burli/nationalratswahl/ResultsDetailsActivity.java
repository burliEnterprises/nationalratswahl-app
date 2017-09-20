package biz.burli.nationalratswahl;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

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
        colors.add(ResourcesCompat.getColor(getResources(), R.color.spo, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.ovp, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.fpo, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.grun, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.neos, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.pilz, null));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(colors);
        set.setSliceSpace(0f);
        set.setDrawValues(true);
        set.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


        PieData data = new PieData(set);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        //data.setValueTextColor(Color.parseColor("#666666"));
        // data.setValueTextColor(Color.parseColor("#ecf0f1"));

        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setDescription(new Description());
        pieChart.setDrawEntryLabels(false);
        pieChart.highlightValues(null);
        pieChart.invalidate();
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        pieChart.getDescription().setEnabled(false);


        Legend l = pieChart.getLegend();
        l.setEnabled(false);
        //http://androidbuddys.com/pie-chartdonut-chart-using-mp-chart/
    }

    @Override
    public void onBackPressed() {
    }
}
