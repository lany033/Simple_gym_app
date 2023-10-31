package com.lifebetter.simplegymapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lifebetter.simplegymapp.ui.navigation.BottomNavGraph
import com.lifebetter.simplegymapp.ui.navigation.BottomNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MyBottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun MyBottomBar(navController: NavHostController){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Workouts,
        BottomNavItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach{ item ->
            NavigationBarItem(
                label = { Text(text = item.title, fontWeight = FontWeight.Bold)},
                icon = { Icon(imageVector = item.imageVector, contentDescription = item.title, modifier = Modifier.size(26.dp)) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}





