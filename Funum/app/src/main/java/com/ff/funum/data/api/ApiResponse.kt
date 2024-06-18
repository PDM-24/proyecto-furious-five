package com.ff.funum.data.api


import com.ff.funum.utils.Constants
import com.google.gson.annotations.SerializedName

data class ApiResponseError(
    @SerializedName(value = Constants.ResponseError)
    val error: String
)

data class ApiResponseSuccessful(
    @SerializedName(value = Constants.ResponseSuccessful)
    val message: String
)

