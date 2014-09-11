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

package at.wada811.dialog.sample.progressdialogfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import at.wada811.dialog.sample.Examples;
import at.wada811.dialog.sample.R;
import at.wada811.dialog.sample.progressdialogfragment.examples.LoaderSpinnerProgressDialogFragmentExamplesActivity;
import at.wada811.dialog.sample.progressdialogfragment.examples.LoaderSpinnerProgressDialogFragmentExamplesFragmentActivity;


public class ProgressDialogFragmentExamplesFragment extends ListFragment{

    private ArrayList<Examples> items;

    public static ProgressDialogFragmentExamplesFragment newInstance(){
        ProgressDialogFragmentExamplesFragment fragment = new ProgressDialogFragmentExamplesFragment();
        Bundle args = new Bundle();
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
        items = new ArrayList<Examples>();
        items.add(
            new Examples(
                getString(R.string.title_activity_progress_dialog_fragment_with_loader_in_activity),
                new Intent(
                    getActivity(), LoaderSpinnerProgressDialogFragmentExamplesActivity.class
                )
            )
        );
        {
            items.add(
                new Examples(
                    getString(R.string.title_activity_progress_dialog_fragment_with_loader_in_fragment),
                    new Intent(
                        getActivity(),
                        LoaderSpinnerProgressDialogFragmentExamplesFragmentActivity.class
                    )
                )
            );
        }
    }

    private void initListFragment(){
        setListAdapter(
            new ArrayAdapter<Examples>(
                getActivity(), android.R.layout.simple_list_item_1, items
            )
        );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Examples item = items.get(position);
        startActivity(item.intent);
    }

}
