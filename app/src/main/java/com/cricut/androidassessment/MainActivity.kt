package com.cricut.androidassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cricut.androidassessment.ui.AppDestinations
import com.cricut.androidassessment.ui.component.MainTopAppBar
import com.cricut.androidassessment.ui.screens.AssessmentScreen
import com.cricut.androidassessment.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            MainTopAppBar()
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestinations.Welcome,
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            composable<AppDestinations.Welcome> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        navController.navigate(AppDestinations.QuizStart)
                    }) {
                        Text(text = stringResource(id = R.string.welcome_to_quizlet))
                    }
                }
            }
            composable<AppDestinations.QuizStart> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        navController.navigate(AppDestinations.QuizQuestion(1))
                    }) {
                        Text(text = stringResource(id = R.string.start_quiz))
                    }
                }
            }
            composable<AppDestinations.QuizQuestion> {
                AssessmentScreen()
            }
            composable<AppDestinations.QuizEnd> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        navController.navigate(AppDestinations.QuizResult)
                    }) {
                        Text(text = stringResource(id = R.string.see_results))
                    }
                }
            }
            composable<AppDestinations.QuizResult> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        navController.navigate(AppDestinations.QuizStart)
                    }) {
                        Text(text = stringResource(id = R.string.restart))
                    }
                }
            }
        }
    }
}

@Preview(
    locale = "en-US",
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun AppLightThemePreview() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        App()
    }
}

@Preview(
    locale = "en-US",
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun AppDarkThemePreview() {
    AppTheme(darkTheme = true, dynamicColor = false) {
        App()
    }
}

@Preview(
    locale = "en-US",
    showSystemUi = true,
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Composable
fun AppDynamicColorLightThemePreview() {
    AppTheme(darkTheme = false, dynamicColor = true) {
        App()
    }
}

@Preview(
    locale = "en-US",
    showSystemUi = true,
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
@Composable
fun AppDynamicColorDarkThemePreview() {
    AppTheme(darkTheme = true, dynamicColor = true) {
        App()
    }
}
