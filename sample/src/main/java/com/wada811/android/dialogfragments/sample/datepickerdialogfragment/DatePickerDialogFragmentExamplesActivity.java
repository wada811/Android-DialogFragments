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

package com.wada811.android.dialogfragments.sample.datepickerdialogfragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;
import com.wada811.android.dialogfragments.interfaces.SimpleDialogFragmentCallback;
import com.wada811.android.dialogfragments.sample.Const;
import com.wada811.android.dialogfragments.sample.R;

public class DatePickerDialogFragmentExamplesActivity extends ActionBarActivity
    implements DialogFragmentCallbackProvider {

    final DatePickerDialogFragmentExamplesActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        boolean isInActivity = getIntent().getBooleanExtra(Const.KEY_IS_IN_ACTIVITY, true);
        getSupportActionBar().setTitle(isInActivity ? R.string.title_activity_date_picker_dialog_fragment_examples_in_activity : R.string.title_activity_date_picker_dialog_fragment_examples_in_fragment);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, DatePickerDialogFragmentExamplesFragment.newInstance(isInActivity))
                .commit();
        }
    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback() {
            @Override
            public void onDateSet(DialogFragmentInterface dialog, DatePicker datePicker, int year, int month, int day){
                String text = "onDateSet[year: " + year + ", month: " + month + ", day: " + day + "]";
                Log.v(dialog.getTag(), text);
                Toast.makeText(self, text, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
