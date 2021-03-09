package com.simarjot.task.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.simarjot.task.network.model.QuestionDto;

import java.util.List;

public class QuestionAdapter extends FragmentStateAdapter {
    private final List<QuestionDto> questions;

    public QuestionAdapter(@NonNull FragmentActivity fragmentActivity, List<QuestionDto> questions) {
        super(fragmentActivity);
        this.questions = questions;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new QuestionFragment(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
