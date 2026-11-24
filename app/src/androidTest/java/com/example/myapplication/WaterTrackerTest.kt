package com.example.myapplication

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class WaterTrackerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // 1) Кнопка добавления жидкости есть и работает
    @Test
    fun addButton_addsWater() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("0 мл").assertExists()
        composeTestRule.onNodeWithText("+250 мл").assertExists().performClick()
        composeTestRule.onNodeWithText("250 мл").assertExists()
    }

    // 2) Кнопка "Завершить день" есть и работает
    @Test
    fun finishDayButton_resetsWater() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("250 мл").assertExists()
        composeTestRule.onNodeWithText("Завершить день").assertExists().performClick()
        composeTestRule.onNodeWithText("0 мл").assertExists()
    }

    // 3) Текст с количеством дней подряд
    @Test
    fun streakText_isCorrect() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("Дней подряд (1500 мл и больше): 0").assertExists()

        repeat(6) { composeTestRule.onNodeWithText("+250 мл").performClick() }
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд (1500 мл и больше): 1").assertExists()

        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд (1500 мл и больше): 0").assertExists()
    }
}