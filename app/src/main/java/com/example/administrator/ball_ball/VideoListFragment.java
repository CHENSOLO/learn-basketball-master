package com.example.administrator.ball_ball;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/13.
 */

@SuppressLint("ValidFragment")
public class VideoListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private FragmentManager fManager;
    private ArrayList<Data> datas;
    private ListView list_news;

   private  ArrayList<Data> ps_fun_item = null; //声明添加数据
   private OkHttpClient mOkHttpClien;  //联网
   private String  fun_item=""; //声明解析
    FragmentTransaction fragmentTransaction;

    public VideoListFragment(FragmentManager fManager, ArrayList<Data> datas) {
        this.fManager = fManager;
        this.datas = datas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_newlist, container, false);
        list_news = (ListView) view.findViewById(R.id.list_news);

        ps_fun_item = new ArrayList<Data>();

        MyAdapter myAdapter = new MyAdapter(datas, getActivity());

        list_news.setAdapter(myAdapter);
        list_news.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        getAsynHttp(datas.get(position).getTags());

    }

    private void getAsynHttp( final String url) {

        mOkHttpClien = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://ccsolo.top/ball_two_item.php?ball_two_item= " + url);
        Request request = requestBuilder.build();
        Call mcall = mOkHttpClien.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                   fun_item = response.body().string();//解析数据
                try {
                    JSONObject jsonObject1 = new JSONObject(fun_item);
                    JSONArray jsonArray = jsonObject1.getJSONArray("data");

                    for (int i = 0; i <jsonArray.length() ; i++) {

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        Data data = new Data();
                        data.setTime(jsonObject.getString("time"));
                        data.setIcon(jsonObject.getString("icon"));
                        data.setTitle(jsonObject.getString("title"));
                        data.setTags(jsonObject.getString("tags"));
                        ps_fun_item.add(data);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                FragmentTransaction fTransaction =  fManager.beginTransaction();
                VideoplayFragment ncFragment = new VideoplayFragment(fManager,ps_fun_item);
                fTransaction.replace(R.id.content,ncFragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

    }
}