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

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;
import com.wada811.android.dialogfragments.material.StringPickerDialogInterface.OnStringSetListener;
import com.wada811.android.dialogfragments.view.StringPicker;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class StringPickerDialogFragment extends AlertDialogFragment
    implements DialogFragmentInterface, StringPickerDialogInterface, OnStringSetListener {

    private static final String VALUES = "values";
    private StringPickerDialog stringPickerDialog;

    private String value;

    public static StringPickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, String[] values){
        return newInstanceInternal(provider, VALUE_NULL, values);
    }

    public static StringPickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, int theme, String[] values){
        return newInstanceInternal(provider, theme, values);
    }

    private static StringPickerDialogFragment newInstanceInternal(DialogFragmentCallbackProvider provider, int theme, String[] values){
        assertListenerBindable(provider);
        StringPickerDialogFragment fragment = new StringPickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(THEME, theme);
        args.putStringArray(VALUES, values);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Bundle args = getArguments();
        final int theme = args.getInt(THEME);
        final String[] values = args.getStringArray(VALUES);

        if(theme == VALUE_NULL){
            stringPickerDialog = new StringPickerDialog(getActivity(), this, values);
        }else{
            stringPickerDialog = new StringPickerDialog(getActivity(), theme, this, values);
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
        setDisplayedValue(value);
        return stringPickerDialog;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.w(TAG, "onDestroyView");
        stringPickerDialog = null;
    }

    @Override
    public Dialog getDialog(){
        return stringPickerDialog;
    }

    @Override
    public StringPicker getStringPicker(){
        return stringPickerDialog.getStringPicker();
    }

    @Override
    public void setDisplayedValue(String value){
        this.value = value;
        if(stringPickerDialog != null){
            stringPickerDialog.setDisplayedValue(value);
        }
    }

    @Override
    public String getDisplayedValue(){
        if(stringPickerDialog == null){
            return value;
        }else{
            return stringPickerDialog.getDisplayedValue();
        }
    }

    @Override
    public void setDisplayedValues(List<String> values){
        setDisplayedValues(values.toArray(new String[values.size()]));
    }

    @Override
    public void setDisplayedValues(String[] values){
        if(stringPickerDialog != null){
            stringPickerDialog.setDisplayedValues(values);
        }
    }

    @Override
    public void onStringSet(StringPicker stringPicker, String value){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onStringSet(this, stringPicker, value);
        }
    }
}
