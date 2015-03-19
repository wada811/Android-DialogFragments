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
import android.support.v4.content.AsyncTaskLoader;

public abstract class AbstractAsyncLoader<D> extends AsyncTaskLoader<D> {

    private D mData = null;

    public AbstractAsyncLoader(Context context){
        super(context);
    }

    /**
     * {@link #startLoading()}が呼び出された時に呼び出される。
     * データが有る場合は結果を送り、ない場合は新規のデータをロードする。
     *
     * @UiThread
     */
    @Override
    protected void onStartLoading(){
        // すでにデータが有る場合は結果を送る
        if(mData != null){
            deliverResult(mData);
        }
        // loader停止中にコンテンツが変わった、またはデータがない
        if(takeContentChanged() || (mData == null)){
            // #startLoading()とは異なり、以前のデータを無視して新規のデータをロードする
            forceLoad();
        }
    }

    /**
     * 非同期処理を行い、その結果を返す。
     *
     * @Background
     */
    @Override
    public D loadInBackground(){
        return mData;
    }

    /**
     * {@link #stopLoading()}が呼び出された時に呼び出される。ロードをキャンセルする。
     *
     * @UiThread
     */
    @Override
    protected void onStopLoading(){
        // 可能な場合、ロードをキャンセルする
        cancelLoad();
    }

    /**
     * {@link #reset()}が呼び出された時に呼び出される。ロードをキャンセルし、データを破棄する。
     *
     * @UiThread
     */
    @Override
    protected void onReset(){
        super.onReset();
        stopLoading();
        mData = null;
    }

    /**
     * onLoadCompleteリスナーに結果を送る。
     * メインスレッドから呼び出す。
     *
     * @param 結果
     */
    @Override
    public void deliverResult(D data){
        if(isReset()){
            // Loader停止中に呼び出された
            return;
        }
        mData = data;
        super.deliverResult(data);
    }

}
