package com.kiran2kirve.gojekassignment.viewmodels;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.kiran2kirve.gojekassignment.models.AuthorList;
import com.kiran2kirve.gojekassignment.repositories.UserResultRepository;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<AuthorList>> resultList = new MutableLiveData<>();
    private UserResultRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private Context context;

    public void init(Context cxt) {
        context = cxt;
        mRepo = UserResultRepository.getInstance(context);
        resultList = mRepo.getUserResultList();
    }

    public LiveData<List<AuthorList>> getUserResultList() {
        if (resultList == null) {
            resultList = new MutableLiveData<>();
        }
        return resultList;
    }

    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }


}
