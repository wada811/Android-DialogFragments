package at.wada811.dialog.sample.progressdialogfragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import at.wada811.dialog.interfaces.SimpleDialogFragmentCallback;
import at.wada811.dialog.sample.Const;
import at.wada811.dialog.sample.Example;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.alertdialogfragment.examples.AdapterAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.BasicAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.CustomViewAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.EventAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.ItemsAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.MultiChoiceAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.SingleChoiceAlertDialogExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.ProgressDialogFragmentExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.asynctaskloader.LoaderHorizontalProgressDialogExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.asynctaskloader.LoaderSpinnerProgressDialogExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.intentservice.IntentServiceHorizontalProgressDialogExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.intentservice.IntentServiceSpinnerProgressDialogExample;


public class ProgressDialogFragmentExamplesFragment extends ListFragment{

    private ArrayList<ProgressDialogFragmentExample> items;

    public static ProgressDialogFragmentExamplesFragment newInstance(boolean isInActivity){
        ProgressDialogFragmentExamplesFragment fragment = new ProgressDialogFragmentExamplesFragment();
        Bundle args = new Bundle();
        args.putBoolean(Const.KEY_IS_IN_ACTIVITY, isInActivity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initExample();
        initListFragment();
    }

    private void initExample(){
        items = new ArrayList<ProgressDialogFragmentExample>();
        items.add(new LoaderSpinnerProgressDialogExample(getActivity()));
        items.add(new LoaderHorizontalProgressDialogExample(getActivity()));
        items.add(new IntentServiceSpinnerProgressDialogExample(getActivity()));
        items.add(new IntentServiceHorizontalProgressDialogExample(getActivity()));
    }

    private void initListFragment(){
        setListAdapter(
            new ArrayAdapter<ProgressDialogFragmentExample>(
                getActivity(), android.R.layout.simple_list_item_1, items
            )
        );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        ProgressDialogFragmentExample item = items.get(position);
        boolean isInActivity = getArguments().getBoolean(Const.KEY_IS_IN_ACTIVITY);
        item.startLoading(isInActivity ? getFragmentManager() : getChildFragmentManager(), getLoaderManager());
    }

}
