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
package at.wada811.android.dialogfragments.sample.progressdialogfragment.examples;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.TimeUnit;

public class AsyncSleepLoader extends AbstractAsyncLoader<Object>{

    public AsyncSleepLoader(Context context){
        super(context);
    }

    @Override
    protected Object onLoadInBackground(){
        for(int i = 0; i < 5; i++){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            Log.i(AsyncSleepLoader.class.getSimpleName(), "onLoadInBackground: " + i);
        }
        deliverResult(null);
        return null;
    }

}
