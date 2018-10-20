package com.app.restart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private int REQUESTCODE = 1;
    private int RESTART_PERIOD = 1000 * 60 * 2;  // set 2 min
    private AppCompatButton btnrestart;
    private String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnrestart=findViewById(R.id.btnrestart);
        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    restartApp(MainActivity.this);
            }
        });
    }

    @SuppressLint("NewApi")
    private void restartApp(Context cnt) {

        Intent intent = new Intent(cnt, MainActivity.class);

        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(cnt, REQUESTCODE, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        // Setup AlarmManager
        AlarmManager alarmManager = (AlarmManager) cnt.getSystemService(Context.ALARM_SERVICE);

        // set time 2 min
        if (alarmManager != null) {
            long trigger = System.currentTimeMillis() + RESTART_PERIOD;

            if (Build.VERSION.SDK_INT >= 23) {
                Log.e(TAG, "alarm manager call if ");
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC, trigger, pendingIntent);
            } else if (Build.VERSION.SDK_INT >= 19) {
                Log.e(TAG, "alarm manager call else if ");
                alarmManager.setExact(AlarmManager.RTC, trigger, pendingIntent);
            } else {
                Log.e(TAG, "alarm manager call else ");
                alarmManager.set(AlarmManager.RTC, trigger, pendingIntent);
            }

        }

        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG," call onStart() method ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG," call onRestart() method ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG," call onResume() method ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG," call onPause() method ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG," call onStop() method ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG," call onDestroy() method ");
    }

}
