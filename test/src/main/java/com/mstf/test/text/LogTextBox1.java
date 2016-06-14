package com.mstf.test.text;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mstf.test.R;

public class LogTextBox1 extends Activity {

    private LogTextBox mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_text_box1);
        mText = (LogTextBox) findViewById(R.id.text);
        Button addBtn = (Button) findViewById(R.id.add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText.append("This is a test\n");
            }
        });
    }
}
