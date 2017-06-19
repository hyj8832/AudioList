package com.example.admin.audiolist;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
      ListView list;
      Button butPlay,butStop;
      TextView textMusic;
      ProgressBar progress;
      String [] musics={"music","oldie","birdbrainz"};
      int[] musicResIds={R.raw.music,R.raw.oldie,R.raw.birdbrainz};
      int selectedMusicID;
      MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.list_music);
        butPlay=(Button)findViewById(R.id.but_play);
        butStop=(Button)findViewById(R.id.but_stop);
        textMusic=(TextView)findViewById(R.id.text_music);
        progress=(ProgressBar)findViewById(R.id.progress_music);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,musics); //한개만 선택할 수 있는 (노래를)
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//실제로 선택이 한 개만 되도록
        list.setItemChecked(0,true);//선택한 인덱스 번호가 실행이 ...어쩌고
        selectedMusicID=musicResIds[0]; //첫번째 노래 실행(기본적으로 )
        mediaPlayer=MediaPlayer.create(this,selectedMusicID);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {//항목이 클릭되었을 때 실행되는 OnItemClickListener
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //선택된 항목의 인덱스 번호 i
                selectedMusicID=musicResIds[i];
            }
        });

        butPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mediaPlayer.start();
                }
            });
        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.create(MainActivity.this,selectedMusicID);
                mediaPlayer.stop();
            }
        });
    }

    @Override
    protected void onStop() { //액티비티가 다른 화면에 가려졌을 때
        super.onStop();
        mediaPlayer.stop();
    }
}
