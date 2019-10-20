package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiningListActivity extends AppCompatActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_list);
    }

    private TextView createDinningList(String content, LinearLayout layout){
        TextView dining = new TextView(this);
        dining.setGravity(Gravity.CENTER);
        dining.setText(content);
        dining.setWidth(layout.getWidth());
        dining.setHeight(layout.getHeight()/6);
        return dining;
    }

    private void addView(String content){
        TextView dining;
        layout = (LinearLayout)findViewById(R.id.diningList);
        dining = createDinningList(content,layout);
        layout.addView(dining);
    }
}
