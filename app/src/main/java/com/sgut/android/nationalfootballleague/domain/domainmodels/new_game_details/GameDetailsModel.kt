package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

import com.sgut.android.nationalfootballleague.GameDetailsBroadcasts
import com.sgut.android.nationalfootballleague.GameDetailsStandings


//    TODO - Make Domain Objcts

data class GameDetailsModel(
    val rosters: List<RostersModel> = listOf(),
    val baseballSituation: SituationModel? = SituationModel(), // needs mod
    val notes: List<String> = listOf(),
    val boxscore: BoxScoreModel? = BoxScoreModel(),
    val format: GameDetailsFormatModel? = GameDetailsFormatModel(),
    val gameInfo: GameInfoModel = GameInfoModel(),
    val lastFiveGames: List<LastFiveGamesModel> = listOf(),
    val leaders: List<GameDetailsLeadersModel> = listOf(),
    val injuries: List<GameDetailsInjuriesListModel> = listOf(),
    val broadcasts: List<GameDetailsBroadcasts> = listOf(), // needs mod ?  ? ?
    val predictor: PredictorModel? = PredictorModel(),
    val pickcenter: List<PickcenterModel> = listOf(),
    val againstTheSpread: List<AgainstTheSpreadModel> = listOf(),
    val odds: List<OddsModel> = listOf(),
    val header: HeaderModel? = HeaderModel(),
    val news: NewsModel? = NewsModel(),
    val singleGameArticle: GameDetailsArticleModel? = GameDetailsArticleModel(),
    val ticketsInfo: TicketsInfoModel? = TicketsInfoModel(), //
    val standings: GameDetailsStandings? = GameDetailsStandings(), // needs mod ? ?
    val drives: DrivesModel? = DrivesModel(),
    val winprobability: List<WinprobabilityModel> = listOf(),
    val scoringPlays: List<ScoringPlayModel> = listOf(),
    val videos: List<VideoModel> = listOf(),
    val plays: List<BasketballPlayModel> = listOf(), // needs mod
    // ------- Added for prediction/analysis (safe across sports — nullable/empty defaults) -------
    val seasonseries: List<SeasonSeriesModel> = listOf(),
    val baseballPlays: List<BaseballPlayModel> = listOf(),
    val meta: GameMetaModel? = null,
    val wallclockAvailable: Boolean = false,
    val atBats: List<String> = listOf(), // play IDs (the API returns $refs)
) {


}
