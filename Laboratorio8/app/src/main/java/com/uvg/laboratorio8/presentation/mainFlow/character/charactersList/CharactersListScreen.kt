package com.uvg.laboratorio8.presentation.mainFlow.character.charactersList

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.laboratorio8.data.local.source.CharacterDb
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.uvg.laboratorio8.data.model.Character
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CharacterListRoute(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharactersListViewModel = viewModel(factory = CharactersListViewModel.Factory)
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCharacterListData()
    }

    CharacterListScreen (
        state = state,
        onSetError = { viewModel.setError() },
        onRetry = { viewModel.getCharacterListData() },
        onCharacterClick = onCharacterClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterListScreen(
    state: CharactersListState,
    onSetError: () -> Unit,
    onRetry: () -> Unit,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val characters = state.data

    Column (modifier){
        TopAppBar(
            title = {
                Text("Characters")
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
                        text = "Error getting characters.",
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
                    items(characters) { character ->
                        CharacterItem(character, onClick = { onCharacterClick(character.id) })
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(30.dp))
    Row (modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = character.image,
            contentDescription = "Image",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = character.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${character.species} - ${character.status}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
private fun PreviewCharacterListScreen(){
    Laboratorio8Theme {
        Surface {
            val characterDb = CharacterDb
            CharacterListScreen(
                onCharacterClick = { },
                state = CharactersListState(data = characterDb.getAllCharacters()),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoadingCharacterListScreen(){
    Laboratorio8Theme {
        Surface {
            CharacterListScreen(
                onCharacterClick = { },
                state = CharactersListState(isLoading = true),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewErrorCharacterListScreen(){
    Laboratorio8Theme {
        Surface {
            CharacterListScreen(
                onCharacterClick = { },
                state = CharactersListState(hasError = true),
                onSetError = { },
                onRetry = { }
            )
        }
    }
}