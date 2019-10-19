package cn.tacitech.umasslife.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

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
        twinklingRefreshLayout = getActivity().findViewById(R.id.frontPage_refreshLayout);


        return view;
    }
}
