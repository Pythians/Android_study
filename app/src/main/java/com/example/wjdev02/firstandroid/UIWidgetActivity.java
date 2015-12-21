package com.example.wjdev02.firstandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class UIWidgetActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonE;
    private Button buttonI;
    private Button buttonP;
    private Button buttonA;
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiwidget);
        setTitle("Widget");

        buttonE = (Button)findViewById(R.id.button_edit);
        buttonI = (Button)findViewById(R.id.button_image);
        buttonP = (Button)findViewById(R.id.button_progress);
        buttonA = (Button)findViewById(R.id.button_alert);

        editText = (EditText)findViewById(R.id.edit_view);
        imageView = (ImageView)findViewById(R.id.image_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);


        buttonE.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonP.setOnClickListener(this);
        buttonA.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_edit:
                String input = editText.getText().toString();
                Toast.makeText(this,input,Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_image:
                imageView.setImageResource(R.drawable.setting);
                break;
            case R.id.button_progress:
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                }
//                int progress = progressBar.getProgress();
//                progress += 10;
//                progressBar.setProgress(progress);
                break;
            case R.id.button_alert:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("This is a Dialog");
                dialog.setMessage("Something important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            default:
                break;
        }
    }
}
