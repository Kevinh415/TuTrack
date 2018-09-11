package com.fakapow.tutrack;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nameId")
    private String id;
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(String word) {
        this.id = UUID.randomUUID().toString();
        this.mWord = word;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getWord(){return this.mWord;}
}