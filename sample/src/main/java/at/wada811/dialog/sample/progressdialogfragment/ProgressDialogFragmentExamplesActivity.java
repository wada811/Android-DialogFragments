package at.wada811.dialog.sample.progressdialogfragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;
import at.wada811.dialog.sample.Const;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.progressdialogfragment.examples.ProgressDialogFragmentExample;


public class ProgressDialogFragmentExamplesActivity extends ActionBarActivity{

    final ProgressDialogFragmentExamplesActivity self = this;
    private ArrayList<ProgressDialogFragmentExample> items;
    private ProgressDialogFragmentExample item;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_progress_dialog_fragment_examples);

        if(savedInstanceState == null){
            boolean isInActivity = getIntent().getBooleanExtra(Const.KEY_IS_IN_ACTIVITY, true);
            getSupportActionBar().setTitle(isInActivity ? R.string.title_activity_progress_dialog_fragment_examples_in_activity : R.string.title_activity_progress_dialog_fragment_examples_in_fragment);
            getSupportFragmentManager().beginTransaction()
                .replace(
                    android.R.id.content,
                    ProgressDialogFragmentExamplesFragment.newInstance(isInActivity)
                )
                .commit();
        }
    }

}
