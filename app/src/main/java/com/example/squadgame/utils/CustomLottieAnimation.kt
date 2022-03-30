package com.example.squadgame.utils

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.squadgame.viewmodel.MainViewModel

@Composable
fun CustomLottieAnimation(viewModel: MainViewModel?, asset: Int, shouldUpdateScore: Boolean) {
    val compositionResult = rememberLottieComposition(LottieCompositionSpec.RawRes(asset))
    val progress by animateLottieCompositionAsState(compositionResult.value)

    LottieAnimation(
        composition = compositionResult.value,
        restartOnPlay = false,
        speed = 0.7f,
        modifier = Modifier.width(300.dp).height(300.dp)
    )

    if(progress==1.0f && shouldUpdateScore ){

    }
//    val context= LocalContext.current
//    Toast.makeText(context,"complted", Toast.LENGTH_SHORT).show()

}