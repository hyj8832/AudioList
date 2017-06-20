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
      Button butPlay,butStop,butPause;
      TextView textMusic;
      ProgressBar progress;
      String [] musics={"music","oldie","never"};
      int[] musicResIds={R.raw.music,R.raw.oldie,R.raw.birdbrainz};
      int selectedMusicID;
      MediaPlayer mediaPlayer;
    boolean selectedPauseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.list_music);
        butPlay=(Button)findViewById(R.id.but_play);
        butStop=(Button)findViewById(R.id.but_stop);
        butPause=(Button)findViewById(R.id.but_pause);
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
                mediaPlayer.stop();
                selectedMusicID=musicResIds[i];
                mediaPlayer=MediaPlayer.create(MainActivity.this,selectedMusicID); //이 거 때문에 이젠 일지정지 후 다른 곡 실행 가능!
                progress.setVisibility(View.INVISIBLE);
            }
        });

        butPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectedPauseButton) {

                        mediaPlayer.start();
                        selectedPauseButton=false;//원래대로 초기화
                    }
                    else {
                        mediaPlayer = MediaPlayer.create(MainActivity.this, selectedMusicID);
                        mediaPlayer.start();
                    }
                    progress.setVisibility(View.VISIBLE);
                }
            });
        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                progress.setVisibility(View.INVISIBLE);
            }
        });

        butPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPauseButton=true;
                mediaPlayer.pause();
                progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onStop() { //액티비티가 다른 화면에 가려졌을 때
        super.onStop();
        mediaPlayer.stop();
    }
}
