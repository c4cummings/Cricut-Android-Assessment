package com.cricut.androidassessment.ui

import kotlinx.serialization.Serializable

object AppDestinations {
    @Serializable
    object Welcome

    @Serializable
    object QuizStart

    @Serializable
    data class QuizQuestion(val id: Int)

    @Serializable
    object QuizEnd

    @Serializable
    object QuizResult
}

//class AppNavigationActions(navController: NavHostController) {
//    val navigateToWelcome: () -> Unit = {
//        navController.navigate(AppDestinations.Welcome) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }
//    val navigateToQuizStart: () -> Unit = {
//        navController.navigate(AppDestinations.QuizStart)
//    }
//    val navigateToQuizQuestion: () -> Unit = {
//        navController.navigate(AppDestinations.QuizQuestion) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = false
//            restoreState = true
//        }
//    }
//    val navigateToQuizEnd: () -> Unit = {
//        navController.navigate(AppDestinations.QuizEnd) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }
//    val navigateToQuizResult: () -> Unit = {
//        navController.navigate(AppDestinations.QuizResult) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }
//}
