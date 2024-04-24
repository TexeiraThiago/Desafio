package com.thiago.desafiopipefy.feature_crud.presentation.Treino

import com.thiago.desafiopipefy.feature_crud.domain.model.Exercicio
import com.thiago.desafiopipefy.feature_crud.domain.model.Treino

sealed interface Evento {
    object Save: Evento
    data class setDescricao(val descricao: String): Evento
    data class setData(val data: String): Evento
    data class setObservacao(val observacao: String): Evento
    object showDialog: Evento
    object HideDialog: Evento
    data class Organizar(val tipoOrdem: TipoOrdem): Evento
    data class Delete(val treino: Treino): Evento
    data class setImagem(val imagem: String): Evento
}