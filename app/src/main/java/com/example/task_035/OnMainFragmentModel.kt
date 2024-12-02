package com.example.task_035

import java.io.Serializable

class OnMainFragmentModel(
    val imageView: Int,
    val cityCaption: String
) : Serializable {
    companion object {
        val viewPagerList = mutableListOf(
            OnMainFragmentModel(R.drawable.moscow, "Moscow"),
            OnMainFragmentModel(R.drawable.kazan, "Kazan"),
            OnMainFragmentModel(R.drawable.yaroslavl, "yaroslavl"),
            OnMainFragmentModel(R.drawable.any_city, "any_city"),
        )
    }
}