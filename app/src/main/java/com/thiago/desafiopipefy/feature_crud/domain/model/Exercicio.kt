package com.thiago.desafiopipefy.feature_crud.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import coil.compose.AsyncImage

@Entity(
    foreignKeys =  [
        ForeignKey(
        entity = Treino::class,
        parentColumns = ["nome"],
        childColumns = ["nome"],
        onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Exercicio(
    @PrimaryKey(autoGenerate = true) val nome: Int = 0,
    val imagem: String? = null,
    val observacao: String = ""
)
