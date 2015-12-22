package com.example.wjdev02.storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wjdev02.firstandroid.R;

/**
 * Created by wjdev02 on 15/12/22.
 */
public class StorageShare extends AppCompatActivity {

    private String Remeber_pass = "Remeber_password";
    private String Account_s = "account";
    private String Password_s = "password";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText textAccount;
    private EditText textPassword;
    private Button buttonLogin;
    private CheckBox remeberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_sharep_layout);
        setTitle(getResources().getString(R.string.storage_sharedPreference));

        textAccount = (EditText)findViewById(R.id.storage_share_account_edit);
        textPassword = (EditText)findViewById(R.id.storage_share_password_edit);
        buttonLogin = (Button)findViewById(R.id.storage_share_login);
        remeberPass = (CheckBox)findViewById(R.id.storage_share_check);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemeber = preferences.getBoolean(Remeber_pass,false);
        if (isRemeber){
            String account = preferences.getString(Account_s,"");
            String password = preferences.getString(Password_s,"");
            textAccount.setText(account);
            textPassword.setText(password);
            remeberPass.setChecked(true);
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = textAccount.getText().toString();
                String password = textPassword.getText().toString();
                if (account.equals("admin")&&password.equals("123456")){
                    editor = preferences.edit();
                    if (remeberPass.isChecked()){
                        editor.putBoolean(Remeber_pass,true);
                        editor.putString(Account_s,account);
                        editor.putString(Password_s,password);
                    }else {
                        editor.clear();
                    }
                    editor.commit();
                    Toast.makeText(StorageShare.this,"save success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(StorageShare.this,"Account or password is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
