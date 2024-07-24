package com.example.booking_hotel.presentation.intro

import androidx.annotation.DrawableRes
import com.example.booking_hotel.R

data class Page(
    val title: String,
    val content: String,
    @DrawableRes val image: Int
)

val pages = listOf<Page>(
    Page(
        "Onism /o-ni-sm/",
        "Trong tiếng Đan Mạch, “Onism” có nghĩa là càng đi xa bao nhiêu, chúng ta càng nhận ra mình nhỏ bé bấy nhiêu.",
        R.drawable.img_intro1
    ),
    Page(
        "Bạn chỉ sống 1 lần!",
        "“Yêu cuộc sống mà bạn sống\n" +
                "Hãy sống cuộc sống mà bạn yêu”",
        R.drawable.img_intro1

    ),
    Page(
        "Xách balo lên và đi!",
        "“Cuộc đời là những chuyến đi”",
        R.drawable.img_intro1
    )
)