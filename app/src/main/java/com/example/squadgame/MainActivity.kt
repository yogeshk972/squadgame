package com.example.squadgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.squadgame.screens.FinishScreen
import com.example.squadgame.screens.RandomSlotGen
import com.example.squadgame.ui.theme.SquadGameTheme
import com.example.squadgame.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            SquadGameTheme {
                Scaffold {
                    NavHost(navController= navController, startDestination = "home") {

                        composable(route = "home") {
                            HomeScreen(navHostController = navController)
                        }

                        composable(route= "randomSlot"+ "?name={name}&score={score}&screenId={screenId}",
                            arguments= listOf(
                                navArgument(name= "name"){
                                    type= NavType.StringType
                                    defaultValue= ""
                                },
                                navArgument(name= "score"){
                                    type= NavType.IntType
                                    defaultValue= 0
                                },
                                navArgument(name= "screenId"){
                                    type= NavType.IntType
                                    defaultValue= 1
                                }
                            )
                        ){
                            val name= it.arguments?.getString("name") ?: ""
                            val score= it.arguments?.getInt("score") ?: 0
                            val screenId= it.arguments?.getInt("screenId") ?: 1
                            RandomSlotGen(
                                navHostController = navController,
                                name,
                                score,
                                screenId
                            )
                        }

                        composable(route = "finishScreen" + "?name={name}&score={score}",
                            arguments= listOf(
                                navArgument(name= "name"){
                                    type= NavType.StringType
                                    defaultValue= ""
                                },
                                navArgument(name= "score"){
                                    type= NavType.IntType
                                    defaultValue= 0
                                }
                            )){
                            val name= it.arguments?.getString("name") ?: ""
                            val score= it.arguments?.getInt("score") ?: 0

                            FinishScreen(navHostController= navController, name = name, score = score)
                        }

                    }
                }

            }
        }
    }
}

