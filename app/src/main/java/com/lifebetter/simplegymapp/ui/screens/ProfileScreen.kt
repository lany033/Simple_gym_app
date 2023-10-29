package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.ChartBar
import compose.icons.fontawesomeicons.solid.Dumbbell
import compose.icons.fontawesomeicons.solid.Ruler

@Composable
fun ProfileScreen(){
    Column(modifier = Modifier.padding(14.dp)) {
        Header()
        Body()
        Dashboard()
    }
}

@Composable
fun Header() {
    Row {
        CommonCirclePhoto(R.drawable.perfilphoto, 70)

        Column {
            CommonTextTitle(text = "Melanie Mantilla", modifier = Modifier.padding(5.dp))
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
        Image(painter = painterResource(id = R.drawable.statistics), contentDescription = "statis")
        Row {
            CommonButtonProfileBody("Duration")
            CommonButtonProfileBody("Volumen")
            CommonButtonProfileBody("Reps")
        }
    }
}

@Composable
fun Dashboard() {
    Column {
        Text(text = "Dashboard")
        Row {
            CommonButtonProfileDashboard("Statistics",Modifier.weight(1f), imageVector = FontAwesomeIcons.Solid.ChartBar)
            Spacer(modifier = Modifier.padding(2.dp))
            CommonButtonProfileDashboard("Exercises",Modifier.weight(1f), imageVector  = FontAwesomeIcons.Solid.Dumbbell)
        }
        Row {
            CommonButtonProfileDashboard("Measures",Modifier.weight(1f), imageVector = FontAwesomeIcons.Solid.Ruler)
            Spacer(modifier = Modifier.padding(2.dp))
            CommonButtonProfileDashboard("Calendar",Modifier.weight(1f), imageVector = FontAwesomeIcons.Solid.Calendar)
        }
    }


}

@Composable
fun CommonButtonProfileDashboard(textButton: String, modifier: Modifier, imageVector: ImageVector) {
    Button(onClick = { /*TODO*/ },shape = RoundedCornerShape(4.dp), modifier = modifier) {
        Icon(imageVector = imageVector, contentDescription = "icon", modifier= Modifier.size(24.dp))
        CommonTextButtons(text = textButton)
    }
}

@Composable
fun CommonButtonProfileBody(text: String) {
    Button(onClick = { /*TODO*/ }, contentPadding = PaddingValues(start = 13.dp, end = 13.dp, top = 0.dp, bottom = 0.dp)) {
        Text(text = text, fontSize = 16.sp)
    }
}

