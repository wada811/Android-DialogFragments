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

package com.wada811.android.dialogfragments;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

/**
 * A simple dialog containing an {@link android.widget.DatePicker}.
 * <p/>
 * <p> See the <a href="{@docRoot}resources/tutorials/views/hello-datepicker.html">Date Picker tutorial</a>. </p>
 */
class DatePickerDialog extends AlertDialog
    implements DatePickerDialogInterface, android.content.DialogInterface.OnClickListener {

    private final DatePicker datePicker;
    private final OnDateSetListener listener;

    /**
     * @param context The context the dialog is to run in.
     * @param listener How the parent is notified that the date is set.
     * @param year The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     * @param dayOfMonth The initial day of the dialog.
     */
    public DatePickerDialog(Context context, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth){
        this(context, 0, listener, year, monthOfYear, dayOfMonth);
    }

    /**
     * @param context The context the dialog is to run in.
     * @param theme the theme to apply to this dialog
     * @param listener How the parent is notified that the date is set.
     * @param year The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     * @param dayOfMonth The initial day of the dialog.
     */
    public DatePickerDialog(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth){
        super(context, theme);
        this.listener = listener;

        Context themeContext = getContext();
        setButton(BUTTON_POSITIVE, themeContext.getText(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, themeContext.getText(android.R.string.cancel), (OnClickListener)null);

        datePicker = newDatePicker(themeContext);
        setView(datePicker);

        // initialize state
        datePicker.init(year, monthOfYear, dayOfMonth, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private DatePicker newDatePicker(Context context){
        DatePicker datePicker = new DatePicker(context);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            datePicker.setCalendarViewShown(false);
        }
        return datePicker;
    }

    @Override
    public void onClick(android.content.DialogInterface dialog, int which){
        if(listener != null){
            datePicker.clearFocus();
            listener.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        }
    }

    /**
     * Gets the {@link android.widget.DatePicker} contained in this dialog.
     *
     * @return The calendar view.
     */
    public DatePicker getDatePicker(){
        return datePicker;
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
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
    }

    @Override
    public Bundle onSaveInstanceState(){
        Bundle state = super.onSaveInstanceState();
        state.putInt(YEAR, datePicker.getYear());
        state.putInt(MONTH, datePicker.getMonth());
        state.putInt(DAY, datePicker.getDayOfMonth());
        return state;
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        int year = savedInstanceState.getInt(YEAR);
        int month = savedInstanceState.getInt(MONTH);
        int day = savedInstanceState.getInt(DAY);
        datePicker.updateDate(year, month, day);
    }

}
