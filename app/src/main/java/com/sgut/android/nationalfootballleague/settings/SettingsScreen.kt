package com.sgut.android.nationalfootballleague.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sgut.android.nationalfootballleague.commoncomposables.DangerousCardEditor
import com.sgut.android.nationalfootballleague.commoncomposables.DialogCancelButton
import com.sgut.android.nationalfootballleague.commoncomposables.DialogConfirmButton
import com.sgut.android.nationalfootballleague.commoncomposables.RegularCardEditor
import com.sgut.android.nationalfootballleague.di.BasicToolBar
import com.sgut.android.nationalfootballleague.utils.card
import com.sgut.android.nationalfootballleague.utils.spacer
import com.sgut.android.nationalfootballleague.R.string as AppText
import com.sgut.android.nationalfootballleague.R.drawable as AppIcon


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    restartApp: (String) -> Unit,
    openSignUpScreen: (String) -> Unit,
    openLogInScreen: (String) -> Unit,

    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = SettingsUiState(false))


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.spacer())

        if(uiState.isAnonymousAccount) {
            RegularCardEditor(
                title = AppText.sign_in,
                content = "",
                AppIcon.ic_sign_in,
                modifier = Modifier.card()
            ) {
                viewModel.onLogInClick(openLogInScreen)
            }
            RegularCardEditor(
                AppText.create_account,
                "",
                AppIcon.ic_create_account,
                Modifier.card()
            ) {
                viewModel.onSignUpClick(openSignUpScreen)
            }
        } else {
            SignOutCard { viewModel.onSignOutClick(restartApp)}
            DeleteMyAccountCard { viewModel.onDeleteMyAccountClick(restartApp) }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignOutCard(
    signOut:() -> Unit,
) {
    var showWarningDialog by remember {mutableStateOf(false)}

    RegularCardEditor(
        AppText.sign_out,
        "",
        AppIcon.ic_exit,
        Modifier.card()
    ) {
        showWarningDialog = true
    }

    if(showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.sign_out_title)) },
            text = { Text(stringResource(AppText.sign_out_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.sign_out) {
                    signOut()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DeleteMyAccountCard(deleteMyAccount: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    DangerousCardEditor(
        AppText.delete_my_account,
        AppIcon.ic_delete_my_account,
        "",
        Modifier.card()
    ) {
        showWarningDialog = true
    }

    if (showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.delete_account_title)) },
            text = { Text(stringResource(AppText.delete_account_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.delete_my_account) {
                    deleteMyAccount()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}