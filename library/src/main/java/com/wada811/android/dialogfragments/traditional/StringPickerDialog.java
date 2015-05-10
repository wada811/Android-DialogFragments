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
package com.wada811.android.dialogfragments.traditional;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.wada811.android.dialogfragments.view.StringPicker;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class StringPickerDialog extends AlertDialog implements StringPickerDialogInterface, DialogInterface.OnClickListener {

    private static final String VALUE = "value";
    private OnStringSetListener listener;
    private StringPicker stringPicker;

    protected StringPickerDialog(Context context, OnStringSetListener listener, String[] values){
        this(context, 0, listener, values);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected StringPickerDialog(Context context, int theme, OnStringSetListener listener, String[] values){
        super(context, theme);
        this.listener = listener;

        Context themeContext = getContext();
        setButton(BUTTON_POSITIVE, themeContext.getText(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, themeContext.getText(android.R.string.cancel), (OnClickListener)null);

        stringPicker = newStringPicker(themeContext);
        setView(stringPicker);

        // initialize state
        stringPicker.setDisplayedValues(values);
    }

    private StringPicker newStringPicker(Context context){
        return new StringPicker(context);
    }

    @Override
    public void onClick(DialogInterface dialog, int which){
        if(listener != null){
            stringPicker.clearFocus();
            listener.onStringSet(stringPicker, stringPicker.getDisplayedValue());
        }
    }

    @Override
    public Bundle onSaveInstanceState(){
        Bundle state = super.onSaveInstanceState();
        state.putString(VALUE, stringPicker.getDisplayedValue());
        return state;
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String value = savedInstanceState.getString(VALUE);
        stringPicker.setDisplayedValue(value);
    }

    @Override
    public StringPicker getStringPicker(){
        return stringPicker;
    }

    @Override
    public void setDisplayedValue(String value){
        stringPicker.setDisplayedValue(value);
    }

    @Override
    public String getDisplayedValue(){
        return stringPicker.getDisplayedValue();
    }

    @Override
    public void setDisplayedValues(List<String> values){
        stringPicker.setDisplayedValues(values);
    }

    @Override
    public void setDisplayedValues(String[] values){
        stringPicker.setDisplayedValues(values);
    }

}
