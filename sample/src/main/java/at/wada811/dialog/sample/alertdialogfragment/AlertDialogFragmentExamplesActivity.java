package at.wada811.dialog.sample.alertdialogfragment;

import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import at.wada811.dialog.interfaces.SimpleDialogFragmentCallback;
import at.wada811.dialog.sample.Example;
import at.wada811.dialog.sample.alertdialogfragment.examples.BasicAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.EventAlertDialogExample;


public class AlertDialogFragmentExamplesActivity extends ActionBarActivity implements DialogFragmentCallbackProvider {

    final AlertDialogFragmentExamplesActivity self = this;

    public static final ArrayList<Example> items = new ArrayList<Example>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_alert_dialog_fragment_examples);

        initExample();
        initListFragment();
    }


    private void initExample(){
        items.add(new BasicAlertDialogExample(this));
        items.add(new EventAlertDialogExample(this));
//        items.add(new Example());
    }


    private void initListFragment(){
        ListFragment listFragment = new ListFragment(){
            @Override
            public void onListItemClick(ListView l, View v, int position, long id) {
                super.onListItemClick(l, v, position, id);
                Example item = items.get(position);
                item.showDialog(self, getSupportFragmentManager());
            }
        };
        listFragment.setListAdapter(new ArrayAdapter<Example>(this, android.R.layout.simple_list_item_1, items));
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, listFragment).commit();
    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback() {
        return new SimpleDialogFragmentCallback(){
            @Override
            public void onShow(DialogFragmentInterface dialog) {
                Log.v(dialog.getTag(), "onShow");
            }

            @Override
            public void onCancel(DialogFragmentInterface dialog) {
                Log.v(dialog.getTag(), "onCancel");
            }

            @Override
            public void onDismiss(DialogFragmentInterface dialog) {
                Log.v(dialog.getTag(), "onDismiss");
            }

            @Override
            public void onClickPositive(DialogFragmentInterface dialog) {
                Log.v(dialog.getTag(), "onClickPositive");
            }

            @Override
            public void onClickNeutral(DialogFragmentInterface dialog) {
                Log.v(dialog.getTag(), "onClickNeutral");
            }

            @Override
            public void onClickNegative(DialogFragmentInterface dialog) {
                Log.v(dialog.getTag(), "onClickNegative");
            }
        };
    }
}
