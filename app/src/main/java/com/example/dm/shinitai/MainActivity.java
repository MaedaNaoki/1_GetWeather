package com.example.dm.shinitai;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        Button btn5 = (Button) findViewById(R.id.button5);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }


    private void onClickButton1() {
        Intent intent = new Intent(MainActivity.this,ViewWeather.class);
        intent.putExtra("CITY","東京");
        startActivity(intent);
    }

    private void onClickButton2() {
        Intent intent = new Intent(MainActivity.this,ViewWeather.class);
        intent.putExtra("CITY","仙台");
        startActivity(intent);
    }

    private void onClickButton3() {
        Intent intent = new Intent(MainActivity.this,ViewWeather.class);
        intent.putExtra("CITY","札幌");
        startActivity(intent);
    }

    private void onClickButton4() {
        Intent intent = new Intent(MainActivity.this,ViewWeather.class);
        intent.putExtra("CITY","大阪");
        startActivity(intent);
    }

    private void onClickButton5() {
        Intent intent = new Intent(MainActivity.this,ViewWeather.class);
        intent.putExtra("CITY","鹿児島");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                onClickButton1();
                break;

            case R.id.button2:
                onClickButton2();
                break;

            case R.id.button3:
                onClickButton3();
                break;

            case R.id.button4:
                onClickButton4();
                break;

            case R.id.button5:
                onClickButton5();
                break;
        }
    }
}
