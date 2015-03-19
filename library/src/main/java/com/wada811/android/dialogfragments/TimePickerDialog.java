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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TimePicker;

/**
 * A dialog that prompts the user for the time of day using a {@link android.widget.TimePicker}.
 * <p/>
 * <p> See the <a href="{@docRoot}resources/tutorials/views/hello-timepicker.html">Time Picker tutorial</a>. </p>
 */
class TimePickerDialog extends AlertDialog implements TimePickerDialogInterface, OnClickListener {

    private final TimePicker timePicker;
    private final OnTimeSetListener listener;

    /**
     * @param context Parent.
     * @param listener How parent is notified.
     * @param hourOfDay The initial hour.
     * @param minute The initial minute.
     * @param is24HourView Whether this is a 24 hour view, or AM/PM.
     */
    public TimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView){
        this(context, 0, listener, hourOfDay, minute, is24HourView);
    }

    /**
     * @param context Parent.
     * @param theme the theme to apply to this dialog
     * @param listener How parent is notified.
     * @param hourOfDay The initial hour.
     * @param minute The initial minute.
     * @param is24HourView Whether this is a 24 hour view, or AM/PM.
     */
    public TimePickerDialog(Context context, int theme, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView){
        super(context, theme);
        this.listener = listener;

        Context themeContext = getContext();
        setButton(BUTTON_POSITIVE, themeContext.getText(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, themeContext.getText(android.R.string.cancel), (OnClickListener)null);

        timePicker = newTimePicker(themeContext);
        setView(timePicker);

        // initialize state
        timePicker.setIs24HourView(is24HourView);
        timePicker.setCurrentHour(hourOfDay);
        timePicker.setCurrentMinute(minute);
    }

    private TimePicker newTimePicker(Context context){
        return new TimePicker(context);
    }

    public void onClick(android.content.DialogInterface dialog, int which){
        if(listener != null){
            timePicker.clearFocus();
            listener.onTimeSet(timePicker, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        }
    }

    @Override
    public void updateTime(int hourOfDay, int minutOfHour){
        timePicker.setCurrentHour(hourOfDay);
        timePicker.setCurrentMinute(minutOfHour);
    }

    @Override
    public Bundle onSaveInstanceState(){
        Bundle state = super.onSaveInstanceState();
        state.putInt(HOUR, timePicker.getCurrentHour());
        state.putInt(MINUTE, timePicker.getCurrentMinute());
        state.putBoolean(IS_24_HOUR, timePicker.is24HourView());
        return state;
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        int hour = savedInstanceState.getInt(HOUR);
        int minute = savedInstanceState.getInt(MINUTE);
        boolean is24hour = savedInstanceState.getBoolean(IS_24_HOUR);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        timePicker.setIs24HourView(is24hour);
    }

    @Override
    public TimePicker getTimePicker(){
        return timePicker;
    }
}
