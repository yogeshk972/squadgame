package com.example.squadgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.squadgame.ui.theme.Purple500
import com.example.squadgame.utils.CustomLottieAnimation

@Composable
fun HomeScreen(navHostController: NavHostController){

    val context = LocalContext.current
    var name by remember { mutableStateOf(TextFieldValue("")) }

    Box(Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {


            OutlinedTextField(
                value = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colors.primary, RoundedCornerShape(8.dp))
                ,
                onValueChange = {newText ->
                    name = newText
                },
                label = {
                    Text("Name", color = MaterialTheme.colors.onSurface)
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSurface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navHostController.navigate("randomSlot?name=${name.text}&score=${0}&screenId=${1}")
                },
                shape = CircleShape,
                enabled = name.text.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(Purple500)
            ) {
                Text(text = "Start Game", color = MaterialTheme.colors.primary)
            }
        }
    }

}