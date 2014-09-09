package at.wada811.dialog.sample.progressdialogfragment.examples.asynctaskloader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import java.util.concurrent.TimeUnit;
import at.wada811.dialog.ProgressDialogFragment;
import at.wada811.dialog.sample.R;
/**
 * Created by wada on 2014/09/09.
 */
public class HorizontalProgressDialogLoader extends AbstractAsyncLoader<Object>{

    private ProgressDialogFragment dialogFragment;
    private FragmentManager fragmentManager;

    public HorizontalProgressDialogLoader(Context context, FragmentManager fragmentManager){
        super(context);
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        dialogFragment = new ProgressDialogFragment();
        dialogFragment.setIcon(R.drawable.ic_launcher);
        dialogFragment.setTitle(R.string.example_horizontal_progress_dialog_within_loader);
        dialogFragment.setProgressStyle(ProgressDialogFragment.STYLE_HORIZONTAL);
        dialogFragment.setMessage("Loading...");
        dialogFragment.setIndeterminate(true);
        dialogFragment.setCancelable(false);
        dialogFragment.setCanceledOnTouchOutside(false);
        dialogFragment.show(fragmentManager, HorizontalProgressDialogLoader.class.getSimpleName());
    }

    @Override
    protected Bundle onLoadInBackground(){
        dialogFragment.setIndeterminate(false);
        dialogFragment.setMax(5);
        for(int i = 0; i < 5; i++){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            dialogFragment.setProgress(i + 1);
        }
        deliverResult(null);
        return null;
    }

    @Override
    protected void onStopLoading(){
        super.onStopLoading();
        dialogFragment.dismiss();
    }

    @Override
    public void deliverResult(Object data){
        super.deliverResult(data);
        dialogFragment.dismiss();
    }

}