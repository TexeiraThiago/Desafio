package com.thiago.desafiopipefy.feature_crud.presentation.Treino.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thiago.desafiopipefy.feature_crud.presentation.Treino.Evento
import com.thiago.desafiopipefy.feature_crud.presentation.Treino.States
import com.thiago.desafiopipefy.feature_crud.presentation.Treino.TipoOrdem

@Composable
fun Screen(
    state: States,
    onEvent: (Evento) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(Evento.showDialog)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Treino Com Exercicio"
                )
            }
        },
    ) { _ ->
        if(state.isAddingTreino) {
            Dialog(
                onEvent = onEvent,
                state = state
                )
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TipoOrdem.values().forEach { tipoOrdem ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically ,
                            modifier = Modifier.clickable {
                                onEvent(Evento.Organizar(tipoOrdem))
                            }
                        ) {
                           RadioButton(
                               selected = state.tipoOrdem == tipoOrdem,
                               onClick = {
                                   onEvent(Evento.Organizar(tipoOrdem))
                               }
                           )
                            Text(text = tipoOrdem.name)
                        }
                    }
                }
            }
            items(state.treino) { treino ->
                Column( modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    AsyncImage(
                        treino.exercicio.last().imagem,
                        contentDescription = "product image",
                        Modifier
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .height(100.dp),
                        placeholder = ColorPainter(Color.Gray),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "${treino.treino.descricao}")
                    Row(modifier = Modifier.weight(1f)) {
                        Text(text = "${treino.treino.data.toString()}")
                        IconButton(onClick = {
                            onEvent(Evento.Delete(treino.treino))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"
                            )
                        }
                    }

                }

            }
        }
    }
}