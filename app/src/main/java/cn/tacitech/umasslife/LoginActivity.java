package cn.tacitech.umasslife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button logInbutton;
    private Button registerButton;
    private EditText inputSpireID;
    private EditText inputPassword;
    String id = "";
    String pass = "";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logInbutton = (Button) findViewById(R.id.logInButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        inputSpireID = (EditText) findViewById(R.id.inputID);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
    }

    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.logInButton:
                id = inputSpireID.getText().toString();
                pass = inputPassword.getText().toString();
                break;
            case R.id.registerButton:
                break;
            default:
                break;
        }
    }
}
