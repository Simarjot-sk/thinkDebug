package com.simarjot.task.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.simarjot.task.network.model.Option;

import java.util.List;

public class ShowSelectedOptionsDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MainViewModel mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(requireContext());
        alertDialogBuilder.setTitle("Selected Options");
        List<Option> selectedOptions = mainViewModel.getSelectedOptions();
        CharSequence[] items = new CharSequence[selectedOptions.size()];

        for (int i = 0; i < selectedOptions.size(); i++) {
            Option option = selectedOptions.get(i);
            items[i] = "Question: " + option.getQuestionId() + "        " + "Option: " + option.getOptionId();
        }

        alertDialogBuilder.setItems(items, (dialog, which) -> {
            //do nothing here
        });

        return alertDialogBuilder.create();
    }
}
