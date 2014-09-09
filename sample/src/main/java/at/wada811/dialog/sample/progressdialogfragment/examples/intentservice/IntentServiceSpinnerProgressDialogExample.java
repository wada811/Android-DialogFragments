package at.wada811.dialog.sample.progressdialogfragment.examples.intentservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import at.wada811.dialog.ProgressDialogFragment;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.progressdialogfragment.examples.ProgressDialogFragmentExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.intentservice.SpinnerProgressDialogIntentService.ProgressResultReceiver;
import at.wada811.dialog.sample.progressdialogfragment.examples.intentservice.SpinnerProgressDialogIntentService.ProgressResultReceiver.OnProgressResultListener;

/**
 * Created by wada on 2014/09/07.
 */
public class IntentServiceSpinnerProgressDialogExample extends ProgressDialogFragmentExample{

    private Context context;
    private ProgressDialogFragment dialogFragment;

    public IntentServiceSpinnerProgressDialogExample(Context context){
        super(context.getString(R.string.example_spinner_progress_dialog_with_intent_service));
        this.context = context;
    }

    public void showDialog(FragmentManager fragmentManager){
        dialogFragment = new ProgressDialogFragment();
        dialogFragment.setIcon(R.drawable.ic_launcher);
        dialogFragment.setTitle(label);
        dialogFragment.setProgressStyle(ProgressDialogFragment.STYLE_HORIZONTAL);
        dialogFragment.setMessage("Loading...");
        dialogFragment.setIndeterminate(true);
        dialogFragment.setCancelable(false);
        dialogFragment.setCanceledOnTouchOutside(false);
        dialogFragment.show(fragmentManager, label);
    }

    @Override
    public void startLoading(FragmentManager fragmentManager, final LoaderManager loaderManager){
        showDialog(fragmentManager);
        Intent intent = new Intent(context, SpinnerProgressDialogIntentService.class);
        intent.putExtra(
            ProgressResultReceiver.class.getSimpleName(),
            new ProgressResultReceiver(new Handler()).setOnProgressResultReceiver(
                new OnProgressResultListener(){
                    @Override
                    public void onReceiveResult(int resultCode, Bundle resultData){
                        int max = resultData.getInt("max");
                        int progress = resultData.getInt("progress");
                        dialogFragment.setIndeterminate(false);
                        dialogFragment.setMax(max);
                        dialogFragment.setProgress(progress);
                        if(progress == dialogFragment.getMax()){
                            dialogFragment.dismiss();
                        }
                    }
                }
            )
        );
        context.startService(intent);
    }

}
