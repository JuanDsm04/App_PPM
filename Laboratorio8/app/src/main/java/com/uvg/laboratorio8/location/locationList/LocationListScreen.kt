package com.uvg.laboratorio8.location.locationList

import com.uvg.laboratorio8.data.Location
import com.uvg.laboratorio8.data.LocationDb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun LocationListRoute(
    onLocationClick: (Int) -> Unit,
    modifier: Modifier
) {
    LocationListScreen (
        onLocationClick = onLocationClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationListScreen(
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val locationDb = LocationDb()
    val locations = locationDb.getAllLocations()

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

        LazyColumn (
            modifier = Modifier.padding(horizontal = 20.dp),
        ){
            items(locations) { location ->
                LocationItem(location, onClick = { onLocationClick(location.id) })
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
            LocationListScreen(onLocationClick = {})
        }
    }
}