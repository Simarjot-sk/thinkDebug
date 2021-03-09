package com.simarjot.task.ui;

import androidx.fragment.app.Fragment;

import com.simarjot.task.network.model.QuestionDto;

public class QuestionFragment extends Fragment {
    private final QuestionDto questionDto;

    public QuestionFragment(QuestionDto questionDto) {
        this.questionDto = questionDto;
    }
}
