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

package at.wada811.android.dialogfragments.sample.datepickerdialogfragment.examples;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import java.util.Calendar;
import java.util.Date;
import at.wada811.android.dialogfragments.DatePickerDialogFragment;
import at.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import at.wada811.android.dialogfragments.sample.Example;
import at.wada811.android.dialogfragments.sample.R;

/**
 * Created by wada on 2014/09/07.
 */
public class BasicDatePickerDialogExample extends Example{

    private Context context;

    public BasicDatePickerDialogExample(Context context){
        super(BasicDatePickerDialogExample.class.getSimpleName());
        this.context = context;
    }

    @Override
    public void showDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        DatePickerDialogFragment dialogFragment = DatePickerDialogFragment.newInstance(
            provider,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialogFragment.setIcon(R.drawable.ic_launcher);
        dialogFragment.setTitle(R.string.dialog_title);
        dialogFragment.show(fragmentManager, label);
    }

}
