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
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.NumberPicker;
import com.wada811.android.dialogfragments.NumberPickerDialogInterface.OnNumberSetListener;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NumberPickerDialogFragment extends AlertDialogFragment
    implements DialogFragmentInterface, NumberPickerDialogInterface, OnNumberSetListener {

    private NumberPickerDialog numberPickerDialog;

    public static NumberPickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, int value, int minValue, int maxValue){
        return newInstanceInternal(provider, VALUE_NULL, value, minValue, maxValue);
    }

    public static NumberPickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, int theme, int value, int minValue, int maxValue){
        return newInstanceInternal(provider, theme, value, minValue, maxValue);
    }

    public static NumberPickerDialogFragment newInstanceInternal(DialogFragmentCallbackProvider provider, int theme, int value, int minValue, int maxValue){
        assertListenerBindable(provider);
        NumberPickerDialogFragment fragment = new NumberPickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(THEME, theme);
        args.putInt(VALUE, value);
        args.putInt(MIN_VALUE, minValue);
        args.putInt(MAX_VALUE, maxValue);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.w(TAG, "onCreate");
        Log.w(TAG, "onCreate:savedInstanceState: " + savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Bundle args = getArguments();
        final int theme = args.getInt(THEME);
        final int value = args.getInt(VALUE);
        final int minValue = args.getInt(MIN_VALUE);
        final int maxValue = args.getInt(MAX_VALUE);

        if(theme == VALUE_NULL){
            numberPickerDialog = new NumberPickerDialog(getActivity(), this, value, minValue, maxValue);
        }else{
            numberPickerDialog = new NumberPickerDialog(getActivity(), theme, this, value, minValue, maxValue);
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
        return numberPickerDialog;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.w(TAG, "onSaveInstanceState");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.w(TAG, "onDestroyView");
        numberPickerDialog = null;
    }

    @Override
    public Dialog getDialog(){
        return numberPickerDialog;
    }

    @Override
    public NumberPicker getNumberPicker(){
        return numberPickerDialog.getNumberPicker();
    }

    @Override
    public void updateNumber(int value){
        if(numberPickerDialog != null){
            numberPickerDialog.updateNumber(value);
        }

    }

    @Override
    public void onNumberSet(NumberPicker numberPicker, int value){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onNumberSet(self, numberPicker, value);
        }
    }
}
