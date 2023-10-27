package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifebetter.simplegymapp.ui.components.CommonAccordion
import com.lifebetter.simplegymapp.ui.components.CommonButtonHome
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.Clipboard
import compose.icons.feathericons.Search
import compose.icons.feathericons.User
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Dumbbell

@Composable
fun HomeScreen() {
    Scaffold(topBar = { MyTopAppBar("Workouts") }, bottomBar = { MyBottomBar() }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(14.dp)
        ) {
            //val availableworkouts = MutableState()
            CommonTextTitle("Quick Start", Modifier.padding(top = 5.dp, bottom = 5.dp))
            CommonButtonHome(
                text = "Start Empty Workout",
                modifier = Modifier.fillMaxWidth(),
                imageVector = Icons.Filled.Add
            )
            CommonTextTitle(text = "Routines", Modifier.padding(top = 5.dp, bottom = 5.dp))
            Row {
                CommonButtonHome("New Routine", Modifier.weight(1f), FeatherIcons.Clipboard)
                Spacer(modifier = Modifier.size(7.dp))
                CommonButtonHome(
                    text = "Explore",
                    modifier = Modifier.weight(1f),
                    imageVector = FeatherIcons.Search
                )
            }
            CommonAccordion()
        }
    }
}

@Preview
@Composable
fun MyBottomBar() {

    var index by remember {
        mutableStateOf(0)
    }

    NavigationBar {
        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0 },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Home") })
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Dumbbell,
                    contentDescription = "Home",
                    modifier = Modifier.size(26.dp)
                )
            }, label = { Text(text = "Workouts") })
        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = { Icon(imageVector = FeatherIcons.User, contentDescription = "User") },
            label = { Text(text = "Profile") })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(title: String) {
    Column {
        CenterAlignedTopAppBar(title = {
            Text(
                text = title,
                fontSize = 16.sp,
                textAlign = TextAlign.Center)
        }, modifier = Modifier.padding(bottom = 1.dp))
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }

}
