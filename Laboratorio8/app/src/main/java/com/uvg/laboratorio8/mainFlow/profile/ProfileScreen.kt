package com.uvg.laboratorio8.mainFlow.profile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.laboratorio8.R
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun ProfileRoute(
    onLogOutClick: () -> Unit,
    modifier: Modifier
) {
    ProfileScreen(
        onLogOutClick = onLogOutClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreen(
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text("Profile")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onSecondary,
                navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
            )
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Column (
                modifier = Modifier
                    .width(250.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Nombre: ")
                    Text(text = "Juan Solís")
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Carné: ")
                    Text(text = "23720")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                modifier = Modifier,
                onClick = { onLogOutClick() },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text(text = "Cerrar Sesión")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProfileScreen(){
    Laboratorio8Theme {
        Surface {
            ProfileScreen(onLogOutClick = {})
        }
    }
}