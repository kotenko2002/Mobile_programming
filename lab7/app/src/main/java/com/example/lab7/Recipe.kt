package com.example.lab7

import android.os.Parcel
import android.os.Parcelable

data class Recipe(
    var id: String = "",
    var title: String = "",
    var shortDescription: String = "",
    var ingredients: List<String> = emptyList(),
    var instructions: List<String> = emptyList()
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.createStringArrayList() ?: emptyList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(shortDescription)
        parcel.writeStringList(ingredients)
        parcel.writeStringList(instructions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}