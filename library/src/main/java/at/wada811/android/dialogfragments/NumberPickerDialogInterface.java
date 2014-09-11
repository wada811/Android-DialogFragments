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
package at.wada811.android.dialogfragments;

import android.widget.NumberPicker;

public interface NumberPickerDialogInterface {

    static final String VALUE = "value";
    static final String MIN_VALUE = "minValue";
    static final String MAX_VALUE = "maxValue";

    public NumberPicker getNumberPicker();

    public void updateNumber(int value);

    /**
     * The callback interface used to indicate the user is done filling in
     * the time (they clicked on the 'Set' button).
     */
    public interface OnNumberSetListener {

        /**
         * @param numberPicker The view associated with this listener.
         * @param value The value that was set.
         */
        void onNumberSet(NumberPicker numberPicker, int value);
    }

}
