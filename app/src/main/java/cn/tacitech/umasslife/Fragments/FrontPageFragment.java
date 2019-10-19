package cn.tacitech.umasslife.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.w3c.dom.Text;

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
                CourseModule.CLASS_LEC, "Address Test", "Nenna", "2019-01-01"); // 临时测试用

        // Alert 文字
        TextView frontPage_alert_text = view.findViewById(R.id.frontPage_alert_text);
        frontPage_alert_text.setText("No alert right now.");
        frontPage_alert_text.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

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

        // courseAddress
        TextView courseAddress_textView = view.findViewById(R.id.frontPage_card_courseAddress);
        courseAddress_textView.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        courseAddress_textView.setText(courseAddress);

        // courseProf
        TextView courseProf_textView = view.findViewById(R.id.frontPage_card_courseProf);
        courseProf_textView.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN));
        courseProf_textView.setText(courseProf);
    }
}
