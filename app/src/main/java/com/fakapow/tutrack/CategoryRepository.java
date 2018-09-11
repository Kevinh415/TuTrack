package com.fakapow.tutrack;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CategoryRepository {

    private CategoryDao mCategoryDao;
    private LiveData<List<Category>> mAllCategories;

    CategoryRepository(Application application, String parentId) {
        CategoryRoomDatabase db = CategoryRoomDatabase.getDatabase(application);
        mCategoryDao = db.categoryDao();
        //mAllCategories = mCategoryDao.getAllCategories();
        mAllCategories = mCategoryDao.findChildCategories(parentId);
    }

    LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    LiveData<List<Category>> findChildCategories(String parentId){
        return mAllCategories;
    }

    public void insert (Category category) {
        new insertAsyncTask(mCategoryDao).execute(category);
    }

    private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao mAsyncTaskDao;

        insertAsyncTask(CategoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}