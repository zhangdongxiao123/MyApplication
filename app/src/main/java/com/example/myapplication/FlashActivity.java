package com.example.myapplication;

import android.app.backup.FullBackupDataOutput;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FlashActivity extends AppCompatActivity {
    Handler handler=new Handler();
    private boolean isFirstUse;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        final SharedPreferences preferences=getSharedPreferences("loginStatus",MODE_PRIVATE);
        isFirstUse=preferences.getBoolean("isFirstUse",true);
        handler.postDelayed(new Runnable() {
            public void run() {
                SharedPreferences loginStatus=getSharedPreferences("loginStatus",MODE_PRIVATE);
                SharedPreferences.Editor editor=loginStatus.edit();
                Intent intent = new Intent();
                if(isFirstUse)
                {
                    intent.setClass(FlashActivity.this, GuideActivity.class);
                    editor.putBoolean("isFirstUse",false);
                }
                else{
                    intent.setClass(FlashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
                editor.commit();
            }
        },3000);
    }
}
