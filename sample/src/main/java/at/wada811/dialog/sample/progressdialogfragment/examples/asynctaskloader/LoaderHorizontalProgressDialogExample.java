package at.wada811.dialog.sample.progressdialogfragment.examples.asynctaskloader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.progressdialogfragment.examples.ProgressDialogFragmentExample;

/**
 * Created by wada on 2014/09/07.
 */
public class LoaderHorizontalProgressDialogExample extends ProgressDialogFragmentExample{

    private Context context;

    public LoaderHorizontalProgressDialogExample(Context context){
        super(context.getString(R.string.example_horizontal_progress_dialog_within_loader));
        this.context = context;
    }

    @Override
    public void startLoading(final FragmentManager fragmentManager, final LoaderManager loaderManager){
        loaderManager.initLoader(
            (int)System.currentTimeMillis(), null, new LoaderCallbacks<Object>(){
                @Override
                public Loader<Object> onCreateLoader(int i, Bundle bundle){
                    return new HorizontalProgressDialogLoader(context, fragmentManager);
                }

                @Override
                public void onLoadFinished(Loader<Object> objectLoader, Object o){
                    loaderManager.destroyLoader(objectLoader.getId());
                }

                @Override
                public void onLoaderReset(Loader<Object> objectLoader){

                }
            }
        );
    }

}
