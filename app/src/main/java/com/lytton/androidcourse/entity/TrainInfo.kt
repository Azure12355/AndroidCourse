package com.lytton.androidcourse.entity// com.lytton.androidcourse.entity.TrainInfo.kt
import android.os.Parcel
import android.os.Parcelable

data class TrainInfo(
    val name: String,
    val start: String,
    val end: String,
    val price: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(start)
        parcel.writeString(end)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrainInfo> {
        override fun createFromParcel(parcel: Parcel): TrainInfo {
            return TrainInfo(parcel)
        }

        override fun newArray(size: Int): Array<TrainInfo?> {
            return arrayOfNulls(size)
        }
    }
}
