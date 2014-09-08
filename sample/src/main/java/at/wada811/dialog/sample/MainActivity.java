package at.wada811.dialog.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import at.wada811.dialog.AlertDialogFragment;
import at.wada811.dialog.DatePickerDialogFragment;
import at.wada811.dialog.NumberPickerDialogFragment;
import at.wada811.dialog.ProgressDialogFragment;
import at.wada811.dialog.StringPickerDialogFragment;
import at.wada811.dialog.TimePickerDialogFragment;
import at.wada811.dialog.sample.alertdialogfragment.AlertDialogFragmentExamplesActivity;
import at.wada811.dialog.sample.alertdialogfragment.AlertDialogFragmentExamplesFragmentActivity;


public class MainActivity extends ActionBarActivity{

    public static final ArrayList<Examples> items = new ArrayList<Examples>();
    final MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        initExamples();
        initListFragment();
    }

    private void initExamples(){
        items.add(
            new Examples(
                "AlertDialogFragment in Activity",
                AlertDialogFragmentExamplesActivity.class
            )
        );
        items.add(
            new Examples(
                "AlertDialogFragment in Fragment",
                AlertDialogFragmentExamplesFragmentActivity.class
            )
        );
        items.add(
            new Examples(
                ProgressDialogFragment.class.getSimpleName(),
                ProgressDialogFragmentExamplesActivity.class
            )
        );
        items.add(
            new Examples(
                DatePickerDialogFragment.class.getSimpleName(),
                DatePickerDialogFragmentExamplesActivity.class
            )
        );
        items.add(
            new Examples(
                TimePickerDialogFragment.class.getSimpleName(),
                TimePickerDialogFragmentExamplesActivity.class
            )
        );
        items.add(
            new Examples(
                NumberPickerDialogFragment.class.getSimpleName(),
                NumberPickerDialogFragmentExamplesActivity.class
            )
        );
        items.add(
            new Examples(
                StringPickerDialogFragment.class.getSimpleName(),
                StringPickerDialogFragmentExamplesActivity.class
            )
        );
    }


    private void initListFragment(){
        ListFragment listFragment = new ListFragment(){
            @Override
            public void onListItemClick(ListView l, View v, int position, long id){
                super.onListItemClick(l, v, position, id);
                Examples item = items.get(position);
                startActivity(new Intent(self, item.clazz));
            }
        };
        listFragment.setListAdapter(
            new ArrayAdapter<Examples>(
                this,
                android.R.layout.simple_list_item_1,
                items
            )
        );
        getSupportFragmentManager().beginTransaction()
            .add(android.R.id.content, listFragment)
            .commit();
    }


}
