package com.example.ahmedetman.peopleapitask.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.example.ahmedetman.peopleapitask.R;

/**
 * Created by Ahmed Etman on 5/6/2018.
 */

public class BaseActivity extends Activity implements BaseActivityActions{

    private ProgressDialog progressDialog;

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.text_pleasewait));
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });
        }
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
