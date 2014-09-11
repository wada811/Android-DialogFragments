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
package at.wada811.dialog.sample.progressdialogfragment.examples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import at.wada811.dialog.sample.Const;
import at.wada811.dialog.sample.R;


public class LoaderSpinnerProgressDialogFragmentExamplesFragmentActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(R.string.example_spinner_progress_dialog_with_loader_in_fragment);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(
                android.R.id.content,
                LoaderSpinnerProgressDialogFragmentExamplesFragment.newInstance()
            ).commit();
        }
    }

}
