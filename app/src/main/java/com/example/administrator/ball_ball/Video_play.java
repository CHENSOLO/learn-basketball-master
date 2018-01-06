package com.example.administrator.ball_ball;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

/**
 * Created by Administrator on 2017/11/23.
 */

public class Video_play extends AppCompatActivity {
    private VideoView videoView;
    private ProgressBar pb;

    @Override


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);

        pb = findViewById(R.id.pb);
        Intent intent = getIntent();
        String cc = getIntent().getStringExtra("cc");


        //隐藏状态栏
       // getSupportActionBar().hide();
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = Video_play.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
        videoView = findViewById(R.id.videoview);
//        try {
//            URL url = new URL("http://www.ccsolo.top/ball_video/"+intent.getStringExtra("cc")+".mp4");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        Log.i("LOG", Uri.parse("http://www.ccsolo.top/ball_video/" + intent.getStringExtra("cc") + ".mp4").toString());
        videoView.setVideoURI(Uri.parse("http://www.ccsolo.top/" + intent.getStringExtra("cc") + ".mp4"));
        //videoView.setVideoURI(Uri.parse("http://www.ccsolo.top/ball_two_video/" + intent.getStringExtra("cc") + ".mp4"));
        //videoView.setVideoURI(Uri.parse("http://www.ccsolo.top/ball_three_video/" + intent.getStringExtra("cc") + ".mp4"));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                pb.setVisibility(View.GONE);
            }
        });
        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        controller.setMediaPlayer(videoView);

    }
}
