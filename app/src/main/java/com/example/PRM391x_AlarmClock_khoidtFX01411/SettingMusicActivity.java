package com.example.myapplication3.PRM391x_AlarmClock_khoidtFX01411;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.R;

public class SettingMusicActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    MediaPlayer mp;
    public String[] listNameFile = {"Người Phán Xử","Giấc Mơ Muộn Màng","Người Phản Bội","Chờ Người Nơi Ấy"};
    public int[] resId = {R.raw.song1,R.raw.song2,R.raw.song3,R.raw.song4,R.raw.song5};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_music);
        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Alarm");

        TextView textview_list = (TextView) findViewById(R.id.textview_list);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, R.id.textview_list,listNameFile);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int i, long id) {

              //  System.out.println("=====================" + resId[i]);


//                Intent intent = new Intent(SettingMusicActivity.this, Service.class);
//                intent.putExtra("keyIDSong",resId[i]);
//                SettingMusicActivity.this.startActivity(intent);

//
//                Log.e("==================== ++++",""+resId[i]);

                Toast.makeText(getApplicationContext(),"Bạn chọn chuông báo " + listNameFile[i],Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
