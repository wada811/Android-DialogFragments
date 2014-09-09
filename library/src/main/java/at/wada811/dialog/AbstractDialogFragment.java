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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import at.wada811.dialog.interfaces.DialogFragmentCallback;
import at.wada811.dialog.interfaces.DialogFragmentCallbackProvider;
import at.wada811.dialog.interfaces.DialogFragmentInterface;

abstract class AbstractDialogFragment extends DialogFragment implements DialogFragmentInterface {

    /**
     * Value for {@code null} or not set.
     */
    static final int VALUE_NULL = 0;
    /**
     * Value for {@code true}.
     */
    static final int VALUE_TRUE = 1;
    /**
     * Value for {@code false}.
     */
    static final int VALUE_FALSE = 2;

    protected static final String ICON_ID = "iconId";
    protected static final String TITLE = "title";
    protected static final String TITLE_ID = "titleId";
    private static final String CANCELED_TOUCH_OUTSIDE = "isCanceledOnTouchOutside";
    private static final String SHOW_LISTENER = "setOnShowListener";
    private static final String CANCEL_LISTENER = "setOnCancelListener";
    private static final String DISMISS_LISTENER = "setOnDismissListener";
    private static final String KEY_LISTENER = "setOnKeyListener";
    private static final String EXTRA = "extra";

    protected int iconId;
    protected CharSequence title;
    protected int titleId;
    protected int isCanceledOnTouchOutside = VALUE_NULL;
    protected boolean useOnShowListener;
    protected boolean useOnCancelListener;
    protected boolean useOnDismissListener;
    protected boolean useOnKeyListener;
    Bundle extra;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            if(iconId == VALUE_NULL){
                iconId = savedInstanceState.getInt(ICON_ID, VALUE_NULL);
            }
            title = savedInstanceState.getString(TITLE);
            titleId = savedInstanceState.getInt(TITLE_ID, VALUE_NULL);
        }
        if(iconId != VALUE_NULL){
            setIcon(iconId);
        }
        if(title != null){
            setTitle(title);
        }
        if(titleId != VALUE_NULL){
            setTitle(titleId);
        }
    }

    @Override
    public abstract Dialog onCreateDialog(Bundle savedInstanceState);

    //============================================================
    //     _____                                     _
    //    |  ___| __ __ _  __ _ _ __ ___   ___ _ __ | |_
    //    | |_ | '__/ _` |/ _` | '_ ` _ \ / _ \ '_ \| __|
    //    |  _|| | | (_| | (_| | | | | | |  __/ | | | |_
    //    |_|  |_|  \__,_|\__, |_| |_| |_|\___|_| |_|\__|
    //                    |___/
    //     _     _  __                      _
    //    | |   (_)/ _| ___  ___ _   _  ___| | ___
    //    | |   | | |_ / _ \/ __| | | |/ __| |/ _ \
    //    | |___| |  _|  __/ (__| |_| | (__| |  __/
    //    |_____|_|_|  \___|\___|\__, |\___|_|\___|
    //                           |___/
    //============================================================
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        Dialog dialog = getDialog();
        if(dialog == null){
            throw new NullPointerException("Dialog is null! #onCreateDialog must not return null.");
        }
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            isCanceledOnTouchOutside = savedInstanceState.getInt(CANCELED_TOUCH_OUTSIDE, VALUE_NULL);
            useOnShowListener = savedInstanceState.getBoolean(SHOW_LISTENER);
            useOnCancelListener = savedInstanceState.getBoolean(CANCEL_LISTENER);
            useOnDismissListener = savedInstanceState.getBoolean(DISMISS_LISTENER);
            useOnKeyListener = savedInstanceState.getBoolean(KEY_LISTENER);
            extra = savedInstanceState.getBundle(EXTRA);
        }
        if(isCanceledOnTouchOutside != VALUE_NULL){
            dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside == VALUE_TRUE);
        }
        if(useOnShowListener){
            setOnShowListener(dialog);
        }
        if(useOnCancelListener){
            setOnCancelListener(dialog);
        }
        if(useOnDismissListener){
            setOnDismissListener(dialog);
        }
        if(useOnKeyListener){
            setOnKeyListener(dialog);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        if(iconId != VALUE_NULL){
            outState.putInt(ICON_ID, iconId);
        }
        if(title != null){
            outState.putString(TITLE, (String)title);
        }
        if(titleId != VALUE_NULL){
            outState.putInt(TITLE_ID, titleId);
        }
        if(isCanceledOnTouchOutside != VALUE_NULL){
            outState.putInt(CANCELED_TOUCH_OUTSIDE, isCanceledOnTouchOutside);
        }
        if(extra != null){
            outState.putBundle(EXTRA, extra);
        }
    }

    @Override
    public void onDestroy(){
        extra = null;
        super.onDestroy();
    }

    @Override
    public void show(FragmentManager manager, String tag){
        super.show(manager, tag);
    }

    @Override
    public int show(FragmentTransaction transaction, String tag){
        return super.show(transaction, tag);
    }

    //============================================================
    //     ____  _       _               ____       _   _   _
    //    |  _ \(_) __ _| | ___   __ _  / ___|  ___| |_| |_(_)_ __   __ _ ___
    //    | | | | |/ _` | |/ _ \ / _` | \___ \ / _ \ __| __| | '_ \ / _` / __|
    //    | |_| | | (_| | | (_) | (_| |  ___) |  __/ |_| |_| | | | | (_| \__ \
    //    |____/|_|\__,_|_|\___/ \__, | |____/ \___|\__|\__|_|_| |_|\__, |___/
    //                           |___/                              |___/
    //============================================================

    public void setIcon(int iconId){
        this.iconId = iconId;
        if(getDialog() != null){
            ((AlertDialog)getDialog()).setIcon(iconId);
        }
    }

    public void setTitle(CharSequence title){
        this.title = title;
        if(getDialog() != null){
            getDialog().setTitle(title);
        }
    }

    public void setTitle(int titleId){
        this.titleId = titleId;
        if(getDialog() != null){
            getDialog().setTitle(titleId);
        }
    }

    /**
     * Sets whether this dialog is canceled when touched outside the window's bounds.
     * If setting to true, the dialog is set to be cancelable if not already set.
     *
     * @param cancel Whether the dialog should be canceled when touched outside the window.
     */
    public void setCanceledOnTouchOutside(boolean cancel){
        isCanceledOnTouchOutside = cancel ? VALUE_TRUE : VALUE_FALSE;
        if(getDialog() != null){
            getDialog().setCanceledOnTouchOutside(isCanceledOnTouchOutside == VALUE_TRUE);
        }
    }

    public boolean isCanceledOnTouchOutside(){
        return isCanceledOnTouchOutside == VALUE_TRUE;
    }

    /**
     * Sets a listener to be invoked when the dialog is shown.
     *
     * @param provider
     */
    public void setOnShowListener(DialogFragmentCallbackProvider provider){
        assertListenerBindable(provider);
        useOnShowListener = true;
        if(getDialog() != null){
            setOnShowListener(getDialog());
        }
    }

    /**
     * Sets the callback that will be called if the dialog is canceled.
     *
     * <p>
     * Even in a cancelable dialog, the dialog may be dismissed for reasons other than being
     * canceled or one of the supplied choices being selected. If you are interested in listening
     * for all cases where the dialog is dismissed and not just when it is canceled, see
     * {@link #setOnDismissListener(android.content.DialogInterface.OnDismissListener)
     * setOnDismissListener}.
     * </p>
     *
     * @see #setCancelable(boolean)
     * @see #setOnDismissListener(android.content.DialogInterface.OnDismissListener)
     *
     * @param provider
     */
    public void setOnCancelListener(DialogFragmentCallbackProvider provider){
        assertListenerBindable(provider);
        useOnCancelListener = true;
        if(getDialog() != null){
            setOnCancelListener(getDialog());
        }
    }

    /**
     * Set a listener to be invoked when the dialog is dismissed.
     *
     * @param provider
     */
    public void setOnDismissListener(DialogFragmentCallbackProvider provider){
        assertListenerBindable(provider);
        useOnDismissListener = true;
        if(getDialog() != null){
            setOnDismissListener(getDialog());
        }
    }

    /**
     * Set a listener to be invoked when the key is pressed.
     *
     * @param provider
     */
    public void setOnKeyListener(DialogFragmentCallbackProvider provider){
        assertListenerBindable(provider);
        useOnKeyListener = true;
        if(getDialog() != null){
            setOnKeyListener(getDialog());
        }
    }

    protected void setOnShowListener(Dialog dialog){
        useOnShowListener = true;
        dialog.setOnShowListener(new OnShowListener(){
            @Override
            public void onShow(DialogInterface dialog){
                bindShowListener();
            }
        });
    }

    protected void setOnCancelListener(Dialog dialog){
        useOnCancelListener = true;
        dialog.setOnCancelListener(new OnCancelListener(){
            @Override
            public void onCancel(DialogInterface dialog){
                bindCancelListener();
            }
        });
    }

    protected void setOnDismissListener(Dialog dialog){
        useOnDismissListener = true;
        dialog.setOnDismissListener(new OnDismissListener(){
            @Override
            public void onDismiss(DialogInterface dialog){
                bindDismissListener();
            }
        });
    }

    protected void setOnKeyListener(Dialog dialog){
        useOnKeyListener = true;
        dialog.setOnKeyListener(new OnKeyListener(){
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event){
                return bindKeyListener(keyCode, event);
            }
        });
    }

    protected void bindShowListener(){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onShow(this);
        }
    }

    protected void bindCancelListener(){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onCancel(this);
        }
    }

    protected void bindDismissListener(){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onDismiss(this);
            dismiss();
        }
    }

    protected boolean bindKeyListener(int keyCode, KeyEvent event){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            return listener.onKey(this, keyCode, event);
        }
        return false;
    }

    //============================================================
    //    ____  _       _             ___       _             __
    //   |  _ \(_) __ _| | ___   __ _|_ _|_ __ | |_ ___ _ __ / _| __ _  ___ ___
    //   | | | | |/ _` | |/ _ \ / _` || || '_ \| __/ _ \ '__| |_ / _` |/ __/ _ \
    //   | |_| | | (_| | | (_) | (_| || || | | | ||  __/ |  |  _| (_| | (_|  __/
    //   |____/|_|\__,_|_|\___/ \__, |___|_| |_|\__\___|_|  |_|  \__,_|\___\___|
    //                          |___/
    //============================================================
    /**
     * Set an extra object. This has no effect to dialog behavior.
     */
    @Override
    public DialogFragmentInterface setExtra(Bundle extra){
        this.extra = extra;
        return this;
    }

    /**
     * Get an extra object you set before.
     */
    @Override
    public Bundle getExtra(){
        return extra;
    }

    static void assertListenerBindable(DialogFragmentCallbackProvider provider){
        if(provider == null){
            throw new IllegalArgumentException(DialogFragmentCallbackProvider.class.getSimpleName() + " must not be null.");
        }else if(provider instanceof Fragment){
            // ok
        }else if(provider instanceof FragmentActivity){
            // ok
        }else{
            throw new AssertionError(DialogFragmentCallbackProvider.class.getSimpleName() + " must not be implemented as anonymous inner class.");
        }
    }

    DialogFragmentCallback getDialogFragmentCallback(){
        DialogFragmentCallback listener = null;
        if(getActivity() == null){
            Log.v(getTag(), "getActivity() returns null. Maybe the activity destroyed by rotation.");
            return null;
        }
        if(getParentFragment() instanceof DialogFragmentCallbackProvider){
            Log.v(getTag(), "getParentFragment() instanceof DialogEventListenerPovider");
            DialogFragmentCallbackProvider provider = (DialogFragmentCallbackProvider)getParentFragment();
            listener = provider.getDialogFragmentCallback();
        }else if(getActivity() instanceof DialogFragmentCallbackProvider){
            Log.v(getTag(), "getActivity() instanceof DialogEventListenerPovider");
            DialogFragmentCallbackProvider provider = (DialogFragmentCallbackProvider)getActivity();
            listener = provider.getDialogFragmentCallback();
        }else{
            Log.v(getTag(), "Not found instance of DialogEventListenerPovider");
        }
        return listener;
    }
}
