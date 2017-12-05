package org.scorelab.soundcom;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        new DelayTask().execute(); // This will delay the spalsh scrren and redierct to the login/register screen based
        //on the ststus of the user.

    }

    class DelayTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(4000);
            }
            catch (Exception e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

                Intent i =new Intent(splash.this, MainActivity.class);
                startActivity(i);




        }
    }
}
