package com.example.fooddelivery

import android.os.Bundle
import androidx.compose.ui.tooling.preview.Device
import androidx.navigation.NavType
import com.example.fooddelivery.data.PopularData
import com.google.gson.Gson

class AssetParamType : NavType<PopularData>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PopularData? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): PopularData {
        return Gson().fromJson(value, PopularData::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: PopularData) {
        bundle.putParcelable(key, value)
    }
}