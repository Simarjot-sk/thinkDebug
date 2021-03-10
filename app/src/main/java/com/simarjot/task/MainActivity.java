package com.simarjot.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayoutMediator;
import com.simarjot.task.databinding.ActivityMainBinding;
import com.simarjot.task.network.model.QuestionDto;
import com.simarjot.task.ui.MainViewModel;
import com.simarjot.task.ui.QuestionAdapter;
import com.simarjot.task.ui.model.state.DataFetched;
import com.simarjot.task.ui.model.state.Error;
import com.simarjot.task.ui.model.state.Loading;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.loadQuestions();
        mainViewModel.getState().observe(this, questionsState -> {
            //loading
            int visibility = questionsState instanceof Loading ? View.VISIBLE : View.GONE;
            binding.progressIndicator.setVisibility(visibility);

            //error
            if (questionsState instanceof Error) {
                String errorMessage = ((Error<List<QuestionDto>>) questionsState).errorMessage;
                Snackbar.make(binding.getRoot(), errorMessage, Snackbar.LENGTH_SHORT).show();
            }

            //data fetched successfully
            if (questionsState instanceof DataFetched) {
                List<QuestionDto> questionDtoList = ((DataFetched<List<QuestionDto>>) questionsState).data;
                Snackbar.make(binding.getRoot(), questionDtoList.size() + " items loaded", Snackbar.LENGTH_SHORT).show();


                QuestionAdapter questionAdapter = new QuestionAdapter(this, questionDtoList);
                binding.viewPager.setAdapter(questionAdapter);

                new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
                    tab.setText("Q: " + (position + 1));
                }).attach();
            }
        });
    }
}

