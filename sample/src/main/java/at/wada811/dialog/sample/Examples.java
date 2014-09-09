package at.wada811.dialog.sample;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;

/**
 * Created by wada on 2014/09/07.
 */
public class Examples{

    public String label;
    public Intent intent;

    public Examples(String label, Intent intent){
        this.label = label;
        this.intent = intent;
    }


    @Override
    public String toString() {
        return label;
    }
}
