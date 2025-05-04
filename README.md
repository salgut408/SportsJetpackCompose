# Sports App

A work-in-progress Sports App 
This project is a personal sandbox of sorts, experimenting with the latest libraries and tools. These include:
-100% Kotlin
-UI wwritten in Jetpack Compose
-Dependacy Injections with Hilt
-AGSL rendering
-Compose navigation For navigation in the app
-ViewModel The ui layer uses the viewmodel pattern
-ExoPlayer For playing videos
-Coroutines For handling async work
-Retrofit For networking tasks

100% Jetpack compose && MVVM Architecture for sports.
UDF & reactice programing with Kotlin Flow API
App State & ScreeUIStates

Each UI 'screen' has its own ViewModel, which exposes a single StateFlow containing the entire view state. Each ViewModel is responsible for subscribing to any data streams required for the view, as well as exposing functions which allow the UI to send events.


This pattern is used across the different screens:
The ViewModel is implemented as ScreenViewModel, which exposes a StateFlow<ScreenViewState> for the UI to observe.
ScreenViewState contains the complete view state for the screen as an @Immutable data class.
The UI uses ScreenViewModel, and observes it's ScreenViewState as Compose State, using collectAsStateWithLifecycle():
  
The Repository class is responsible for handling the data fetching of all information:
