package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AlertDetailsActivity extends AppCompatActivity {
    private int status=1;
    private String content;
    private String time;
    private ImageView icon;
    private TextView contentText;
    private TextView timeText;

    final private int status_Dangerous = 0;
    final private int status_Safe = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_details);
        icon = (ImageView) findViewById(R.id.statusIcon);
        contentText = (TextView) findViewById(R.id.content);
        timeText = (TextView) findViewById(R.id.time);
        updateStatus(status,icon);
        initalize(content, time, contentText, timeText);
    }

    protected void updateStatus(int status, ImageView icon){
        switch(status) {
            case 0:
                icon.setColorFilter(Color.red(1));
                break;
            case 1:
                icon.setColorFilter(Color.green(1));
                break;
            default:
                icon.setColorFilter(Color.green(1));
                break;
        }
    }

    protected void initalize(String content, String time, TextView contentText, TextView timeText){
        contentText.setText(content);
        timeText.setText(time);
    }

}
