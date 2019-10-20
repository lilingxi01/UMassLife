package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.tacitech.umasslife.Fragments.ExplorationFragment;
import cn.tacitech.umasslife.Fragments.FrontPageFragment;
import cn.tacitech.umasslife.Fragments.PersonalFragment;
import cn.tacitech.umasslife.Modules.NavigationModule;
import cn.tacitech.umasslife.Modules.UiModule;

public class MainActivity extends AppCompatActivity {

    private UiModule uiModule;

    private NavigationModule navigationModule_home;
    private NavigationModule navigationModule_discover;
    private NavigationModule navigationModule_user;

    private FragmentManager fragmentManager;
    private final int FRAGMENT_CONTENT = R.id.mainActivity_Fragment;

    private FrontPageFragment frontPageFragment;
    private ExplorationFragment explorationFragment;
    private PersonalFragment personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * MainActivity
         *      此页面用于存放主页面Fragment的Activity
         *      需要进行服务器交互运算的东西尽量保留在主Activity进行，不要留到Fragment内，不然容易导致切换页面的卡顿与崩溃
         *      需要在Fragment进行调用的东西可以写成方法的形式存在于主Activity中
         */

        // UiModule
        uiModule = new UiModule(this);
        uiModule.setStatusBar(UiModule.LIGHT_STATUSBAR);

        // NavigationModule 初始化
        navigationModule_home = new NavigationModule(this,
                (ImageView) findViewById(R.id.MainActivity_NavigationBar_1_img),
                R.mipmap.frontpage_main, R.mipmap.frontpage_sub,
                (TextView) findViewById(R.id.MainActivity_NavigationBar_1_text));
        navigationModule_discover = new NavigationModule(this,
                (ImageView) findViewById(R.id.MainActivity_NavigationBar_2_img),
                R.mipmap.exploration_main, R.mipmap.exploration_sub,
                (TextView) findViewById(R.id.MainActivity_NavigationBar_2_text));
        navigationModule_user = new NavigationModule(this,
                (ImageView) findViewById(R.id.MainActivity_NavigationBar_3_img),
                R.mipmap.personal_main, R.mipmap.personal_sub,
                (TextView) findViewById(R.id.MainActivity_NavigationBar_3_text));

        // FragmentManager 初始化
        fragmentManager = getSupportFragmentManager();
        initializeFragments();

        // TODO 优化导航栏字体颜色与图片相符

        // NavigationBar Button Assignment
        RelativeLayout mainAcitivity_NavigationBar_1_layout =
                findViewById(R.id.MainActivity_NavigationBar_1_layout);
        RelativeLayout mainAcitivity_NavigationBar_2_layout =
                findViewById(R.id.MainActivity_NavigationBar_2_layout);
        RelativeLayout mainAcitivity_NavigationBar_3_layout =
                findViewById(R.id.MainActivity_NavigationBar_3_layout);
        mainAcitivity_NavigationBar_1_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToFrontPage();
            }
        });
        mainAcitivity_NavigationBar_2_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToExploration();
            }
        });
        mainAcitivity_NavigationBar_3_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToPersonal();
            }
        });

    }

    /**
     * 该方法在activity启动时进行调用
     * 预先完成FragmentManager的配置与加载
     * 重要：此方法必须在NavigationModule后声明，因为该方法要调用NavigationModule的setStatus()方法
     */
    public void initializeFragments(){
        frontPageFragment = new FrontPageFragment();
        explorationFragment = new ExplorationFragment();
        personalFragment = new PersonalFragment();

        // ** 重要：每次进行页面切换都需要声明一遍FragmentTransaction

        // 具体需不需要进行add的预加载还有待测试
        // fragmentTransaction.add(FRAGMENT_CONTENT, homePage);
        // fragmentTransaction.add(FRAGMENT_CONTENT, discoverPage);
        // fragmentTransaction.add(FRAGMENT_CONTENT, userPage);

        // 通过replace方法进行页面切换
        // TODO 在Fragments的class中重写onPause()和onResume()方法以便切出与切入时的动画及信息调用
        fragmentToFrontPage();
    }

    /**
     * 目前不确定使用replace方法进行页面切换是否节约内存资源，可以考虑继续使用show和hide进行页面切换调用
     */

    // 该方法用于切换Fragment至homePage
    public void fragmentToFrontPage(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(FRAGMENT_CONTENT, frontPageFragment);
        fragmentTransaction.commit();
        navigationModule_home.setStatus(NavigationModule.STATUS_MAIN);
        navigationModule_discover.setStatus(NavigationModule.STATUS_SUB);
        navigationModule_user.setStatus(NavigationModule.STATUS_SUB);
    }

    // 该方法用于切换Fragment至discoverPage
    public void fragmentToExploration(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(FRAGMENT_CONTENT, explorationFragment);
        fragmentTransaction.commit();
        navigationModule_home.setStatus(NavigationModule.STATUS_SUB);
        navigationModule_discover.setStatus(NavigationModule.STATUS_MAIN);
        navigationModule_user.setStatus(NavigationModule.STATUS_SUB);
    }

    // 该方法用于切换Fragment至userPage
    public void fragmentToPersonal(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(FRAGMENT_CONTENT, personalFragment);
        fragmentTransaction.commit();
        navigationModule_home.setStatus(NavigationModule.STATUS_SUB);
        navigationModule_discover.setStatus(NavigationModule.STATUS_SUB);
        navigationModule_user.setStatus(NavigationModule.STATUS_MAIN);
    }
}
