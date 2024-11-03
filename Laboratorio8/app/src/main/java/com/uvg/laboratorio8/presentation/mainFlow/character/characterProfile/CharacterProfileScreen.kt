package com.uvg.laboratorio8.presentation.mainFlow.character.characterProfile

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.laboratorio8.data.model.Character

@Composable
fun CharacterProfileRoute(
    onNavigateBack: () -> Unit,
    viewModel: CharacterProfileViewModel = viewModel()
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCharacterData()
    }

    CharacterProfileScreen(
        state = state,
        onSetError = { viewModel.setError() },
        onRetry = { viewModel.getCharacterData() },
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterProfileScreen(
    state: CharacterProfileState,
    onSetError: () -> Unit,
    onRetry: () -> Unit,
    onNavigateBack: ()-> Unit
){
    val character = state.data

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TopAppBar(
            title = {
                Text("Character Detail")
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
                        text = "Error getting character.",
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

            character == null -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Error getting character.")
                }
            }

            else -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    AsyncImage(
                        model = character.image,
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = character.name, style = MaterialTheme.typography.titleLarge)
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
                            Text(text = "Species: ")
                            Text(text = character.species)
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "Status ")
                            Text(text = character.status)
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "Gender: ")
                            Text(text = character.gender)
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun PreviewCharacterProfileScreen(){
    Laboratorio8Theme {
        Surface {
            CharacterProfileScreen(
                onNavigateBack = {},
                state = CharacterProfileState(
                    data = Character(1, "Rick Sanchez", "Alive", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/1.jpeg")
                ),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoadingCharacterProfileScreen(){
    Laboratorio8Theme {
        Surface {
            CharacterProfileScreen(
                onNavigateBack = { },
                state = CharacterProfileState(
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
private fun PreviewErrorCharacterProfileScreen(){
    Laboratorio8Theme {
        Surface {
            CharacterProfileScreen(
                onNavigateBack = { },
                state = CharacterProfileState(
                    hasError = true
                ),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}