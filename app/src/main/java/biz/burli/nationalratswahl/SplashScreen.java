package biz.burli.nationalratswahl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fujiyuu75.sequent.Sequent;

public class SplashScreen extends AppCompatActivity {

    /* start activity, splash screen with logo ... */
    private RelativeLayout xy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        xy = (RelativeLayout) findViewById(R.id.rel);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // fadein:
        Sequent
                .origin(xy)
                .duration(300) // option.
                .start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, StartActivity.class));
                finish();
            }
        }, 2*1000);
    }
}
