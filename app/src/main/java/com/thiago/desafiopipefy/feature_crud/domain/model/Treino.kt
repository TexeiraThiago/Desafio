package com.thiago.desafiopipefy.feature_crud.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
@Parcelize
data class Treino(
    @PrimaryKey(autoGenerate = true) val nome: Int = 0,
    val descricao: String = "",
    val data: LocalDate? = LocalDate.now()
    ):Parcelable {

        val createDateFormated: String?
            get() = data?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}