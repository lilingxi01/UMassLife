package cn.tacitech.umasslife.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.w3c.dom.Text;

import cn.tacitech.umasslife.Modules.UiModule;
import cn.tacitech.umasslife.R;

public class FrontPageFragment extends Fragment {

    private TwinklingRefreshLayout twinklingRefreshLayout;

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
        View view = inflater.inflate(R.layout.fragment_frontpage, container, false);

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

        // Alert 文字
        TextView frontPage_alert_text = view.findViewById(R.id.frontPage_alert_text);
        frontPage_alert_text.setText("No alert right now.");
        frontPage_alert_text.setTypeface(UiModule.getTypeface(getActivity(), UiModule.DIN)); // 设置字体

        return view;
    }
}
