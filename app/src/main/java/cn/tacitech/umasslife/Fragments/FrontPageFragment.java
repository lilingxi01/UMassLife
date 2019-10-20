package cn.tacitech.umasslife.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import cn.tacitech.umasslife.AlertDetailsActivity;
import cn.tacitech.umasslife.Modules.CourseModule;
import cn.tacitech.umasslife.Modules.UiModule;
import cn.tacitech.umasslife.R;

public class FrontPageFragment extends Fragment {

    private TwinklingRefreshLayout twinklingRefreshLayout;
    private View view;

    /**
     * FrontPage Fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_frontpage, container, false);

        // 初始化tkrefresh方法
        twinklingRefreshLayout = view.findViewById(R.id.frontPage_refreshLayout);

        // Header 头部大文字
        TextView frontPage_header_boldText = view.findViewById(R.id.frontPage_header_boldText);
        frontPage_header_boldText.setText("Morning");
        frontPage_header_boldText.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        // Header 头部小文字
        TextView frontPage_header_lightText = view.findViewById(R.id.frontPage_header_lightText);
        frontPage_header_lightText.setText("Enjoy your fresh day~");
        frontPage_header_lightText.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        // Card 课程表
        setFrontPageCard("CS 121", CourseModule.COURSE_CS,
                CourseModule.CLASS_LEC, "ILC N151", "Nenna", "10/16/2019 14:30 - 15:45"); // 临时测试用

        // Alert 文字
        TextView frontPage_alert_text = view.findViewById(R.id.frontPage_alert_text);
        frontPage_alert_text.setText("No alert right now.");
        frontPage_alert_text.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        // Alert 详情
        CardView frontPage_alert = view.findViewById(R.id.frontPage_alert);
        frontPage_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AlertDetailsActivity.class);
                startActivity(intent);
            }
        });

        // Hold 文字
        TextView frontPage_hold_text = view.findViewById(R.id.frontPage_hold_text);
        frontPage_hold_text.setText("You have 1 hold.");
        frontPage_hold_text.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        // TODO 动态布局测试
        LinearLayout frontPage_favoriteList = view.findViewById(R.id.frontPage_favoriteList);
        LinearLayout.LayoutParams layoutParams_mother = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams_mother.setMargins(0, (int) UiModule.dpToPixels(20, getActivity()), 0, 0);
        RelativeLayout newSubLayout = generateLayout("Integrative Learning Center", R.mipmap.ilcimg, "24 Hours");
        frontPage_favoriteList.addView(newSubLayout, layoutParams_mother);
        RelativeLayout newSubLayout2 = generateLayout("Campus Center", R.mipmap.campuscenterimg, "24 Hours");
        frontPage_favoriteList.addView(newSubLayout2, layoutParams_mother);

        return view;
    }

    /**
     * setFrontPageCard 设置首页顶部卡片提醒内容（仅传入，不包含算法）
     * @param courseName
     * @param courseSubject
     * @param courseType
     * @param courseAddress
     * @param courseProf
     * @param courseDate
     */
    public void setFrontPageCard(String courseName, int courseSubject, int courseType,
                                 String courseAddress, String courseProf,
                                 String courseDate){
        // courseName
        TextView courseName_textView = view.findViewById(R.id.frontPage_card_courseName);
        courseName_textView.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        courseName_textView.setText(courseName);

        // courseSubject（课程图标）
        ImageView courseIcon = view.findViewById(R.id.frontPage_card_courseIcon);
        if(courseSubject == CourseModule.COURSE_CS) courseIcon.setImageResource(R.mipmap.frontpage_course_cs);

        // courseType
        TextView courseType_textView = view.findViewById(R.id.frontPage_card_courseType);
        courseType_textView.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        if(courseType == CourseModule.CLASS_LEC) courseType_textView.setText("Lecture");
        else if(courseType == CourseModule.CLASS_DIS) courseType_textView.setText("Discussion");
        else if(courseType == CourseModule.CLASS_LAB) courseType_textView.setText("Lab");
        else courseType_textView.setText("Other");

        // courseType
        TextView courseTime_textView = view.findViewById(R.id.frontPage_card_courseTime);
        courseTime_textView.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        courseTime_textView.setText(courseDate);

        // courseAddress
        TextView courseAddress_textView = view.findViewById(R.id.frontPage_card_courseAddress);
        courseAddress_textView.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        courseAddress_textView.setText(courseAddress);

        // courseProf
        TextView courseProf_textView = view.findViewById(R.id.frontPage_card_courseProf);
        courseProf_textView.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        courseProf_textView.setText(courseProf);
    }

    public RelativeLayout generateLayout(String favoriteTitle, int imageId, String content){
        RelativeLayout layout_root_relative = new RelativeLayout(getActivity());

        CardView relativeLayout_subHeader = new CardView(getActivity());
        RelativeLayout.LayoutParams cardView_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout_subHeader.setLayoutParams(cardView_params);
        relativeLayout_subHeader.setRadius(UiModule.dpToPixels(12, getActivity()));
        relativeLayout_subHeader.setElevation(0);
        relativeLayout_subHeader.setCardBackgroundColor(getResources().getColor(R.color.bg_lightBrown2));

        LinearLayout linearLayout_sub = new LinearLayout(getActivity());
        linearLayout_sub.setOrientation(LinearLayout.VERTICAL);
        relativeLayout_subHeader.addView(linearLayout_sub);

        RelativeLayout relativeLayout_subLayout = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams relativeLayout_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout_params.height = (int) UiModule.dpToPixels(110, getActivity());
        relativeLayout_subLayout.setLayoutParams(relativeLayout_params);
        linearLayout_sub.addView(relativeLayout_subLayout);

        ImageView imageView_headerBg = new ImageView(getActivity());
        RelativeLayout.LayoutParams imageView_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
        imageView_headerBg.setLayoutParams(imageView_params);
        imageView_headerBg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView_headerBg.setForeground(getResources().getDrawable(R.drawable.foreground));
        relativeLayout_subLayout.addView(imageView_headerBg);
        // 传入图片
        if(imageId != 0) imageView_headerBg.setImageResource(imageId);
        else imageView_headerBg.setBackgroundColor(getResources().getColor(R.color.bg_lightBrown3));

        TextView textView_subHeaderText = new TextView(getActivity());
        RelativeLayout.LayoutParams textView_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        textView_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        textView_params.setMargins((int) UiModule.dpToPixels(20, getActivity()),
                0, 0, (int) UiModule.dpToPixels(15, getActivity()));
        textView_subHeaderText.setLayoutParams(textView_params);
        textView_subHeaderText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        textView_subHeaderText.setTextColor(getResources().getColor(R.color.text_mainWhite));
        textView_subHeaderText.setText(favoriteTitle); // TODO 传入
        textView_subHeaderText.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        relativeLayout_subLayout.addView(textView_subHeaderText);

        ImageView loveIcon = new ImageView(getActivity());
        RelativeLayout.LayoutParams loveIcon_param =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        loveIcon_param.width = (int) UiModule.dpToPixels(20, getActivity());
        loveIcon_param.height = (int) UiModule.dpToPixels(20, getActivity());
        loveIcon_param.addRule(RelativeLayout.CENTER_VERTICAL);
        loveIcon_param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        loveIcon_param.setMargins(0, 0, (int) UiModule.dpToPixels(20, getActivity()), 0);
        loveIcon.setLayoutParams(loveIcon_param);
        loveIcon.setImageResource(R.mipmap.frontpage_love);
        relativeLayout_subLayout.addView(loveIcon);

        RelativeLayout contentLayout = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams contentLayout_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        contentLayout.setLayoutParams(contentLayout_params);
        linearLayout_sub.addView(contentLayout);

        // Content TEXT Params
        RelativeLayout.LayoutParams contentText_params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        contentText_params.setMargins((int) UiModule.dpToPixels(20, getActivity()),
                (int) UiModule.dpToPixels(20, getActivity()),
                (int) UiModule.dpToPixels(20, getActivity()),
                (int) UiModule.dpToPixels(20, getActivity()));

        TextView content_time_textView = new TextView(getActivity());
        content_time_textView.setLayoutParams(contentText_params);
        content_time_textView.setText(content); // TODO 传入
        content_time_textView.setTextColor(getResources().getColor(R.color.text_mainBlack));
        content_time_textView.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        contentLayout.addView(content_time_textView);

        RelativeLayout.LayoutParams RL_MW = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);//尤其注意这个位置，用的是父容器的布局参数
        // RL_MW.addRule(RelativeLayout.LEFT_OF,imageID);
        layout_root_relative.addView(relativeLayout_subHeader, RL_MW);

        return layout_root_relative;
    }
}
