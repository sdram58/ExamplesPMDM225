package com.catata.dragonballadaptative.ui.screens.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.catata.dragonballadaptative.ui.screens.common.AuthorInfo

// Componente propio para tener un Scaffold unificado en toda la aplicación
@Composable
fun AppScaffold(
    showBackArrow: Boolean = false,
    onClickBlackArrow: () -> Unit = {},
    showAuthorInfo: Boolean = false,
    onAuthorInfoClick: () -> Unit,
    onFABClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                showBackArrow = showBackArrow,
                onClickBlackArrow = { onClickBlackArrow() },
            )
        },
        floatingActionButton = {
            // El botón solo se mostrará si la variable de estado viewAuthor es false
            if (!showAuthorInfo) {
                FloatingActionButton(onClick = {
                    onFABClick()
                }) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Autor"
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            content()

            AuthorInfo(
                visible = showAuthorInfo,
                onClick = {
                    onAuthorInfoClick()
                }
            )
        }
    }
}

