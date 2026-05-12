package com.sgut.android.nationalfootballleague.data.service.impl

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.sgut.android.nationalfootballleague.domain.domainmodels.TeamDomainModel
import com.sgut.android.nationalfootballleague.data.service.AccountService
import com.sgut.android.nationalfootballleague.data.service.StorageService
import com.sgut.android.nationalfootballleague.data.service.trace
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore, private val auth: AccountService
) :
    StorageService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val teams: Flow<List<TeamDomainModel>>
        get() = auth.currentUser.flatMapLatest { user ->
            currentCollection(user.id)
                .snapshots().map { snapshot -> snapshot.toObjects() }
        }

    override suspend fun getTeam(teamId: String): TeamDomainModel? =
        currentCollection(auth.currentUserId).document(teamId).get().await().toObject()

    override suspend fun saveTeam(team: TeamDomainModel): String =
        trace(SAVE_TEAM_TRACE) {currentCollection(auth.currentUserId).add(team).await().id}


      override suspend fun updateTeam(team: TeamDomainModel): Unit =
        trace(UPDATE_TEAM_TRACE) {
            currentCollection(auth.currentUserId).document(team.id).set(team).await()
        }

    override suspend fun deleteTeam(teamId: String) {
        currentCollection(auth.currentUserId).document(teamId).delete().await()
    }

    // TODO: It's not recommended to delete on the client:

    override suspend fun deleteAllTeamsForUser(userId: String) {
       val matchingTeams = currentCollection(userId).get().await()
        matchingTeams.map { it.reference.delete().asDeferred() }.awaitAll()
    }

    private fun currentCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(TEAM_COLLECTION)


    companion object {
        private const val USER_COLLECTION = "users"
        private const val TEAM_COLLECTION = "tasks"
        private const val SAVE_TEAM_TRACE = "saveTask"
        private const val UPDATE_TEAM_TRACE = "updateTask"
    }

}