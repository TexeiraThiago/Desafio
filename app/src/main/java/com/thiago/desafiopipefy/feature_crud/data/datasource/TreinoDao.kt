package com.thiago.desafiopipefy.feature_crud.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.thiago.desafiopipefy.feature_crud.domain.model.Exercicio
import com.thiago.desafiopipefy.feature_crud.domain.model.Treino
import com.thiago.desafiopipefy.feature_crud.domain.model.TreinoComExercicio
import kotlinx.coroutines.flow.Flow

@Dao
interface TreinoDao {

    @Transaction
    @Query("SELECT * FROM treino ORDER by nome")
    fun getTreinoOrderByNome() : Flow<List<TreinoComExercicio>>

    @Transaction
    @Query("SELECT * FROM treino ORDER by data")
    fun getTreinoOrderByData() : Flow<List<TreinoComExercicio>>

    @Transaction
    @Query("SELECT * FROM treino WHERE nome = :nome")
    fun getTreinosBynome(nome: Int) : Flow<List<TreinoComExercicio>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertTreino(treino: Treino)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertExercicio(exercicio: Exercicio)

    @Delete
    suspend fun deleteTreino(treino: Treino)
}