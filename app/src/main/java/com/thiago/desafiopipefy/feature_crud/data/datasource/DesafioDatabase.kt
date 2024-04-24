package com.thiago.desafiopipefy.feature_crud.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thiago.desafiopipefy.feature_crud.domain.model.Exercicio
import com.thiago.desafiopipefy.feature_crud.domain.model.Treino
import com.thiago.desafiopipefy.feature_crud.domain.model.TreinoComExercicio
import com.thiago.desafiopipefy.feature_crud.domain.model.utils.Converters

@Database(
    entities = [Treino::class, Exercicio::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DesafioDatabase: RoomDatabase() {

    abstract val treinoDao: TreinoDao

    companion object {
        @Volatile
        private var INSTANCE: DesafioDatabase? = null

        fun getInstance(context: Context): DesafioDatabase {
            synchronized(this) {
                return INSTANCE ?: Room
                    .databaseBuilder(
                    context.applicationContext,
                    DesafioDatabase::class.java,
                    "desafio_db")
                    .addTypeConverter(Converters())
                    .build().also {
                    INSTANCE = it
                }
            }
        }
    }
}