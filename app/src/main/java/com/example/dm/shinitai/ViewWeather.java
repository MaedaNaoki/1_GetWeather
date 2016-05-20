package com.example.dm.shinitai;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DM 前田 尚暉 on 2016/05/12.
 */
public class ViewWeather extends Activity {

    String cityName;

    List<String> list = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Bundle extras = getIntent().getExtras();
        cityName = extras.getString("CITY");
        getHtml();
        dispView(returnWeather());
    }

    public void getHtml() {
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
            String TagetUrl = "http://weather.yahoo.co.jp/weather/";//URLを変数へ
            String strLine;
            URL url = new URL(TagetUrl);
            Object content = url.getContent();
            if (content instanceof InputStream) {
                BufferedReader bf = new BufferedReader(new InputStreamReader
                        ((InputStream) content, "utf-8"));//文字コード

                int count = 0;
                while ((strLine = bf.readLine()) != null) {
                    Log.d("ログ", strLine);
                    if(count > 180) { //前半の180行程度はいらないので省く
                        list.add(strLine);
                    }
                    count = count + 1;
                }
            } else {
                System.out.println(content.toString());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("引数にURLを指定してください");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }

    public String returnWeather() {
        for(int i =0; i< list.size(); i++) {
            if(list.get(i).matches(".*" + cityName + ".*")) {
                //部分一致
                //天気の部分を切り出し
                String cut = list.get(i+2).substring(0,list.get(i+2).indexOf("</span>"));
                cut = cut.substring(cut.indexOf(">")+1);
                return cut;

            }else {
                //部分一致していない
            }
        }
        return "No Data";
    }
    public void dispView(String weather) {
        TextView text = (TextView)findViewById(R.id.textView);
        text.setText(cityName + " の現在の天気");

        TextView text2 = (TextView)findViewById(R.id.textView2);
        text2.setText(weather);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        Uri uri = null;
        switch (cityName) {
            case "東京":
                //東京電力
                uri = Uri.parse("http://thunder.tepco.co.jp/wdata/1/42_saishin.gif");
            case "仙台":
                uri = Uri.parse("http://thunder.tepco.co.jp/wdata/1/42_saishin.gif");
            case "札幌":
                uri = Uri.parse("http://thunder.tepco.co.jp/wdata/1/21_saishin.gif");
            case "大阪":
                uri = Uri.parse("http://thunder.tepco.co.jp/wdata/1/21_saishin.gif");
            case "鹿児島":
                uri = Uri.parse("http://thunder.tepco.co.jp/wdata/1/21_saishin.gif");
        }
        //画像取得
        Uri.Builder builder = uri.buildUpon();
        AsyncTaskHttpRequest task = new AsyncTaskHttpRequest(imageView);
        task.execute(builder);
    }
}
