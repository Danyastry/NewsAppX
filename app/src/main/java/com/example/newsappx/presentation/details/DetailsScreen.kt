package com.example.newsappx.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsappx.R
import com.example.newsappx.domain.model.Article
import com.example.newsappx.ui.theme.ReadButton
import com.example.newsappx.ui.theme.montserrat_bold
import com.example.newsappx.ui.theme.montserrat_reg
import es.dmoral.toasty.Toasty
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(article: Article, viewModel: DetailsViewModel = koinViewModel()) {

    val context = LocalContext.current
    val isBookmark = remember { mutableStateOf(false) }

    LaunchedEffect(article.url) {
        isBookmark.value = viewModel.getArticle(article.url) != null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Spacer(Modifier.height(25.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(article.urlToImage.takeIf { it?.isNotEmpty() == true }
                            ?: R.drawable.logo).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(22.dp)), contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(article.source.name, fontFamily = montserrat_reg, color = Color.White)
                    Text(article.publishedAt, fontFamily = montserrat_reg, color = Color.White)
                }

                Spacer(Modifier.height(12.dp))
                Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                    Text(
                        article.title,
                        fontFamily = montserrat_bold,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Spacer(Modifier.height(25.dp))
                    Text(
                        article.content,
                        fontFamily = montserrat_reg,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                Intent(Intent.ACTION_VIEW).also {
                                    it.data = Uri.parse(article.url)
                                    if (it.resolveActivity(context.packageManager) != null) {
                                        context.startActivity(it)
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(0.85f),
                            colors = ButtonDefaults.buttonColors(containerColor = ReadButton),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                "Read Full Article",
                                fontFamily = montserrat_reg,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                fontSize = 17.sp
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null,
                            tint = if (isBookmark.value) Color.White else ReadButton,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    if (isBookmark.value) {
                                        viewModel.deleteArticle(article)
                                        isBookmark.value = false
                                        Toasty
                                            .normal(context, "Article Deleted", Toasty.LENGTH_LONG)
                                            .show()
                                    } else {
                                        viewModel.addArticle(article)
                                        isBookmark.value = true
                                        Toasty
                                            .success(context, "Article Saved", Toasty.LENGTH_LONG)
                                            .show()
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}