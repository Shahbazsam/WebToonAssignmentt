package com.example.webtoonassignmentt.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.webtoonassignmentt.R

data class WebToon(
    @DrawableRes val image : Int,
    @StringRes val title : Int,
    @StringRes val description : Int,
    @StringRes val detailedDescription : Int
)

val webtoon = listOf(
    WebToon(R.drawable.photo1, R.string.title1,R.string.description1 , R.string.detailed_description1),
    WebToon(R.drawable.photo2, R.string.title2,R.string.description2 , R.string.detailed_description2),
    WebToon(R.drawable.photo3, R.string.title3 ,R.string.description3 , R.string.detailed_description3),
    WebToon(R.drawable.photo4, R.string.title4,R.string.description4 , R.string.detailed_description4),
    WebToon(R.drawable.photo5, R.string.title5,R.string.description5 , R.string.detailed_description5),
    WebToon(R.drawable.photo6, R.string.title6,R.string.description6 , R.string.detailed_description6),
    WebToon(R.drawable.photo7, R.string.title7,R.string.description7 , R.string.detailed_description7),
    WebToon(R.drawable.photo8, R.string.title8,R.string.description8 , R.string.detailed_description8),
    WebToon(R.drawable.photo9, R.string.title9,R.string.description9 , R.string.detailed_description9),
    WebToon(R.drawable.photo10, R.string.title10,R.string.description10 , R.string.detailed_description10),
    WebToon(R.drawable.photo11, R.string.title11,R.string.description11 , R.string.detailed_description11),
    WebToon(R.drawable.photo12, R.string.title12,R.string.description12 , R.string.detailed_description12),
    WebToon(R.drawable.photo13, R.string.title13,R.string.description13 , R.string.detailed_description13),
    WebToon(R.drawable.photo14, R.string.title14,R.string.description14 , R.string.detailed_description14),
    WebToon(R.drawable.photo15, R.string.title15,R.string.description15 , R.string.detailed_description15),
    WebToon(R.drawable.photo16, R.string.title16,R.string.description16 , R.string.detailed_description16),
    WebToon(R.drawable.photo17, R.string.title17,R.string.description17 , R.string.detailed_description17),
    WebToon(R.drawable.photo18, R.string.title18,R.string.description18 , R.string.detailed_description18),
    WebToon(R.drawable.photo19, R.string.title19,R.string.description19 , R.string.detailed_description19),
    WebToon(R.drawable.photo20, R.string.title20,R.string.description20 , R.string.detailed_description20)

)