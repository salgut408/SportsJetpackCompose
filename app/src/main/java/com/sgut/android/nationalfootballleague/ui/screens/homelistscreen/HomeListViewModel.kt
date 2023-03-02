package com.sgut.android.nationalfootballleague.ui.screens.homelistscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.sgut.android.nationalfootballleague.domain.domainmodels.TeamDomainModel
import com.sgut.android.nationalfootballleague.data.service.AccountService
import com.sgut.android.nationalfootballleague.data.repository.EspnRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val accountService: AccountService,
    private val espnRepository: EspnRepositoryImpl,
) : ViewModel() {

    private val _ListUiState = MutableStateFlow(ListUiState())
    val listUiState: StateFlow<ListUiState> = _ListUiState.asStateFlow()

    var nflTeamsList = mutableStateOf<List<TeamDomainModel>>(listOf())
    var collegeTeamsList = mutableStateOf<List<TeamDomainModel>>(listOf())
    var baseballTeamsList = mutableStateOf<List<TeamDomainModel>>(listOf())
    var hockeyTeamsList = mutableStateOf<List<TeamDomainModel>>(listOf())
    var basketballTeamsList = mutableStateOf<List<TeamDomainModel>>(listOf())
    var soccerTeamsList = mutableStateOf<List<TeamDomainModel>>(listOf())
    var womensBasketballTeamsList = mutableStateOf<List<TeamDomainModel>>(listOf())
    var worldCupTeams = mutableStateOf<List<TeamDomainModel>>(listOf())
    var collegeBasketballTeams = mutableStateOf<List<TeamDomainModel>>(listOf())
    var laLigaTeams = mutableStateOf<List<TeamDomainModel>>(listOf())
    var englishTeams = mutableStateOf<List<TeamDomainModel>>(listOf())
    var euroTeams = mutableStateOf<List<TeamDomainModel>>(listOf())


    init {
        loadAllNflTeams()
        loadAllCollegeTeams()
        loadAllBaseballTeams()
        loadAllBasketballTeams()
        loadAllSoccerTeams()
        loadAllWomensBasketballTeams()
        loadAllHockeyTeams()
        loadWorldCupTeams()
        loadCollegeBasketballTeams()
        setAccount()
        loadEnglishTeams()
        loadLaLigaTeams()
        loadEuroTeams()

    }

    fun loadEuroTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getLaLigaSoccerTeams()
            euroTeams.value = result
        } catch (e: Exception) {
            Log.e("Euro", e.message.toString())
        }
    }

    fun loadLaLigaTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getLaLigaSoccerTeams()
            laLigaTeams.value = result
        } catch (e: Exception) {
            Log.e("Liga", e.message.toString())
        }
    }

    fun loadEnglishTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllEnglishSoccerTeams()
            englishTeams.value = result
        } catch (e: Exception) {
            Log.e("Eng", e.message.toString())
        }
    }

    fun loadAllBaseballTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllBaseballTeams()
            baseballTeamsList.value = result
        } catch (e: Exception) {
            Log.i("tag", e.message.toString())
        }
    }

    fun loadAllNflTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getTeams()
            nflTeamsList.value = result
//            Default list Ui State set here
            _ListUiState.update { currentState ->
                currentState.copy(
                    currentTeams = result,
                    currentSport = "football",
                    currentLeague = "nfl"
                )
            }
        } catch (e: Exception) {
            Log.i("tag", e.message.toString())
        }
    }

    fun loadAllCollegeTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllCollegeTeams()
            collegeTeamsList.value = result
        } catch (e: Exception) {
            Log.i("tag", e.message.toString())

        }
    }

    fun loadAllHockeyTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllHockeyTeams()
            hockeyTeamsList.value = result

        } catch (e: Exception) {
            Log.i("tag", e.message.toString())

        }
    }

    fun loadAllBasketballTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllBasketballTeams()
            basketballTeamsList.value = result
        } catch (e: Exception) {
            Log.i("tag", e.message.toString())
        }
    }

    fun loadAllSoccerTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllSoccerTeams()
            soccerTeamsList.value = result
        } catch (e: Exception) {
            Log.i("tag", e.message.toString())
        }
    }

    fun loadWorldCupTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllWorldCupTeams()
            worldCupTeams.value = result
        } catch (e: Exception) {
            Log.e("tag", e.message.toString())
        }
    }

    fun loadAllWomensBasketballTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllWomensBasketballTeams()
            womensBasketballTeamsList.value = result
        } catch (e: Exception) {
            Log.i("tag", e.message.toString())
        }
    }

    fun loadCollegeBasketballTeams() = viewModelScope.launch {
        try {
            val result = espnRepository.getAllCollegeBasketballTeams()
            collegeBasketballTeams.value = result
        } catch (e: Exception) {
            Log.i("tag", e.message.toString())
        }
    }




    fun setLaLigaTeams() {
        _ListUiState.update {
            it.copy(currentTeams = laLigaTeams.value,
                currentSport = "soccer",
                currentLeague = "esp.1")
        }
    }

    fun setEnglishTeams() {
        _ListUiState.update {
            it.copy(currentTeams = englishTeams.value,
                currentSport = "soccer",
                currentLeague = "eng.1")
        }
    }

    fun setEuroTeams() {
        _ListUiState.update {
            it.copy(currentTeams = euroTeams.value,
                currentSport = "soccer",
                currentLeague = "uefa.europa")
        }
    }


    fun setBaseballTeam() {
        _ListUiState.update {
            it.copy(currentTeams = baseballTeamsList.value,
                currentSport = "baseball",
                currentLeague = "mlb")
        }
    }

    fun setCollegeBasketballTeam() {
        _ListUiState.update {
            it.copy(currentTeams = collegeBasketballTeams.value,
                currentSport = "basketball",
                currentLeague = "mens-college-basketball")
        }
    }

    fun setBasketballTeam() {
        _ListUiState.update {
            it.copy(currentTeams = basketballTeamsList.value,
                currentSport = "basketball",
                currentLeague = "nba")
        }
    }

    fun setFootballTeam() {
        _ListUiState.update {
            it.copy(currentTeams = nflTeamsList.value,
                currentSport = "football",
                currentLeague = "nfl")
        }
    }


    fun setHockeyTeam() {
        _ListUiState.update {
            it.copy(currentTeams = hockeyTeamsList.value,
                currentSport = "hockey",
                currentLeague = "nhl")
        }
    }

    fun setWnbaTeam() {
        _ListUiState.update {
            it.copy(currentTeams = womensBasketballTeamsList.value,
                currentSport = "basketball",
                currentLeague = "wnba")
        }
    }

    fun setMlsTeam() {
        _ListUiState.update {
            it.copy(currentTeams = soccerTeamsList.value,
                currentSport = "soccer",
                currentLeague = "usa.1")
        }
    }

    fun setCollegeTeam() {
        _ListUiState.update {
            it.copy(currentTeams = collegeTeamsList.value,
                currentSport = "football",
                currentLeague = "college-football")
        }
    }

    fun setWorldCupTeam() {
        _ListUiState.update {
            it.copy(currentTeams = worldCupTeams.value,
                currentSport = "soccer",
                currentLeague = "fifa.world")
        }
    }



    fun setAccount() = viewModelScope.launch {
        if (!accountService.hasUser) {
            createAnonymousAccount()
        }
    }

    private fun createAnonymousAccount() = viewModelScope.launch {
        try {
            accountService.createAnonymousAccount()
        } catch (ex: FirebaseAuthException) {
            throw ex
        }
    }
}