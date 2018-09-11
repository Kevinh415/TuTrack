package com.fakapow.tutrack;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "categories",
        foreignKeys = @ForeignKey(entity = Word.class,
                                  parentColumns = "nameId",
                                  childColumns = "owner",
                                  onDelete = CASCADE)
        )
public class Category {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "categoryId")
    public int id;
    public String category;
    public String owner;

    public Category(int id, String category, String owner){
        this.id = id;
        this.category = category;
        this.owner = owner;
    }
}
