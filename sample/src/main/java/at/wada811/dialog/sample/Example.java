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

package at.wada811.dialog.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;

/**
 * Created by wada on 2014/09/07.
 */
public abstract class Example {

    public String label;

    public Example(String label){
        this.label = label;
    }

    public abstract void showDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager);

    @Override
    public String toString() {
        return label;
    }
}
