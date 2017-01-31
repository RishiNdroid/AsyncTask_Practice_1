package com.example.rndroid.asynctask_practice_1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonStart;
    ProgressBar progressBar;
    EditText editText;
    int s, status;
    public class MyTask extends AsyncTask<Integer, Integer, Float>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Inside onPreExecute", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Float doInBackground(Integer... integers) {
            float sum = 0;
            s = integers[0];
            for(int i =1; i<=s; i++){
                sum = sum + i;
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return sum;// this will go to below postExecute methode
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            progressBar.setMax(s);
//            progressBar.setProgress(values[0]);
            ProgressBarDialog progressBarDialog = new ProgressBarDialog();
            progressBarDialog.getProgress(values[0]);
            }

        @Override
        protected void onPostExecute(Float aFloat) {
            Toast.makeText(MainActivity.this, "Sum = "+aFloat, Toast.LENGTH_SHORT).show();
            super.onPostExecute(aFloat);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void clickStart(View v){
        MyTask m = new MyTask();
        m.execute(Integer.valueOf(editText.getText().toString()));
        ProgressBarDialog progressBarDialog = new ProgressBarDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("max", Integer.valueOf(editText.getText().toString()));
        progressBarDialog.setArguments(bundle);
        progressBarDialog.show(getSupportFragmentManager(),null);
    }
}