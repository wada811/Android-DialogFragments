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
package com.wada811.android.dialogfragments.interfaces;

import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import com.wada811.android.dialogfragments.view.StringPicker;

public interface DialogFragmentCallback {

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is shown.
     */
    /**
     * This method will be invoked when the dialog is shown.
     *
     * @param dialog The dialog that was shown will be passed into the method.
     */
    void onShow(DialogFragmentInterface dialog);

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is canceled.
     * <p>
     * This will only be called when the dialog is canceled, if the creator needs to know when it is
     * dismissed in general, use {@link CommonDialogInterface.OnDismissListener}.
     */
    /**
     * This method will be invoked when the dialog is canceled.
     *
     * @param dialog The dialog that was canceled will be passed into the method.
     */
    void onCancel(DialogFragmentInterface dialog);

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is dismissed.
     */
    /**
     * This method will be invoked when the dialog is dismissed.
     *
     * @param dialog The dialog that was dismissed will be passed into the method.
     */
    void onDismiss(DialogFragmentInterface dialog);

    /**
     * Interface used to allow the creator of a dialog to run some code when an
     * item on the dialog is clicked..
     */

    /**
     * This method will be invoked when a positive button in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     */
    void onClickPositive(DialogFragmentInterface dialog);

    /**
     * This method will be invoked when a negative button in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     */
    void onClickNegative(DialogFragmentInterface dialog);

    /**
     * This method will be invoked when a neutral button in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     */
    void onClickNeutral(DialogFragmentInterface dialog);

    /**
     * This method will be invoked when an item in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     * @param position The position of the item clicked.
     */
    void onItemClick(DialogFragmentInterface dialog, int position);

    /**
     * This method will be invoked when an item in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     * @param position The position of the item clicked.
     */
    void onSingleChoiceClick(DialogFragmentInterface dialog, int position);

    /**
     * Interface used to allow the creator of a dialog to run some code when an
     * item in a multi-choice dialog is clicked.
     */
    /**
     * This method will be invoked when an item in the dialog is clicked.
     *
     * @param dialog The dialog where the selection was made.
     * @param position The position of the item in the list that was clicked.
     * @param isChecked True if the click checked the item, else false.
     */
    void onMultiChoiceClick(DialogFragmentInterface dialog, int position, boolean isChecked);

    /**
     * Interface definition for a callback to be invoked when a key event is
     * dispatched to this dialog. The callback will be invoked before the key
     * event is given to the dialog.
     */
    /**
     * Called when a key is dispatched to a dialog. This allows listeners to get a chance to respond before the dialog.
     *
     * @param dialog The dialog the key has been dispatched to.
     * @param keyCode The code for the physical key that was pressed
     * @param event The KeyEvent object containing full information about the event.
     *
     * @return True if the listener has consumed the event, false otherwise.
     */
    boolean onKey(DialogFragmentInterface dialog, int keyCode, KeyEvent event);

    /**
     * Delegate for dialog custom title view.
     */
    View getCustomTitle(DialogFragmentInterface dialog);

    /**
     * Delegate for dialog view.
     */
    View getView(DialogFragmentInterface dialog);

    /**
     * Delegate for list adapter bindings.
     */
    ListAdapter getAdapter(DialogFragmentInterface dialog);

    /**
     * @param dialog The dialog associated with this listener.
     * @param datePicker The datePicker associated with this listener.
     * @param year The year that was set.
     * @param monthOfYear The month that was set (0-11) for compatibility with {@link java.util.Calendar}.
     * @param dayOfMonth The day of the month that was set.
     */
    void onDateSet(DialogFragmentInterface dialog, DatePicker datePicker, int year, int monthOfYear, int dayOfMonth);

    /**
     * @param dialog The dialog associated with this listener.
     * @param timePicker The timePicker associated with this listener.
     * @param hourOfDay The hour that was set.
     * @param minute The minute that was set.
     */
    void onTimeSet(DialogFragmentInterface dialog, TimePicker timePicker, int hourOfDay, int minute);

    /**
     * @param dialog The dialog associated with this listener.
     * @param numberPicker The numberPicker associated with this listener.
     * @param value The value that was set.
     */
    void onNumberSet(DialogFragmentInterface dialog, NumberPicker numberPicker, int value);

    /**
     * @param dialog The dialog associated with this listener.
     * @param stringPicker The stringPicker associated with this listener.
     * @param value The value that was set.
     */
    void onStringSet(DialogFragmentInterface dialog, StringPicker stringPicker, String value);

}
