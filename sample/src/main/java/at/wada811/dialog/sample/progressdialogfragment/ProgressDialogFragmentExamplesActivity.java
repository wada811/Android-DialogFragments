package at.wada811.dialog.sample.progressdialogfragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class ProgressDialogFragmentExamplesActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(
                android.R.id.content, ProgressDialogFragmentExamplesFragment.newInstance()
            ).commit();
        }
    }

}
