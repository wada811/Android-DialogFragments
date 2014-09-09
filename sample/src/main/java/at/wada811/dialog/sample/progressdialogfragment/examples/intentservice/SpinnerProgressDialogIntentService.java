package at.wada811.dialog.sample.progressdialogfragment.examples.intentservice;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import java.util.concurrent.TimeUnit;

public class SpinnerProgressDialogIntentService extends IntentService {

    public static class ProgressResultReceiver extends ResultReceiver{

        private OnProgressResultListener listener;

        public interface OnProgressResultListener {
            public void onReceiveResult(int resultCode, Bundle resultData);
        }

        public ProgressResultReceiver(Handler handler) {
            super(handler);
        }

        public ProgressResultReceiver setOnProgressResultReceiver(OnProgressResultListener listener){
            this.listener= listener;
            return this;
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData){
            if(listener != null){
                listener.onReceiveResult(resultCode, resultData);
            }
        }
    }

    public SpinnerProgressDialogIntentService(String name) {
        super(name);
    }

    public SpinnerProgressDialogIntentService() {
        super(SpinnerProgressDialogIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent){
        ResultReceiver receiver = intent.getParcelableExtra(ProgressResultReceiver.class.getSimpleName());

        for(int i = 0; i < 5; i++){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            Bundle result = new Bundle();
            result.putInt("progress", i + 1);
            result.putInt("max", 5);
            receiver.send(Activity.RESULT_OK, result);
        }
    }
}
