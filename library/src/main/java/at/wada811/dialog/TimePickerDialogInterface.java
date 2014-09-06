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
package at.wada811.dialog;

import android.widget.TimePicker;

interface TimePickerDialogInterface {
    static final String HOUR = "hour";
    static final String MINUTE = "minute";
    static final String IS_24_HOUR = "is24hour";

    public TimePicker getTimePicker();

    public void updateTime(int hour, int minute);

    /**
     * The callback interface used to indicate the user is done filling in
     * the time (they clicked on the 'Set' button).
     */
    public interface OnTimeSetListener {

        /**
         * @param timePicker The view associated with this listener.
         * @param hour The hour that was set.
         * @param minute The minute that was set.
         */
        void onTimeSet(TimePicker timePicker, int hour, int minute);
    }

}
