package com.example.wjdev02.storage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wjdev02.firstandroid.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by wjdev02 on 15/12/22.
 */
public class StorageFile extends AppCompatActivity {

    private static String fileName = "SaveFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_file_layout);
        setTitle(getResources().getString(getIntent().getIntExtra(StorageActivity.TITLE_S, 0)));

        Button button_s = (Button)findViewById(R.id.storage_file_b_save);
        Button button_r = (Button)findViewById(R.id.storage_file_b_read);

        button_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.storage_file_edit);
                save(editText.getText().toString());
                Toast.makeText(StorageFile.this,"Save Success",Toast.LENGTH_SHORT).show();
            }
        });

        button_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)findViewById(R.id.storage_file_text);
                String string = read();
                if (!TextUtils.isEmpty(string)){
                    textView.setText(string);
                    Toast.makeText(StorageFile.this,"Read Success",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String read(){
        FileInputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try{
            inputStream = openFileInput(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public void save(String input){
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try{
            outputStream = openFileOutput(fileName,Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(input);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (writer != null)
                    writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
