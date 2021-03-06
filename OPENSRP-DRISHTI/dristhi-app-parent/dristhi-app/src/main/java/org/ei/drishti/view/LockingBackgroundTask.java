package org.ei.drishti.view;

import static org.ei.drishti.util.Log.logVerbose;

import java.util.concurrent.locks.ReentrantLock;

import android.os.AsyncTask;

public class LockingBackgroundTask {
    private ProgressIndicator indicator;
    private static final ReentrantLock lock = new ReentrantLock();

    public LockingBackgroundTask(ProgressIndicator progressIndicator) {
        this.indicator = progressIndicator;
    }

    public <T> void doActionInBackground(final BackgroundAction<T> backgroundAction) {
        new AsyncTask<Void, Void, T>() {
            @Override
            protected T doInBackground(Void... params) {
                if (!lock.tryLock()) {
                    logVerbose("Going away. Something else is holding the lock.");
                    cancel(true);
                    return null;
                }
                try {
                    publishProgress();
                    return backgroundAction.actionToDoInBackgroundThread();
                } finally {
                    lock.unlock();
                }
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                indicator.setVisible();
            }

            @Override
            protected void onPostExecute(T result) {
                backgroundAction.postExecuteInUIThread(result);
                indicator.setInvisible();
            }
        }.execute((Void) null);
    }
}
