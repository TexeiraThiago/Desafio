package com.thiago.desafiopipefy.feature_crud.domain.model.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@ProvidedTypeConverter
class Converters {
        @TypeConverter
        fun fromTimeStamp(value: String?) : LocalDate? {
            return value?.let { LocalDate.parse(it) }
        }

        @TypeConverter
        fun dateTimeStamp(date: LocalDate?) : String? {
            return date?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    }
}