package com.thiago.desafiopipefy.feature_crud.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class TreinoComExercicio(
    @Embedded val treino: Treino,
    @Relation(
        parentColumn = "nome",
        entityColumn = "nome"
    )
    val exercicio: List<Exercicio>
)