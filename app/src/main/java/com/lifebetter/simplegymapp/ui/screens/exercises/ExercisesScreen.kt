package com.lifebetter.simplegymapp.ui.screens.exercises

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
import com.lifebetter.simplegymapp.model.database.MyDatabaseFactory
import com.lifebetter.simplegymapp.model.remotedata.ExerciseRemoteMediator
import com.lifebetter.simplegymapp.model.remotedata.ExerciseService
import com.lifebetter.simplegymapp.model.remotedata.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection
import com.lifebetter.simplegymapp.ui.components.MyTopWithIconsBar

@OptIn(ExperimentalPagingApi::class)
@Composable
fun ExercisesScreen() {

    //val exerciseApi: ExerciseService

    val context = LocalContext.current

    val viewModel: ExercisesViewModel = viewModel {
        ExercisesViewModel(
            pager = Pager(
                config = PagingConfig(pageSize = 20),
                remoteMediator = ExerciseRemoteMediator(
                    ExerciseDatabase.createDatabase(context),
                    ExercisesRemoteDataSource()
                ),
                pagingSourceFactory = {
                    ExerciseDatabase.createRepoDao(context).pagingSource()
                })
        )
    }

    //val exerciseList = viewModel.exercisePager.collectAsLazyPagingItems()
    val exerciseList = viewModel.exercisePagingFlow.collectAsLazyPagingItems()

    Scaffold(topBar = { MyTopWithIconsBar(title = "Exercises") }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp),
                shape = RoundedCornerShape(4.dp),
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
                value = " ",
                onValueChange = {},
                placeholder = { Text(text = "Search exercise", fontSize = 16.sp) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search"
                    )
                })
            Row() {
                Button(
                    onClick = { /*TODO*/ }, shape = RoundedCornerShape(4.dp), modifier = Modifier
                        .weight(1f)
                        .size(40.dp)
                ) {
                    Text(text = "All equipment", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.size(15.dp))
                Button(
                    onClick = { /*TODO*/ }, shape = RoundedCornerShape(4.dp), modifier = Modifier
                        .weight(1f)
                        .size(40.dp)
                ) {
                    Text(text = "All Muscles", fontSize = 16.sp)
                }
            }
            Text(text = "Results")

            LaunchedEffect(key1 = exerciseList.loadState) {
                if (exerciseList.loadState.refresh is LoadState.Error) {
                    Toast.makeText(
                        context,
                        "Error: " + (exerciseList.loadState.refresh as LoadState.Error).error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                if (exerciseList.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(exerciseList) { exercise ->
                            if (exercise != null) {
                                Text(text = "Exercise: ${exercise.count}")
                            }
                        }
                        item {
                            if (exerciseList.loadState.append is LoadState.Loading) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }

        }
    }
}
