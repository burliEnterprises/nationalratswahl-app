package biz.burli.nationalratswahl;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ResultsDetailsActivity extends AppCompatActivity {

    private Button btn_share;
    private ImageView iv_home;
    private ArrayList<Integer> userinputs;
    private PieChart pieChart;
    private int[] scores;
    private int highest_party_nr;
    private String party_favorite;
    private TextView text_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_details);

        btn_share = (Button) findViewById(R.id.btn_share);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        pieChart = (PieChart) findViewById(R.id.piechart);
        text_head = (TextView) findViewById(R.id.text_head);

        scores = (int[]) getIntent().getExtras().getIntArray("scores");
        highest_party_nr = (int) getIntent().getExtras().getInt("highest");
        switch(highest_party_nr) {
            case 1:
                party_favorite = getResources().getString(R.string.p1);
                break;
            case 2:
                party_favorite = getResources().getString(R.string.p2);
                break;
            case 3:
                party_favorite = getResources().getString(R.string.p3);
                break;
            case 4:
                party_favorite = getResources().getString(R.string.p4);
                break;
            case 5:
                party_favorite = getResources().getString(R.string.p5);
                break;
            case 6:
                party_favorite = getResources().getString(R.string.p6);
                break;
        };
        text_head.setText("Deine Partei ist: " + party_favorite + "!");

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
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Die Wahlhelfer-App hat mir meine ideale Partei berechnet. \n\n " +
                        "Zu " + String.valueOf(scores[highest_party_nr-1]) +" % bin ich der perfekte " + party_favorite + "-Wähler");
                sendIntent.setType("text/plain");
                if (sendIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(Intent.createChooser(sendIntent, "Share"));
            }
        });


        // diagram shit:
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(scores[0], "SPÖ"));    // labels nicht wichtig, nicht angezeigt, sondern unten geholt und eigens gezeigt
        entries.add(new PieEntry(scores[1], "Liste Kurz"));
        entries.add(new PieEntry(scores[2], "FPÖ"));
        entries.add(new PieEntry(scores[3], "Die Grünen"));
        entries.add(new PieEntry(scores[4], "NEOS"));
        entries.add(new PieEntry(scores[5], "Liste Pilz"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ResourcesCompat.getColor(getResources(), R.color.spo, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.ovp, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.fpo, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.grun, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.neos, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.pilz, null));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(colors);
        set.setSliceSpace(2f);
        set.setDrawValues(true);
        //set.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


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
