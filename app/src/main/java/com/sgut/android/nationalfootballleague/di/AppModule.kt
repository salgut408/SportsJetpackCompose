package com.sgut.android.nationalfootballleague.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.db.article.ArticleDao
import com.sgut.android.nationalfootballleague.data.db.team.TeamsDao
import com.sgut.android.nationalfootballleague.data.location.DefaultLocationTrackerImpl
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.data.repository.ArticleRepositoryImpl
import com.sgut.android.nationalfootballleague.data.repository.AthleteRepositoryImpl
import com.sgut.android.nationalfootballleague.data.repository.GameDetailsRepositoryImpl
import com.sgut.android.nationalfootballleague.data.repository.ScoreboardRepositoryImpl
import com.sgut.android.nationalfootballleague.data.repository.SportRepositoryImpl
import com.sgut.android.nationalfootballleague.data.repository.StandingsRepositoryImpl
import com.sgut.android.nationalfootballleague.data.repository.TeamDetailsRepositoryImpl
import com.sgut.android.nationalfootballleague.data.service.AccountService
import com.sgut.android.nationalfootballleague.data.service.LogService
import com.sgut.android.nationalfootballleague.data.service.StorageService
import com.sgut.android.nationalfootballleague.data.service.impl.AccountServiceImpl
import com.sgut.android.nationalfootballleague.data.service.impl.LogServiceImpl
import com.sgut.android.nationalfootballleague.data.service.impl.StorageServiceImpl
import com.sgut.android.nationalfootballleague.domain.location.LocationTracker
import com.sgut.android.nationalfootballleague.domain.repositories.ArticleRepository
import com.sgut.android.nationalfootballleague.domain.repositories.AthleteRepository
import com.sgut.android.nationalfootballleague.domain.repositories.GameDetailsRepository
import com.sgut.android.nationalfootballleague.domain.repositories.ScoreboardRepository
import com.sgut.android.nationalfootballleague.domain.repositories.SportRepository
import com.sgut.android.nationalfootballleague.domain.repositories.StandingsRepository
import com.sgut.android.nationalfootballleague.domain.repositories.TeamDetailsRepository
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

/**
 * App-wide infrastructure: database, networking, dispatchers, Firebase, location.
 *
 * Repositories and use cases are NOT provided here. They all have `@Inject`
 * constructors, so Hilt builds them itself; [RepositoryModule] only tells Hilt
 * which implementation backs each domain interface.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Database

    @Provides
    @Singleton
    fun provideSportsDatabase(@ApplicationContext context: Context): SportsDataBase =
        Room.databaseBuilder(context, SportsDataBase::class.java, "sports_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideArticleDao(sportsDataBase: SportsDataBase): ArticleDao = sportsDataBase.getArticleDao()

    @Provides
    fun provideTeamDao(sportsDataBase: SportsDataBase): TeamsDao = sportsDataBase.getTeamsDao()

    // Coroutines

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    // Networking

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                Timber.d("SAL_GUT ENDPOINT - ${request.url}")
                chain.proceed(request)
            }
            .build()

    @Provides
    @Singleton
    fun provideEspnApi(okHttpClient: OkHttpClient, json: Json): SportsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(SportsApi::class.java)

    // Firebase

    @Provides
    fun auth(): FirebaseAuth = Firebase.auth

    @Provides
    fun firestore(): FirebaseFirestore = Firebase.firestore

    // Location

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(
        application: Application,
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)

    @Provides
    @Singleton
    fun providesLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient,
        application: Application,
    ): LocationTracker = DefaultLocationTrackerImpl(
        fusedLocationProviderClient = fusedLocationProviderClient,
        application = application,
    )

    @Module
    @InstallIn(ViewModelComponent::class)
    abstract class ServiceModule {
        @Binds
        abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

        @Binds
        abstract fun provideLogService(impl: LogServiceImpl): LogService

        @Binds
        abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
    }
}

/**
 * Interface-to-implementation bindings for the data layer. `@Binds` is preferred over
 * `@Provides` here: it's a pure type mapping, so Dagger generates no factory method and
 * constructor changes in an impl never require touching this module.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSportRepository(impl: SportRepositoryImpl): SportRepository

    @Binds
    abstract fun bindStandingsRepository(impl: StandingsRepositoryImpl): StandingsRepository

    @Binds
    abstract fun bindTeamDetailsRepository(impl: TeamDetailsRepositoryImpl): TeamDetailsRepository

    @Binds
    abstract fun bindGameDetailsRepository(impl: GameDetailsRepositoryImpl): GameDetailsRepository

    @Binds
    abstract fun bindScoreboardRepository(impl: ScoreboardRepositoryImpl): ScoreboardRepository

    @Binds
    abstract fun bindArticleRepository(impl: ArticleRepositoryImpl): ArticleRepository

    @Binds
    abstract fun bindAthleteRepository(impl: AthleteRepositoryImpl): AthleteRepository
}
