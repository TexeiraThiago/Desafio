package com.thiago.desafiopipefy.feature_crud.presentation.Treino

import com.thiago.desafiopipefy.feature_crud.domain.model.Exercicio
import com.thiago.desafiopipefy.feature_crud.domain.model.TreinoComExercicio

data class States(
    val treino: List<TreinoComExercicio> = emptyList(),
    val descricao: String = "",
    val data: String = "",
    val observacao: String = "",
    val imagem: String = "",
    val isAddingTreino: Boolean = false,
    val tipoOrdem: TipoOrdem = TipoOrdem.NOME
)
