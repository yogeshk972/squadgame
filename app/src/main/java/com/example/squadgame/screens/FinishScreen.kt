package com.example.squadgame.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.squadgame.R
import com.example.squadgame.ui.theme.Purple500
import com.example.squadgame.utils.CustomLottieAnimation

@Composable
fun FinishScreen(navHostController: NavController, name: String, score: Int){

    Column{
        Box(
            Modifier
                .width(300.dp)
                .height(300.dp)
                .align(CenterHorizontally)
        ) {
            val compositionResult =
                rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.finished))
            LottieAnimation(
                composition = compositionResult.value
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Name : $name",
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Score : $score",
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            Modifier
                .align(CenterHorizontally)
        ) {
            Button(
                onClick = {
                    navHostController.navigate("home") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Purple500)
            ) {
                Text(text = "Restart Game", color = MaterialTheme.colors.primary)
            }
        }
    }

}