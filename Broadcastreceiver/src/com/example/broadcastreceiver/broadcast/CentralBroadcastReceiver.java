package com.example.broadcastreceiver.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class CentralBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent it) {
		if (it != null){
			if (it.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
				Toast.makeText(context, "O Celular acabou de ligar", Toast.LENGTH_LONG).show();
			}else if (it.getAction().equals("ciandt.personalizei.um.broadcast")){
				Toast.makeText(context, "Broadcast personalizado acionado!", Toast.LENGTH_LONG).show();
			}else if(it.getAction().equals("ciandt.broadcast.agendado")){
				Toast.makeText(context, "Broadcast agendado recuperado!", Toast.LENGTH_LONG).show();
				Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			    vibrator.vibrate(2000);
			}
		}
	}

}
