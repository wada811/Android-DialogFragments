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

package at.wada811.dialog.sample.dialogfragmentcallbackprovider;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import at.wada811.dialog.interfaces.SimpleDialogFragmentCallback;
import at.wada811.dialog.sample.Example;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.dialogfragmentcallbackprovider.examples.DialogFragmentCallbackInActivityExample;
import at.wada811.dialog.sample.dialogfragmentcallbackprovider.examples.DialogFragmentCallbackInFragmentExample;


public class DialogFragmentCallbackProviderFragment extends ListFragment
    implements DialogFragmentCallbackProvider{

    private ArrayList<Example> items;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        initExample();
        initListFragment();
    }

    private void initExample(){
        items = new ArrayList<Example>();
        items.add(new DialogFragmentCallbackInActivityExample(getActivity()));
        items.add(new DialogFragmentCallbackInFragmentExample(getActivity()));
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
        if(item instanceof DialogFragmentCallbackInActivityExample){
            item.showDialog(this, getFragmentManager());
        }else if(item instanceof DialogFragmentCallbackInFragmentExample){
            item.showDialog(this, getChildFragmentManager());
        }
    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback(){
            @Override
            public View getView(DialogFragmentInterface dialog){
                TextView titleView = new TextView(getActivity());
                titleView.setText("Use getChildFragmentManager()");
                titleView.setPadding(0, 24, 0, 24);
                titleView.setGravity(Gravity.CENTER);
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                return titleView;
            }
        };
    }
}
