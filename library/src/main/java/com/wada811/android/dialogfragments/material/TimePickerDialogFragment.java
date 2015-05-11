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
package com.wada811.android.dialogfragments.material;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TimePicker;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;
import com.wada811.android.dialogfragments.material.TimePickerDialogInterface.OnTimeSetListener;

public class TimePickerDialogFragment extends AlertDialogFragment
    implements DialogFragmentInterface, TimePickerDialogInterface, OnTimeSetListener {

    private TimePickerDialog timePickerDialog;

    public static TimePickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, int hour, int minute, boolean is24hour){
        return newInstanceInternal(provider, VALUE_NULL, hour, minute, is24hour);
    }

    public static TimePickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, int theme, int hour, int minute, boolean is24hour){
        return newInstanceInternal(provider, theme, hour, minute, is24hour);
    }

    private static TimePickerDialogFragment newInstanceInternal(DialogFragmentCallbackProvider provider, int theme, int hour, int minute, boolean is24hour){
        assertListenerBindable(provider);
        TimePickerDialogFragment fragment = new TimePickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(THEME, theme);
        args.putInt(HOUR, hour);
        args.putInt(MINUTE, minute);
        args.putBoolean(IS_24_HOUR, is24hour);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Bundle args = getArguments();
        final int theme = args.getInt(THEME);
        final int hour = args.getInt(HOUR);
        final int minute = args.getInt(MINUTE);
        final boolean is24hour = args.getBoolean(IS_24_HOUR);

        if(theme == VALUE_NULL){
            timePickerDialog = new TimePickerDialog(getActivity(), this, hour, minute, is24hour);
        }else{
            timePickerDialog = new TimePickerDialog(getActivity(), theme, this, hour, minute, is24hour);
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
        return timePickerDialog;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.w(TAG, "onDestroyView");
        timePickerDialog = null;
    }

    @Override
    public Dialog getDialog(){
        return timePickerDialog;
    }

    @Override
    public TimePicker getTimePicker(){
        return timePickerDialog.getTimePicker();
    }

    @Override
    public void updateTime(int hourOfDay, int minute){
        if(timePickerDialog != null){
            timePickerDialog.updateTime(hourOfDay, minute);
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onTimeSet(this, timePicker, hourOfDay, minute);
        }
    }
}
