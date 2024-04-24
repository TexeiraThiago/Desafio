package com.thiago.desafiopipefy.feature_crud.data.repository

import com.thiago.desafiopipefy.feature_crud.data.datasource.TreinoDao
import com.thiago.desafiopipefy.feature_crud.domain.model.Exercicio
import com.thiago.desafiopipefy.feature_crud.domain.model.Treino
import com.thiago.desafiopipefy.feature_crud.domain.model.TreinoComExercicio
import com.thiago.desafiopipefy.feature_crud.domain.repository.TreinoRepository
import kotlinx.coroutines.flow.Flow



class TreinoRepositoryImpl(private val dao:TreinoDao ): TreinoRepository {

    override fun getTreinoOrderByNome(): Flow<List<TreinoComExercicio>> {
        return dao.getTreinoOrderByNome()
    }

    override fun getTreinoOrderByData(): Flow<List<TreinoComExercicio>> {
        return dao.getTreinoOrderByData()
    }

    override fun getTreinosBynome(nome: Int): Flow<List<TreinoComExercicio>> {
        return dao.getTreinosBynome(nome)
    }

    override suspend fun insertTreino(treino: Treino) {
        return dao.insertTreino(treino)
    }

    override suspend fun insertExercicio(exercicio: Exercicio) {
        return insertExercicio(exercicio)
    }

    override suspend fun deleteTreino(treino: Treino) {
        return deleteTreino(treino)
    }
}