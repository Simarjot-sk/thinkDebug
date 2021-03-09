package com.simarjot.task.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.simarjot.task.databinding.FragmentQuestionBinding;
import com.simarjot.task.network.model.QuestionDto;

public class QuestionFragment extends Fragment {
    private final QuestionDto questionDto;
    private FragmentQuestionBinding binding;

    public QuestionFragment(QuestionDto questionDto) {
        this.questionDto = questionDto;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQuestionBinding.inflate(inflater, container, false);
        binding.setQuestion(questionDto);
        return binding.getRoot();
    }
}
