package com.fakapow.tutrack;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("DELETE FROM categories")
    void deleteAll();

    @Query("SELECT * from categories ORDER BY categoryId ASC")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM categories WHERE owner=:parentId")
    LiveData<List<Category>> findChildCategories(String parentId);

}