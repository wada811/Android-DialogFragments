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

package at.wada811.dialog.sample.stringpickerdialogfragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;
import java.util.ArrayList;
import at.wada811.dialog.StringPicker;
import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import at.wada811.dialog.interfaces.SimpleDialogFragmentCallback;
import at.wada811.dialog.sample.Const;
import at.wada811.dialog.sample.Example;
import at.wada811.dialog.sample.stringpickerdialogfragment.examples.BasicStringPickerDialogExample;


public class StringPickerDialogFragmentExamplesFragment extends ListFragment
    implements DialogFragmentCallbackProvider{

    private ArrayList<Example> items;

    public static StringPickerDialogFragmentExamplesFragment newInstance(boolean isInActivity){
        StringPickerDialogFragmentExamplesFragment fragment = new StringPickerDialogFragmentExamplesFragment();
        Bundle args = new Bundle();
        args.putBoolean(Const.KEY_IS_IN_ACTIVITY, isInActivity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initExample();
        initListFragment();
    }

    private void initExample(){
        items = new ArrayList<Example>();
        items.add(new BasicStringPickerDialogExample(getActivity()));
    }

    private void initListFragment(){
        setListAdapter(
            new ArrayAdapter<Example>(
                getActivity(), android.R.layout.simple_list_item_1, items
            )
        );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Example item = items.get(position);
        boolean isInActivity = getArguments().getBoolean(Const.KEY_IS_IN_ACTIVITY);
        item.showDialog(this, isInActivity ? getFragmentManager() : getChildFragmentManager());
    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback(){
            @Override
            public void onStringSet(DialogFragmentInterface dialog, StringPicker stringPicker, String value){
                String text = "onStringSet[value: " + value + "]";
                Log.v(dialog.getTag(), text);
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
