package com.everstone.blankapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.everstone.blankapp.R;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        Bundle bundle=getIntent().getExtras();
        int id = bundle.getInt("id");
        TextView tv = (TextView)findViewById(R.id.textView);
        switch (id){
            case R.id.tv_1:
                tv.setText("这是从第1个item过来的");
                break;
            case R.id.tv_2:
                tv.setText("这是从第2个item过来的");
                break;
            case R.id.tv_3:
                tv.setText("这是从第3个item过来的");
                break;
            case R.id.tv_4:
                tv.setText("这是从第4个item过来的");
                break;
            case R.id.tv_5:
                tv.setText("这是从第5个item过来的");
                break;
        }
    }
}
