package at.wada811.dialog.sample.progressdialogfragment.examples.asynctaskloader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import at.wada811.dialog.ProgressDialogFragment;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.progressdialogfragment.examples.ProgressDialogFragmentExample;

/**
 * Created by wada on 2014/09/07.
 */
public class LoaderSpinnerProgressDialogExample extends ProgressDialogFragmentExample{

    private Context context;
    private ProgressDialogFragment dialogFragment;

    public LoaderSpinnerProgressDialogExample(Context context){
        super(context.getString(R.string.example_spinner_progress_dialog_with_loader));
        this.context = context;
    }

    public void showDialog(FragmentManager fragmentManager){
        dialogFragment = new ProgressDialogFragment();
        dialogFragment.setIcon(R.drawable.ic_launcher);
        dialogFragment.setTitle(label);
        dialogFragment.setProgressStyle(ProgressDialogFragment.STYLE_SPINNER);
        dialogFragment.setMessage("Loading...");
        dialogFragment.setIndeterminate(true);
        dialogFragment.setCancelable(false);
        dialogFragment.setCanceledOnTouchOutside(false);
        dialogFragment.show(fragmentManager, label);
    }

    @Override
    public void startLoading(FragmentManager fragmentManager, final LoaderManager loaderManager){
        showDialog(fragmentManager);
        loaderManager.initLoader(
            (int)System.currentTimeMillis(), null, new LoaderCallbacks<Object>(){
                @Override
                public Loader<Object> onCreateLoader(int i, Bundle bundle){
                    return new SpinnerProgressDialogLoader(context);
                }

                @Override
                public void onLoadFinished(Loader<Object> objectLoader, Object o){
                    loaderManager.destroyLoader(objectLoader.getId());
                    dialogFragment.dismiss();
                }

                @Override
                public void onLoaderReset(Loader<Object> objectLoader){

                }
            }
        );
    }

}
