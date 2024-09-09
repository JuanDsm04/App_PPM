package com.uvg.laboratorio8.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.laboratorio8.R
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme

@Composable
fun LoginRoute(
    onLoginClick: () -> Unit
){
    LoginScreen(
        onLoginClick = onLoginClick
    )
}

@Composable
private fun LoginScreen(
    onLoginClick: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.weight(1f))
        Column (){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "RickAndMorty LOGO",
                modifier = Modifier
                .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "Entrar",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Juan Solís - 23720")
    }
}

@Preview
@Composable
private fun PreviewLoginScreen(){
    Laboratorio8Theme {
        Surface {
            LoginScreen(onLoginClick = {})
        }
    }
}