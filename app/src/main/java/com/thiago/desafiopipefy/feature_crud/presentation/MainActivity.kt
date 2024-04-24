package com.thiago.desafiopipefy.feature_crud.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.thiago.desafiopipefy.feature_crud.data.datasource.DesafioDatabase
import com.thiago.desafiopipefy.feature_crud.presentation.Treino.TreinoViewModel
import com.thiago.desafiopipefy.feature_crud.presentation.Treino.components.Screen
import com.thiago.desafiopipefy.ui.theme.DesafioPipefyTheme

class MainActivity : ComponentActivity() {

    private val dao by lazy {
        DesafioDatabase
            .getInstance(applicationContext)
            .treinoDao
    }

    private val viewModel by viewModels<TreinoViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TreinoViewModel(dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesafioPipefyTheme {
                val state by viewModel.state.collectAsState()
                Screen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}