package com.uzlahpri.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    private var uid: String = "0",
    private var username: String = "",
    private var email: String = ""
) : Parcelable
