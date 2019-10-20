package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.tacitech.umasslife.Modules.UiModule;

public class DormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dorm);

        // uiModule
        UiModule uiModule = new UiModule(this);
        uiModule.setStatusBar(UiModule.DARK_STATUSBAR);
        // Common Back Btn
        RelativeLayout common_backBtn = findViewById(R.id.common_backBtn);
        common_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // Common Title Text
        TextView common_titleText = findViewById(R.id.common_titleText);
        common_titleText.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体

        // My Dorm Name
        TextView dorm_myDormName = findViewById(R.id.dorm_myDormName);
        dorm_myDormName.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体

        // Roommate Texts
        TextView dorm_roommate_title = findViewById(R.id.dorm_roommate_title);
        dorm_roommate_title.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体
        TextView dorm_roommate_name = findViewById(R.id.dorm_roommate_name);
        dorm_roommate_name.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体
        TextView dorm_roommate_email = findViewById(R.id.dorm_roommate_email);
        dorm_roommate_email.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体

        // RSD Package
        TextView dorm_package_title = findViewById(R.id.dorm_package_title);
        dorm_package_title.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体

        // iService System
        TextView dorm_iservice_title = findViewById(R.id.dorm_iservice_title);
        dorm_iservice_title.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体

        // Other
        TextView dorm_other_title = findViewById(R.id.dorm_other_title);
        dorm_other_title.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体
    }
}
