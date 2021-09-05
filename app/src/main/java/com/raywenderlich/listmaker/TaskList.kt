package com.raywenderlich.listmaker

import android.os.Parcel
import android.os.Parcelable

class TaskList(val name: String, val tasks: ArrayList<String> =
    ArrayList()) : Parcelable {
    //reading from parcel
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.createStringArrayList()!!
    )
    override fun describeContents() = 0
    //writing to parcel
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(tasks)
    }
    //fulfilling static interface requirements
    companion object CREATOR: Parcelable.Creator<TaskList> {
        //calling constructor
        override fun createFromParcel(source: Parcel): TaskList =
            TaskList(source)
        override fun newArray(size: Int): Array<TaskList?> =
            arrayOfNulls(size)
    }

    }