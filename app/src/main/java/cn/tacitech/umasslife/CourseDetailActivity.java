package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CourseDetailActivity extends AppCompatActivity {
    private String course = "Math-233";
    private String description = "Math is fun.";
    private TextView courseName;
    private TextView descriptionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        courseName = (TextView) findViewById(R.id.course);
        descriptionText = (TextView) findViewById(R.id.contentTitle);
        initialize(courseName, descriptionText);
    }

    protected void initialize(TextView courseName, TextView descriptionText){
        courseName.setText(course);
        descriptionText.setText(description);
    }

}
