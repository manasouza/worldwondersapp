package com.curso.android.ciant.worldwondersapp.service;

import com.curso.android.ciant.worldwondersapp.infrastructure.Constants;
import com.curso.android.ciant.worldwondersapp.infrastructure.NetworkUtil;
import com.curso.android.ciant.worldwondersapp.service.syncer.PlaceSyncer;
import com.curso.android.ciant.worldwondersapp.service.syncer.Syncer;
import com.example.worldwondersapp.R;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

public class SyncService extends Service{

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Syncer syncer;
		
		int command = intent.getIntExtra(Constants.Service.Tag.COMMAND, 0);
		
		// Test the connection status
        if (!NetworkUtil.isConnectionAvailable(this)) {
        	
        	ResultReceiver receiver = intent.getParcelableExtra(Constants.Service.Tag.RESULT_RECEIVER);
        	Bundle bundleExtras = new Bundle();
            bundleExtras.putString(Constants.Service.Tag.ERROR_MSG, this.getResources().getString(R.string.msg_error_no_connection_available));
            receiver.send(Constants.Service.Status.ERROR, bundleExtras);

            return this.START_NOT_STICKY;

        }
        
        switch (command) {
	        case Constants.Service.SyncCommand.PLACE_ALL:
	            syncer = new PlaceSyncer(this);
	            break;
	
	        default:
	            // Unknown command
	            return this.START_NOT_STICKY;
        }

        syncer.sync(intent);

        return this.START_NOT_STICKY;
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
