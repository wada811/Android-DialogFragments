package at.wada811.dialog.sample.progressdialogfragment.examples.intentservice;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import java.util.concurrent.TimeUnit;

public class HorizontalProgressDialogIntentService extends IntentService{

    public HorizontalProgressDialogIntentService(String name){
        super(name);
    }

    public HorizontalProgressDialogIntentService(){
        super(HorizontalProgressDialogIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent){
        ResultReceiver receiver = intent.getParcelableExtra(ProgressResultReceiver.class.getSimpleName());
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        Bundle result = new Bundle();
        receiver.send(Activity.RESULT_OK, result);
    }

    public static class ProgressResultReceiver extends ResultReceiver{

        private OnProgressResultListener listener;

        public ProgressResultReceiver(Handler handler){
            super(handler);
        }

        public ProgressResultReceiver setOnProgressResultReceiver(OnProgressResultListener listener){
            this.listener = listener;
            return this;
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData){
            if(listener != null){
                listener.onReceiveResult(resultCode, resultData);
            }
        }

        public interface OnProgressResultListener{

            public void onReceiveResult(int resultCode, Bundle resultData);
        }
    }
}
