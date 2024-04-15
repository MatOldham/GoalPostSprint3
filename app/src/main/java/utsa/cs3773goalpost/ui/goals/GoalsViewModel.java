package utsa.cs3773goalpost.ui.goals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GoalsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GoalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is goals fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}