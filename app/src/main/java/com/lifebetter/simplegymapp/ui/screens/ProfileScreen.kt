package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.lifebetter.simplegymapp.R
import com.lifebetter.simplegymapp.model.statistics.Statistics
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
fun ProfileScreen(onClickExercises: () -> Unit, onClickMeasures: () -> Unit) {

    val profileViewModel: ProfileViewModel = hiltViewModel()
    val profileState by profileViewModel.profileState.collectAsState()

    Scaffold(topBar = { MyTopWithIconsBar(title = "lany033") }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(14.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Header(profileState.workoutCount)
            Chart()
            Dashboard(onClickExercises = onClickExercises, onClickMeasures = onClickMeasures)
        }
    }
}

@Composable
fun Header(workoutCount: Int) {
    Row {
        CommonCirclePhoto(R.drawable.perfilphoto, 70)
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            CommonTextTitle(
                text = "Melanie Mantilla",
                modifier = Modifier.padding(1.dp)
            )
            Box {
                Column {
                    CommonLittleText(text = "Workouts")
                    Text(text = workoutCount.toString())
                }
            }
        }
    }
}

@Composable
fun Chart() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp), colors = CardDefaults.cardColors(containerColor = Color.Transparent)) {

        val data: List<Statistics> = listOf(
            Statistics("lunes", 2f),
            Statistics("Martes", 1f),
            Statistics("Miercoles", 3f),
            Statistics("Jueves", 2f),
            Statistics("Viernes", 4f)
        )

        var barras = ArrayList<BarChartData.Bar>()

        data.mapIndexed { index, statistics ->
            barras.add(
                BarChartData.Bar(
                    label = statistics.dia,
                    value = statistics.hours,
                    color = Color.Blue
                )
            )
        }
        Text(text = "2 horas esta semana")
        BarChart(
            barChartData = BarChartData(bars = barras),
            modifier = Modifier
                .padding(vertical = 15.dp)
                .height(200.dp)
                .fillMaxWidth(),
            labelDrawer = SimpleValueDrawer(
                drawLocation = SimpleValueDrawer.DrawLocation.XAxis
            )
        )
    }
}

@Composable
fun Dashboard(onClickExercises: () -> Unit, onClickMeasures: () -> Unit) {
    Column {
        Text(text = "Dashboard")
        Row {
            CommonButtonProfileDashboard(
                "Statistics",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.ChartBar,
                onclick = { })
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
                onclick = { onClickMeasures() })
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

