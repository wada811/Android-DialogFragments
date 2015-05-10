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
package com.wada811.android.dialogfragments.interfaces;

import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import com.wada811.android.dialogfragments.view.StringPicker;

public class SimpleDialogFragmentCallback implements DialogFragmentCallback {

    @Override
    public void onShow(DialogFragmentInterface dialog){
        throw new RuntimeException("#setOnShowListener called, but #onShow has not implemented.");
    }

    @Override
    public void onCancel(DialogFragmentInterface dialog){
        throw new RuntimeException("#setOnCancelListener called, but #onCancel has not implemented.");
    }

    @Override
    public void onDismiss(DialogFragmentInterface dialog){
        throw new RuntimeException("#setOnDismissListener called, but #onDismiss has not implemented.");
    }

    @Override
    public void onClickPositive(DialogFragmentInterface dialog){
        throw new RuntimeException("#setPositiveButton called, but #onClickPositive has not implemented.");
    }

    @Override
    public void onClickNegative(DialogFragmentInterface dialog){
        throw new RuntimeException("#setNegativeButton called, but #onClickNegative has not implemented.");
    }

    @Override
    public void onClickNeutral(DialogFragmentInterface dialog){
        throw new RuntimeException("#setNeutralButton called, but #onClickNeutral has not implemented.");
    }

    @Override
    public void onItemClick(DialogFragmentInterface dialog, int position){
        throw new RuntimeException("#setItems or #setAdapter called, but #onItemClick has not implemented.");
    }

    @Override
    public void onSingleChoiceClick(DialogFragmentInterface dialog, int position){
        throw new RuntimeException("#setSingleChoiceItems called, but #onSingleChoiceClick has not implemented.");
    }

    @Override
    public void onMultiChoiceClick(DialogFragmentInterface dialog, int position, boolean isChecked){
        throw new RuntimeException("#setMultiChoiceItems called, but #onMultiChoiceClick has not implemented.");
    }

    @Override
    public boolean onKey(DialogFragmentInterface dialog, int keyCode, KeyEvent event){
        throw new RuntimeException("#setOnKeyListener called, but #onKey has not implemented.");
    }

    @Override
    public View getCustomTitle(DialogFragmentInterface dialog){
        throw new RuntimeException("#setCustomTitle called, but #getCustomTitle has not implemented.");
    }

    @Override
    public View getView(DialogFragmentInterface dialog){
        throw new RuntimeException("#setView called, but #getView has not implemented.");
    }

    @Override
    public ListAdapter getAdapter(DialogFragmentInterface dialog){
        throw new RuntimeException("#setAdapter called, but #getAdapter has not implemented.");
    }

    @Override
    public void onDateSet(DialogFragmentInterface dialog, DatePicker datePicker, int year, int month, int day){
        throw new RuntimeException("DatePickerDialogFragment has instantiated, but #onDateSet has not implemented.");
    }

    @Override
    public void onTimeSet(DialogFragmentInterface dialog, TimePicker timePicker, int hour, int minute){
        throw new RuntimeException("TimePickerDialogFragment has instantiated, but #onTimeSet has not implemented.");
    }

    @Override
    public void onNumberSet(DialogFragmentInterface dialog, NumberPicker numberPicker, int value){
        throw new RuntimeException("NumberPickerDialogFragment has instantiated, but #onNumberSet has not implemented.");
    }

    @Override
    public void onStringSet(DialogFragmentInterface dialog, StringPicker stringPicker, String value){
        throw new RuntimeException("StringPickerDialogFragment has instantiated, but #onStringSet has not implemented.");
    }
}
