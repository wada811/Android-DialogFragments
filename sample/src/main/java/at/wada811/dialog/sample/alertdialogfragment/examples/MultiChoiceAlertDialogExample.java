package at.wada811.dialog.sample.alertdialogfragment.examples;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.method.CharacterPickerDialog;
import at.wada811.dialog.AlertDialogFragment;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.sample.Example;
import at.wada811.dialog.sample.R;

/**
 * Created by wada on 2014/09/07.
 */
public class MultiChoiceAlertDialogExample extends Example{

    private Context context;

    public MultiChoiceAlertDialogExample(Context context){
        super(MultiChoiceAlertDialogExample.class.getSimpleName());
        this.context = context;
    }

    @Override
    public void showDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager){
        String[] items = new String[]{
            AlertDialog.class.getSimpleName(),
            CharacterPickerDialog.class.getSimpleName(),
            ProgressDialog.class.getSimpleName(),
            DatePickerDialog.class.getSimpleName(),
            TimePickerDialog.class.getSimpleName(),
        };
        boolean[] checkedItems = new boolean[]{
            true,
            false,
            true,
            false,
            true
        };
        Bundle extra = new Bundle();
        extra.putStringArray("items", items);
        new AlertDialogFragment.Builder(context).setIcon(R.drawable.ic_launcher)
            .setTitle(R.string.dialog_title)
            .setMultiChoiceItems(items, checkedItems)
            .setExtra(extra)
            .setPositiveButton(R.string.dialog_ok, null)
            .setNegativeButton(R.string.dialog_cancel, null)
            .show(fragmentManager, MultiChoiceAlertDialogExample.class.getSimpleName());
    }

}
