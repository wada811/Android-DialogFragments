package at.wada811.dialog.sample.dialogfragmentcallbackprovider;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import at.wada811.dialog.interfaces.SimpleDialogFragmentCallback;
import at.wada811.dialog.sample.R;


public class DialogFragmentCallbackProviderFragmentActivity extends ActionBarActivity
    implements DialogFragmentCallbackProvider{

    final DialogFragmentCallbackProviderFragmentActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new DialogFragmentCallbackProviderFragment())
                .commit();
        }
    }


    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback(){
            @Override
            public View getView(DialogFragmentInterface dialog){
                TextView titleView = new TextView(self);
                titleView.setText("Use getFragmentManager()");
                titleView.setPadding(0, 24, 0, 24);
                titleView.setGravity(Gravity.CENTER);
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                return titleView;
            }
        };
    }
}
