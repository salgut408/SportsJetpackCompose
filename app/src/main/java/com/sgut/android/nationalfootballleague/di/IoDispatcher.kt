package com.sgut.android.nationalfootballleague.di

import javax.inject.Qualifier

/**
 * Marks the [kotlinx.coroutines.CoroutineDispatcher] used for network/disk work.
 * A qualifier keeps the binding unambiguous if a Default or Main dispatcher is ever
 * added, and lets tests swap in a test dispatcher by replacing one binding.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher
