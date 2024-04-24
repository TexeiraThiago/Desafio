package com.thiago.desafiopipefy.feature_crud.presentation.Treino.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thiago.desafiopipefy.feature_crud.presentation.Treino.Evento
import com.thiago.desafiopipefy.feature_crud.presentation.Treino.States

@Composable
fun Dialog(
    modifier: Modifier = Modifier,
    onEvent: (Evento) -> Unit,
    state: States
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(Evento.HideDialog)
        },
        title = { Text(text = "Add Treino") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.descricao,
                    onValueChange = {
                        onEvent(Evento.setDescricao(it))
                    },
                    placeholder = {
                        Text(text = "Descrição")
                    }
                )
                TextField(
                    value = state.data,
                    onValueChange = {
                        onEvent(Evento.setData(it))
                    },
                    placeholder = {
                        Text(text = "Data")
                    }
                )
                TextField(
                    value = state.observacao,
                    onValueChange = {
                        onEvent(Evento.setObservacao(it))
                    },
                    placeholder = {
                        Text(text = "Observacao")
                    }
                )
                TextField(
                    value = state.imagem,
                    onValueChange = {
                        onEvent(Evento.setImagem(it))
                    },
                    placeholder = {
                        Text(text = "Imagem URL")
                    }
                )
            }
        },
        confirmButton ={
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = {
                        onEvent(Evento.Save)
                    }
                ) {
                    Text(text = "Salva")
                }
            }
        }
    )
}