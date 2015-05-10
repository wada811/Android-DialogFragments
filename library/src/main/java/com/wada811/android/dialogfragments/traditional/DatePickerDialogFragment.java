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

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.DatePicker;
import com.wada811.android.dialogfragments.traditional.DatePickerDialogInterface.OnDateSetListener;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;

public class DatePickerDialogFragment extends AlertDialogFragment
    implements DialogFragmentInterface, DatePickerDialogInterface, OnDateSetListener {

    private DatePickerDialog datePickerDialog;

    public static DatePickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, int year, int month, int day){
        return newInstanceInternal(provider, VALUE_NULL, year, month, day);
    }

    public static DatePickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, int theme, int year, int month, int day){
        return newInstanceInternal(provider, theme, year, month, day);
    }

    private static DatePickerDialogFragment newInstanceInternal(DialogFragmentCallbackProvider provider, int theme, int year, int month, int day){
        assertListenerBindable(provider);
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(THEME, theme);
        args.putInt(YEAR, year);
        args.putInt(MONTH, month);
        args.putInt(DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Bundle args = getArguments();
        final int theme = args.getInt(THEME);
        final int year = args.getInt(YEAR);
        final int month = args.getInt(MONTH);
        final int day = args.getInt(DAY);

        if(theme == VALUE_NULL){
            datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        }else{
            datePickerDialog = new DatePickerDialog(getActivity(), theme, this, year, month, day);
        }
        if(iconId != VALUE_NULL){
            setIcon(iconId);
        }
        if(title != null){
            setTitle(title);
        }
        if(titleId != VALUE_NULL){
            setTitle(titleId);
        }
        return datePickerDialog;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.w(TAG, "onDestroyView");
        datePickerDialog = null;
    }

    @Override
    public Dialog getDialog(){
        return datePickerDialog;
    }

    @Override
    public DatePicker getDatePicker(){
        return datePickerDialog.getDatePicker();
    }

    /**
     * Sets the current date.
     *
     * @param year The date year.
     * @param monthOfYear The date month.
     * @param dayOfMonth The date day of month.
     */
    @Override
    public void updateDate(int year, int monthOfYear, int dayOfMonth){
        if(datePickerDialog != null){
            datePickerDialog.updateDate(year, monthOfYear, dayOfMonth);
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onDateSet(this, datePicker, year, monthOfYear, dayOfMonth);
        }
    }

}
