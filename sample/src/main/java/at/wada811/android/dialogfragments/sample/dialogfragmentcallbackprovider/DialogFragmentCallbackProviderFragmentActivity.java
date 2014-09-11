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

package at.wada811.android.dialogfragments.sample.dialogfragmentcallbackprovider;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import at.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import at.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import at.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;
import at.wada811.android.dialogfragments.interfaces.SimpleDialogFragmentCallback;


public class DialogFragmentCallbackProviderFragmentActivity extends ActionBarActivity
    implements DialogFragmentCallbackProvider{

    final DialogFragmentCallbackProviderFragmentActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new DialogFragmentCallbackProviderFragment())
                .commit();
        }
    }


    @Override
    public DialogFragmentCallback getDialogFragmentCallback(){
        return new SimpleDialogFragmentCallback(){
            @Override
            public View getView(DialogFragmentInterface dialog){
                TextView titleView = new TextView(self);
                titleView.setText("Use getFragmentManager()");
                titleView.setPadding(0, 24, 0, 24);
                titleView.setGravity(Gravity.CENTER);
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                return titleView;
            }
        };
    }
}
