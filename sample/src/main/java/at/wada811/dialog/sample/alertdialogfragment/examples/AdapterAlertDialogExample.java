package at.wada811.dialog.sample.alertdialogfragment.examples;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import at.wada811.dialog.AlertDialogFragment;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.sample.Example;
import at.wada811.dialog.sample.R;

/**
 * Created by wada on 2014/09/07.
 */
public class AdapterAlertDialogExample extends Example{

    private Context context;

    public AdapterAlertDialogExample(Context context){
        super(AdapterAlertDialogExample.class.getSimpleName());
        this.context = context;
    }

    @Override
    public void showDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager){
        new AlertDialogFragment.Builder(context).setIcon(R.drawable.ic_launcher)
            .setTitle(R.string.dialog_title)
            .setAdapter(provider)
            .setNegativeButton(R.string.dialog_cancel, null)
            .show(fragmentManager, label);
    }

}
