package com.example.newsappx.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.example.newsappx.R

data class Page(
    val title: String,
    val subTitle: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "The Rise of Artificial Intelligence",
        subTitle = "How AI is Revolutionizing Industries, Enhancing Human Capabilities, and Redefining the Future of Technology",
        image = R.drawable.background_1
    ),
    Page(
        title = "Exploring Culinary Delights",
        subTitle = "Discover the Rich Tapestry of Global Flavors, Innovative Recipes, and Cooking Techniques That Bring Cultures Together",
        image = R.drawable.background_2
    ),
    Page(
        title = "Shaping the Future of Governance",
        subTitle = "Understanding the Impact of Political Movements, Policy Changes, and Global Power Shifts in an Ever-Evolving World",
        image = R.drawable.background_3
    ),
)
