package com.baguio.admincrud

import android.os.Parcel
import android.os.Parcelable

data class ClassSuspension_data(
    val id: String? = null,  // Unique identifier for each announcement
    val serviceAdvisory: String?,
    val dateClassSuspension: String?,
    val cause: String?,
    val others: String?,
    val isElementarySelected: Boolean,
    val isHighSchoolSelected: Boolean,
    val isCollegeSelected: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id) // Add this line to write the id to the Parcel
        parcel.writeString(serviceAdvisory)
        parcel.writeString(dateClassSuspension)
        parcel.writeString(cause)
        parcel.writeString(others)
        parcel.writeByte(if (isElementarySelected) 1 else 0)
        parcel.writeByte(if (isHighSchoolSelected) 1 else 0)
        parcel.writeByte(if (isCollegeSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClassSuspension_data> {
        override fun createFromParcel(parcel: Parcel): ClassSuspension_data {
            return ClassSuspension_data(parcel)
        }

        override fun newArray(size: Int): Array<ClassSuspension_data?> {
            return arrayOfNulls(size)
        }
    }
}
