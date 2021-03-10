package com.simarjot.task.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.simarjot.task.R;
import com.simarjot.task.databinding.FragmentQuestionBinding;
import com.simarjot.task.databinding.ItemOptionBinding;
import com.simarjot.task.network.model.Option;
import com.simarjot.task.network.model.QuestionDto;

import java.util.List;

public class QuestionFragment extends Fragment {
    private final QuestionDto questionDto;
    private FragmentQuestionBinding binding;
    private MainViewModel mainViewModel;
    private ItemOptionBinding selectedOptionBinding = null;

    public QuestionFragment(QuestionDto questionDto) {
        this.questionDto = questionDto;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //creating a shared view model
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        binding = FragmentQuestionBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setQuestion(questionDto);
        //adding options to the screen
        List<Option> options = questionDto.getOptions();
        addOptions(options, inflater);
        return binding.getRoot();
    }

    private void addOptions(List<Option> options, LayoutInflater inflater) {
        for (int i = 0; i < options.size(); i++) {
            ItemOptionBinding itemOptionBinding = ItemOptionBinding.inflate(inflater, binding.optionsLl, true);
            final Option option = options.get(i);
            itemOptionBinding.setOption(option);
            itemOptionBinding.optionNumber.setText(getResources().getString(R.string.number, Integer.toString(i + 1)));
            itemOptionBinding.getRoot().setOnClickListener((view) -> {
                onOptionClicked(itemOptionBinding);
            });
        }
    }

    public void onOptionClicked(ItemOptionBinding itemOptionBinding) {
        if (selectedOptionBinding != null) {
            //deselecting the previously selected item
            selectedOptionBinding.setIsSelected(false);
            selectedOptionBinding.getRoot().invalidate();
        }
        //selecting the newly selected item
        selectedOptionBinding = itemOptionBinding;
        itemOptionBinding.setIsSelected(true);
    }
}


