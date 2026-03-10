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
import com.google.gson.*
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.db.article.ArticleDao
import com.sgut.android.nationalfootballleague.data.db.team.TeamsDao
import com.sgut.android.nationalfootballleague.data.help.GenNetworkFlow
import com.sgut.android.nationalfootballleague.data.location.DefaultLocationTrackerImpl
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.*
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
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.ATP
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.BASE_URL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.CHAMPIONS
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.CLUB_FRIENDLIES
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.EPL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.FIFA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.FRA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.LA_LIGA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.MLB
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.MLS
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NBA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_BASEBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_BASKETBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_FOOTBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_HOCKEY
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_LACROSSE
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NFL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NHL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.UEFA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.UFC
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.WBC
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.WNBA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.XFL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.reflect.Type
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
    fun provideTeamsListRepository(
        sportsApi: SportsApi,
        sportsDataBase: SportsDataBase,
        ioDispatcher: CoroutineDispatcher,
    ): TeamsListsRepository = TeamsListRepositoryImpl(sportsApi, sportsDataBase, ioDispatcher)


    @Provides
    fun provideStandingsRepository(
        sportsApi: SportsApi,
        sportsDataBase: SportsDataBase,
        ioDispatcher: CoroutineDispatcher,
    ): StandingsRepository = StandingsRepositoryImpl(sportsApi, sportsDataBase, ioDispatcher)


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
        sportsDataBase: SportsDataBase,
        ioDispatcher: CoroutineDispatcher,
    ): ArticleRepository = ArticleRepositoryImpl(sportsApi, sportsDataBase, ioDispatcher)

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    fun provideArticleUseCase(
        articleRepository: ArticleRepository,
    ): GetArticlesUseCase = GetArticlesUseCase(articleRepository)

    @Provides
    fun provideNewArticleRepository(
         articleDao: ArticleDao,
         sportsApi: SportsApi,
        genericNetworkFlow: GenNetworkFlow,
    ): NewArticleRepository = NewArticleRepository(articleDao, sportsApi, genericNetworkFlow)

    @Provides
    fun provideNewGetArticlesUseCase(
        articleRepository: NewArticleRepository,
        ioDispatcher: CoroutineDispatcher,
        articleDao: ArticleDao
    ): NewGetArticlesUseCase = NewGetArticlesUseCase(articleRepository,
//        ioDispatcher,
//        articleDao
    )

    @Provides
    fun provideGenericNetworkFlow(@ApplicationContext context: Context): GenNetworkFlow {
        return GenNetworkFlow(context)
    }

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
    fun provideEspnApi(okHttpClient: OkHttpClient): SportsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapter(ScoreboardData::class.java, InterfaceAdapter())
                    .create()
            ))
            .build()
            .create(SportsApi::class.java)


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


class InterfaceAdapter : JsonDeserializer<ScoreboardData> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): ScoreboardData {
        val jsonObject = json.asJsonObject
        val leagues = jsonObject.get("leagues").asJsonArray.first()
        val slug = leagues.asJsonObject.get("slug").asString
        Timber.d("SAL_GUT INTERFACE ADAPTER SLUG - $slug")
        return when (slug) { // TODO rest of leagues or figure out sport
            NFL ->             context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            NBA ->             context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            NHL ->             context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            MLB ->             context.deserialize(jsonObject, BaseballScoreboard::class.java)
            NCAA_BASKETBALL -> context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            NCAA_FOOTBALL ->   context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            WNBA ->            context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            ATP ->             context.deserialize(jsonObject, TennisScoreboard::class.java)
            UFC ->             context.deserialize(jsonObject, MmaScoreboard::class.java)
            MLS ->             context.deserialize(jsonObject, SoccerScoreboard::class.java)
            FIFA ->            context.deserialize(jsonObject, SoccerScoreboard::class.java)
            XFL ->             context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            WBC ->             context.deserialize(jsonObject, BaseballScoreboard::class.java)
            CHAMPIONS ->       context.deserialize(jsonObject, SoccerScoreboard::class.java)
            NCAA_BASEBALL ->   context.deserialize(jsonObject, BaseballScoreboard::class.java)
            NCAA_HOCKEY ->     context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            NCAA_LACROSSE ->   context.deserialize(jsonObject, DefaultScoreboardData::class.java)
            LA_LIGA ->         context.deserialize(jsonObject, SoccerScoreboard::class.java)
            EPL ->             context.deserialize(jsonObject, SoccerScoreboard::class.java)
            FRA ->             context.deserialize(jsonObject, SoccerScoreboard::class.java)
            UEFA ->            context.deserialize(jsonObject, SoccerScoreboard::class.java)
            CLUB_FRIENDLIES -> context.deserialize(jsonObject, SoccerScoreboard::class.java)


            else -> throw IllegalArgumentException("SAL_GUT APP MOD INTERFACE ADAPTER Unknown type: $slug")
        }
    }
}


//class InterfaceAdapter<T> : JsonDeserializer<T> {
//    override fun deserialize(
//        elem: JsonElement,
//        interfaceType: Type,
//        context: JsonDeserializationContext
//    ): T {
//        Log.e("JSON ELEMENT ", elem.toString())
//        val wrapper = elem as JsonObject
//        Log.e("JSON SHIT ", elem.toString())
//
//        val typeName = get(wrapper, "leagues") // type
//        val data = get(wrapper, "leagues") // data
//        val actualType = typeForName(typeName)
//        return context.deserialize(data, actualType)
//    }
//
//    private fun typeForName(typeElem: JsonElement): Type {
//        return try {
//            Class.forName(typeElem.toString())
//        } catch (e: ClassNotFoundException) {
//            throw JsonParseException(e)
//        }
//    }
//
//    private fun get(wrapper: JsonObject, memberName: String): JsonElement {
//        val elem = wrapper.get(memberName)
//        if (elem == null) {
//            throw JsonParseException("no '$memberName' member found in what was expected to be an interface wrapper")
//        }
//        return elem
//    }
//}























