package at.wada811.dialog.sample.alertdialogfragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class AlertDialogFragmentExamplesFragmentActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AlertDialogFragmentExamplesFragment())
                .commit();
        }
    }

}
