package com.example.ciccc_cirac.flag_quiz_demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by CICCC-CIRAC on 2017-08-03.
 */

public class ResultDialogAlert extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("RESULT IS "+ "\n"+"Do you want" +
                "to Restart the Quiz?")
                .setPositiveButton("RESET QUIZ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //call the method which will start the Quiz
                        Toast.makeText(getContext(),"you clicked REstart Quiz",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "you cliked cancel", Toast.LENGTH_SHORT).show();
                    }
                });

        return dialog.create();
    }
}
