package com.mstf.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class TextSwitcherActivity extends AppCompatActivity {

    private TextSwitcher switcher;
    private Button nextBtn;
    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);
        switcher = (TextSwitcher) findViewById(R.id.switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(TextSwitcherActivity.this);
                tv.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
                tv.setTextAppearance(TextSwitcherActivity.this, android.R.style.TextAppearance_Large);
                return tv;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        switcher.setInAnimation(in);
        switcher.setOutAnimation(out);
        switcher.setCurrentText(String.valueOf(mCounter));
        nextBtn = (Button) findViewById(R.id.next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter ++;
                switcher.setText(String.valueOf(mCounter));
            }
        });
    }
}
