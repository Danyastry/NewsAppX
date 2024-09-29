package com.example.newsappx.presentation.onBoarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappx.presentation.onBoarding.Page
import com.example.newsappx.ui.theme.montserrat_bold
import com.example.newsappx.ui.theme.montserrat_reg

@Composable
fun OnBoardingPage(page: Page) {
    Column {
        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .clip(RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp))

        )
        Spacer(Modifier.height(30.dp))
        Box {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    page.title,
                    fontSize = 25.sp,
                    fontFamily = montserrat_bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    page.subTitle,
                    fontSize = 22.sp,
                    fontFamily = montserrat_reg,
                    color = Color.LightGray.copy(0.8f),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}