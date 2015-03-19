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
package com.wada811.android.dialogfragments.sample.progressdialogfragment.examples;

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
import com.wada811.android.dialogfragments.ProgressDialogFragment;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;
import com.wada811.android.dialogfragments.interfaces.SimpleDialogFragmentCallback;
import com.wada811.android.dialogfragments.sample.R;

public class LoaderSpinnerProgressDialogFragmentExamplesFragment extends ListFragment
    implements DialogFragmentCallbackProvider, LoaderCallbacks<Object> {

    private static final String TAG = LoaderSpinnerProgressDialogFragmentExamplesActivity.class.getSimpleName();
    private static final int LOADER_ID = 811;

    public static LoaderSpinnerProgressDialogFragmentExamplesFragment newInstance(){
        LoaderSpinnerProgressDialogFragmentExamplesFragment fragment = new LoaderSpinnerProgressDialogFragmentExamplesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ArrayList<String> items = new ArrayList<>();
        items.add("Start Loader");
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getLoaderManager().getLoader(LOADER_ID) != null){
            getLoaderManager().initLoader(LOADER_ID, null, this);
            Log.i(TAG, "re-init: " + LOADER_ID);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback() {
            @Override
            public void onCancel(DialogFragmentInterface dialog){
                Log.i(TAG, "DialogFragmentCallback#onCancel");
                getLoaderManager().destroyLoader(LOADER_ID);
            }
        };
    }

    @Override
    public Loader<Object> onCreateLoader(int i, Bundle bundle){
        Log.i(TAG, "onCreateLoader");
        ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.setIcon(R.drawable.ic_launcher);
        dialogFragment.setTitle(R.string.title_activity_progress_dialog_fragment_with_loader_in_fragment);
        dialogFragment.setProgressStyle(ProgressDialogFragment.STYLE_SPINNER);
        dialogFragment.setMessage("Loading...");
        dialogFragment.setOnCancelListener(this);
        dialogFragment.show(getChildFragmentManager(), ProgressDialogFragment.TAG);
        return new AsyncSleepLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Object> objectLoader, Object o){
        Log.i(TAG, "onLoadFinished");
        getLoaderManager().destroyLoader(objectLoader.getId());
        Fragment fragment = getChildFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG);
        if(fragment != null){
            ProgressDialogFragment dialogFragment = (ProgressDialogFragment)fragment;
            dialogFragment.dismissAllowingStateLoss();
        }
    }

    @Override
    public void onLoaderReset(Loader<Object> objectLoader){
        Log.i(TAG, "onLoaderReset");
        getLoaderManager().destroyLoader(objectLoader.getId());
        Fragment fragment = getChildFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG);
        if(fragment != null){
            ProgressDialogFragment dialogFragment = (ProgressDialogFragment)fragment;
            dialogFragment.dismissAllowingStateLoss();
        }
    }
}
