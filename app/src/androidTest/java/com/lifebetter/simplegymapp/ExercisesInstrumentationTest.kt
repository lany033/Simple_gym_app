package com.lifebetter.simplegymapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.lifebetter.simplegymapp.ui.screens.workout.SaveAlertDialog
import org.junit.Rule
import org.junit.Test

class ExercisesInstrumentationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenDialogGetATrue_thenShowDialog(){
        composeTestRule.setContent {
            SaveAlertDialog(
                show = true,
                onDismissRequest = { },
                onConfirmation = { },
                dialogTitle = "test"
            )
        }

        composeTestRule.onNodeWithTag("saveAlertDialogTest").assertIsDisplayed()
    }

    @Test
    fun whenDialogGetAFalse_thenShowDialog(){
        composeTestRule.setContent {
            SaveAlertDialog(
                show = false,
                onDismissRequest = { },
                onConfirmation = { },
                dialogTitle = "test"
            )
        }

        composeTestRule.onNodeWithTag("saveAlertDialogTest").assertDoesNotExist()
    }

}