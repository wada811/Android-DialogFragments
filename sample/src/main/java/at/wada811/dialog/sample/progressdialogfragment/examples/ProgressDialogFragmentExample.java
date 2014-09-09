package at.wada811.dialog.sample.progressdialogfragment.examples;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import at.wada811.dialog.ProgressDialogFragment;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.sample.Example;

/**
 * Created by wada on 2014/09/07.
 */
public abstract class ProgressDialogFragmentExample {
    public String label;

    public ProgressDialogFragmentExample(String label){
        this.label = label;
    }

    public abstract void startLoading(FragmentManager fragmentManager, LoaderManager loaderManager);

    @Override
    public String toString() {
        return label;
    }
}
