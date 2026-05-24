# CLAUDE.md

This file provides guidance to Claude Code when working with code in this repository.

---

## Build & Run

This is a single-module Android project: `:app`.

The Gradle version catalog is located at:

- `gradle/libs.versions.toml`

The user's shell exports a broken `JAVA_HOME` stale sdkman path. Any Gradle invocation must override `JAVA_HOME` inline, or the launcher fails before Gradle starts.

Use this pattern:

`JAVA_HOME=/Users/sgutierr/Library/Java/JavaVirtualMachines/jbr-17.0.14/Contents/Home ./gradlew <task>`

`org.gradle.java.home` in `gradle.properties` only configures the Gradle daemon. The `gradlew` launcher reads the shell `JAVA_HOME` first.

Common tasks:

- Build debug APK:
  `JAVA_HOME=/Users/sgutierr/Library/Java/JavaVirtualMachines/jbr-17.0.14/Contents/Home ./gradlew :app:assembleDebug`

- Install on device/emulator:
  `JAVA_HOME=/Users/sgutierr/Library/Java/JavaVirtualMachines/jbr-17.0.14/Contents/Home ./gradlew :app:installDebug`

- Unit tests:
  `JAVA_HOME=/Users/sgutierr/Library/Java/JavaVirtualMachines/jbr-17.0.14/Contents/Home ./gradlew :app:testDebugUnitTest`

- Single unit test:
  `JAVA_HOME=/Users/sgutierr/Library/Java/JavaVirtualMachines/jbr-17.0.14/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.sgut.android.nationalfootballleague.SomeTest.someMethod"`

- Instrumented tests:
  `JAVA_HOME=/Users/sgutierr/Library/Java/JavaVirtualMachines/jbr-17.0.14/Contents/Home ./gradlew :app:connectedDebugAndroidTest`

- Lint:
  `JAVA_HOME=/Users/sgutierr/Library/Java/JavaVirtualMachines/jbr-17.0.14/Contents/Home ./gradlew :app:lintDebug`

Toolchain:

- AGP 8.9.3
- Kotlin 2.1.20
- JDK 17
- compileSdk 36
- targetSdk 36
- minSdk 26
- Kotlin 2.0's `kotlin.plugin.compose` owns the Compose compiler.
- There is no `composeOptions` block.
- Annotation processing is KSP only.
- Do not introduce kapt.
- Hilt and Room compilers run through KSP.

---

## Project Context

This is a playground for testing new libraries, patterns, and approaches in an Android codebase.

It is not a production app yet. The code may have rough edges, inconsistencies, and technical debt. The goal is to experiment, learn, and eventually move toward a more polished product.

I am not a beginner developer. I have been working professionally as an Android engineer for about 4 years. However, I am self-taught and did not study computer science formally, so I use this project to keep sharp, fill in knowledge gaps, and better understand the “why” behind architecture, patterns, Kotlin, and Android best practices.

The primary goals are:

- Have fun building a sports and prediction-focused app.
- Keep improving as an Android engineer.
- Practice modern Android architecture patterns.
- Deepen my understanding of Kotlin, Compose, coroutines, flows, serialization, Room, Hilt, Retrofit, and other modern Android libraries.
- Get more comfortable with business logic, domain layers, repositories, use cases, and how the layers interact.
- Learn computer-science-adjacent concepts in a practical way through real app code instead of abstract examples.
- Build engaging sports experiences around scoreboards, stats, athletes, teams, and data visualization.
- Gradually improve polish, maintainability, modularity, and testability over time.

When introducing new patterns, architecture decisions, Kotlin concepts, or software design ideas, explain the reasoning clearly without talking down to me.

Assume I understand Android development, but I may want extra explanation around:

- Why a certain architecture choice is better.
- What tradeoffs exist between two approaches.
- How a pattern maps to this specific codebase.
- How to make code more testable or maintainable.
- How domain/business logic should be separated from UI and data layers.
- How to think about caching, state management, and data modeling.

When suggesting an architecture change, explain why it fits this app.

Examples:

- Why MVVM?
- Why UDF?
- Why use cases?
- Why one `StateFlow` per screen?
- Why separate DTOs from domain models?
- Why keep prediction logic out of ViewModels and Composables?

Avoid over-engineering too early, but call out where a small architecture decision now will prevent problems later.

---

## Architecture

The app uses Clean Architecture with MVVM and UDF.

Root package:

- `com.sgut.android.nationalfootballleague`

Layer flow:

- UI Composables + ViewModel
- Domain use cases + repository interfaces
- Data repository implementations
- Retrofit `SportsApi` + Room `SportsDataBase`

Hilt wires dependencies in:

- `di/AppModule.kt`

### State Pattern

Each screen should expose a single `StateFlow<XxxUiState>` from a `@HiltViewModel`.

The UI state should usually be an `@Immutable` data class.

Composables should collect state with `collectAsStateWithLifecycle()`.

Do not fan out multiple unrelated flows from a ViewModel. Prefer folding screen data into one view state.

### Navigation

Routes live in:

- `ui/navigation/NavigationScreens.kt`

Navigation uses a sealed class with a `withArgs(vararg)` helper.

The single `NavHost` lives in:

- `Navigation.kt`

The app shell is:

- `ui/application/EspnApp.kt`
- `ui/application/EspnAppState.kt`

The app is hosted from `MainActivity` inside the `Theme { }` wrapper from:

- `uiStyleDefinitions/design/style/`

---

## ESPN API / Endpoint Context

This app uses ESPN endpoints that are not fully public or officially documented.

Because of that, API response models, field availability, and behavior may be inconsistent or change over time.

Do not assume ESPN response models are complete or stable.

Before creating or changing models, inspect real API responses when possible.

ESPN responses may differ depending on:

- Sport / league
- Whether the game is live, upcoming, postponed, delayed, or completed
- Whether the event is preseason, regular season, postseason, etc.
- Whether betting, odds, leaders, probabilities, injuries, or play-by-play data are available
- Whether the API endpoint includes full data or partial summary data

Some properties may only exist for live games.

Some properties may only exist after a game is complete.

Some sports may return fields that other sports do not.

When unsure about a field or endpoint behavior:

- Do not guess confidently.
- Inspect sample JSON.
- Add logging or temporary debug output if useful.
- Make the model nullable.
- Add TODO comments explaining what needs verification.
- Prefer safe fallback behavior over crashes.

---

## Networking: Sport-Specific Scoreboard Variants

Base URL:

- `https://site.api.espn.com/apis/`

Defined in:

- `utils/Constants.kt`

Endpoints follow this pattern:

- `site/v2/sports/{sport}/{league}/...`

The 19 supported league pairs are enumerated in:

- `Constants.LIST_OF_LEAGUE_PAIRS`

The same `/scoreboard` URL can return structurally different JSON per sport.

Examples:

- Baseball has innings.
- Tennis has sets.
- Soccer may have aggregate scores.
- Football, hockey, basketball, racing, golf, MMA, and other sports may expose different fields.

The codebase handles this with separate `SportsApi` methods that return different response types for the same path.

Examples:

- `getGeneralScoreboard` returns `NetworkScoreboardResponse`
- `getBaseballScoreboard` returns `BaseballScoreBoardNetwork`
- College basketball has its own scoreboard response type.
- Tennis has its own scoreboard response type.

Sport-specific network models live under:

- `data/remote/network_responses/abs_scores/`

Important folders include:

- `baseball/`
- `basketball/`
- `football/`
- `golf/`
- `hockey/`
- `mma/`
- `racing/`
- `soccer/`
- `tennis/`

The shared abstract scoreboard shape lives in:

- `data/remote/network_responses/abs_scores/a_common/`

Important shared files include:

- `ScoreboardData`
- `EventData`
- `CompetitionData`
- `CompetitorData`
- `ScoreboardDataSerializer.kt`

When adding a new sport or endpoint, decide whether it fits the common abstraction or needs its own response type and API method.

---

## Serialization

The app uses kotlinx.serialization for all network DTOs. Gson has been fully removed.

Retrofit is configured in:

- `AppModule.provideEspnApi`

The JSON configuration uses:

- `ignoreUnknownKeys = true`
- `coerceInputValues = true`
- `isLenient = true`

All network DTOs are Kotlin classes annotated with `@Serializable`. Do not reintroduce Gson, `@SerializedName`, or `converter-gson` — the codebase has zero references to `com.google.gson` and the build no longer depends on it.

---

## Data Modeling Rules

When creating Kotlin data models for ESPN responses:

- Prefer nullable fields unless the property is verified to always exist.
- Avoid assuming lists are non-empty.
- Avoid assuming object nesting is always present.
- Add safe parsing and fallback behavior.
- Keep models flexible enough to handle missing or partial data.
- Do not crash the app because a field is missing.
- Use real API samples when possible before finalizing models.
- Prefer DTOs that reflect the raw API response.
- Prefer mappers that convert DTOs into safer domain models for the app.

Good pattern:

- Raw ESPN DTO
- Mapper
- App/domain model
- UI state

Avoid pushing raw ESPN response objects directly into UI when the shape is unstable.

---

## Current Development Focus

The main sport to focus on right now is MLB / baseball.

Prioritize MLB because baseball is currently in season and should provide useful live, upcoming, and completed game responses.

After MLB, prioritize any sport that is currently in season so we can test against real and meaningful API responses.

When adding a sports feature, start with MLB first unless another sport is specifically requested.

---

## Live Game Behavior

Live games may return different data than scheduled or completed games.

When implementing features around live games:

- Double-check whether the required fields are available during live state.
- Handle partial data gracefully.
- Expect score, inning, situation, odds, win probability, leaders, and play-by-play data to vary by endpoint.
- Build UI states for loading, unavailable, partial, live, final, postponed, delayed, canceled, and error cases.
- Do not assume live games always have complete stats, odds, or play-by-play.
- Do not assume completed games still expose every live-game property.

---

## Caching Strategy

A caching strategy is needed because ESPN endpoint data can be frequent, inconsistent, or expensive to repeatedly fetch.

Use different caching behavior depending on game state.

Suggested starting rules:

- Upcoming games: cache longer because data changes less frequently.
- Live games: cache for a very short time or refresh frequently.
- Completed games: cache longer because results are mostly stable.
- Historical data: cache very long or permanently unless manually refreshed.
- Failed requests: avoid aggressive retry loops.

Caching should protect the app from unnecessary network calls while still keeping live scores reasonably fresh.

Consider repository-level caching with clear rules based on:

- Sport
- League
- Game ID
- Endpoint type
- Game status
- Last successful fetch time
- Whether cached data is complete or partial

Do not build caching directly into Composables.

Preferred flow:

- Repository decides network vs cache.
- Use case exposes result.
- ViewModel maps result into UI state.
- Composable renders UI state.

---

## Data Sources & Room

Room database:

- `data/db/SportsDataBase.kt`

Database name:

- `sports_db`

DAOs live in:

- `data/db/article/`
- `data/db/league/`
- `data/db/sport/`
- `data/db/team/`

The database uses `fallbackToDestructiveMigration()`. This means schema changes wipe local data.

Repository implementations live in:

- `data/repository/`

Repository implementations usually take:

- `SportsApi`
- `SportsDataBase`
- `ioDispatcher: CoroutineDispatcher`

Repository implementations are responsible for network-then-cache behavior.

Use cases live in:

- `domain/use_cases/`

Most use cases are currently thin pass-throughs that inject `ioDispatcher`.

---

## Prediction Center / Betting-Style Analytics

If possible, create an internal Prediction Center or betting-style analytics section.

This should not depend only on ESPN-provided betting data.

The prediction logic should be pure Kotlin business logic, separate from UI and network code.

Goals:

- Build our own calculations.
- Create sport-specific algorithms.
- Support different formulas for MLB, NBA, NFL, NHL, and other sports.
- Keep prediction logic testable with unit tests.
- Avoid mixing prediction logic directly into ViewModels or Composables.
- Make the logic easy to tune over time.

Prediction logic should live in the domain layer when possible.

Possible package structure:

- `domain/betting/BettingCenter.kt`
- `domain/betting/BettingPrediction.kt`
- `domain/betting/BettingSignal.kt`
- `domain/betting/SportAlgorithm.kt`
- `domain/betting/mlb/MlbPredictionAlgorithm.kt`
- `domain/betting/mlb/MlbFeatureExtractor.kt`
- `domain/betting/mlb/MlbPredictionInput.kt`
- `domain/betting/nba/NbaPredictionAlgorithm.kt`
- `domain/betting/nba/NbaFeatureExtractor.kt`

Good pattern:

- `MlbPredictionAlgorithm` should expose a pure `predict(input: MlbPredictionInput): BettingPrediction` function.
- The algorithm should depend on domain input models.
- The algorithm should not depend on Android framework classes.
- The algorithm should not depend directly on a ViewModel.
- The algorithm should not depend directly on raw Retrofit DTOs.

The Prediction Center should not require Android framework classes.

The app can use ESPN data as input, but the algorithm itself should depend on domain models, not raw network DTOs.

---

## Business Logic Rules

Business logic should be pure Kotlin whenever possible.

Prefer functions and classes that take input data and return calculated results without depending on Android framework classes.

Good business logic is:

- Testable
- Deterministic when possible
- Independent from Compose
- Independent from Android framework classes
- Independent from Retrofit DTOs
- Easy to reason about
- Easy to reuse across screens

ViewModels can coordinate business logic, but they should not contain complex formulas directly.

Composables should not contain business logic.

---

## Testing Guidance

Add tests for pure Kotlin prediction and business logic.

For ESPN response handling, use sample JSON fixtures when possible.

Useful test cases:

- Upcoming game
- Live game
- Final game
- Postponed game
- Delayed game
- Missing odds
- Missing team stats
- Empty competitors list
- Missing nested objects
- Sport-specific missing fields
- MLB live inning state
- Network error
- Cached response fallback
- Partial response fallback

Prediction tests should verify calculations without needing Android, Retrofit, Room, or Compose.

---

## DI Structure

All bindings are currently in:

- `di/AppModule.kt`

App-scoped dependencies use `@InstallIn(SingletonComponent::class)`.

This includes:

- Database
- OkHttp
- Retrofit
- Location dependencies

Nested `ServiceModule` uses `@InstallIn(ViewModelComponent::class)`.

This is used for Firebase-backed services:

- `AccountService`
- `LogService`
- `StorageService`

Application class:

- `NflBaseApp`

It is declared in `di/`, registered in the manifest, and annotated with `@HiltAndroidApp`.

---

## UI Conventions

There are two parallel component folders:

- `ui/commoncomps/`
- `ui/newComponents/`

`ui/commoncomps/` is older and includes files like:

- `Bars.kt`
- `Button.kt`
- scaffolds
- `Dimens.kt`

`ui/newComponents/` is newer.

Prefer extending the newer component set.

Do not reflexively migrate older components unless specifically asked.

Design tokens, styles, and extensions live under:

- `uiStyleDefinitions/design/`

They are applied through the `Theme { }` wrapper in `MainActivity`.

This is separate from the older:

- `ui/theme/NationalFootballLeagueTheme`

Images use Coil 3.

Coil group:

- `io.coil-kt.coil3`

Do not use the old Coil v2 group:

- `io.coil-kt`

Media3 / ExoPlayer is used for the video screen.

---

## Claude Behavior

Assume I am an experienced Android engineer, but also someone who is self-taught and wants to keep improving fundamentals, architecture, and modern Android practices.

Do not over-explain basic Android concepts unless they are directly relevant.

Do explain the reasoning behind important architecture, Kotlin, Compose, coroutine, Flow, serialization, caching, and domain-layer decisions.

When suggesting a pattern, explain why it fits this app and what tradeoffs it has.

Do not confidently invent ESPN API behavior.

Before creating models or assuming API fields exist, check actual ESPN response examples when available.

Do not treat undocumented ESPN endpoints as stable contracts.

When implementing features, favor:

- Defensive Kotlin models
- Nullable fields
- Safe mappers
- Clear UI states
- Repository-level caching
- Pure Kotlin business logic
- Testable domain algorithms
- Modern Android libraries and practices

Avoid over-engineering too early, but call out where a small architecture decision now will prevent problems later.

---

## Memory Note

The auto-memory snapshot lists older SDK versions and a `common_scoreboards` branch. Those are stale.

Treat these as the source of truth:

- `app/build.gradle`
- `git branch`
- actual repository files