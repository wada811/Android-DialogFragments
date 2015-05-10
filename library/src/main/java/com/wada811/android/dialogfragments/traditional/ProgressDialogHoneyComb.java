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

package com.wada811.android.dialogfragments.traditional;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * <p> A dialog showing a progress indicator and an optional text message or view. Only a text message or a view can be
 * used at the same time. </p> <p> The dialog can be made cancelable on back key press. </p> <p> The progress range is
 * 0..10000. </p>
 */
class ProgressDialogHoneyComb extends ProgressDialog implements ProgressDialogInterface {

    public ProgressDialogHoneyComb(Context context){
        super(context);
    }

    public ProgressDialogHoneyComb(Context context, int theme){
        super(context, theme);
    }

    @Override
    public Dialog getDialog(){
        return this;
    }

}
