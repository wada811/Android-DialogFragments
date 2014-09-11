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

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.NumberPicker;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class StringPicker extends NumberPicker {

    public StringPicker(Context context) {
        super(context);
    }

    public StringPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StringPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setDisplayedValue(String value){
        String[] displayedValues = getDisplayedValues();
        int i = 0;
        for(String displayedValue : displayedValues){
            if(displayedValue.equals(value)){
                setValue(i);
                break;
            }
            i++;
        }
    }

    public String getDisplayedValue(){
        String[] displayedValues = getDisplayedValues();
        return displayedValues[getValue()];
    }

    public void setDisplayedValues(List<String> values){
        setDisplayedValues(values.toArray(new String[values.size()]));
    }

    @Override
    public void setDisplayedValues(String[] values){
        setMinValue(0);
        setMaxValue(values.length - 1);
        super.setDisplayedValues(values);
    }

}
