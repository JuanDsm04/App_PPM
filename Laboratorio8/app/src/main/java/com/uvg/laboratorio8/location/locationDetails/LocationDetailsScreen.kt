package com.uvg.laboratorio8.location.locationDetails

import com.uvg.laboratorio8.data.LocationDb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun LocationDetailsRoute(
    id: Int,
    onNavigateBack: () -> Unit
) {
    LocationDetailsScreen(
        id = id,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationDetailsScreen(
    id: Int,
    onNavigateBack: () -> Unit
){
    val locationDb = LocationDb()
    val location = locationDb.getLocationById(id)

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

@Preview
@Composable
private fun PreviewLocationDetailsScreen(){
    Laboratorio8Theme {
        Surface {
            LocationDetailsScreen(id = 1, onNavigateBack = {})
        }
    }
}