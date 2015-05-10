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
package com.wada811.android.dialogfragments.traditional;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListAdapter;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallback;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentCallbackProvider;
import com.wada811.android.dialogfragments.interfaces.DialogFragmentInterface;

@SuppressWarnings("unused")
@SuppressLint("InlinedApi")
public class AlertDialogFragment extends AbstractDialogFragment implements DialogFragmentInterface {

    final AlertDialogFragment self = this;
    public static final String TAG = AlertDialogFragment.class.getSimpleName();

    /**
     * Special theme constant for {@link AlertDialogFragment.Builder#Builder(android.content.Context, int)}: use the
     * traditional (pre-Holo) alert dialog theme.
     */
    public static final int THEME_TRADITIONAL = AlertDialog.THEME_TRADITIONAL;

    /**
     * Special theme constant for {@link AlertDialogFragment.Builder#Builder(android.content.Context, int)}: use the
     * holographic alert theme with a dark background.
     */
    public static final int THEME_HOLO_DARK = AlertDialog.THEME_HOLO_DARK;

    /**
     * Special theme constant for {@link AlertDialogFragment.Builder#Builder(android.content.Context, int)}: use the
     * holographic alert theme with a light background.
     */
    public static final int THEME_HOLO_LIGHT = AlertDialog.THEME_HOLO_LIGHT;

    /**
     * Special theme constant for {@link AlertDialogFragment.Builder#Builder(android.content.Context, int)}: use the
     * device's default alert theme with a dark background.
     */
    public static final int THEME_DEVICE_DEFAULT_DARK = AlertDialog.THEME_DEVICE_DEFAULT_DARK;

    /**
     * Special theme constant for {@link AlertDialogFragment.Builder#Builder(android.content.Context, int)}: use the
     * device's default alert theme with a dark background.
     */
    public static final int THEME_DEVICE_DEFAULT_LIGHT = AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;

    protected static final String THEME = "theme";
    private static final String INVERSE_BACKGROUND = "inverseBackground";
    private static final String CUSTOM_TITLE = "customTitle";
    protected static final String MESSAGE = "message";
    private static final String VIEW = "view";

    private static final String ITEMS = "items";
    private static final String ADAPTER = "adapter";
    private static final String CHECKED_ITEMS = "checkedItems";
    private static final String MULTI_CHOICE_ITEMS = "multiChoiceItems";
    private static final String CHECKED_ITEM = "checkedItem";
    private static final String SINGLE_CHOICE_ITEMS = "singleChoiceItems";

    private static final String POSITIVE_BUTTON = "positiveButton";
    private static final String NEUTRAL_BUTTON = "neutralButton";
    private static final String NEGATIVE_BUTTON = "negativeButton";

    private static final String POSITIVE_CLICK_LISTENER = "onClickPositive";
    private static final String NEUTRAL_CLICK_LISTENER = "onClickNeutral";
    private static final String NEGATIVE_CLICK_LISTENER = "onClickNegative";

    public static class Builder {

        private final Context context;
        private final Bundle args = new Bundle();
        private boolean isCancelable = true;
        private boolean isCanceledOnTouchOutside = true;
        private boolean useOnShowListener;
        private boolean useOnCancelListener;
        private boolean useOnDismissListener;
        private boolean useOnKeyListener;
        private Bundle extra;

        /**
         * Constructor using a context for this builder and the {@link AlertDialogFragment} it creates.
         */
        public Builder(Context context){
            this(context, 0);
        }

        /**
         * Constructor using a context and theme for this builder and the {@link AlertDialogFragment} it creates. The
         * actual theme that an AlertDialog uses is a private implementation, however you can here supply either the
         * name of an attribute in the theme from which to get the dialog's style (such as {@link
         * android.R.attr#alertDialogTheme} or one of the constants {@link AlertDialogFragment#THEME_TRADITIONAL},
         * {@link AlertDialogFragment#THEME_HOLO_DARK}, or {@link AlertDialogFragment#THEME_HOLO_LIGHT}.
         */
        public Builder(Context context, int theme){
            this.context = context.getApplicationContext();
            args.putInt(THEME, theme);
        }

        /**
         * Creates a {@link AlertDialogFragment} with the arguments supplied to this builder. It does not {@link
         * AlertDialogFragment#show} the dialog. This allows the user to do any extra processing before displaying the
         * dialog. Use {@link #show} if you don't have any other processing to do and want this to be created and
         * displayed.
         */
        public AlertDialogFragment create(){
            final AlertDialogFragment fragment = new AlertDialogFragment();
            fragment.setFromBuilder(true);
            fragment.setArguments(args);
            if(!isCancelable){
                fragment.setCancelable(false);
            }
            if(!isCanceledOnTouchOutside){
                fragment.setCanceledOnTouchOutside(false);
            }
            fragment.useOnShowListener = useOnShowListener;
            fragment.useOnCancelListener = useOnCancelListener;
            fragment.useOnDismissListener = useOnDismissListener;
            fragment.useOnKeyListener = useOnKeyListener;
            if(extra != null){
                fragment.setExtra(extra);
            }
            return fragment;
        }

        /**
         * Creates a {@link AlertDialogFragment} with the arguments supplied to this builder and {@link
         * AlertDialogFragment#show}'s the dialog.
         */
        public void show(FragmentManager manager, String tag){
            create().show(manager, tag);
        }

        /**
         * Creates a {@link AlertDialogFragment} with the arguments supplied to this builder and {@link
         * AlertDialogFragment#show}'s the dialog.
         */
        public void show(FragmentTransaction transaction, String tag){
            create().show(transaction, tag);
        }

        /**
         * Sets whether the dialog is cancelable or not. Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setCancelable(boolean cancelable){
            isCancelable = cancelable;
            return this;
        }

        /**
         * Sets whether this dialog is canceled when touched outside the window's bounds. If setting to true, the dialog
         * is set to be cancelable if not already set.
         *
         * @param cancel Whether the dialog should be canceled when touched outside the window.
         */
        public Builder setCanceledOnTouchOutside(boolean cancel){
            isCanceledOnTouchOutside = cancel;
            return this;
        }

        /**
         * Sets the Dialog to use the inverse background, regardless of what the contents is.
         *
         * @param useInverseBackground Whether to use the inverse background
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setInverseBackgroundForced(boolean useInverseBackground){
            args.putInt(INVERSE_BACKGROUND, useInverseBackground ? VALUE_TRUE : VALUE_FALSE);
            return this;
        }

        /**
         * Set the resource id of the {@link android.graphics.drawable.Drawable} to be used in the title.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setIcon(int iconId){
            args.putInt(ICON_ID, iconId);
            return this;
        }

        /**
         * Set an icon as supplied by a theme attribute. e.g. android.R.attr.alertDialogIcon
         *
         * @param attrId ID of a theme attribute that points to a drawable resource.
         */
        public Builder setIconAttribute(int attrId){
            TypedValue out = new TypedValue();
            context.getTheme().resolveAttribute(attrId, out, true);
            setIcon(out.resourceId);
            return this;
        }

        /**
         * Sets an extra object.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         *
         * @see AlertDialogFragment#setExtra(android.os.Bundle)
         */
        public Builder setExtra(Bundle extra){
            this.extra = extra;
            return this;
        }

        /**
         * Set the title displayed in the {@link android.app.Dialog}.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setTitle(CharSequence title){
            args.putCharSequence(TITLE, title);
            return this;
        }

        /**
         * Set the title using the given resource id.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setTitle(int resId){
            args.putCharSequence(TITLE, context.getText(resId));
            return this;
        }

        public Builder setCustomTitle(DialogFragmentCallbackProvider povider){
            assertListenerBindable(povider);
            args.putBoolean(CUSTOM_TITLE, true);
            return this;
        }

        /**
         * Set the message to display.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setMessage(CharSequence message){
            args.putCharSequence(MESSAGE, message);
            return this;
        }

        /**
         * Set the message to display using the given resource id.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setMessage(int resId){
            return setMessage(context.getText(resId));
        }

        public Builder setView(DialogFragmentCallbackProvider povider){
            assertListenerBindable(povider);
            args.putBoolean(VIEW, true);
            return this;
        }

        /**
         * Set a list of items to be displayed in the dialog as the content, you will be notified of the selected item
         * via the supplied listener.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setItems(CharSequence[] items){
            args.putCharSequenceArray(ITEMS, items);
            return this;
        }

        /**
         * Set a list of items to be displayed in the dialog as the content, you will be notified of the selected item
         * via the supplied listener. This should be an array type i.e. R.array.foo
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setItems(int itemsId){
            return setItems(context.getResources().getTextArray(itemsId));
        }

        /**
         * Set a list of items, which are supplied by the given {@link android.widget.ListAdapter}, to be displayed in
         * the dialog as the content, you will be notified of the selected item via the supplied listener.
         *
         * @param povider The {@link DialogFragmentCallback#getAdapter(DialogFragmentInterface)} to supply the list of
         * items
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setAdapter(DialogFragmentCallbackProvider povider){
            assertListenerBindable(povider);
            args.putBoolean(ADAPTER, true);
            return this;
        }

        /**
         * Set a list of items to be displayed in the dialog as the content. The list will have a check mark displayed
         * to the right of the text for the checked item. Clicking on an item in the list will not dismiss the dialog.
         * Clicking on a button will dismiss the dialog.
         *
         * @param items the items to be displayed.
         * @param checkedItem specifies which item is checked. If -1 no items are checked.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem){
            args.putCharSequenceArray(SINGLE_CHOICE_ITEMS, items);
            args.putInt(CHECKED_ITEM, checkedItem);
            return this;
        }

        /**
         * Set a list of items to be displayed in the dialog as the content. This should be an array type i.e.
         * R.array.foo The list will have a check mark displayed to the right of the text for the checked item. Clicking
         * on an item in the list will not dismiss the dialog. Clicking on a button will dismiss the dialog.
         *
         * @param itemsId the resource id of an array i.e. R.array.foo
         * @param checkedItem specifies which item is checked. If -1 no items are checked.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setSingleChoiceItems(int itemsId, int checkedItem){
            return setSingleChoiceItems(context.getResources().getTextArray(itemsId), checkedItem);
        }

        /**
         * Set a list of items to be displayed in the dialog as the content. The list will have a check mark displayed
         * to the right of the text for each checked item. Clicking on an item in the list will not dismiss the dialog.
         * Clicking on a button will dismiss the dialog.
         *
         * @param items the text of the items to be displayed in the list.
         * @param checkedItems specifies which items are checked. It should be null in which case no items are checked.
         * If non null it must be exactly the same length as the array of items.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems){
            args.putCharSequenceArray(MULTI_CHOICE_ITEMS, items);
            args.putBooleanArray(CHECKED_ITEMS, checkedItems);
            return this;
        }

        /**
         * Set a list of items to be displayed in the dialog as the content, you will be notified of the selected item
         * via the supplied listener. This should be an array type, e.g. R.array.foo. The list will have a check mark
         * displayed to the right of the text for each checked item. Clicking on an item in the list will not dismiss
         * the dialog. Clicking on a button will dismiss the dialog.
         *
         * @param itemsId the resource id of an array i.e. R.array.foo
         * @param checkedItems specifies which items are checked. It should be null in which case no items are checked.
         * If non null it must be exactly the same length as the array of items.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems){
            return setMultiChoiceItems(context.getResources().getTextArray(itemsId), checkedItems);
        }

        /**
         * Set a listener to be invoked when the positive button of the dialog is pressed.
         *
         * @param text The text to display in the positive button
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setPositiveButton(CharSequence text, DialogFragmentCallbackProvider provider){
            if(provider != null){
                assertListenerBindable(provider);
                args.putBoolean(POSITIVE_CLICK_LISTENER, true);
            }
            args.putCharSequence(POSITIVE_BUTTON, text);
            return this;
        }

        /**
         * Set a listener to be invoked when the positive button of the dialog is pressed.
         *
         * @param textId The resource id of the text to display in the positive button
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setPositiveButton(int textId, DialogFragmentCallbackProvider provider){
            return setPositiveButton(context.getText(textId), provider);
        }

        /**
         * Set a listener to be invoked when the neutral button of the dialog is pressed.
         *
         * @param text The text to display in the neutral button
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNeutralButton(CharSequence text, DialogFragmentCallbackProvider provider){
            if(provider != null){
                assertListenerBindable(provider);
                args.putBoolean(NEUTRAL_CLICK_LISTENER, true);
            }
            args.putCharSequence(NEUTRAL_BUTTON, text);
            return this;
        }

        /**
         * Set a listener to be invoked when the neutral button of the dialog is pressed.
         *
         * @param textId The resource id of the text to display in the neutral button
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNeutralButton(int textId, DialogFragmentCallbackProvider provider){
            return setNeutralButton(context.getText(textId), provider);
        }

        /**
         * Set a listener to be invoked when the negative button of the dialog is pressed.
         *
         * @param text The text to display in the negative button
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNegativeButton(CharSequence text, DialogFragmentCallbackProvider provider){
            if(provider != null){
                assertListenerBindable(provider);
                args.putBoolean(NEGATIVE_CLICK_LISTENER, true);
            }
            args.putCharSequence(NEGATIVE_BUTTON, text);
            return this;
        }

        /**
         * Set a listener to be invoked when the negative button of the dialog is pressed.
         *
         * @param textId The resource id of the text to display in the negative button
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNegativeButton(int textId, DialogFragmentCallbackProvider provider){
            return setNegativeButton(context.getText(textId), provider);
        }

        /**
         * Sets a listener to be invoked when the dialog is shown.
         *
         * @param provider
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnShowListener(DialogFragmentCallbackProvider provider){
            assertListenerBindable(provider);
            useOnShowListener = true;
            return this;
        }

        /**
         * Sets the callback that will be called if the dialog is canceled. <p/> <p> Even in a cancelable dialog, the
         * dialog may be dismissed for reasons other than being canceled or one of the supplied choices being selected.
         * If you are interested in listening for all cases where the dialog is dismissed and not just when it is
         * canceled, see {@link #setOnDismissListener}. </p>
         *
         * @param provider
         *
         * @return This Builder object to allow for chaining of calls to set methods
         *
         * @see #setCancelable(boolean)
         * @see #setOnDismissListener
         */
        public Builder setOnCancelListener(DialogFragmentCallbackProvider provider){
            assertListenerBindable(provider);
            useOnCancelListener = true;
            return this;
        }

        /**
         * Set a listener to be invoked when the dialog is dismissed.
         *
         * @param provider
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnDismissListener(DialogFragmentCallbackProvider provider){
            assertListenerBindable(provider);
            useOnDismissListener = true;
            return this;
        }

        /**
         * Set a listener to be invoked when the key is pressed.
         *
         * @param provider
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnKeyListener(DialogFragmentCallbackProvider provider){
            assertListenerBindable(provider);
            useOnKeyListener = true;
            return this;
        }
    }

    private static final String IS_FROM_BUILDER = "isFromBuilder";

    boolean isFromBuilder;

    public AlertDialogFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            return;
        }
        isFromBuilder = savedInstanceState.getBoolean(IS_FROM_BUILDER);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        if(!isFromBuilder){
            throw new RuntimeException("Use AlertDialogFragment$Builder");
        }

        final Bundle args = getArguments();
        final int theme = args.getInt(THEME);

        AlertDialog.Builder builder;
        if(theme == VALUE_NULL || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
            builder = new AlertDialog.Builder(getActivity());
        }else{
            builder = newDialogBuilder(theme);
        }

        iconId = args.getInt(ICON_ID, VALUE_NULL);
        if(iconId != VALUE_NULL){
            builder.setIcon(iconId);
        }

        final CharSequence title = args.getCharSequence(TITLE);
        boolean useCustomTitle = args.getBoolean(CUSTOM_TITLE);
        if(title != null){
            builder.setTitle(title);
        }else if(useCustomTitle){
            setCustomTitle(builder);
        }

        final CharSequence message = args.getCharSequence(MESSAGE);
        if(message != null){
            builder.setMessage(message);
        }

        final int useInverseBackground = args.getInt(INVERSE_BACKGROUND);
        if(useInverseBackground != VALUE_NULL){
            builder.setInverseBackgroundForced(useInverseBackground == VALUE_TRUE);
        }

        // View
        setView(builder);

        // List
        setItems(builder);
        setAdapter(builder);
        setSingleChoiceItems(builder);
        setMultiChoiceItems(builder);

        // Buttons
        setPositiveButton(builder);
        setNeutralButton(builder);
        setNegativeButton(builder);

        // Build
        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_FROM_BUILDER, isFromBuilder);
    }

    protected void setFromBuilder(boolean fromBuilder){
        isFromBuilder = fromBuilder;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private AlertDialog.Builder newDialogBuilder(int theme){
        return new AlertDialog.Builder(getActivity(), theme);
    }

    private void setCustomTitle(AlertDialog.Builder builder){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener == null){
            throw new RuntimeException("DialogEventListenerPovider has not implemented.");
        }
        View view = listener.getCustomTitle(this);
        if(view == null){
            throw new NullPointerException("DialogEventListener#getCustomTitle returns null.");
        }
        builder.setCustomTitle(view);
    }

    private void setView(AlertDialog.Builder builder){
        final Bundle args = getArguments();
        boolean useView = args.getBoolean(VIEW, false);
        if(!useView){
            return;
        }
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener == null){
            throw new RuntimeException("DialogEventListenerPovider has not implemented.");
        }
        View view = listener.getView(this);
        if(view == null){
            throw new NullPointerException("DialogEventListener#getView returns null.");
        }
        builder.setView(view);
    }

    private void setItems(AlertDialog.Builder builder){
        final Bundle args = getArguments();
        final CharSequence[] items = args.getCharSequenceArray(ITEMS);
        if(items == null){
            return;
        }
        builder.setItems(items, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                bindItemClickListener(which);
            }
        });
    }

    private void setAdapter(AlertDialog.Builder builder){
        final Bundle args = getArguments();
        boolean useAdapter = args.getBoolean(ADAPTER);
        if(!useAdapter){
            return;
        }
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener == null){
            throw new RuntimeException("DialogEventListenerPovider has not implemented.");
        }
        ListAdapter adapter = listener.getAdapter(this);
        if(adapter == null){
            throw new NullPointerException("DialogEventListener#getListAdapter returns null.");
        }
        builder.setAdapter(adapter, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                bindItemClickListener(which);
            }
        });
    }

    private void setSingleChoiceItems(AlertDialog.Builder builder){
        final Bundle args = getArguments();
        final CharSequence[] items = args.getCharSequenceArray(SINGLE_CHOICE_ITEMS);
        final int checkedItem = args.getInt(CHECKED_ITEM);
        if(items == null){
            return;
        }
        builder.setSingleChoiceItems(items, checkedItem, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                bindSingleChoiceClickListener(which);
            }
        });
    }

    private void setMultiChoiceItems(AlertDialog.Builder builder){
        final Bundle args = getArguments();
        final CharSequence[] items = args.getCharSequenceArray(MULTI_CHOICE_ITEMS);
        final boolean[] checked = args.getBooleanArray(CHECKED_ITEMS);
        if(items == null || checked == null){
            return;
        }
        if(items.length != checked.length){
            throw new IllegalArgumentException("Item's length is not same as checkedItem's length.");
        }
        builder.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked){
                bindMultiChoiceClickListener(which, isChecked);
            }
        });
    }

    private void setPositiveButton(AlertDialog.Builder builder){
        final Bundle args = getArguments();
        final CharSequence positiveButtonText = args.getCharSequence(POSITIVE_BUTTON);
        if(positiveButtonText == null){
            return;
        }
        final boolean useOnPositiveClickListener = args.getBoolean(POSITIVE_CLICK_LISTENER);
        builder.setPositiveButton(positiveButtonText, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                if(useOnPositiveClickListener){
                    bindClickListener(which);
                }
            }
        });
    }

    private void setNeutralButton(AlertDialog.Builder builder){
        final Bundle args = getArguments();
        final CharSequence naturalButtonText = args.getCharSequence(NEUTRAL_BUTTON);
        if(naturalButtonText == null){
            return;
        }
        final boolean useOnNeutralClickListener = args.getBoolean(NEUTRAL_CLICK_LISTENER);
        builder.setNeutralButton(naturalButtonText, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                if(useOnNeutralClickListener){
                    bindClickListener(which);
                }
            }
        });
    }

    private void setNegativeButton(AlertDialog.Builder builder){
        final Bundle args = getArguments();
        final CharSequence negativeButtonText = args.getCharSequence(NEGATIVE_BUTTON);
        if(negativeButtonText == null){
            return;
        }
        final boolean useOnNegativeClickListener = args.getBoolean(NEGATIVE_CLICK_LISTENER);
        builder.setNegativeButton(negativeButtonText, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                if(useOnNegativeClickListener){
                    bindClickListener(which);
                }
            }
        });
    }

    //============================================================
    //     ____
    //    / ___|___  _ __ ___  _ __ ___   ___  _ __
    //   | |   / _ \| '_ ` _ \| '_ ` _ \ / _ \| '_ \
    //   | |__| (_) | | | | | | | | | | | (_) | | | |
    //    \____\___/|_| |_| |_|_| |_| |_|\___/|_| |_|
    //
    //    ____  _       _             ___       _             __
    //   |  _ \(_) __ _| | ___   __ _|_ _|_ __ | |_ ___ _ __ / _| __ _  ___ ___
    //   | | | | |/ _` | |/ _ \ / _` || || '_ \| __/ _ \ '__| |_ / _` |/ __/ _ \
    //   | |_| | | (_| | | (_) | (_| || || | | | ||  __/ |  |  _| (_| | (_|  __/
    //   |____/|_|\__,_|_|\___/ \__, |___|_| |_|\__\___|_|  |_|  \__,_|\___\___|
    //                          |___/
    //============================================================
    protected void bindClickListener(int which){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener == null){
            return;
        }
        switch(which){
            case DialogInterface.BUTTON_POSITIVE:
                listener.onClickPositive(this);
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                listener.onClickNeutral(this);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                listener.onClickNegative(this);
                break;
            default:
                break;
        }
    }

    protected void bindItemClickListener(int position){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onItemClick(this, position);
        }
    }

    protected void bindSingleChoiceClickListener(int position){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onSingleChoiceClick(this, position);
        }
    }

    protected void bindMultiChoiceClickListener(int position, boolean isChecked){
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onMultiChoiceClick(this, position, isChecked);
        }
    }

}
