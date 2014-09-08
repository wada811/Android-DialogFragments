package at.wada811.dialog.sample.dialogfragmentcallbackprovider.examples;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import at.wada811.dialog.AlertDialogFragment;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.sample.Example;
import at.wada811.dialog.sample.R;

/**
 * Created by wada on 2014/09/07.
 */
public class DialogFragmentCallbackInFragmentExample extends Example{

    private Context context;

    public DialogFragmentCallbackInFragmentExample(Context context){
        super(context.getString(R.string.example_dialog_fragment_callback_in_fragment));
        this.context = context;
    }

    @Override
    public void showDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager){
        new AlertDialogFragment.Builder(context).setTitle(label)
            .setView(provider)
            .setPositiveButton(R.string.dialog_ok, null)
            .setNegativeButton(R.string.dialog_cancel, null)
            .show(
                fragmentManager, DialogFragmentCallbackInFragmentExample.class.getSimpleName()
            );
    }

}
