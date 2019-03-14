package com.wildcardenter.myfab.pr_sir_front_end.ViewModels;

import android.app.Application;

import com.wildcardenter.myfab.pr_sir_front_end.models.Text;
import com.wildcardenter.myfab.pr_sir_front_end.repository.StudentRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TextViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Text>> allTextList;
    public TextViewModel(@NonNull Application application) {
        super(application);
        repository=new StudentRepository(application);
        allTextList=repository.getAllText();

    }
    public LiveData<List<Text>> getAllTextList(){return allTextList;}
    public void insertText(Text text){
        repository.insertText(text);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository=null;
    }
}
