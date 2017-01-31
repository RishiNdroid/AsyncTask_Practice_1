package com.example.rndroid.asynctask_practice_1;



import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressBarDialog extends DialogFragment {

    ProgressDialog progressDialog;
    int max, status;
    public ProgressBarDialog() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        max = bundle.getInt("max");
        Toast.makeText(getActivity(), ""+bundle.getInt("status"), Toast.LENGTH_SHORT).show();
        progressDialog.setMax(max);
        progressDialog.setProgress(status);
    }

    public  void  getProgress(int status){
        this.status = status;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog;
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Downloading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return dialog = progressDialog;
    }

}
