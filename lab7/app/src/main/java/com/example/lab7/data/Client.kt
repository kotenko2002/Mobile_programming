package com.example.lab7.data

import android.os.Parcel
import android.os.Parcelable

data class Client(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val age: Int = 0,
    val gender: String = "",
    val schedule: String = "",
    val contactInfo: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
    ) {
    }

    val ageCategory: String
        get() = when {
            age < 18 -> "Under 18"
            age in 18..25 -> "18-25"
            age in 26..35 -> "26-35"
            age in 36..45 -> "36-45"
            else -> "46+"
        }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(email)
        parcel.writeInt(age)
        parcel.writeString(gender)
        parcel.writeString(schedule)
        parcel.writeString(contactInfo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Client> {
        override fun createFromParcel(parcel: Parcel): Client {
            return Client(parcel)
        }

        override fun newArray(size: Int): Array<Client?> {
            return arrayOfNulls(size)
        }
    }
}
