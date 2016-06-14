package com.mstf.test.text;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.mstf.test.R;

import org.w3c.dom.Text;

public class Link extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        TextView textView2 = (TextView) findViewById(R.id.text2);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView3 = (TextView) findViewById(R.id.text3);
        textView3.setText(
                Html.fromHtml("<b>text3:Constructed from HTML programmatically.</b> Text with a " +
                "<a href=\"http://www.google.com\">link</a> " +
                "created in the Java source code using HTML.")
        );
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textView4 = (TextView) findViewById(R.id.text4);
        SpannableString spannableString = new SpannableString("text4: Manually created spans. Click here to dial the phone.");
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new URLSpan("tel:4155551212"), 31+6, 31+10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView4.setText(spannableString);
        textView4.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
