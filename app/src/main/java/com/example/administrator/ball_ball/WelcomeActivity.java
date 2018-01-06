package com.example.administrator.ball_ball;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/12/31.
 */

public class WelcomeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                return false;
            }
        }).sendEmptyMessageDelayed(0,2000);
    }
}
//        new Thread(this).start();
//    }
//
//    @Override
//    public void run() {
//        try {
//            Thread.sleep(2000);//延迟两秒
//            SharedPreferences preferences = getSharedPreferences("count", 0);//存在就打开否者创建新的prefernce
//            int count = preferences.getInt("count", 0);
//            //如果用户不是第一次使用直接进入页面否则跳转到欢迎页面
//            if ( count == 0 ) {
//                setContentView(R.layout.activity_main);
//
//            } else {
//                Intent intent = new Intent();
//                intent.setClass(WelcomeActivity.this, MainActivity.class);
//                startActivity(intent);
//
//            }
//            finish();
//            //s实例化Editor对象
//            SharedPreferences.Editor editor = preferences.edit();
//            //存入数据
//            editor.putInt("count", 1);
//            //提交修改
//            editor.commit();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//    ////延时跳转使用Handler
////    @Override
////    protected void onCreate(@Nullable Bundle savedInstanceState) {
////
////        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
////        super.onCreate(savedInstanceState);
//////      根据版本号判断是不是第一次使用
////        PackageInfo info = null;
////
////        try {
////            info=getPackageManager().getPackageInfo(getPackageName(),0);
////        } catch (PackageManager.NameNotFoundException e) {
////            e.printStackTrace();
////        }
////              int currentVersion = info.versionCode;
////        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
////        int lastVersion = sp.getInt("VERSION_KEY",0);
////
////        if ( currentVersion>lastVersion ){
////            //第一次启动将当前版本进行存储
////            sp.edit().putInt("VERSION_KEY",currentVersion).commit();
////            setContentView(R.layout.welcome);
////        }else {
////            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
////            startActivity(intent);
////        }
////
////
////        new Handler(new Handler.Callback() {
////            @Override
////            public boolean handleMessage(Message message) {
////               startActivity(new Intent(getApplicationContext(),MainActivity.class));
////                return false;
////            }
////        }).sendEmptyMessageDelayed(0,3000);//
////    }
//
//
////}
