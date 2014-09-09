package at.wada811.dialog.sample.progressdialogfragment.examples.asynctaskloader;

import android.content.Context;
import java.util.concurrent.TimeUnit;
/**
 * Created by wada on 2014/09/09.
 */
public class SpinnerProgressDialogLoader extends AbstractAsyncLoader<Object>{

    public SpinnerProgressDialogLoader(Context context){
        super(context);
    }

    @Override
    protected Object onLoadInBackground(){
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

}
