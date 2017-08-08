package com.example.ciccc_cirac.flag_quiz_demo;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by CICCC-CIRAC on 2017-08-02.
 */

public class ResultDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new  AlertDialog.Builder(getActivity());
       dialog.setMessage(getString(R.string.results,
               MainActivityFragment.totalGuess,
               (1000/(double)MainActivityFragment.totalGuess)))
                .setPositiveButton("Reset Quiz", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            Activity activity = getActivity(); //In a Fragment returns activity in which fragment is currently associated
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // completely cleared the entire stack and made the new activity the only one in the app
                            activity.finish();
                            startActivity(intent);

                    }
                })
               .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();

                   }
               });
        return dialog.create();
    }
}
