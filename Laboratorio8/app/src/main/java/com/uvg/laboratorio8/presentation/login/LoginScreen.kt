package com.uvg.laboratorio8.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.laboratorio8.R
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import kotlin.math.sin

@Composable
fun LoginRoute(
    onLoginClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
){
    val state by viewModel.state.collectAsState()
    var errorMessage by remember { mutableStateOf("") }

    LoginScreen(
        loading = state.loading,
        onLoginClick = {
            if (state.username.isBlank()) {
                errorMessage = "Por favor, ingrese su nombre."
            } else {
                errorMessage = ""
                viewModel.saveUsername()
                onLoginClick()
            }
        },
        username = state.username,
        onUsernameChange = {
            viewModel.onUsernameChange(it)
            errorMessage = ""
        },
        errorMessage = errorMessage,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    loading: Boolean,
    username: String,
    onLoginClick: () -> Unit,
    onUsernameChange: (String) -> Unit,
    errorMessage: String
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
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = onUsernameChange,
                placeholder = {
                    Text(text = "Nombre")
                },
                singleLine = true
            )
            if (errorMessage.isNotBlank()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.secondary),
                enabled = !loading
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
            LoginScreen(
                loading = false,
                onLoginClick = {},
                username = "",
                onUsernameChange = {},
                modifier = Modifier.fillMaxSize(),
                errorMessage = ""
            )
        }
    }
}