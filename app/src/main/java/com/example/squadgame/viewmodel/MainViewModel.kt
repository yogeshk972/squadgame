package com.example.squadgame.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {


    private val squads = listOf(
        "Nucleus", "Sigma", "Qubit", "Electrons", "Momentum", "Photon", "Quantum",
        "Delta"
    )

    private val members = listOf(
        "Omar", "Prashant", "Sanchit", "Sanjeev", "Ankit", "Abhishek", "Faheem",
        "Ankit Raj", "Sakshi Pruthi", "Aditya Mathur", "Moghira", "Abhilash",
        "Gauri Advani", "Gunjit", "Nitin Bhatia",
        "Vaibhav", "Vatsal", "Yogesh", "Ricky", "Tushar"
    )

    private val teamMap = HashMap<String, String>()
    fun buildTeamMap(){
        teamMap.apply {
            this["Omar"] = "Sigma"
            this["Prashant"] = "Nucleus"
            this["Sanchit"] = "Qubit"
            this["Sanjeev"] = "Electrons"
            this["Ankit"] = "Sigma"
            this["Abhishek"] = "Photon"
            this["Faheem"] = "Photon"
            this["Ankit Raj"] = "Momentum"
            this["Sakshi Pruthi"] = "Qubit"
            this["Aditya Mathur"] = "Nucleus"
            this["Moghira"] = "Momentum"
            this["Abhilash"] = "Delta"
            this["Gauri Advani"] = "Quantum"
            this["Gunjit"] = "Nucleus"
            this["Nitin Bhatia"] = "Nucleus"
            this["Vaibhav"] = "Sigma"
            this["Yogesh"] = "Qubit"
            this["Ricky"] = "Nucleus"
            this["Tushar"] = "Sigma"
        }
    }

    var score = MutableStateFlow(0)
    var screenId = MutableStateFlow(1)

    init{
        buildTeamMap()
        score.value= 0
        screenId.value= 1
    }


    fun setScore(points: Int){
        score.value= points
    }
    
    fun setScreenId(id: Int){
        screenId.value= id
    }

    fun addScore(points: Int){
        score.value+= points
    }
    

    fun getSquads(): List<String>{
        return squads
    }

    fun getMembers(): List<String>{
        return members
    }

    fun getTeamMap(): Map<String, String>{
        return teamMap
    }

    fun resetPlay(){
        score.value= 0
        screenId.value= 1
    }
//
//    fun get_score.value(): Int {
//        return _score.value
//    }
//
//    fun get_screenId.value(): Int{
//        return _screenId.value.value
//    }



}