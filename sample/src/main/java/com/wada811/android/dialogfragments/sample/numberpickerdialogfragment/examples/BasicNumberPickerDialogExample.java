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

package com.wada811.android.dialogfragments.sample.numberpickerdialogfragment.examples;

import android.support.v4.app.FragmentManager;
import com.wada811.android.dialogfragments.traditional.NumberPickerDialogFragment;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.sample.Example;
import com.wada811.android.dialogfragments.sample.R;

public class BasicNumberPickerDialogExample extends Example {

    public BasicNumberPickerDialogExample(){
        super(BasicNumberPickerDialogExample.class.getSimpleName());
    }

    @Override
    public void showDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager){
        int value = 1;
        int min = 1;
        int max = 10;
        NumberPickerDialogFragment dialogFragment = NumberPickerDialogFragment.newInstance(provider, value, min, max);
        dialogFragment.setIcon(R.drawable.ic_launcher);
        dialogFragment.setTitle(R.string.dialog_title);
        dialogFragment.show(fragmentManager, label);
    }

}
