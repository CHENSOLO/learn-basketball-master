package com.example.administrator.ball_ball;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ChenListFragment chenListFragment;
    private HomeListFragment homeListFragment;
    private VideoListFragment videoListFragment;
    private FragmentManager fragmentManager;
    private ArrayList<Data> ps_index_item = null;
    private ArrayList<Data> ps_video_item = null;
    private ArrayList<Data> ps_chen_item = null;

    private OkHttpClient mOkHttpClient;
    private String ps_item = "";
    FragmentTransaction fragmentTransaction;
    private long exitTime = 0;
    private long firstTime;



    private Toast mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    hideFragment(fragmentTransaction);
                    homeListFragment = new HomeListFragment(fragmentManager, ps_index_item);
                    fragmentTransaction.replace(R.id.content, homeListFragment);
                    fragmentTransaction.commit();
//                    if ( homeListFragment== null ){

//                        homeListFragment = new HomeListFragment(fragmentManager, ps_index_item);
//                    }
//                    if ( !homeListFragment.isAdded() ){

//                        fragmentTransaction.add(R.id.content,homeListFragment);
//                    }else {
//                        if ( videoListFragment!=null ){
//                            fragmentTransaction.hide(videoListFragment);
//                        }
//                        fragmentTransaction.show(homeListFragment);
//                    }
//                    break;

                  return true;

                case R.id.navigation_dashboard:
                    hideFragment(fragmentTransaction);
                    videoListFragment = new VideoListFragment(fragmentManager, ps_video_item);
                    fragmentTransaction.replace(R.id.content, videoListFragment);
                    fragmentTransaction.commit();
                    return true;

//                    if ( videoListFragment == null ){
//                        videoListFragment = new VideoListFragment(fragmentManager, ps_video_item);
//
//                    }
//                    if ( !videoListFragment.isAdded() ){
//                        fragmentTransaction.add(R.id.content,videoListFragment);
//                    }else {
//                        if (chenListFragment!=null){
//                            fragmentTransaction.hide(chenListFragment);
//                        }
//                        fragmentTransaction.show(videoListFragment);
//                    }
//       break;
                case R.id.navigation_notifications:
                    chenListFragment = new ChenListFragment(fragmentManager, ps_chen_item);
                    fragmentTransaction.replace(R.id.content, chenListFragment);
                    fragmentTransaction.commit();
                    return true;
//                      if ( chenListFragment==null){
//                          chenListFragment = new ChenListFragment(fragmentManager, ps_chen_item);
//                      }
//                      if ( !chenListFragment.isAdded() ){
//                          fragmentTransaction.add(R.id.content,chenListFragment);
//                      }else {
//                          if ( homeListFragment!=null ){
//                              fragmentTransaction.hide(homeListFragment);
//                          }
//                          fragmentTransaction.show(chenListFragment);
//                      }
//                      break;
//
//                      default:
//                        break;
//                    hideFragment(fragmentTransaction);

            }
            return false;
        }

    };


    /**
     * 去掉隐藏所有的Fragment
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if ( homeListFragment!=null ){
            fragmentTransaction.hide(homeListFragment);
        }
        if ( videoListFragment!=null){
            fragmentTransaction.hide(videoListFragment);
        }
        if ( chenListFragment != null ) {

            fragmentTransaction.hide(chenListFragment);
        }

    }


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        ps_index_item = new ArrayList<Data>();
        ps_video_item = new ArrayList<Data>();
        ps_chen_item = new ArrayList<Data>();
        fragmentTransaction = fragmentManager.beginTransaction();

        getAsynHttp("ball_index.php");
        getAsynHttp("try_index.php");
        getAsynHttp("crazy_index.php");

//        homeListFragment = new HomeListFragment(fragmentManager, ps_index_item);
//        fragmentTransaction.replace(R.id.content, homeListFragment);
//        fragmentTransaction.commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }


    private void getAsynHttp(final String url) {
        mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://www.ccsolo.top/" + url);
        final Request request = requestBuilder.build();
        Call mcall = mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ps_item = response.body().string();
//                ps_two_item =response.body().string();
//                ps_three_item = response.body().string();
                try {
                    JSONObject jsonObject1 = new JSONObject(ps_item);
                    JSONArray jsonArray = jsonObject1.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        Data data = new Data();
                        data.setIcon(jsonObject.getString("icon"));
                        data.setTitle(jsonObject.getString("title"));
                        data.setTags(jsonObject.getString("tags"));
//                        data.setTime(jsonObject.getString("time"));
                        if ( url == "ball_index.php" ) {
                            ps_index_item.add(data);
                        } else if ( url == "try_index.php" ) {
                            ps_video_item.add(data);
                        } else {
                            ps_chen_item.add(data);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }



    public void onBackPressed() {
        //1.点击的时间差如果大于2000，则提示用户点击两次退出
        if ( System.currentTimeMillis() - exitTime < 1000 ) {
            //保存当前时间
//            exitTime = System.currentTimeMillis();
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT)
                    .show();

        } else {
            exitTime = System.currentTimeMillis();

            //点击时间小于2000，调用父类onBackPressed
            super.onBackPressed();
        }

    }

//    @Override
//    protected void onResume() {
//        onCreate(null);
//        super.onResume();
//    }
}