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
package at.wada811.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import at.wada811.dialog.interfaces.DialogFragmentInterface;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;

public class ProgressDialogFragment extends AlertDialogFragment implements DialogFragmentInterface, ProgressDialogInterface {

    final ProgressDialogFragment self = this;
    public static final String TAG = ProgressDialogFragment.class.getSimpleName();

    private static final String PROGRESS_STYLE = "progressStyle";
    private static final String IS_INDETERMINATE = "isInterminate";
    private static final String MAX = "max";
    private static final String PROGRESS_VALUE = "progress";
    private static final String SECONDARY_PROGRESS_VALUE = "secondaryProgress";
    private static final String PROGRESS_NUMBER_FORMAT = "progressNumberFormat";

    private ProgressDialogInterface progressDialog;

    private int theme;
    private int progressStyle;
    private CharSequence message;
    private boolean isIndeterminate;
    private Drawable mIndeterminateDrawable;
    private int max;
    private int progress;
    private int secondaryProgress;
    private Drawable progressDrawable;
    private String progressNumberFormat;
    private NumberFormat progressPercentFormat;
    private ProgressDialogUpdateHandler handler;

    private static final int MSG_TITLE_CHANGED = 0;
    private static final int MSG_MESSAGE_CHANGED = 1;

    private static class ProgressDialogUpdateHandler extends Handler {

        final WeakReference<ProgressDialogFragment> ref;

        protected ProgressDialogUpdateHandler(ProgressDialogFragment dialog) {
            ref = new WeakReference<ProgressDialogFragment>(dialog);
        }

        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            final ProgressDialogFragment fragment = ref.get();
            if(fragment == null || fragment.progressDialog == null){
                return;
            }
            switch(msg.what){
                case MSG_TITLE_CHANGED:
                    fragment.progressDialog.setTitle(fragment.title);
                    break;
                case MSG_MESSAGE_CHANGED:
                    fragment.progressDialog.setMessage(fragment.message);
                    break;
                default:
                    break;
            }

        }
    }

    public ProgressDialogFragment() {
        Log.w(TAG, "ProgressDialogFragment");
    }

    /**
     * @param theme {@link THEME_TRADITIONAL}, {@link THEME_HOLO_LIGHT}, {@link THEME_HOLO_DARK},
     *        {@link THEME_DEVICE_DEFAULT_LIGHT}, {@link THEME_DEVICE_DEFAULT_DARK}
     */
    public ProgressDialogFragment(int theme) {
        Log.w(TAG, "ProgressDialogFragment");
        this.theme = theme;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.w(TAG, "onCreate");
        Log.w(TAG, "onCreate:savedInstanceState: " + savedInstanceState);
        if(savedInstanceState != null){
            theme = savedInstanceState.getInt(THEME, VALUE_NULL);
            progressStyle = savedInstanceState.getInt(PROGRESS_STYLE, STYLE_SPINNER);
            message = savedInstanceState.getCharSequence(MESSAGE);
            isIndeterminate = savedInstanceState.getBoolean(IS_INDETERMINATE, false);
            max = savedInstanceState.getInt(MAX, 100);
            progress = savedInstanceState.getInt(PROGRESS_VALUE, 0);
            secondaryProgress = savedInstanceState.getInt(SECONDARY_PROGRESS_VALUE, 0);
            progressNumberFormat = savedInstanceState.getString(PROGRESS_NUMBER_FORMAT);
        }
        handler = new ProgressDialogUpdateHandler(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Log.w(TAG, "onCreateDialog");
        progressDialog = newProgressDialog(getActivity(), theme);
        progressDialog.setProgressStyle(progressStyle);
        if(iconId != VALUE_NULL){
            progressDialog.setIcon(iconId);
        }
        if(title != null){
            progressDialog.setTitle(title);
        }
        if(titleId != VALUE_NULL){
            progressDialog.setTitle(titleId);
        }
        if(message != null){
            progressDialog.setMessage(message);
        }
        progressDialog.setIndeterminate(isIndeterminate);
        if(mIndeterminateDrawable != null){
            progressDialog.setIndeterminateDrawable(mIndeterminateDrawable);
        }
        if(max > 0){
            progressDialog.setMax(max);
        }
        if(progress > 0){
            progressDialog.setProgress(progress);
        }
        if(secondaryProgress > 0){
            progressDialog.setSecondaryProgress(secondaryProgress);
        }
        if(progressDrawable != null){
            progressDialog.setProgressDrawable(progressDrawable);
        }
        if(progressNumberFormat != null){
            progressDialog.setProgressNumberFormat(progressNumberFormat);
        }
        if(progressPercentFormat != null){
            progressDialog.setProgressPercentFormat(progressPercentFormat);
        }
        return progressDialog.getDialog();
    }

    private static ProgressDialogInterface newProgressDialog(Context context, int theme){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && theme != VALUE_NULL){
            return new ProgressDialogHoneyComb(context, theme);
        }else{
            return new ProgressDialogBase(context);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.w(TAG, "onSaveInstanceState");
        outState.putInt(THEME, theme);
        outState.putInt(PROGRESS_STYLE, progressStyle);
        if(message != null){
            outState.putCharSequence(MESSAGE, message);
        }
        outState.putBoolean(IS_INDETERMINATE, isIndeterminate);
        outState.putInt(MAX, max);
        outState.putInt(PROGRESS_VALUE, progress);
        outState.putInt(SECONDARY_PROGRESS_VALUE, secondaryProgress);
        if(progressNumberFormat != null){
            outState.putString(PROGRESS_NUMBER_FORMAT, progressNumberFormat);
        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.w(TAG, "onDestroyView");
        progressDialog = null;
    }

    @Override
    public void dismiss(){
        new Handler(Looper.getMainLooper()).post(
            new Runnable(){
                @Override
                public void run(){
                    ProgressDialogFragment.super.dismiss();
                }
            }
        );
    }

    @Override
    public void dismissAllowingStateLoss(){
        new Handler(Looper.getMainLooper()).post(
            new Runnable(){
                @Override
                public void run(){
                    ProgressDialogFragment.super.dismissAllowingStateLoss();
                }
            }
        );
    }

    @Override
    public void setProgressStyle(int style){
        progressStyle = style;
        if(progressDialog != null){
            progressDialog.setProgressStyle(style);
        }
    }

    @Override
    public void setTitle(CharSequence title){
        this.title = title;
        if(handler != null && !handler.hasMessages(MSG_TITLE_CHANGED) && progressDialog != null){
            handler.sendEmptyMessage(MSG_TITLE_CHANGED);
        }
    }

    @Override
    public void setTitle(int titleId){
        this.titleId = titleId;
        if(isAdded()){
            setTitle(getString(titleId));
        }
    }

    @Override
    public void setMessage(CharSequence message){
        this.message = message;
        if(handler != null && !handler.hasMessages(MSG_MESSAGE_CHANGED) && progressDialog != null){
            handler.sendEmptyMessage(MSG_MESSAGE_CHANGED);
        }
    }

    @Override
    public void setIndeterminate(boolean indeterminate){
        isIndeterminate = indeterminate;
        if(progressDialog != null){
            progressDialog.setIndeterminate(indeterminate);
        }
    }

    @Override
    public boolean isIndeterminate(){
        return progressDialog != null ? progressDialog.isIndeterminate() : isIndeterminate;
    }

    @Override
    public void setIndeterminateDrawable(Drawable indeterminateDrawable){
        mIndeterminateDrawable = indeterminateDrawable;
        if(progressDialog != null){
            progressDialog.setIndeterminateDrawable(indeterminateDrawable);
        }
    }

    @Override
    public void setMax(int max){
        this.max = max;
        if(progressDialog != null){
            progressDialog.setMax(max);
        }
    }

    @Override
    public int getMax(){
        return progressDialog != null ? progressDialog.getMax() : max;
    }

    @Override
    public void setProgress(int progress){
        this.progress = progress;
        if(progressDialog != null){
            progressDialog.setProgress(progress);
        }
    }

    @Override
    public int getProgress(){
        return progressDialog != null ? progressDialog.getProgress() : progress;
    }

    @Override
    public void setSecondaryProgress(int secondaryProgress){
        this.secondaryProgress = secondaryProgress;
        if(progressDialog != null){
            progressDialog.setSecondaryProgress(secondaryProgress);
        }
    }

    @Override
    public int getSecondaryProgress(){
        return progressDialog != null ? progressDialog.getSecondaryProgress() : secondaryProgress;
    }

    @Override
    public void incrementProgressBy(int diff){
        progress += diff;
        if(progressDialog != null){
            progressDialog.incrementProgressBy(diff);
        }
    }

    @Override
    public void incrementSecondaryProgressBy(int diff){
        secondaryProgress += diff;
        if(progressDialog != null){
            progressDialog.incrementSecondaryProgressBy(diff);
        }
    }

    @Override
    public void setProgressDrawable(Drawable progressDrawable){
        this.progressDrawable = progressDrawable;
        if(progressDialog != null){
            progressDialog.setProgressDrawable(progressDrawable);
        }
    }

    @Override
    public void setProgressNumberFormat(String progressNumberFormat){
        this.progressNumberFormat = progressNumberFormat;
        if(progressDialog != null){
            progressDialog.setProgressNumberFormat(progressNumberFormat);
        }
    }

    @Override
    public void setProgressPercentFormat(NumberFormat progressPercentFormat){
        this.progressPercentFormat = progressPercentFormat;
        if(progressDialog != null){
            progressDialog.setProgressPercentFormat(progressPercentFormat);
        }
    }

}
