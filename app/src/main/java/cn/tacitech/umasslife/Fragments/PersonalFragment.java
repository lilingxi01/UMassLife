package cn.tacitech.umasslife.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import cn.tacitech.umasslife.Modules.UiModule;
import cn.tacitech.umasslife.R;

public class PersonalFragment extends Fragment {

    private TwinklingRefreshLayout twinklingRefreshLayout;
    private RelativeLayout personal_spireid_layout;
    private View view;

    /**
     * Personal Fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_personal, container, false);

        // 初始化tkrefresh方法
        twinklingRefreshLayout = view.findViewById(R.id.personal_refreshLayout);

        // Header Name
        TextView personal_header_name = view.findViewById(R.id.personal_header_name);
        personal_header_name.setText(getStudentName()); // 传入用户姓名
        personal_header_name.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        // Layout SpireID
        personal_spireid_layout = view.findViewById(R.id.personal_spireid_layout);

        // Header SpireID
        TextView personal_header_spireid = view.findViewById(R.id.personal_header_spireid);
        personal_header_spireid.setText(getStudentID()); // 传入学生SpireID
        personal_header_spireid.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体
        personal_header_spireid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSpireIdPanel();
            }
        });
        RelativeLayout personal_spireid_bg = view.findViewById(R.id.personal_spireid_bg);
        personal_spireid_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeSpireIdPanel();
            }
        });

        // Header Major
        TextView personal_header_major = view.findViewById(R.id.personal_header_major);
        personal_header_major.setText(getStudentMajor()); // 传入学生专业+年份
        personal_header_major.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        // Debit 文字设置
        TextView personal_debit_title = view.findViewById(R.id.personal_debit_title);
        personal_debit_title.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体
        TextView personal_debit_num = view.findViewById(R.id.personal_debit_num);
        personal_debit_num.setText("100.00");
        personal_debit_num.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体
        TextView personal_debit_diningDollar = view.findViewById(R.id.personal_debit_diningDollar);
        personal_debit_diningDollar.setText("0.00");
        personal_debit_diningDollar.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        // Dorm 文字设置
        TextView personal_dorm_title = view.findViewById(R.id.personal_dorm_title);
        personal_dorm_title.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        return view;
    }

    // Load student's name (for example)
    public String getStudentName(){
        // TODO 返回对应学生名字
        String firstName = "Chuyang";
        String lastName = "Zhong";
        return firstName+" "+lastName;
    }

    // Load student's major (for example)
    public String getStudentMajor(){
        // TODO 返回对应学生名字
        String major = "Computer Science";
        String year = "2023";
        return major+", "+year;
    }

    // Load student's Spire ID (for example)
    public String getStudentID(){
        // TODO 返回对应学生名字
        return "12345678";
    }

    public void openSpireIdPanel(){
        personal_spireid_layout.setVisibility(View.VISIBLE);
        TextView personal_spireid_text = view.findViewById(R.id.personal_spireid_text);
        personal_spireid_text.setText(getStudentID()); // 传入学生SpireID
        personal_spireid_text.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体
    }

    public void closeSpireIdPanel(){
        personal_spireid_layout.setVisibility(View.GONE);
    }
}
