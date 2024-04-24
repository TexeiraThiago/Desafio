package com.thiago.desafiopipefy.feature_crud.domain.repository

import com.thiago.desafiopipefy.feature_crud.domain.model.Exercicio
import com.thiago.desafiopipefy.feature_crud.domain.model.Treino
import com.thiago.desafiopipefy.feature_crud.domain.model.TreinoComExercicio
import kotlinx.coroutines.flow.Flow

interface TreinoRepository {

    fun getTreinoOrderByNome() : Flow<List<TreinoComExercicio>>

    fun getTreinoOrderByData() :Flow<List<TreinoComExercicio>>

    fun getTreinosBynome(nome: Int): Flow<List<TreinoComExercicio>>

    suspend fun  insertTreino(treino: Treino)

    suspend fun  insertExercicio(exercicio: Exercicio)

    suspend fun deleteTreino(treino: Treino)

}