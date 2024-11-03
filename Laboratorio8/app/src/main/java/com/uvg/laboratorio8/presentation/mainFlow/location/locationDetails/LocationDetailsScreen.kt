package com.uvg.laboratorio8.presentation.mainFlow.location.locationDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.laboratorio8.data.model.Location
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun LocationDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: LocationDetailsViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect (Unit) {
        viewModel.getLocationData()
    }

    LocationDetailsScreen(
        state = state,
        onSetError = { viewModel.setError() },
        onRetry = { viewModel.getLocationData() },
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationDetailsScreen(
    state: LocationDetailsState,
    onSetError: () -> Unit,
    onRetry: () -> Unit,
    onNavigateBack: () -> Unit
){
    val location = state.data

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text("Location Details")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onSecondary,
                navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
            ),
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        when {
            state.isLoading -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onSetError() }
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text( text = "Loading" )
                }
            }

            state.hasError -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info icon",
                        Modifier.size(55.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Error getting location.",
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "Try again.",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { onRetry() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text(text = "Retry")
                    }
                }
            }

            location == null -> {
                Text(text = "Error al obtener la locación")
            }

            else -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = location.name, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(30.dp))

                    Column (
                        modifier = Modifier
                            .width(280.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "ID: ")
                            Text(text = "${location.id}")
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "Type: ")
                            Text(text = location.type)
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "Dimensions: ")
                            Text(text = location.dimension)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLocationDetailsScreen(){
    Laboratorio8Theme {
        Surface {
            LocationDetailsScreen(
                onNavigateBack = {},
                state = LocationDetailsState(
                    data = Location(1, "Earth (C-137)", "Planet", "Dimension C-137")
                ),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoadingLocationDetailsScreen(){
    Laboratorio8Theme {
        Surface {
            LocationDetailsScreen(
                onNavigateBack = {},
                state = LocationDetailsState(
                    isLoading = true
                ),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewErrorLocationDetailsScreen(){
    Laboratorio8Theme {
        Surface {
            LocationDetailsScreen(
                onNavigateBack = {},
                state = LocationDetailsState(
                    hasError = true
                ),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}