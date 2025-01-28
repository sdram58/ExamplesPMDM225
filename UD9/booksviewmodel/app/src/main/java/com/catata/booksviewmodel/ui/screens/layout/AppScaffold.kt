package com.catata.booksviewmodel.ui.screens.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Componente propio para tener un Scaffold unificado en toda la aplicación

@Composable
fun AppScaffold(
    showBackArrow: Boolean = false,
    onBlackArrowClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                showBackArrow = showBackArrow,
                onClickBlackArrow = onBlackArrowClick,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Box(
                modifier = Modifier.weight(9f).fillMaxWidth()
            ) {
                content()
            }
            HorizontalDivider(
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary).height(2.dp)
            )
            AuthorInfo(modifier = Modifier.padding(vertical = 4.dp).weight(1f))
        }
    }
}

