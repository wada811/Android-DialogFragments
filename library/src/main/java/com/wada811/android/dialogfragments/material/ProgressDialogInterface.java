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

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import java.text.NumberFormat;

interface ProgressDialogInterface {

    /**
     * Creates a ProgressDialog with a circular, spinning progress bar. This is the default.
     */
    int STYLE_SPINNER = ProgressDialog.STYLE_SPINNER;

    /**
     * Creates a ProgressDialog with a horizontal progress bar.
     */
    int STYLE_HORIZONTAL = ProgressDialog.STYLE_HORIZONTAL;

    Dialog getDialog();

    void setProgressStyle(int style);

    void setIcon(int iconId);

    void setTitle(CharSequence message);

    void setTitle(int resId);

    void setMessage(CharSequence message);

    void setIndeterminate(boolean indeterminate);

    boolean isIndeterminate();

    void setIndeterminateDrawable(Drawable d);

    void setMax(int max);

    int getMax();

    void setProgress(int value);

    int getProgress();

    void setSecondaryProgress(int secondaryProgress);

    int getSecondaryProgress();

    void incrementProgressBy(int diff);

    void incrementSecondaryProgressBy(int diff);

    void setProgressDrawable(Drawable d);

    /**
     * Change the format of the small text showing current and maximum units of progress. The default is "%1d/%2d".
     * Should not be called during the number is progressing.
     *
     * @param format A string passed to {@link String#format String.format()}; use "%1d" for the current number and
     * "%2d" for the maximum. If null, nothing will be shown.
     */
    void setProgressNumberFormat(String format);

    /**
     * Change the format of the small text showing the percentage of progress. The default is {@link
     * NumberFormat#getPercentInstance() NumberFormat.getPercentageInstnace().} Should not be called during
     * the number is progressing.
     *
     * @param format An instance of a {@link NumberFormat} to generate the percentage text. If null, nothing
     * will be shown.
     */
    void setProgressPercentFormat(NumberFormat format);

}
