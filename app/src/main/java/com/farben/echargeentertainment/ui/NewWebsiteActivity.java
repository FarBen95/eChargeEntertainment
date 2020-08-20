package com.farben.echargeentertainment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.farben.echargeentertainment.R;

import androidx.appcompat.app.AppCompatActivity;

public class NewWebsiteActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.websitelistsql.REPLY";
    private EditText mEditWebsiteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_website);

        mEditWebsiteView = findViewById(R.id.edit_website);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWebsiteView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String link = mEditWebsiteView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, link);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}