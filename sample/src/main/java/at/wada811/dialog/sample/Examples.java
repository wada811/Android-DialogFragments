package at.wada811.dialog.sample;

import android.support.v7.app.ActionBarActivity;

/**
 * Created by wada on 2014/09/07.
 */
public class Examples {

    public String label;

    public Class<? extends ActionBarActivity> clazz;

    public Examples(String label, Class<? extends ActionBarActivity> clazz) {
        this.label = label;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return label;
    }

}
