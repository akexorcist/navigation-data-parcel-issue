package com.example.parcelbug

import android.os.Parcel
import android.os.Parcelable

/**
 * NavigationData - Parcelable with BUG for reproduction.
 *
 * BUG: field3 is written in writeToParcel() but NEVER read in the constructor,
 * causing "consumed 88 bytes, but 92 expected" crash during deserialization.
 */
open class NavigationData : Parcelable {

    var field1: String? = null
    var field2: String? = null
    var field3: String? = null // Written but NOT read (bug)

    constructor()

    @Suppress("DEPRECATION", "UNCHECKED_CAST")
    protected constructor(parcel: Parcel) {
        field1 = parcel.readString()
        field2 = parcel.readString()
        /**
         * BUG: Missing field3 = parcel.readString()
         */
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(field1)
        parcel.writeString(field2)
        parcel.writeString(field3)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<NavigationData> {
        override fun createFromParcel(parcel: Parcel): NavigationData = NavigationData(parcel)
        override fun newArray(size: Int): Array<NavigationData?> = arrayOfNulls(size)
    }
}
