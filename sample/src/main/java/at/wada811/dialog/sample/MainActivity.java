package at.wada811.dialog.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity{

    final MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, ExamplesFragment.newInstance())
                .commit();
        }
    }

}
