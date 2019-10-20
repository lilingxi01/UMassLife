package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CourseScheduleActivity extends AppCompatActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_schedule);
    }


    private TextView createCourse(int startTime, int endTime, String courseTitle, LinearLayout layout){
        TextView course = new TextView(this);
        course.setGravity(Gravity.CENTER);
        course.setText(courseTitle);
        course.setWidth(layout.getWidth());
        course.setHeight(layout.getHeight()*(endTime-startTime)/24);
        course.setY(layout.getHeight()*(startTime/24));
        return course;
    }

    private void addView(int day, int startTime, int endTime, String courseTitle){
        TextView course;
        switch(day){
            case 1:
                layout = (LinearLayout)findViewById(R.id.monday);
                break;
            case 2:
                layout = (LinearLayout)findViewById(R.id.tuesday);
                break;
            case 3:
                layout = (LinearLayout)findViewById(R.id.wednesday);
                break;
            case 4:
                layout = (LinearLayout)findViewById(R.id.thursday);
                break;
            case 5:
                layout = (LinearLayout)findViewById(R.id.friday);
                break;
            case 6:
                layout = (LinearLayout)findViewById(R.id.satauday);
                break;
            case 7:
                layout = (LinearLayout)findViewById(R.id.sunday);
                break;
            default:
                break;
        }
        layout.setLayoutParams(new LinearLayout.LayoutParams((LinearLayout.LayoutParams.MATCH_PARENT)/7,layout.getHeight()));
        course = createCourse(startTime,endTime,courseTitle,layout);
        layout.addView(course);
    }
}
