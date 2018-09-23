package com.rocket.sample.daggerrxsample.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;

import com.rocket.sample.daggerrxsample.DaggerRxApplication;
import com.rocket.sample.daggerrxsample.R;

/**
 * Used as helper class for all dialog related transaction
 */

public class DialogUtils {

    private static AlertDialog mNoNetworkDialog;
    private static AlertDialog mCommonDialog;

    /**
     *  Display a error dialog stating network not present
     * @param context
     * @param resources
     */
    public static void showNetworkErrorDialog(Context context, Resources resources) {
        if (mNoNetworkDialog == null) {// recreate only if not present
            AlertDialog.Builder networkDialogBuilder = new AlertDialog.Builder(context);
            networkDialogBuilder.setMessage(resources.getString(R.string.network_error));
            networkDialogBuilder.setCancelable(true);
            networkDialogBuilder.setPositiveButton(resources.getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            mNoNetworkDialog = networkDialogBuilder.create();
        }
        try {
            if (!mNoNetworkDialog.isShowing()) {
                mNoNetworkDialog.show();
            }
        } catch (Exception e) {
            mNoNetworkDialog = null;
        }

    }

    public static void hideNoNetworkDialog() {
        if (mNoNetworkDialog != null && mNoNetworkDialog.isShowing()) {
            mNoNetworkDialog.dismiss();
        }
    }

    /**
     *  Common method to display any dialogues in the app
     * @param context
     * @param resources
     * @param message
     */
    public static void showAlertDialog(Context context, Resources resources, String message) {
        if (mCommonDialog == null) {
            AlertDialog.Builder errorDialogBuilder = new AlertDialog.Builder(context);
            errorDialogBuilder.setMessage(message);
            errorDialogBuilder.setCancelable(true);
            errorDialogBuilder.setPositiveButton(resources.getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            mCommonDialog = errorDialogBuilder.create();
        } else {
            mCommonDialog.setMessage(message);
        }
        try {
            mCommonDialog.show();
        } catch (Exception e) {
            mCommonDialog = null;
        }
    }

    public static void hideAlerDialog() {
        if (mCommonDialog != null && mCommonDialog.isShowing()) {
            mCommonDialog.dismiss();
        }
    }
}
