package cn.tacitech.umasslife.Modules;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.WindowManager;

public class UiModule {

    // 状态值预设
    public static final int LIGHT_STATUSBAR = 1; // 浅色顶栏
    public static final int DARK_STATUSBAR = 2; // 深色顶栏

    // 文字类型预设
    public static final int DIN = 1;
    public static final int DIN2 = 2;

    // Class预设
    private Activity activity;

    public UiModule(Activity activity){
        this.activity = activity;
    }

    public void setStatusBar(int statusbarType){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if(statusbarType == LIGHT_STATUSBAR) {
            // 浅色顶栏
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if(statusbarType == DARK_STATUSBAR) {
            // 深色顶栏
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    public static Typeface getTypeface(Context context, int textType){
        if(textType == DIN)
            return Typeface.createFromAsset(context.getAssets(),"font/din.ttf");
        else if(textType == DIN2)
            return Typeface.createFromAsset(context.getAssets(),"font/din2.ttf");
        else
            return Typeface.createFromAsset(context.getAssets(),"font/din.ttf"); // 默认
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}