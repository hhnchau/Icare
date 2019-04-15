package ptt.vn.icaremobileapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.custom.MyButton;

public class Login extends AppCompatActivity implements MyButton.OnListener  {
    private EditText edtUserName, edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Set Full Window
        Window window = getWindow();
        if (window != null) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorMain));
            }
        }

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        MyButton btnOk = findViewById(R.id.btnOk);
        btnOk.setOnSelectedListener(this);
    }

    private void login() {
        ApiController.getInstance().login(this, null);
    }


    private void gotoDashboard() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick() {
        String u = edtUserName.getText().toString();
        String p = edtPassword.getText().toString();

        if (u.equals("admin") && p.equals("123")) {
            gotoDashboard();
        }else {
            Toast.makeText(this, "Sai ten dang nhap hoac mat khau", Toast.LENGTH_SHORT).show();
        }
    }
}