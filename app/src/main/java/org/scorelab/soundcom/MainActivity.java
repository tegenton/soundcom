package org.scorelab.soundcom;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder myAudioRec;
    ImageButton play,stop, record;
    private String outFile=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=(ImageButton)findViewById(R.id.imageButtonPlay);
        stop=(ImageButton)findViewById(R.id.imageButtonStop);
        record=(ImageButton)findViewById(R.id.imageButtonStart);

        stop.setEnabled(false);
        play.setEnabled(false);
        outFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.mp4";

        myAudioRec = new MediaRecorder();
        myAudioRec.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRec.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRec.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRec.setOutputFile(outFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    myAudioRec.prepare();
                    myAudioRec.start();
                } catch(IllegalStateException e){
                    //TODO run catching process
                    e.printStackTrace();
                } catch (IOException e) {
                    //TODO run catching process
                    e.printStackTrace();
                }
                record.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();

                }
            });
        //stop activity
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myAudioRec.stop();
                myAudioRec.release();
                myAudioRec=null;

                stop.setEnabled(false);
                play.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_LONG).show();
            }
        });
        //play activity
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) throws IllegalArgumentException, SecurityException, IllegalStateException {
                MediaPlayer m = new MediaPlayer();

                try {
                    m.setDataSource(outFile);
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    m.prepare();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                m.start();
                Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
            }
        });
    }
    //not work
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
