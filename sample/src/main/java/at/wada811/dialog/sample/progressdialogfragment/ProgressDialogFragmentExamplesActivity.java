package at.wada811.dialog.sample.progressdialogfragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import at.wada811.dialog.interfaces.SimpleDialogFragmentCallback;
import at.wada811.dialog.sample.progressdialogfragment.examples.ProgressDialogFragmentExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.asynctaskloader.LoaderHorizontalProgressDialogExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.asynctaskloader.LoaderSpinnerProgressDialogExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.intentservice.IntentServiceHorizontalProgressDialogExample;
import at.wada811.dialog.sample.progressdialogfragment.examples.intentservice.IntentServiceSpinnerProgressDialogExample;


public class ProgressDialogFragmentExamplesActivity extends ActionBarActivity{

    final ProgressDialogFragmentExamplesActivity self = this;
    private ArrayList<ProgressDialogFragmentExample> items;
    private ProgressDialogFragmentExample item;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_progress_dialog_fragment_examples);

        if(savedInstanceState == null){
            initExample();
            initListFragment();
        }
    }

    private void initExample(){
        items = new ArrayList<ProgressDialogFragmentExample>();
        items.add(new LoaderSpinnerProgressDialogExample(this));
        items.add(new LoaderHorizontalProgressDialogExample(this));
        items.add(new IntentServiceSpinnerProgressDialogExample(this));
        items.add(new IntentServiceHorizontalProgressDialogExample(this));
    }

    private void initListFragment(){
        ListFragment listFragment = new ListFragment(){
            @Override
            public void onListItemClick(ListView l, View v, int position, long id){
                super.onListItemClick(l, v, position, id);
                item = items.get(position);
                item.startLoading(getSupportFragmentManager(), getSupportLoaderManager());
            }
        };
        listFragment.setListAdapter(
            new ArrayAdapter<ProgressDialogFragmentExample>(
                this, android.R.layout.simple_list_item_1, items
            )
        );
        getSupportFragmentManager().beginTransaction()
            .replace(android.R.id.content, listFragment)
            .commit();
    }

}
