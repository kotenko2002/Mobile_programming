package com.example.lab9.models

import android.os.Parcel
import android.os.Parcelable

public data class DbPost(
    val id: String = "",
    var title: String = "",
    var body: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DbPost> {
        override fun createFromParcel(parcel: Parcel): DbPost {
            return DbPost(parcel)
        }

        override fun newArray(size: Int): Array<DbPost?> {
            return arrayOfNulls(size)
        }
    }
}