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

package com.wada811.android.dialogfragments.sample.alertdialogfragment.examples.material;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import com.wada811.android.dialogfragments.material.AlertDialogFragment;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.sample.Example;
import com.wada811.android.dialogfragments.sample.R;

public class EventAlertDialogExample extends Example {

    private Context context;

    public EventAlertDialogExample(Context context){
        super(EventAlertDialogExample.class.getSimpleName() + "(Material)");
        this.context = context;
    }

    @Override
    public void showDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager){
        new AlertDialogFragment.Builder(context).setIcon(R.drawable.ic_launcher)
            .setTitle(R.string.dialog_title)
            .setMessage(R.string.dialog_message)
            .setPositiveButton(R.string.dialog_yes, provider)
            .setNegativeButton(R.string.dialog_no, provider)
            .setNeutralButton(R.string.dialog_cancel, provider)
            .setOnShowListener(provider)
            .setOnCancelListener(provider)
            .setOnDismissListener(provider)
            .setOnKeyListener(provider)
            .show(fragmentManager, label);
    }

}
