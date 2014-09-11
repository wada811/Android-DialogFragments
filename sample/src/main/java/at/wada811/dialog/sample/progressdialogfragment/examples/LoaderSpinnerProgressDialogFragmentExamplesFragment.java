/*
 * Copyright 2014 wada811<at.wada811@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.wada811.dialog.sample.progressdialogfragment.examples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import at.wada811.dialog.ProgressDialogFragment;
import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import at.wada811.dialog.interfaces.SimpleDialogFragmentCallback;
import at.wada811.dialog.sample.R;


public class LoaderSpinnerProgressDialogFragmentExamplesFragment extends ListFragment
    implements DialogFragmentCallbackProvider, LoaderCallbacks<Object>{

    private static final int LOADER_ID = 811;

    public static LoaderSpinnerProgressDialogFragmentExamplesFragment newInstance(boolean isRetain){
        LoaderSpinnerProgressDialogFragmentExamplesFragment fragment = new LoaderSpinnerProgressDialogFragmentExamplesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setRetainInstance(isRetain);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ArrayList<String> items = new ArrayList<String>();
        items.add("Start Loader");
        setListAdapter(
            new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items)
        );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getLoaderManager().getLoader(LOADER_ID) != null){
            getLoaderManager().initLoader(LOADER_ID, null, this);
            Log.i(
                LoaderSpinnerProgressDialogFragmentExamplesActivity.class.getSimpleName(),
                "re-init: " + LOADER_ID
            );
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback(){
            @Override
            public void onCancel(DialogFragmentInterface dialog){
                Log.i(
                    LoaderSpinnerProgressDialogFragmentExamplesFragment.class.getSimpleName(),
                    "DialogFragmentCallback#onCancel"
                );
                getLoaderManager().destroyLoader(LOADER_ID);
            }
        };
    }

    @Override
    public Loader<Object> onCreateLoader(int i, Bundle bundle){
        Log.i(
            LoaderSpinnerProgressDialogFragmentExamplesFragment.class.getSimpleName(),
            "onCreateLoader"
        );
        ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.setRetainInstance(true);
        dialogFragment.setIcon(R.drawable.ic_launcher);
        dialogFragment.setTitle(R.string.example_spinner_progress_dialog_with_loader);
        dialogFragment.setProgressStyle(ProgressDialogFragment.STYLE_SPINNER);
        dialogFragment.setMessage("Loading...");
        dialogFragment.setOnCancelListener(this);
        dialogFragment.show(getChildFragmentManager(), ProgressDialogFragment.TAG);
        return new AsyncSleepLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Object> objectLoader, Object o){
        Log.i(
            LoaderSpinnerProgressDialogFragmentExamplesFragment.class.getSimpleName(),
            "onLoadFinished"
        );
        getLoaderManager().destroyLoader(objectLoader.getId());
        Fragment fragment = getChildFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG);
        if(fragment != null){
            ProgressDialogFragment dialogFragment = (ProgressDialogFragment)fragment;
            dialogFragment.dismiss();
        }
    }

    @Override
    public void onLoaderReset(Loader<Object> objectLoader){
        Log.i(
            LoaderSpinnerProgressDialogFragmentExamplesFragment.class.getSimpleName(),
            "onLoaderReset"
        );
        getLoaderManager().destroyLoader(objectLoader.getId());
        Fragment fragment = getChildFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG);
        if(fragment != null){
            ProgressDialogFragment dialogFragment = (ProgressDialogFragment)fragment;
            dialogFragment.dismiss();
        }
    }
}
