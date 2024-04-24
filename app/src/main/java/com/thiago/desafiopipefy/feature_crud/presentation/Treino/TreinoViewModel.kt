package com.thiago.desafiopipefy.feature_crud.presentation.Treino

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiago.desafiopipefy.feature_crud.data.datasource.TreinoDao
import com.thiago.desafiopipefy.feature_crud.domain.model.Exercicio
import com.thiago.desafiopipefy.feature_crud.domain.model.Treino
import com.thiago.desafiopipefy.feature_crud.domain.model.utils.Converters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TreinoViewModel(private val dao: TreinoDao): ViewModel() {

    private val _state = MutableStateFlow(States())
    private val _tipoOrdem = MutableStateFlow(TipoOrdem.NOME)
    private val _treino = _tipoOrdem.flatMapLatest { tipoOrdem ->
        when(tipoOrdem) {
            TipoOrdem.NOME -> dao.getTreinoOrderByNome()
            TipoOrdem.DATA -> dao.getTreinoOrderByData()
        }
    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _tipoOrdem, _treino) { state, tipoOrdem, treino ->
        state.copy(
            treino = treino,
            tipoOrdem = tipoOrdem
        )
    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000), States())

    fun onEvent(evento:Evento) {
        when(evento) {
            is Evento.Delete -> {
                viewModelScope.launch {
                    dao.deleteTreino(evento.treino)
                }
            }
            Evento.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingTreino = false
                    )
                }
            }
            is Evento.Organizar -> {
                _tipoOrdem.value = evento.tipoOrdem
            }
            Evento.Save -> {
                val descricao = state.value.descricao
                val data = state.value.data
                val observacao = state.value.observacao
                val imagem = state.value.imagem


                if(descricao.isBlank() || descricao.isNullOrBlank()|| observacao.isBlank()){
                    return
                }
                val exercicio = Exercicio(
                    observacao = observacao,
                    imagem = imagem
                )

                val converter = Converters()
                val convertedData = converter.fromTimeStamp(data)

                val treino = Treino (
                    descricao = descricao,
                    data = convertedData
                )

                viewModelScope.launch {
                    dao.insertTreino(treino)
                    dao.insertExercicio(exercicio)
                }
                _state.update {
                    it.copy(
                        isAddingTreino = false,
                        descricao = "",
                        data = "",
                        imagem = "",
                        observacao  = ""
                    )
                }
            }
            is Evento.setData -> {
                _state.update {
                    it.copy(
                        data = evento.data
                    )
                }
            }
            is Evento.setDescricao -> {
                _state.update {
                    it.copy(
                        descricao = evento.descricao
                    )
                }
            }
            is Evento.setObservacao -> {
                _state.update {
                    it.copy(
                        observacao = evento.observacao
                    )
                }
            }
            Evento.showDialog -> {
                _state.update {
                    it.copy(
                        isAddingTreino = true
                    )
                }
            }
            is Evento.setImagem -> {
                _state.update {
                    it.copy(
                        imagem = evento.imagem
                    )
                }
            }
        }
    }
}