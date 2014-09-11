package at.wada811.dialog.sample.progressdialogfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import at.wada811.dialog.sample.Const;
import at.wada811.dialog.sample.Examples;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.progressdialogfragment.examples.LoaderSpinnerProgressDialogFragmentExamplesActivity;
import at.wada811.dialog.sample.progressdialogfragment.examples.LoaderSpinnerProgressDialogFragmentExamplesFragmentActivity;


public class ProgressDialogFragmentExamplesFragment extends ListFragment{

    private ArrayList<Examples> items;

    public static ProgressDialogFragmentExamplesFragment newInstance(){
        ProgressDialogFragmentExamplesFragment fragment = new ProgressDialogFragmentExamplesFragment();
        Bundle args = new Bundle();
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
        items = new ArrayList<Examples>();
        items.add(
            new Examples(
                getString(R.string.example_spinner_progress_dialog_with_loader_in_activity),
                new Intent(
                    getActivity(), LoaderSpinnerProgressDialogFragmentExamplesActivity.class
                )
            )
        );
        {
            Intent intent = new Intent(
                getActivity(), LoaderSpinnerProgressDialogFragmentExamplesFragmentActivity.class
            );
            intent.putExtra(Const.KEY_IS_RETAIN, false);
            items.add(
                new Examples(
                    getString(R.string.example_spinner_progress_dialog_with_loader_in_fragment),
                    intent
                )
            );
        }
        {
            Intent intent = new Intent(
                getActivity(), LoaderSpinnerProgressDialogFragmentExamplesFragmentActivity.class
            );
            intent.putExtra(Const.KEY_IS_RETAIN, false);
            items.add(
                new Examples(
                    getString(R.string.example_spinner_progress_dialog_with_loader_in_retain_fragment),
                    intent
                )
            );
        }
    }

    private void initListFragment(){
        setListAdapter(
            new ArrayAdapter<Examples>(
                getActivity(), android.R.layout.simple_list_item_1, items
            )
        );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Examples item = items.get(position);
        startActivity(item.intent);
    }

}
