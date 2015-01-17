package com.example.broadcastreceiver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnSend = (Button) findViewById(R.id.send);
        btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent it = new Intent();
				it.setAction("ciandt.personalizei.um.broadcast");
				sendBroadcast(it);
			}
		});
        
        Button btnScheduler = (Button) findViewById(R.id.scheduler);
        btnScheduler.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent it = new Intent();
				it.setAction("ciandt.broadcast.agendado");
				PendingIntent pendingIntent = PendingIntent.getBroadcast(view.getContext(), 98767, it, 0);
				
				AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000, pendingIntent);
				
				Toast.makeText(view.getContext(), "Broadcast agendado para daqui 1 minuto!", Toast.LENGTH_LONG).show();
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
