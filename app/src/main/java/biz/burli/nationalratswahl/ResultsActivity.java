package biz.burli.nationalratswahl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private Button btn_details;
    private ImageView iv_home;
    private ArrayList<Integer> userinputs;
    private ArrayList<int[]> candidat_stats;
    private int[] scores;
    private int highest, smallest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        btn_details = (Button) findViewById(R.id.btn_share);
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
                Intent x = new Intent(ResultsActivity.this, ResultsDetailsActivity.class);
                x.putExtra("scores", scores);
                x.putExtra("highest", highest);
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

        candidat_stats = new ArrayList();
        candidat_stats.add(spo);
        candidat_stats.add(ovp);
        candidat_stats.add(fpo);
        candidat_stats.add(grun);
        candidat_stats.add(neos);
        candidat_stats.add(pilz);

        scores = new int[6];  // final scores
        userinputs = (ArrayList<Integer>) getIntent().getSerializableExtra("userinputs");

        for (int i = 0; i < 6; i++) {
            int[] cand_tmp = candidat_stats.get(i);
            int tmp_value = 0;
            int tmp_summe = 0;
            for (int j = 0; j < spo.length; j++) {
                switch(cand_tmp[j]) {
                    case 1:
                        tmp_value = 1;
                        break;
                    case 2:
                        tmp_value = 2;
                        break;
                    case 3:
                        tmp_value = 3;
                        break;
                    case 4:
                        tmp_value = 1;
                        break;
                    case 5:
                        tmp_value = 2;
                        break;
                    case 6:
                        tmp_value = 3;
                        break;
                };
                if (cand_tmp[j] < 4 && userinputs.get(j) == 1 ) {
                    tmp_value = tmp_value * (-1);
                };
                if (cand_tmp[j] > 3 && userinputs.get(j) == 0 ) {
                    tmp_value = tmp_value * (-1);
                };
                tmp_summe = tmp_summe + tmp_value;
            };
            scores[i] = tmp_summe;
        };

        // make all values positive (lowest at +10):
        smallest = 1000;    // random
        highest = 0;
        for(int i = 0; i < scores.length; i++) {
            if(smallest > scores[i]) {
                smallest = scores[i];
            };
        };
        for (int i = 0; i < scores.length; i++) {
            scores[i] = scores[i] - smallest;   // - und - git +
            scores[i] += 5;

        }

        // make percentages:
        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        };
        int highest_value = 0;
        for (int i = 0; i < scores.length; i++) {
            scores[i] = 100 * scores[i] / total;
            if (highest_value < scores[i]) {
                highest_value = scores[i];
                highest = i + 1;
            }
        };



    }
    @Override
    public void onBackPressed() {
    }
}
