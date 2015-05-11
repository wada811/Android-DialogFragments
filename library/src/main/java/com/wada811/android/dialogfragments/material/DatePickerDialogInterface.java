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

import android.widget.DatePicker;

interface DatePickerDialogInterface {

    String YEAR = "year";
    String MONTH = "month";
    String DAY = "day";

    DatePicker getDatePicker();

    void updateDate(int year, int monthOfYear, int day);

    /**
     * The callback used to indicate the user is done filling in the date.
     */
    interface OnDateSetListener {

        /**
         * @param view The view associated with this listener.
         * @param year The year that was set.
         * @param month The month that was set (0-11) for compatibility with {@link java.util.Calendar}.
         * @param day The day of the month that was set.
         */
        void onDateSet(DatePicker view, int year, int month, int day);
    }

}
