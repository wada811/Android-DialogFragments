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

package com.wada811.android.dialogfragments.sample;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.wada811.android.dialogfragments.sample.alertdialogfragment.AlertDialogFragmentExamplesActivity;
import com.wada811.android.dialogfragments.sample.datepickerdialogfragment.DatePickerDialogFragmentExamplesActivity;
import com.wada811.android.dialogfragments.sample.dialogfragmentcallbackprovider.DialogFragmentCallbackProviderActivity;
import com.wada811.android.dialogfragments.sample.numberpickerdialogfragment.NumberPickerDialogFragmentExamplesActivity;
import com.wada811.android.dialogfragments.sample.progressdialogfragment.ProgressDialogFragmentExamplesActivity;
import com.wada811.android.dialogfragments.sample.stringpickerdialogfragment.StringPickerDialogFragmentExamplesActivity;
import com.wada811.android.dialogfragments.sample.timepickerdialogfragment.TimePickerDialogFragmentExamplesActivity;
import java.util.ArrayList;

public class ExamplesFragment extends ListFragment {

    private ArrayList<Examples> items;

    public static ExamplesFragment newInstance(){
        ExamplesFragment fragment = new ExamplesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        initExamples();
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items));
    }

    private void initExamples(){
        items = new ArrayList<>();
        {
            Intent intent = new Intent(getActivity(), DialogFragmentCallbackProviderActivity.class);
            items.add(new Examples(getString(R.string.title_activity_dialog_fragment_callback_provider), intent));
        }
        {
            Intent intent = new Intent(getActivity(), AlertDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, true);
            String label = getString(R.string.title_activity_alert_dialog_fragment_examples_in_activity);
            items.add(new Examples(label, intent));
        }
        {
            Intent intent = new Intent(getActivity(), AlertDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, false);
            String label = getString(R.string.title_activity_alert_dialog_fragment_examples_in_fragment);
            items.add(new Examples(label, intent));
        }
        {
            Intent intent = new Intent(getActivity(), ProgressDialogFragmentExamplesActivity.class);
            items.add(new Examples(getString(R.string.title_activity_progress_dialog_fragment_examples), intent));
        }
        {
            Intent intent = new Intent(getActivity(), DatePickerDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, true);
            String label = getString(R.string.title_activity_date_picker_dialog_fragment_examples_in_activity);
            items.add(new Examples(label, intent));
        }
        {
            Intent intent = new Intent(getActivity(), DatePickerDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, false);
            String label = getString(R.string.title_activity_date_picker_dialog_fragment_examples_in_fragment);
            items.add(new Examples(label, intent));
        }
        {
            Intent intent = new Intent(getActivity(), TimePickerDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, true);
            String label = getString(R.string.title_activity_time_picker_dialog_fragment_examples_in_activity);
            items.add(new Examples(label, intent));
        }
        {
            Intent intent = new Intent(getActivity(), TimePickerDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, false);
            String label = getString(R.string.title_activity_time_picker_dialog_fragment_examples_in_fragment);
            items.add(new Examples(label, intent));
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            Intent intent = new Intent(getActivity(), NumberPickerDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, true);
            String label = getString(R.string.title_activity_number_picker_dialog_fragment_examples_in_activity);
            items.add(new Examples(label, intent));
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            Intent intent = new Intent(getActivity(), NumberPickerDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, false);
            String label = getString(R.string.title_activity_number_picker_dialog_fragment_examples_in_fragment);
            items.add(new Examples(label, intent));
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            Intent intent = new Intent(getActivity(), StringPickerDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, true);
            String label = getString(R.string.title_activity_string_picker_dialog_fragment_examples_in_activity);
            items.add(new Examples(label, intent));
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            Intent intent = new Intent(getActivity(), StringPickerDialogFragmentExamplesActivity.class);
            intent.putExtra(Const.KEY_IS_IN_ACTIVITY, false);
            String label = getString(R.string.title_activity_string_picker_dialog_fragment_examples_in_fragment);
            items.add(new Examples(label, intent));
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Examples item = items.get(position);
        startActivity(item.intent);
    }

}
