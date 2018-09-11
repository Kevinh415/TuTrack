package com.fakapow.tutrack;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "assignments")
public class Assignment {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "assignmentId")
    private String assignmentId;
    @ColumnInfo(name = "assignment")
    public String assignmentString;
    @ColumnInfo(name = "owner")
    public String owner;

    public Assignment(String assignment, String owner){
        this.assignmentId = UUID.randomUUID().toString();
        this.assignmentString = assignment;
        this.owner = owner;
    }

    public String getAssignmentId(){
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId){
        this.assignmentId = assignmentId;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public String getAssignmentString(){
        return assignmentString;
    }

    public void setCategoryString(String category){
        this.assignmentString = category;
    }
}
