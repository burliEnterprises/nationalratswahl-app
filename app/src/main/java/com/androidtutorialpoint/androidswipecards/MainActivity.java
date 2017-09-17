package com.androidtutorialpoint.androidswipecards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Data> array;
    private ArrayList<Integer>opinion;
    private SwipeFlingAdapterView flingContainer;
    private ImageView backToStart;
    private ImageButton btn_right, btn_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        backToStart = (ImageView) findViewById(R.id.backToStart);
        btn_right = (ImageButton) findViewById(R.id.btn_right);
        btn_left = (ImageButton) findViewById(R.id.btn_left);

        backToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StartActivity.class));
                overridePendingTransition(R.animator.top_in, R.animator.bottom_out);
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flingContainer.getTopCardListener().selectRight();
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flingContainer.getTopCardListener().selectLeft();
            }
        });

        opinion = new ArrayList<>();    // all answers from user, 0 no, 1 yes
        array = new ArrayList<>();
        array.add(new Data(1, "Soll statt der Mindestsicherung ein bedingungsloses Grundeinkommen eingeführt werden?"));
        array.add(new Data(2, "Soll die wöchentliche Arbeitszeit bei vollem Lohnausgleich auf 30 Stunden gekürzt werden?"));
        array.add(new Data(3, "Soll die Pflichtmitgliedschaft in der Arbeiter- und Wirtschaftskammer beibehalten werden?"));
        array.add(new Data(4, "Soll zur steuerlichen Entlastung von ArbeitnehmerInnen eine Wertschöpfungsabgabe eingeführt werden?"));
        array.add(new Data(5, "Sollen gesetzliche Regelungen zur Mietzinshöhe wie bisher auf Altbauwohnungen beschränkt bleiben?"));
        array.add(new Data(6, "Soll Erben weiterhin steuerfrei bleiben?"));
        array.add(new Data(7, "Soll es zugunsten öffentlicher Investitionen möglich sein, neue Schulden zu machen?"));
        array.add(new Data(8, "Sollen flächendeckende Zugangsbeschränkungen an Hochschulen eingeführt werden?"));
        array.add(new Data(9, "Sollen Doppelstaatsbürgerschaften allgemein verboten sein?"));
        array.add(new Data(10, "Soll das allgemeine Wahlrecht auf Bundesebene auf österreichische StaatsbürgerInnen beschränkt bleiben?"));
        array.add(new Data(11, "Soll sich Österreich dafür einsetzen, dass der EU-Kommissionspräsident direkt gewählt wird?"));
        array.add(new Data(12, "Soll sich Österreich dafür einsetzen, die EU-Sanktionen gegenüber Russland aufzuheben?"));
        array.add(new Data(13, "Soll es anerkannten Flüchtlingen uneingeschränkt erlaubt sein, ihre unmittelbaren Familienangehörigen nachzuholen?"));
        array.add(new Data(14, "Soll Österreich sich dafür einsetzen, dass die EU finanzielle Mittel für die Seenotrettung im Mittelmeer erhöht?"));
        array.add(new Data(15, "Sollen Schwangerschaftsabbrüche von der Krankenkasse teilweise übernommen werden?"));
        array.add(new Data(16, "Soll es eine dritte Option bei der Eintragung des Geschlechts in behördlichen Dokumenten geben?"));
        array.add(new Data(17, "Soll die Ehe nur heterosexuellen Paaren vorbehalten bleiben?"));
        array.add(new Data(18, "Sollen Väter auch in der Privatwirtschaft einen gesetzlichen Anspruch auf den \"Papamonat\" haben?"));
        array.add(new Data(19, "Soll eine Impfpflicht für Kinderkrankheiten eingeführt werden?"));
        array.add(new Data(20, "Soll ein verpflichtender Mindestlohn für Praktika eingeführt werden?"));
        array.add(new Data(21, "Soll eine universelle Kindergrundsicherung eingeführt werden?"));
        array.add(new Data(22, "Soll der Konsum von Haschisch und Marihuana strafrechtlich verfolgt werden?"));
        array.add(new Data(23, "Soll die allgemeine Presseförderung an die Mitgliedschaft im Presserat geknüpft werden?"));
        array.add(new Data(24, "Soll Kunst, die gesellschaftlich polarisiert, mit staatlichen Mitteln verstärkt gefördert werden?"));
        array.add(new Data(25, "Soll es dem Staat möglich sein, verschlüsselte Onlinekommunikation (zB Whatsapp) zu überwachen?"));
        array.add(new Data(26, "Sollen die Klimaziele des Pariser Abkommens in Österreich gesetzlich verbindlich werden?"));

        myAppAdapter = new MyAppAdapter(array, MainActivity.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                array.remove(0);
                opinion.add(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                array.remove(0);
                opinion.add(1);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                startActivity(new Intent(MainActivity.this, ResultsActivity.class));    // works, because i changed it in layout to 0
                overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public TextView Number;
        //public ImageView cardImage;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Data> QuestionList;
        public Context context;

        private MyAppAdapter(List<Data> apps, Context context) {
            this.QuestionList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return QuestionList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.Number = (TextView) rowView.findViewById(R.id.tv_questionNumber);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                //viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(QuestionList.get(position).getDescription() + "");
            // darstellung 01 statt 1:
            if (QuestionList.get(position).getNumber() < 10) {
                viewHolder.Number.setText("0" + String.valueOf(QuestionList.get(position).getNumber()));
            } else {
                viewHolder.Number.setText(String.valueOf(QuestionList.get(position).getNumber()));
            }
            //Glide.with(MainActivity.this).load(QuestionList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }
    @Override
    public void onBackPressed() {
    }
}
