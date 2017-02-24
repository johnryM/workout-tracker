package com.example.skeeno.workouttracker.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by skeeno on 11/02/2017.
 */

public class Exercise implements Parcelable {
    String name;
    Double weight;
    int numOfReps;

    public Exercise(Double weight, int numOfReps, String name) {
        this.weight = weight;
        this.numOfReps = numOfReps;
        this.name = name;
    }

    protected Exercise(Parcel in) {
        weight = in.readDouble();
        name = in.readString();
        numOfReps = in.readInt();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(weight);
        dest.writeString(name);
        dest.writeInt(numOfReps);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfReps() {
        return numOfReps;
    }

    public void setNumOfReps(int numOfReps) {
        this.numOfReps = numOfReps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
