package org.example.geoblinker.presentation.features.profile.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileEditScreen(
    state: ProfileEditState,
    onEvent: (ProfileEditEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Редактирование профиля") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(ProfileEditEvent.OnBackClick) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ПОЛЕ: ИМЯ
            OutlinedTextField(
                value = state.name,
                onValueChange = { onEvent(ProfileEditEvent.OnNameChanged(it)) },
                label = { Text("Ваше имя") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !state.isLoading
            )

            // ПОЛЕ: EMAIL
            OutlinedTextField(
                value = state.email,
                onValueChange = { onEvent(ProfileEditEvent.OnEmailChanged(it)) },
                label = { Text("E-mail для уведомлений") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !state.isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))

            // КНОПКА СОХРАНЕНИЯ
            Button(
                onClick = { onEvent(ProfileEditEvent.OnSaveClick) },
                enabled = state.isSaveEnabled,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(size = 24.dp, color = Color.White)
                } else if (state.isSuccess) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Check, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("СОХРАНЕНО")
                    }
                } else {
                    Text("СОХРАНИТЬ ИЗМЕНЕНИЯ")
                }
            }

            if (state.errorText != null) {
                Text(text = state.errorText, color = Color.Red, style = MaterialTheme.typography.caption)
            }
        }
    }
}
