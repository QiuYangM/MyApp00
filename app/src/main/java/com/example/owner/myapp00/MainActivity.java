package com.example.owner.myapp00;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView chuan;
    private WaterView viewById;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chuan =  findViewById(R.id.img_chuan);
        viewById = findViewById(R.id.water_bt);

        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) chuan.getLayoutParams();

        viewById.animation(new WaterView.AnimationListener() {
            @Override
            public void animation(float y) {
                layoutParams.setMargins(0,0,0, (int) y);
                chuan.setLayoutParams(layoutParams);
            }
        });
    }
}
