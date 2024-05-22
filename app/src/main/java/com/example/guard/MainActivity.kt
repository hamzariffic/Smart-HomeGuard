package com.example.guard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guard.ui.theme.GuardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                // Set up Navigation
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignupScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("settings") { SettingsScreen(navController) }
            composable("notifications") { NotificationsScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.Settings,
        Screen.Notifications,
        Screen.Profile
    )

    var currentScreen by remember { mutableStateOf(items[0]) }

    BottomNavigation {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = { Text(text = screen.title) },
                selected = currentScreen == screen,
                onClick = {
                    currentScreen = screen
                    navController.navigate(screen.route)
                }
            )
        }
    }
}

// Screen Definitions
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", ImageVector.vectorResource(id = R.drawable.ic_home))
    object Settings : Screen("settings", "Settings", ImageVector.vectorResource(id = R.drawable.ic_settings))
    object Notifications : Screen("notifications", "Notifications", ImageVector.vectorResource(id = R.drawable.ic_notifications))
    object Profile : Screen("profile", "Profile", ImageVector.vectorResource(id = R.drawable.ic_profile))
}

// Screen Composables - Implement these
@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("home") }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("signup") }) {
            Text("Sign Up")
        }
    }
}

@Composable
fun SignupScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("home") }) {
            Text("Sign Up")
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home Dashboard")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("settings") }) {
            Text("Settings")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("notifications") }) {
            Text("Notifications")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("profile") }) {
            Text("Profile")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Your Settings Screen")
    }
}

@Composable
fun NotificationsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Notifications Screen")
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profile Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GuardTheme {
        Navigation(navController = rememberNavController())
    }
}