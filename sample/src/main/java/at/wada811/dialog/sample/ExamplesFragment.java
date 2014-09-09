package at.wada811.dialog.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import at.wada811.dialog.DatePickerDialogFragment;
import at.wada811.dialog.NumberPickerDialogFragment;
import at.wada811.dialog.ProgressDialogFragment;
import at.wada811.dialog.StringPickerDialogFragment;
import at.wada811.dialog.TimePickerDialogFragment;
import at.wada811.dialog.sample.alertdialogfragment.AlertDialogFragmentExamplesActivity;
import at.wada811.dialog.sample.dialogfragmentcallbackprovider.DialogFragmentCallbackProviderFragmentActivity;
import at.wada811.dialog.sample.progressdialogfragment.ProgressDialogFragmentExamplesActivity;


public class ExamplesFragment extends ListFragment{

    private ArrayList<Examples> items;

    public static ExamplesFragment newInstance(){
        ExamplesFragment fragment = new ExamplesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        initExamples();
        setListAdapter(
            new ArrayAdapter<Examples>(
                getActivity(), android.R.layout.simple_list_item_1, items
            )
        );
    }

    private void initExamples(){
        items = new ArrayList<Examples>();
        {
            items.add(
                new Examples(
                    getString(R.string.title_activity_dialog_fragment_callback_provider),
                    new Intent(getActivity(), DialogFragmentCallbackProviderFragmentActivity.class)
                )
            );
        }
        {
            Intent intent = new Intent(
                getActivity(),
                AlertDialogFragmentExamplesActivity.class
            );
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, true);
            items.add(
                new Examples(
                    getString(R.string.title_activity_alert_dialog_fragment_examples_in_activity),
                    intent
                )
            );
        }
        {
            Intent intent = new Intent(
                getActivity(),
                AlertDialogFragmentExamplesActivity.class
            );
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, false);
            items.add(
                new Examples(
                    getString(R.string.title_activity_alert_dialog_fragment_examples_in_fragment),
                    intent
                )
            );
        }
        {
            Intent intent = new Intent(
                getActivity(),
                ProgressDialogFragmentExamplesActivity.class
            );
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, true);
            items.add(
                new Examples(
                    getString(R.string.title_activity_progress_dialog_fragment_examples_in_activity),
                    intent
                )
            );
        }
        {
            Intent intent = new Intent(
                getActivity(),
                ProgressDialogFragmentExamplesActivity.class
            );
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, false);
            items.add(
                new Examples(
                    getString(R.string.title_activity_progress_dialog_fragment_examples_in_fragment),
                    intent
                )
            );
        }
        items.add(
            new Examples(
                DatePickerDialogFragment.class.getSimpleName(),
                new Intent(getActivity(), DatePickerDialogFragmentExamplesActivity.class)
            )
        );
        items.add(
            new Examples(
                TimePickerDialogFragment.class.getSimpleName(),
                new Intent(getActivity(), TimePickerDialogFragmentExamplesActivity.class)
            )
        );
        items.add(
            new Examples(
                NumberPickerDialogFragment.class.getSimpleName(),
                new Intent(getActivity(), NumberPickerDialogFragmentExamplesActivity.class)
            )
        );
        items.add(
            new Examples(
                StringPickerDialogFragment.class.getSimpleName(),
                new Intent(getActivity(), StringPickerDialogFragmentExamplesActivity.class)
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
