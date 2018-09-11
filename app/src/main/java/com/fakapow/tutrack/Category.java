package com.fakapow.tutrack;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import static android.arch.persistence.room.ForeignKey.CASCADE;
/*
@Entity(tableName = "categories",
        foreignKeys = @ForeignKey(entity = Word.class,
                                  parentColumns = "nameId",
                                  childColumns = "categoryId",
                                  onDelete = CASCADE)
        )
*/
@Entity(tableName = "categories")
public class Category {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "categoryId")
    private String categoryId;
    @ColumnInfo(name = "category")
    public String category;
    @ColumnInfo(name = "owner")
    public String owner;

    public Category(String category, String owner){
        this.categoryId = UUID.randomUUID().toString();
        this.category = category;
        this.owner = owner;
    }

    public String getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public String getCategoryString(){
        return category;
    }

    public void setCategoryString(String category){
        this.category = category;
    }
}
