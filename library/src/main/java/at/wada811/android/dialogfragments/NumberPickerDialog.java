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
package at.wada811.android.dialogfragments;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.NumberPicker;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class NumberPickerDialog extends AlertDialog implements NumberPickerDialogInterface, DialogInterface.OnClickListener {

    private static final String VALUE = "value";

    private NumberPicker numberPicker;
    private OnNumberSetListener listener;

    public NumberPickerDialog(Context context, OnNumberSetListener listener, int value, int minValue, int maxValue){
        this(context, 0, listener, value, minValue, maxValue);
    }

    public NumberPickerDialog(Context context, int theme, OnNumberSetListener listener, int value, int minValue, int maxValue){
        super(context, theme);
        this.listener = listener;

        Context themeContext = getContext();
        setButton(BUTTON_POSITIVE, themeContext.getText(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, themeContext.getText(android.R.string.cancel), (OnClickListener)null);

        numberPicker = newNumberPicker(themeContext);
        setView(numberPicker);

        // initialize state
        numberPicker.setMinValue(minValue);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setValue(value);
    }

    private NumberPicker newNumberPicker(Context context){
        return new NumberPicker(context);
    }

    @Override
    public void onClick(DialogInterface dialog, int which){
        if(listener != null){
            numberPicker.clearFocus();
            listener.onNumberSet(numberPicker, numberPicker.getValue());
        }
    }

    @Override
    public void updateNumber(int value){
        numberPicker.setValue(value);
    }

    @Override
    public Bundle onSaveInstanceState(){
        Bundle state = super.onSaveInstanceState();
        state.putInt(VALUE, numberPicker.getValue());
        return state;
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        int value = savedInstanceState.getInt(VALUE);
        numberPicker.setValue(value);
    }

    @Override
    public NumberPicker getNumberPicker(){
        return numberPicker;
    }

}
