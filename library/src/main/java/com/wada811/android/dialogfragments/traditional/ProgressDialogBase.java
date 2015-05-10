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

/*
 * Modified from: android.app.ProgressDialog
 *  (The Android Open Source Project: android-2.3.7_r1)
 */

package com.wada811.android.dialogfragments.traditional;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;

/**
 * <p> A dialog showing a progress indicator and an optional text message or view. Only a text message or a view can be
 * used at the same time. </p> <p> The dialog can be made cancelable on back key press. </p> <p> The progress range is
 * 0..10000. </p>
 */
final class ProgressDialogBase extends AlertDialog implements ProgressDialogInterface {

    protected ProgressBar progressBar;
    protected TextView messageView;

    protected int progressStyle = STYLE_SPINNER;
    protected TextView progressNumber;
    protected String progressNumberFormat;
    protected TextView progressPercent;
    protected NumberFormat progressPercentFormat;

    protected int iconId;
    protected CharSequence title;
    protected CharSequence message;
    protected boolean isIndeterminate;
    protected Drawable indeterminateDrawable;
    protected int max;
    protected int progress;
    protected int secondaryProgress;
    protected int incrementBy;
    protected int incrementSecondaryBy;
    protected Drawable progressDrawable;

    protected boolean hasStarted;
    protected Handler viewUpdateHandler;

    public ProgressDialogBase(Context context){
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setIcon(iconId);
        if(title != null){
            setTitle(title);
        }
        if(progressStyle == STYLE_HORIZONTAL){
            /*
             * Use a separate handler to update the text views as they
             * must be updated on the same thread that created them.
             */
            viewUpdateHandler = new ViewUpdateHandler(this);
            setView(getHorizontalView());
        }else{
            setView(getSpinnerView());
        }
        if(message != null){
            setMessage(message);
        }
        setIndeterminate(isIndeterminate);
        if(indeterminateDrawable != null){
            setIndeterminateDrawable(indeterminateDrawable);
        }
        if(max > 0){
            setMax(max);
        }
        if(progress > 0){
            setProgress(progress);
        }
        if(secondaryProgress > 0){
            setSecondaryProgress(secondaryProgress);
        }
        if(incrementBy > 0){
            incrementProgressBy(incrementBy);
        }
        if(incrementSecondaryBy > 0){
            incrementSecondaryProgressBy(incrementSecondaryBy);
        }
        if(progressDrawable != null){
            setProgressDrawable(progressDrawable);
        }
        onProgressChanged();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart(){
        super.onStart();
        hasStarted = true;
    }

    @Override
    protected void onStop(){
        super.onStop();
        hasStarted = false;
    }

    @Override
    public Dialog getDialog(){
        return this;
    }

    public void setProgressStyle(int style){
        progressStyle = style;
    }

    @Override
    public void setIcon(int iconId){
        super.setIcon(iconId);
    }

    @Override
    public void setTitle(CharSequence title){
        super.setTitle(title);
    }

    @Override
    public void setTitle(int titleId){
        setTitle(getContext().getString(titleId));
    }

    @Override
    public void setMessage(CharSequence message){
        if(progressBar != null){
            if(progressStyle == STYLE_HORIZONTAL){
                super.setMessage(message);
            }else{
                messageView.setText(message);
            }
        }else{
            this.message = message;
        }
    }

    public void setIndeterminate(boolean indeterminate){
        if(progressBar != null){
            progressBar.setIndeterminate(indeterminate);
        }else{
            isIndeterminate = indeterminate;
        }
    }

    public boolean isIndeterminate(){
        if(progressBar != null){
            return progressBar.isIndeterminate();
        }
        return isIndeterminate;
    }

    public void setIndeterminateDrawable(Drawable d){
        if(progressBar != null){
            progressBar.setIndeterminateDrawable(d);
        }else{
            indeterminateDrawable = d;
        }
    }

    public void setMax(int max){
        if(progressBar != null){
            progressBar.setMax(max);
            onProgressChanged();
        }else{
            this.max = max;
        }
    }

    public int getMax(){
        if(progressBar != null){
            return progressBar.getMax();
        }
        return max;
    }

    public void setProgress(int progress){
        if(hasStarted){
            progressBar.setProgress(progress);
            onProgressChanged();
        }else{
            this.progress = progress;
        }
    }

    public int getProgress(){
        if(progressBar != null){
            return progressBar.getProgress();
        }
        return progress;
    }

    public void setSecondaryProgress(int secondaryProgress){
        if(progressBar != null){
            progressBar.setSecondaryProgress(secondaryProgress);
            onProgressChanged();
        }else{
            this.secondaryProgress = secondaryProgress;
        }
    }

    public int getSecondaryProgress(){
        if(progressBar != null){
            return progressBar.getSecondaryProgress();
        }
        return secondaryProgress;
    }

    public void incrementProgressBy(int diff){
        if(progressBar != null){
            progressBar.incrementProgressBy(diff);
            onProgressChanged();
        }else{
            incrementBy += diff;
        }
    }

    public void incrementSecondaryProgressBy(int diff){
        if(progressBar != null){
            progressBar.incrementSecondaryProgressBy(diff);
            onProgressChanged();
        }else{
            incrementSecondaryBy += diff;
        }
    }

    public void setProgressDrawable(Drawable progressDrawable){
        if(progressBar != null){
            progressBar.setProgressDrawable(progressDrawable);
        }else{
            this.progressDrawable = progressDrawable;
        }
    }

    /**
     * Change the format of the small text showing current and maximum units of progress. The default is "%1d/%2d".
     * Should not be called during the number is progressing.
     *
     * @param format A string passed to {@link String#format String.format()}; use "%1d" for the current number and
     * "%2d" for the maximum. If null, nothing will be shown.
     */
    public void setProgressNumberFormat(String format){
        progressNumberFormat = format;
        onProgressChanged();
    }

    /**
     * Change the format of the small text showing the percentage of progress. The default is {@link
     * java.text.NumberFormat#getPercentInstance() NumberFormat.getPercentageInstnace().} Should not be called during
     * the number is progressing.
     *
     * @param format An instance of a {@link java.text.NumberFormat} to generate the percentage text. If null, nothing
     * will be shown.
     */
    public void setProgressPercentFormat(NumberFormat format){
        progressPercentFormat = format;
        onProgressChanged();
    }

    protected void onProgressChanged(){
        if(progressStyle == STYLE_HORIZONTAL){
            if(viewUpdateHandler != null && !viewUpdateHandler.hasMessages(0)){
                viewUpdateHandler.sendEmptyMessage(0);
            }
        }
    }

    private static class ViewUpdateHandler extends Handler {

        private WeakReference<ProgressDialogBase> weakReference;

        public ViewUpdateHandler(ProgressDialogBase progressDialog){
            weakReference = new WeakReference<>(progressDialog);
        }

        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            ProgressDialogBase progressDialog = weakReference.get();
            if(progressDialog == null){
                return;
            }
            progressDialog.viewUpdate();
        }
    }

    protected void viewUpdate(){
        /* Update the number and percent */
        int progress = progressBar.getProgress();
        int max = progressBar.getMax();
        if(progressNumberFormat != null){
            String format = progressNumberFormat;
            progressNumber.setText(String.format(format, progress, max));
        }else{
            progressNumber.setText("");
        }
        if(progressPercentFormat != null){
            double percent = (double)progress / (double)max;
            SpannableString spannableString = new SpannableString(progressPercentFormat.format(percent));
            StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
            spannableString.setSpan(styleSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            progressPercent.setText(spannableString);
        }else{
            progressPercent.setText("");
        }
    }

    protected View getSpinnerView(){
        final Context context = getContext();
        final float density = context.getResources().getDisplayMetrics().density;

        final FrameLayout root = new FrameLayout(context);
        root.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        final int paddingH = (int)(8 * density);
        final int paddingV = (int)(10 * density);
        final LinearLayout container = new LinearLayout(context);
        container.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setBaselineAligned(false);
        container.setPadding(paddingH, paddingV, paddingH, paddingV);
        root.addView(container);

        final LinearLayout.LayoutParams progressParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT);
        progressParams.rightMargin = (int)(12 * density);
        final ProgressBar progress = progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyle);
        progress.setLayoutParams(progressParams);
        progress.setId(android.R.id.progress);
        progress.setMax(10000);
        container.addView(progress);

        final LinearLayout.LayoutParams messageParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT);
        messageParams.gravity = Gravity.CENTER_VERTICAL;
        final TextView message = messageView = new TextView(context);
        message.setLayoutParams(messageParams);
        message.setId(android.R.id.message);
        container.addView(message);

        return root;
    }

    protected View getHorizontalView(){
        final Context context = getContext();
        final float density = context.getResources().getDisplayMetrics().density;

        final RelativeLayout root = new RelativeLayout(context);
        root.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

        final int marginH = (int)(10 * density);
        final int marginT = (int)(12 * density);
        final RelativeLayout.LayoutParams progressParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT);
        progressParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        progressParams.topMargin = marginT;
        progressParams.bottomMargin = (int)(density + 0.5f);
        progressParams.leftMargin = marginH;
        progressParams.rightMargin = marginH;
        final ProgressBar progress = progressBar = new ProgressBar(context,
            null,
            android.R.attr.progressBarStyleHorizontal);
        progress.setLayoutParams(progressParams);
        progress.setId(android.R.id.progress);
        root.addView(progress);

        final RelativeLayout.LayoutParams percentParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT);
        percentParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        percentParams.addRule(RelativeLayout.BELOW, android.R.id.progress);
        percentParams.leftMargin = marginH;
        percentParams.rightMargin = marginH;
        final TextView percent = progressPercent = new TextView(context);
        percent.setLayoutParams(percentParams);
        root.addView(percent);

        final RelativeLayout.LayoutParams numberParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT);
        numberParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        numberParams.addRule(RelativeLayout.BELOW, android.R.id.progress);
        numberParams.leftMargin = marginH;
        numberParams.rightMargin = marginH;
        final TextView number = progressNumber = new TextView(context);
        number.setLayoutParams(numberParams);
        root.addView(number);

        return root;
    }

}
