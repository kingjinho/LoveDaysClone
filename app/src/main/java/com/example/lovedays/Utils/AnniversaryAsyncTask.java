package com.example.lovedays.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by KING JINHO on 2019-07-29
 */
public class AnniversaryAsyncTask extends AsyncTask<Void, Void, Object> {

    public static final String TAG = AnniversaryAsyncTask.class.getSimpleName();
    private static AnniversaryAsyncTask mAnniversaryAsync;
    private Context mContext;
    private onExecuteCallback mCallback;

    public AnniversaryAsyncTask() {
    }

    public AnniversaryAsyncTask(Context context, onExecuteCallback callback) {
        this.mContext = context;
        this.mCallback = callback;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        if(mContext != null && mCallback != null)
            mCallback.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... voids) {
        if(mContext != null && mCallback != null)
            try{
                return  mCallback.doInBackground();
            }catch (Exception e){
                Toast.makeText(mContext, "문제 발생", Toast.LENGTH_SHORT).show();
            }
        return null;
    }

    public static boolean isRunning(){
        return mAnniversaryAsync !=null && mAnniversaryAsync.getStatus() == Status.RUNNING;
    }

    public static void stop(){
        if(mAnniversaryAsync != null)
            mAnniversaryAsync.cancel(true);
    }

    @Override
    protected void onPostExecute(Object o) {
        if(mAnniversaryAsync != null && mCallback != null)
            mCallback.onPostExecute(0);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public static void execute(Context context, onExecuteCallback callback) {
        mAnniversaryAsync = new AnniversaryAsyncTask(context, callback);
        mAnniversaryAsync.execute();
    }




    public static abstract class onExecuteCallback<T> {

       protected void onPreExecute() {}

       public abstract T doInBackground() throws Exception;

       protected void onPostExecute(T result) {}

       protected void onCancelled() {}

    }


}
