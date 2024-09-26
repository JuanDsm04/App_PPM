package com.uvg.laboratorio8.character.charactersList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.uvg.laboratorio8.data.CharacterDb
import com.uvg.laboratorio8.ui.theme.Laboratorio8Theme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uvg.laboratorio8.data.Character

@Composable
fun CharacterListRoute(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    CharacterListScreen (
        onCharacterClick = onCharacterClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterListScreen(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val characterDb = CharacterDb()
    val characters = characterDb.getAllCharacters()

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
        LazyColumn (
            modifier = Modifier.padding(horizontal = 20.dp),
        ){
            items(characters) { character ->
                CharacterItem(character, onClick = { onCharacterClick(character.id) })
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
            CharacterListScreen(onCharacterClick = {})
        }
    }
}