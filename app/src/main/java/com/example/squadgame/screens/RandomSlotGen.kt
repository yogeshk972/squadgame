package com.example.squadgame.screens

import android.os.Handler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.squadgame.R
import com.example.squadgame.ui.theme.BabyBlue
import com.example.squadgame.ui.theme.RedOrange
import com.example.squadgame.viewmodel.MainViewModel
import kotlin.random.Random

const val MAX_SCREENS= 5

@Composable
fun RandomSlotGen(navHostController: NavHostController, name: String, score: Int, screenId: Int) {
    val context = LocalContext.current

    if (screenId == MAX_SCREENS+1) {
        val route = "finishScreen?name=$name&score=$score"
        LaunchedEffect(context) {
            navHostController.navigate(route) {
                popUpTo("home") {
                    inclusive = false
                }
            }
        }
        return
    }

    val viewModel = MainViewModel()


    val squads = viewModel.getSquads()
    val members = viewModel.getMembers()
    val teamMap = viewModel.getTeamMap()

    val targetSquad = Random.nextInt(squads.size)
    val targetMember = Random.nextInt(members.size)


    var nextBtnEnabled by remember {
        mutableStateOf(false)
    }

    var squadIdx by remember {
        mutableStateOf(0)
    }
    var memberIdx by remember {
        mutableStateOf(0)
    }

    viewModel.setScore(score)
    viewModel.setScreenId(screenId)

    val scoreState = viewModel.score.collectAsState()
    val screenIdState = viewModel.screenId.collectAsState()

    val iterations = 1 + Random.nextInt(2)
    val handler = Handler()
    var isPersonSet by remember {
        mutableStateOf(false)
    }
    var isSquadSet by remember {
        mutableStateOf(false)
    }

    var iter by remember {
        mutableStateOf(0)
    }

    val runnable = Runnable {
        if (!isPersonSet)
            memberIdx = if (memberIdx == members.size - 1) 0 else memberIdx + 1

        if (!isSquadSet)
            squadIdx = if (squadIdx == squads.size - 1) 0 else squadIdx + 1

        iter++
    }
    handler.postDelayed(runnable, 100)



    if (iter >= iterations * members.size && memberIdx == targetMember) {
        isPersonSet = true
    }
    if (iter >= iterations * squads.size && squadIdx == targetSquad) {
        isSquadSet = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(
            Modifier
                .align(TopCenter)
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = RedOrange,
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 6.dp),
                    text = "score : ${scoreState.value}",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1
                )
            }
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = RedOrange,
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 6.dp),
                    text = "game : ${screenIdState.value}/5",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1
                )
            }
        }

        Column(
            Modifier
                .fillMaxWidth()
                .align(Center)
        ) {

            Row(
                Modifier
                    .align(CenterHorizontally)
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = BabyBlue,
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .width(150.dp)
                        .height(150.dp)
                ) {

                    Box(Modifier.fillMaxSize()) {
                        Text(
                            text = members[memberIdx],
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 1,
                            modifier = Modifier.align(Center)
                        )
                    }


                }

                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = BabyBlue,
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .width(150.dp)
                        .height(150.dp)
                ) {
                    Box(Modifier.fillMaxSize()) {
                        Text(
                            text = squads[squadIdx],
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 1,
                            modifier = Modifier.align(Center)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.align(CenterHorizontally)
            ) {

                Button(
                    onClick = {
                        val route =
                            "randomSlot?name=$name&score=${scoreState.value}&screenId=${1 + screenIdState.value}"
                        navHostController.navigate(route) {
                            popUpTo("home") {
                                inclusive = false
                            }
                        }
                    },
                    enabled = nextBtnEnabled,
                    colors = ButtonDefaults.buttonColors(Color.Magenta)
                ) {
                    Text(text = "next game", color = MaterialTheme.colors.primary)
                }
            }

        }


        if (isPersonSet && isSquadSet) {
            val assetId = remember {
                mutableStateOf(-1)
            }
            val compositionResult =
                rememberLottieComposition(LottieCompositionSpec.RawRes(assetId.value))
            val progress by animateLottieCompositionAsState(compositionResult.value)

            LottieAnimation(
                composition = compositionResult.value,
                restartOnPlay = false,
                speed = 0.8f,
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .align(Center)
            )

            if (teamMap[members[memberIdx]] == squads[squadIdx]) {
                assetId.value = R.raw.match
            } else {
                assetId.value = R.raw.failure
            }

            if (progress == 1.0f) {
                if(assetId.value == R.raw.match)
                    viewModel.addScore(1)
                nextBtnEnabled = true
            }
        }
    }
}

