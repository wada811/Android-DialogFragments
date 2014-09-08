package at.wada811.dialog.sample.alertdialogfragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import at.wada811.dialog.interfaces.SimpleDialogFragmentCallback;
import at.wada811.dialog.sample.Example;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.alertdialogfragment.examples.AdapterAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.BasicAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.CustomViewAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.EventAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.ItemsAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.MultiChoiceAlertDialogExample;
import at.wada811.dialog.sample.alertdialogfragment.examples.SingleChoiceAlertDialogExample;


public class AlertDialogFragmentExamplesActivity extends ActionBarActivity
    implements DialogFragmentCallbackProvider{

    final AlertDialogFragmentExamplesActivity self = this;
    private ArrayList<Example> items;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_alert_dialog_fragment_examples);

        if(savedInstanceState == null){
            initExample();
            initListFragment();
        }
    }

    private void initExample(){
        items = new ArrayList<Example>();
        items.add(new BasicAlertDialogExample(this));
        items.add(new EventAlertDialogExample(this));
        items.add(new ItemsAlertDialogExample(this));
        items.add(new AdapterAlertDialogExample(this));
        items.add(new SingleChoiceAlertDialogExample(this));
        items.add(new MultiChoiceAlertDialogExample(this));
        items.add(new CustomViewAlertDialogExample(this));
    }

    private void initListFragment(){
        ListFragment listFragment = new ListFragment(){
            @Override
            public void onListItemClick(ListView l, View v, int position, long id){
                super.onListItemClick(l, v, position, id);
                Example item = items.get(position);
                item.showDialog(self, getSupportFragmentManager());
            }
        };
        listFragment.setListAdapter(
            new ArrayAdapter<Example>(
                this, android.R.layout.simple_list_item_1, items
            )
        );
        getSupportFragmentManager().beginTransaction()
            .replace(android.R.id.content, listFragment)
            .commit();
    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback(){
            @Override
            public void onShow(DialogFragmentInterface dialog){
                String text = "onShow";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(DialogFragmentInterface dialog){
                String text = "onCancel";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDismiss(DialogFragmentInterface dialog){
                String text = "onDismiss";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickPositive(DialogFragmentInterface dialog){
                String text = "onClickPositive";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickNeutral(DialogFragmentInterface dialog){
                String text = "onClickNeutral";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickNegative(DialogFragmentInterface dialog){
                String text = "onClickNegative";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onKey(DialogFragmentInterface dialog, int keyCode, KeyEvent event){
                String text = "onKey[keyCode: " + keyCode + ", KeyEvent: " + event + "]";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onItemClick(DialogFragmentInterface dialog, int position){
                Bundle extra = dialog.getExtra();
                String[] items = extra.getStringArray("items");
                String text = "onItemClick[position: " + position + ", item: " + items[position] + "]";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public ListAdapter getAdapter(DialogFragmentInterface dialog){
                String[] items = new String[]{
                    AlertDialog.class.getSimpleName(),
                    CharacterPickerDialog.class.getSimpleName(),
                    ProgressDialog.class.getSimpleName(),
                    DatePickerDialog.class.getSimpleName(),
                    TimePickerDialog.class.getSimpleName(),
                };
                Bundle extra = new Bundle();
                extra.putStringArray("items", items);
                dialog.setExtra(extra);
                return new ArrayAdapter<String>(self, android.R.layout.simple_list_item_1, items);
            }

            @Override
            public void onSingleChoiceClick(DialogFragmentInterface dialog, int position){
                Bundle extra = dialog.getExtra();
                String[] items = extra.getStringArray("items");
                String text = "onSingleChoiceClick[position: " + position + ", item: " + items[position] + "]";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMultiChoiceClick(DialogFragmentInterface dialog, int position, boolean isChecked){
                Bundle extra = dialog.getExtra();
                String[] items = extra.getStringArray("items");
                String text = "onMultiChoiceClick[position: " + position + ", item: " + items[position] + ", isChecked: " + isChecked + "]";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public View getCustomTitle(DialogFragmentInterface dialog){
                TextView titleView = new TextView(self);
                titleView.setText(dialog.getTag());
                titleView.setPadding(0, 24, 0, 24);
                titleView.setGravity(Gravity.CENTER);
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                return titleView;
            }

            @Override
            public View getView(DialogFragmentInterface dialog){
                ImageView imageView = new ImageView(self);
                imageView.setImageResource(R.drawable.ic_launcher);
                imageView.setPadding(0, 24, 0, 24);
                return imageView;
            }
        };
    }
}
