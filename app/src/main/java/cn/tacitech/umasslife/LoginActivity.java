package cn.tacitech.umasslife;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fivehundredpx.android.blur.BlurringView;

import org.w3c.dom.Text;

import cn.tacitech.umasslife.Modules.UiModule;

public class LoginActivity extends AppCompatActivity {

    private TextView login_netid_text;
    private TextView login_key_text;

    private RelativeLayout login_netid_field;
    private RelativeLayout login_key_field;
    private RelativeLayout login_test_info;

    private EditText login_netid_input;
    private EditText login_key_input;

    private CardView login_btn;

    private BlurringView mBlurringView;

    private String test_netid = "dzhong";
    private String test_password = "dzhong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // UiModule
        UiModule uiModule = new UiModule(this);
        uiModule.setStatusBar(UiModule.LIGHT_STATUSBAR);

        LinearLayout login_basic_layout = findViewById(R.id.login_basic_layout);
        mBlurringView = findViewById(R.id.blurring_view);
        mBlurringView.setBlurredView(login_basic_layout);

        // Login Netid Text
        login_netid_text = findViewById(R.id.login_netid_text);
        login_netid_text.setText("Your NetID");
        login_netid_text.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体
        // Login Key Text
        login_key_text = findViewById(R.id.login_key_text);
        login_key_text.setText("Your Password");
        login_key_text.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体
        // Login Btn Text
        TextView login_btn_text = findViewById(R.id.login_btn_text);
        login_btn_text.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体

        // Fields
        login_netid_field = findViewById(R.id.login_netid_field);
        login_key_field = findViewById(R.id.login_key_field);
        login_test_info = findViewById(R.id.login_test_info);

        // Inputs
        login_netid_input = findViewById(R.id.login_netid_input);
        login_netid_input.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体
        login_key_input = findViewById(R.id.login_key_input);
        login_key_input.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体

        // Login Netid CardView
        CardView login_netid = findViewById(R.id.login_netid);
        login_netid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_netid_field.setVisibility(View.VISIBLE);
                if(!login_netid_input.isFocused()) login_netid_input.requestFocus();
                showSoftKeyboard(login_netid_input);
                mBlurringView.setVisibility(View.VISIBLE);
                mBlurringView.invalidate();
            }
        });

        // Login Key CardView
        CardView login_key = findViewById(R.id.login_key);
        login_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_key_field.setVisibility(View.VISIBLE);
                if(!login_key_input.isFocused()) login_key_input.requestFocus();
                showSoftKeyboard(login_key_input);
                mBlurringView.setVisibility(View.VISIBLE);
                mBlurringView.invalidate();
            }
        });

        // Fields Bg
        RelativeLayout login_netid_field_bg = findViewById(R.id.login_netid_field_bg);
        login_netid_field_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAllField();
            }
        });
        RelativeLayout login_key_field_bg = findViewById(R.id.login_key_field_bg);
        login_key_field_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAllField();
            }
        });
        RelativeLayout login_test_info_bg = findViewById(R.id.login_test_info_bg);
        login_test_info_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAllField();
            }
        });

        // Login Btn
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_new = login_netid_input.getText().toString();
                String password_new = login_key_input.getText().toString();
                loginRequest(username_new, password_new);
            }
        });

        // Testing Information
        TextView login_test_info_title = findViewById(R.id.login_test_info_title);
        login_test_info_title.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体
        TextView login_test_info_content = findViewById(R.id.login_test_info_content);
        login_test_info_content.setTypeface(UiModule.getTypeface(this, UiModule.DIN)); // 设置字体
        login_test_info_content.setText("Testing NetID: dzhong\n" +
                "Testing Password: dzhong\n\n---\n\n" +
                "Time is so limited that we cannot finish everything for this app.\n" +
                "If you all like it and want it, we will bring it to the real life for sure!\n" +
                "We are now trying to gain the API of campus system to make it further!\n" +
                "Again, thanks for your testing and support~"
        );


    }

    public void loginRequest(String netid, String psw){
        if(netid.equals(test_netid) && psw.equals(test_password)){
            finish();
        } else {
            login_btn.setCardBackgroundColor(getResources().getColor(R.color.used_red));
        }
    }

    public void closeAllField(){
        updateFromField();
        closeSoftKeybord(login_netid_input);
        closeSoftKeybord(login_key_input);
        mBlurringView.setVisibility(View.GONE);
        login_test_info.setVisibility(View.GONE);
        login_netid_field.setVisibility(View.GONE);
        login_key_field.setVisibility(View.GONE);
    }

    public void updateFromField(){
        String username_new = login_netid_input.getText().toString();
        String password_new = login_key_input.getText().toString();
        if(!username_new.equals("")){
            login_netid_text.setText(username_new);
            login_netid_text.setTextColor(getResources().getColor(R.color.text_mainBlack));
        } else {
            login_netid_text.setText("Your NetID");
            login_netid_text.setTextColor(getResources().getColor(R.color.text_secondBlack));
        }
        if(!password_new.equals("")){
            String textOutput = "";
            for(int i = 0; i < password_new.length(); i++){
                textOutput += "•";
            }
            login_key_text.setText(textOutput);
            login_key_text.setTextColor(getResources().getColor(R.color.text_mainBlack));
        } else {
            login_key_text.setText("Your Password");
            login_key_text.setTextColor(getResources().getColor(R.color.text_secondBlack));
        }
    }

    public void showSoftKeyboard(View view){
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void closeSoftKeybord(EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
