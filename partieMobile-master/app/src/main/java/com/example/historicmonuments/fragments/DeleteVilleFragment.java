package com.example.historicmonuments.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.historicmonuments.dao.DeleteVilleInterface;

public class DeleteVilleFragment extends DialogFragment {

    private String message;
    private int id;
    private DeleteVilleInterface deleteInterface;

    public DeleteVilleFragment(String message, int id, DeleteVilleInterface deleteInterface) {
        this.message = message;
        this.id = id;
        this.deleteInterface = deleteInterface;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message + id + "?")
                .setPositiveButton("Accepter" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteInterface.delete(id);
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Action" , "Annuler");
                    }
                });
        return builder.create();
    }
}
