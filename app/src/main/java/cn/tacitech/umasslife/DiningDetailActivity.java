package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DiningDetailActivity extends AppCompatActivity {
    private String nameStr = "abc";
    private String descriptionStr = "def.";
    private TextView name;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_detail);
        name = (TextView) findViewById(R.id.course);
        description = (TextView) findViewById(R.id.contentTitle);
        initialize(name, description);
    }
    protected void initialize(TextView name, TextView description){
        name.setText(nameStr);
        description.setText(descriptionStr);
    }

}
