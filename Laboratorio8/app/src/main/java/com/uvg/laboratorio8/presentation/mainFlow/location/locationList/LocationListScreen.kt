package com.uvg.laboratorio8.presentation.mainFlow.location.locationList

import com.uvg.laboratorio8.data.model.Location
import com.uvg.laboratorio8.data.local.source.LocationDb
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.laboratorio8.domain.remote.util.DataError

@Composable
fun LocationListRoute(
    onLocationClick: (Int) -> Unit,
    modifier: Modifier,
    viewModel: LocationListViewModel = viewModel(factory = LocationListViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getLocationListData()
    }

    LocationListScreen (
        state = state,
        onSetError = { viewModel.setError(DataError.GENERIC_FAILURE) },
        onRetry = { viewModel.getLocationListData() },
        onLocationClick = onLocationClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationListScreen(
    state: LocationListState,
    onSetError: () -> Unit,
    onRetry: () -> Unit,
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val locations = state.data

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TopAppBar(
            title = {
                Text("Locations")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onSecondary,
                navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
            )
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
                        text = "Error getting locations.",
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

            else -> {
                LazyColumn (
                    modifier = Modifier.padding(horizontal = 20.dp),
                ){
                    items(locations) { location ->
                        LocationItem(location, onClick = { onLocationClick(location.id) })
                    }
                }
            }
        }
    }
}


@Composable
fun LocationItem(location: Location, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(30.dp))
    Row (modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = location.name, style = MaterialTheme.typography.titleMedium)
            Text(text = location.type, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
private fun PreviewLocationListScreen(){
    Laboratorio8Theme {
        Surface {
            val locationDb = LocationDb()
            LocationListScreen(
                onLocationClick = { },
                state = LocationListState(data = locationDb.getAllLocations()),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoadingLocationListScreen(){
    Laboratorio8Theme {
        Surface {
            LocationListScreen(
                onLocationClick = { },
                state = LocationListState(isLoading = true),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewErrorLocationListScreen(){
    Laboratorio8Theme {
        Surface {
            LocationListScreen(
                onLocationClick = { },
                state = LocationListState(hasError = true),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}