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
package com.wada811.android.dialogfragments.material;

import android.widget.TimePicker;

interface TimePickerDialogInterface {

    String HOUR = "hour";
    String MINUTE = "minute";
    String IS_24_HOUR = "is24hour";

    TimePicker getTimePicker();

    void updateTime(int hour, int minute);

    /**
     * The callback interface used to indicate the user is done filling in the time (they clicked on the 'Set' button).
     */
    interface OnTimeSetListener {

        /**
         * @param timePicker The view associated with this listener.
         * @param hour The hour that was set.
         * @param minute The minute that was set.
         */
        void onTimeSet(TimePicker timePicker, int hour, int minute);
    }

}
