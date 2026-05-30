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
import com.sgut.android.nationalfootballleague.data.repository.*
import com.sgut.android.nationalfootballleague.data.service.AccountService
import com.sgut.android.nationalfootballleague.data.service.LogService
import com.sgut.android.nationalfootballleague.data.service.StorageService
import com.sgut.android.nationalfootballleague.data.service.impl.AccountServiceImpl
import com.sgut.android.nationalfootballleague.data.service.impl.LogServiceImpl
import com.sgut.android.nationalfootballleague.data.service.impl.StorageServiceImpl
import com.sgut.android.nationalfootballleague.domain.location.LocationTracker
import com.sgut.android.nationalfootballleague.domain.repositories.*
import com.sgut.android.nationalfootballleague.domain.use_cases.*
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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideArticleDao(sportsDataBase: SportsDataBase): ArticleDao =
        sportsDataBase.getArticleDao()

    @Provides
    fun provideTeamDao(sportsDataBase: SportsDataBase): TeamsDao = sportsDataBase.getTeamsDao()

    @Provides
    @Singleton
    fun provideSportsDatabase(@ApplicationContext context: Context): SportsDataBase =
        Room.databaseBuilder(
            context,
            SportsDataBase::class.java,
            "sports_db"
        ).fallbackToDestructiveMigration()
            .build()

    // repositories

    @Provides
    fun provideSportRepository(
        sportsApi: SportsApi,
        ioDispatcher: CoroutineDispatcher,
    ): SportRepository = SportRepositoryImpl(sportsApi, ioDispatcher)


    @Provides
    fun provideStandingsRepository(
        sportsApi: SportsApi,
        ioDispatcher: CoroutineDispatcher,
    ): StandingsRepository = StandingsRepositoryImpl(sportsApi, ioDispatcher)


    @Provides
    fun provideTeamsDetailRepository(
        sportsApi: SportsApi,
        sportsDataBase: SportsDataBase,
        ioDispatcher: CoroutineDispatcher,
    ): TeamDetailsRepository = TeamDetailsRepositoryImpl(sportsApi, sportsDataBase, ioDispatcher)

    @Provides
    fun provideGameDetailsRepository(
        sportsApi: SportsApi,
        sportsDataBase: SportsDataBase,
        ioDispatcher: CoroutineDispatcher,
    ): GameDetailsRepository = GameDetailsRepositoryImpl(sportsApi, sportsDataBase, ioDispatcher)


    @Provides
    fun provideScoreboardRepository(
        sportsApi: SportsApi,
        sportsDataBase: SportsDataBase,
        ioDispatcher: CoroutineDispatcher,
    ): ScoreboardRepository = ScoreboardRepositoryImpl(sportsApi, sportsDataBase, ioDispatcher)

    @Provides
    fun provideArticleRepository(
        sportsApi: SportsApi,
        ioDispatcher: CoroutineDispatcher,
    ): ArticleRepository = ArticleRepositoryImpl(sportsApi, ioDispatcher)

    @Provides
    fun provideAthleteRepository(
        sportsApi: SportsApi,
        ioDispatcher: CoroutineDispatcher,
    ): AthleteRepository = AthleteRepositoryImpl(sportsApi, ioDispatcher)

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    fun provideArticleUseCase(
        articleRepository: ArticleRepository,
    ): GetArticlesUseCase = GetArticlesUseCase(articleRepository)

    @Provides
    fun provideGetBaseballSituationUseCase(
        gameDetailsRepository: GameDetailsRepository,
        ioDispatcher: CoroutineDispatcher,
    ): GetBaseballSituationUseCase =
        GetBaseballSituationUseCase(gameDetailsRepository, ioDispatcher)

    @Provides
    fun providePlayersMapUseCase(
        teamDetailsRepository: TeamDetailsRepository,
        ioDispatcher: CoroutineDispatcher,
    ): PlayersMapUseCase = PlayersMapUseCase(teamDetailsRepository, ioDispatcher)

    @Provides
    fun provideGetScoresUseCase(
        scoreboardRepository: ScoreboardRepository,
        ioDispatcher: CoroutineDispatcher,
    ): GetScoresUseCase = GetScoresUseCase(scoreboardRepository, ioDispatcher)

    @Provides
    fun provideNewGetScoresUseCase(
        scoreboardRepository: ScoreboardRepository,
        ioDispatcher: CoroutineDispatcher,
    ): AbstractScoresUseCase = AbstractScoresUseCase(scoreboardRepository, ioDispatcher)

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                Timber.d("SAL_GUT ENDPOINT - ${request.url}")
                chain.proceed(request)
            }
            .build()

    @Singleton
    @Provides
    fun provideEspnApi(okHttpClient: OkHttpClient): SportsApi {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(SportsApi::class.java)
    }


    //Firebase things
    @Provides
    fun auth(): FirebaseAuth = Firebase.auth

    @Provides
    fun firestore(): FirebaseFirestore = Firebase.firestore

    //Location

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
        application = application
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


//}























