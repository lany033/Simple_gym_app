package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifebetter.simplegymapp.R
import com.lifebetter.simplegymapp.ui.components.CommonCirclePhoto
import com.lifebetter.simplegymapp.ui.components.CommonLittleText
import com.lifebetter.simplegymapp.ui.components.CommonTextButtons
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopWithIconsBar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.ChartBar
import compose.icons.fontawesomeicons.solid.Dumbbell
import compose.icons.fontawesomeicons.solid.Ruler

@Composable
fun ProfileScreen(onClick: () -> Unit) {
    Scaffold(topBar = { MyTopWithIconsBar(title = "lany033") }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(14.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Header()
            Body()
            Dashboard(onClickExercises = onClick)
            /*
            Button(onClick = { onClick() }) {
                Text(text = "ejerciciosTest")
            }
             */
        }
    }
}

@Composable
fun Header() {
    Row {
        CommonCirclePhoto(R.drawable.perfilphoto, 70)

        Column {
            CommonTextTitle(
                text = "Melanie Mantilla",
                modifier = Modifier.padding(5.dp)
            )
            Box {
                Column {
                    CommonLittleText(text = "Workouts")
                    Text(text = "63")
                }
            }
        }
    }
}

@Composable
fun Body() {
    Column {
        Row {
            Row {
                Text(text = "5 hours")
                Text(text = "this week")
            }
            Text(text = "Last 12 weeks")
        }
        Image(
            painter = painterResource(id = R.drawable.statistics),
            contentDescription = "statis",
            modifier = Modifier.size(200.dp)
        )
        Row {
            CommonButtonProfileBody("Duration")
            CommonButtonProfileBody("Volumen")
            CommonButtonProfileBody("Reps")
        }
    }
}

@Composable
fun Dashboard(onClickExercises: () -> Unit) {
    Column {
        Text(text = "Dashboard")
        Row {
            CommonButtonProfileDashboard(
                "Statistics",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.ChartBar,
                onclick = {})
            Spacer(modifier = Modifier.padding(2.dp))
            CommonButtonProfileDashboard(
                "Exercises",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.Dumbbell,
                onclick = { onClickExercises() }
            )
        }
        Row {
            CommonButtonProfileDashboard(
                "Measures",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.Ruler,
                onclick = {})
            Spacer(modifier = Modifier.padding(2.dp))
            CommonButtonProfileDashboard(
                "Calendar",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.Calendar,
                onclick = {})
        }
    }


}

@Composable
fun CommonButtonProfileDashboard(
    textButton: String,
    modifier: Modifier,
    imageVector: ImageVector,
    onclick: () -> Unit
) {
    Button(
        onClick = { onclick() },
        shape = RoundedCornerShape(4.dp), modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "icon",
            modifier = Modifier.size(24.dp)
        )
        CommonTextButtons(text = textButton)
    }
}

@Composable
fun CommonButtonProfileBody(text: String) {
    Button(
        onClick = { /*TODO*/ },
        contentPadding = PaddingValues(start = 13.dp, end = 13.dp, top = 0.dp, bottom = 0.dp)
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

