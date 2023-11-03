package com.compose.jetbooks

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.jetbooks.ui.theme.JetBooksTheme
import com.compose.jetbooks.ui.theme.navigation.Screen
import com.compose.jetbooks.ui.theme.screen.Home.HomeScreen
import com.compose.jetbooks.ui.theme.screen.about.AboutScreen
import com.compose.jetbooks.ui.theme.screen.detail.DetailScreen

@Composable
fun JetBooksApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(topBar = {
        if (currentRoute == Screen.Home.route) {
            TopBar(navController = navController, modifier = modifier.padding(start = 8.dp))
        }
    }, content = { innerPadding ->
        NavHost(navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { bookId ->
                    navController.navigate(Screen.Detail.createRoute(bookId))
                })
            }
            composable(Screen.About.route) {
                AboutScreen(navigateBack = { navController.navigateUp() })
            }
            composable(route = Screen.Detail.route,
                arguments = listOf(navArgument("bookId") { type = NavType.LongType })) {
                val id = it.arguments?.getLong("bookId") ?: -1L
                DetailScreen(bookId = id, navigateBack = { navController.navigateUp() })
            }
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavHostController,
    modifier: Modifier,
) {
    CenterAlignedTopAppBar(title = {
        Text(
            stringResource(R.string.app_name),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h6,
        )
    }, actions = {
        IconButton(onClick = {
            navController.navigate(Screen.About.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }) {
            Icon(imageVector = Icons.Filled.Person,
                contentDescription = stringResource(R.string.about_page),
                modifier = modifier.size(32.dp))
        }
    })
}

@Preview(showBackground = true)
@Composable
fun JetSportBooksAppPreview() {
    JetBooksTheme() {
        JetBooksApp()
    }
}