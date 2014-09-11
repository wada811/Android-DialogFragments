/*
 * Copyright 2014 wada811
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.wada811.android.dialogfragments.sample.timepickerdialogfragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;
import at.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import at.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import at.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;
import at.wada811.android.dialogfragments.interfaces.SimpleDialogFragmentCallback;
import at.wada811.android.dialogfragments.sample.Const;
import at.wada811.android.dialogfragments.sample.R;


public class TimePickerDialogFragmentExamplesActivity extends ActionBarActivity
    implements DialogFragmentCallbackProvider{

    final TimePickerDialogFragmentExamplesActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        boolean isInActivity = getIntent().getBooleanExtra(Const.KEY_IS_IN_ACTIVITY, true);
        getSupportActionBar().setTitle(isInActivity ? R.string.title_activity_time_picker_dialog_fragment_examples_in_activity : R.string.title_activity_time_picker_dialog_fragment_examples_in_fragment);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(
                android.R.id.content,
                TimePickerDialogFragmentExamplesFragment.newInstance(isInActivity)
            ).commit();
        }
    }


    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback(){
            @Override
            public void onTimeSet(DialogFragmentInterface dialog, TimePicker timePicker, int hour, int minute){
                String text = "onTimeSet[hour: " + hour + ", minute: " + minute + "]";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
