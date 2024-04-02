package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.lifebetter.simplegymapp.data.formatTime
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopBarWithBackIcon
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun SaveWorkoutSessionScreen(
    onBack: () -> Unit,
    onCamera: () -> Unit,
    onHome: () -> Unit,
    lwvm: LogWorkoutViewModel = hiltViewModel()
) {
    /*
    val logWorkoutViewModel: LogWorkoutViewModel =
        hiltViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)

     */
    val timer by lwvm.timer.collectAsState()
    val uris by lwvm.uri.collectAsState()
    val logState by lwvm.logState.collectAsState()

    Scaffold(topBar = {
        MyTopBarWithBackIcon(
            title = "Save Workout",
            subtitleTwo = "Save",
            onClickArrowBack = { onBack() },
            onClickSave = {
                lwvm.saveWorkoutSession()
                onHome()
            })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp), verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CommonTextTitle(
                text = logState
                    .nameWorkout, modifier = Modifier
            )
            Row {
                Duration(modifier = Modifier.weight(1f), timer)
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Volume")
                    Text(text = logState.sumKg.toString(), fontSize = 20.sp)

                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Reps")
                    Text(text = logState.sumRep.toString(), fontSize = 20.sp)
                }
            }
            Divider()
            DateTime()
            Divider()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { onCamera() },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = null,
                            tint = Color(0XFF40A1F7)
                        )
                    }
                }
                if (uris.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Add a photo", fontSize = 18.sp)
                    }
                } else {
                    LazyRow() {
                        items(uris) { uri ->
                            Box(
                                modifier = Modifier
                                    .size(130.dp)
                                    .background(Color.Transparent),
                                contentAlignment = Alignment.Center
                            ) {
                                val painter: Painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(uri.toUri())
                                        .size(Size.ORIGINAL) // Set the target size to load the image at.
                                        .build()
                                )
                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier.clip(
                                        RoundedCornerShape(10.dp)
                                    ),
                                    contentScale = ContentScale.Crop
                                )

                            }

                            Spacer(modifier = Modifier.size(10.dp))
                        }
                    }
                }
            }
            Divider()
            Card(
                modifier = Modifier.fillMaxWidth(), shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Text(text = "Description")
                Text(text = "Write fkjfnkfkfnsf", fontSize = 20.sp)
            }
        }


    }
}

@Composable
fun DateTime() {
    Card(
        modifier = Modifier.fillMaxWidth(), shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Text(text = "When")
        Text(
            text = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            fontSize = 20.sp,
            color = Color(0XFF40A1F7)
        )
    }
}

@Composable
fun Duration(modifier: Modifier, timerValue: Long) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Text(text = "Duration")
        Text(text = timerValue.formatTime(), fontSize = 20.sp, color = Color(0XFF40A1F7))

    }
}