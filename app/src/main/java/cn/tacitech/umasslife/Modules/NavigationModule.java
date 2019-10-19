package cn.tacitech.umasslife.Modules;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import cn.tacitech.umasslife.R;

public class NavigationModule {

    private Context context;
    private ImageView imageView;
    private int mainImage;
    private int subImage;
    private TextView textView;
    private int mainColor;
    private int subColor;

    public static int STATUS_MAIN = 1;
    public static int STATUS_SUB = 2;

    /**
     * NavigationModule
     * Made by ❤ in 2019
     * Author: Lingxi Li (1027513601@qq.com)
     */

    /**
     * NavigationModule Constructor without ColorAssignment
     * @param context 传入上下文
     * @param imageView 传入导航栏对应图标控件
     * @param mainImage 传入导航栏选中时图标资源
     * @param subImage 传入导航栏非选中时图标资源
     * @param textView 传入导航栏对应文字控件
     */
    public NavigationModule(Context context, ImageView imageView,
                            int mainImage, int subImage, TextView textView){
        this.context = context;
        this.imageView = imageView;
        this.mainImage = mainImage;
        this.subImage = subImage;
        this.textView = textView;
        this.mainColor = R.color.text_mainBlack; // TODO 颜色分配
        this.subColor = R.color.text_mainBlack; // TODO 颜色分配
        imageView.setImageResource(subImage);
    }

    /**
     * NavigationModule Constructor with ColorAssignment
     * @param context 传入上下文
     * @param imageView 传入导航栏对应图标控件
     * @param mainImage 传入导航栏选中时图标资源
     * @param subImage 传入导航栏非选中时图标资源
     * @param textView 传入导航栏对应文字控件
     * @param mainColor 传入导航栏选中时文字颜色
     * @param subColor 传入导航栏非选中时文字颜色
     */
    public NavigationModule(Context context, ImageView imageView,
                            int mainImage, int subImage,
                            TextView textView, int mainColor, int subColor){
        this.context = context;
        this.imageView = imageView;
        this.mainImage = mainImage;
        this.subImage = subImage;
        this.textView = textView;
        this.mainColor = mainColor;
        this.subColor = subColor;
        imageView.setImageResource(subImage);
    }

    public void setStatus(int status){
        if(status == STATUS_MAIN){
            // 切换为选中状态
            imageView.setImageResource(mainImage);
            textView.setTextColor(context.getResources().getColor(mainColor));
        } else if(status == STATUS_SUB){
            // 切换为非选中状态
            imageView.setImageResource(subImage);
            textView.setTextColor(context.getResources().getColor(subColor));
        }
    }
}
