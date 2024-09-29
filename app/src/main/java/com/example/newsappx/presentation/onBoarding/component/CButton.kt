package com.example.newsappx.presentation.onBoarding.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappx.ui.theme.ColorButton
import com.example.newsappx.ui.theme.montserrat_reg

@Composable
fun CButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        interactionSource = remember { MutableInteractionSource() },
        colors = ButtonDefaults.buttonColors(contentColor = ColorButton),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontFamily = montserrat_reg,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}