package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.tacitech.umasslife.Modules.UiModule;

public class AlertDetailsActivity extends AppCompatActivity {
    private int status = 1;
    private RelativeLayout alert_pagebg;
    private LinearLayout alert_list_motherLayout;

    private final int STATUS_DANGEROUS = 0;
    private final int STATUS_SAFE = 1;

    private String[] test_postTime = {
            "10/11/2019",
            "10/12/2019",
            "10/13/2019",
            "10/14/2019",
            "10/15/2019"
    };
    private String[] test_content = {
            "These values are only for test.",
            "If you device's location is ON and still using your gmail account then you can see the present location of your device.",
            "Please read the article about Find, lock, or erase a lost Android device, you may get some help from it.",
            "If you device's location is ON and still using your gmail account then you can see the present location of your device.",
            "Please read the article about Find, lock, or erase a lost Android device, you may get some help from it."
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_details);

        // UiModule
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
        common_titleText.setText(getStatusString());
        common_titleText.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体

        alert_pagebg = findViewById(R.id.alert_pagebg);
        alert_list_motherLayout = findViewById(R.id.alert_list_motherLayout);

        updateStatus(STATUS_SAFE);
        initialize();
    }

    protected void updateStatus(int status){
        this.status = status;
        switch(status) {
            case STATUS_DANGEROUS:
                alert_pagebg.setBackgroundColor(getResources().getColor(R.color.used_red));
                break;
            case STATUS_SAFE:
                alert_pagebg.setBackgroundColor(getResources().getColor(R.color.used_green));
                break;
            default:
                // BUG
                break;
        }
        TextView common_titleText = findViewById(R.id.common_titleText);
        common_titleText.setText(getStatusString());
    }

    public String getStatusString(){
        if(status == STATUS_SAFE) return "It's safe now.";
        else return "Alert On!";
    }

    private void initialize(){
        if(status == STATUS_DANGEROUS){
            loadContent(test_postTime[0], test_content[0], 1);
            for(int i = 1; i < test_postTime.length; i++){
                loadContent(test_postTime[i], test_content[i], 0);
            }
        } else {
            for(int i = 0; i < test_postTime.length; i++){
                loadContent(test_postTime[i], test_content[i], 0);
            }
        }
    }

    public void loadContent(String postTime, String content, int type){
        LinearLayout.LayoutParams layoutParams_mother = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams_mother.setMargins(0, (int) UiModule.dpToPixels(20, this), 0, 0);
        RelativeLayout newSubLayout = generateLayout(postTime, content, type);
        alert_list_motherLayout.addView(newSubLayout, layoutParams_mother);
    }

    public RelativeLayout generateLayout(String postTime, String content, int type){
        RelativeLayout layout_root_relative = new RelativeLayout(this);

        CardView cardView_layout = new CardView(this);
        RelativeLayout.LayoutParams cardView_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        cardView_layout.setLayoutParams(cardView_params);
        cardView_layout.setRadius(UiModule.dpToPixels(12, this));
        cardView_layout.setElevation(0);
        cardView_layout.setCardBackgroundColor(getResources().getColor(R.color.bg_lightBrown1));

        RelativeLayout relativeLayout_sub = new RelativeLayout(this);
        RelativeLayout.LayoutParams relativeLayout_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout_sub.setLayoutParams(relativeLayout_params);
        cardView_layout.addView(relativeLayout_sub);

        if(type == 1) {
            TextView now_label = new TextView(this);
            RelativeLayout.LayoutParams label_params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            label_params.setMargins(0,
                    (int) UiModule.dpToPixels(20, this),
                    (int) UiModule.dpToPixels(20, this), 0);
            label_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            label_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            now_label.setLayoutParams(label_params);
            now_label.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
            now_label.setTextColor(getResources().getColor(R.color.bg_lightBrown3));
            now_label.setText("NOW"); // TODO 传入
            now_label.setTypeface(UiModule.getTypeface(this, UiModule.DIN));
            relativeLayout_sub.addView(now_label);
        }

        LinearLayout linearLayout_content = new LinearLayout(this);
        linearLayout_content.setOrientation(LinearLayout.VERTICAL);
        relativeLayout_sub.addView(linearLayout_content);

        TextView textView_time = new TextView(this);
        RelativeLayout.LayoutParams textView_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        textView_params.setMargins((int) UiModule.dpToPixels(20, this),
                (int) UiModule.dpToPixels(20, this),
                (int) UiModule.dpToPixels(20, this),
                (int) UiModule.dpToPixels(10, this));
        textView_time.setLayoutParams(textView_params);
        textView_time.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView_time.setTextColor(getResources().getColor(R.color.text_secondBlack));
        textView_time.setText(postTime); // TODO 传入
        textView_time.setTypeface(UiModule.getTypeface(this, UiModule.DIN));
        linearLayout_content.addView(textView_time);

        // Content TEXT Params
        RelativeLayout.LayoutParams contentText_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        contentText_params.setMargins((int) UiModule.dpToPixels(20, this),
                0,
                (int) UiModule.dpToPixels(20, this),
                (int) UiModule.dpToPixels(20, this));
        TextView content_textView = new TextView(this);
        content_textView.setLayoutParams(contentText_params);
        content_textView.setText(content); // TODO 传入
        content_textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        content_textView.setTextColor(getResources().getColor(R.color.text_mainBlack));
        content_textView.setTypeface(UiModule.getTypeface(this, UiModule.DIN));
        linearLayout_content.addView(content_textView);

        RelativeLayout.LayoutParams RL_MW = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);//尤其注意这个位置，用的是父容器的布局参数
        // RL_MW.addRule(RelativeLayout.LEFT_OF,imageID);
        layout_root_relative.addView(cardView_layout, RL_MW);

        return layout_root_relative;
    }

}
