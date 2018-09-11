package com.fakapow.tutrack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository mRepository;

    private LiveData<List<Category>> mAllCategories;

    private LiveData<List<Category>> mCategories;

    public CategoryViewModel (Application application, String parentId) {
        super(application);
        mRepository = new CategoryRepository(application, parentId);
        //mAllCategories = mRepository.getAllCategories();
        mCategories = mRepository.findChildCategories(parentId);
    }

    LiveData<List<Category>> getAllCategories() { return mAllCategories; }

    LiveData<List<Category>> findChildCategories(String parentId){
        return mCategories;
    }
    public void insert(Category category) { mRepository.insert(category); }
}