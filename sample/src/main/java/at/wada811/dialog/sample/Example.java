package at.wada811.dialog.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;

/**
 * Created by wada on 2014/09/07.
 */
public abstract class Example {

    public String label;

    public Example(String label){
        this.label = label;
    }

    public abstract void showDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager);

    @Override
    public String toString() {
        return label;
    }
}
