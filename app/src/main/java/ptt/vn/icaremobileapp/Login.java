package ptt.vn.icaremobileapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.custom.Key;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyFloatingKeyboard;
import ptt.vn.icaremobileapp.model.account.AccountDomain;
import ptt.vn.icaremobileapp.storage.Storage;
import ptt.vn.icaremobileapp.tooltip.MyTooltip;

public class Login extends AppCompatActivity implements MyButton.OnListener {
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
        edtUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Key.getInstance(Login.this).hide();
            }
        });
        edtPassword = findViewById(R.id.edtPassword);
        MyButton btnOk = findViewById(R.id.btnOk);
        btnOk.setOnSelectedListener(this);
    }

    private void gotoDashboard() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick() {
        //String u = edtUserName.getText().toString();
        //String p = edtPassword.getText().toString();

        //login(u, p);

        //MyFloatingKeyboard.on(edtPassword).position(MyFloatingKeyboard.Position.TOP).show();
   Key.getInstance(this).show( edtPassword);
    }

    private void login(String u, String p) {
        ApiController.getInstance().login(this, u, p, new ACallback<AccountDomain>() {
            @Override
            public void response(List<AccountDomain> list) {
                if (list != null && list.size() > 0) {
                    Storage.getInstance(Login.this).setUserName(list.get(0).getUsername());
                    gotoDashboard();
                } else {
                    Toast.makeText(Login.this, getString(R.string.txt_login_fail), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}