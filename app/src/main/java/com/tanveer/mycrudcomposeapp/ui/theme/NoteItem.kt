package com.tanveer.mycrudcomposeapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tanveer.mycrudcomposeapp.model.Note

@Composable
fun NoteItem(note: Note, onEdit: (Note) -> Unit, onDelete: (Note) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        EditNoteDialog(
            note = note,
            onConfirm = {
                onEdit(it)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(note.title, modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(note.title, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { showDialog = true }) {
                Text(text = "Edit")
            }

            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onDelete(note) }) {
                Text(text = "Delete")
            }

        }

    }


}


@Composable
fun EditNoteDialog(
    note: Note,
    onConfirm: (Note) -> Unit,
    onDismiss: () -> Unit
){
    var text by remember { mutableStateOf(note.title) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Edit Note")},
        text = {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {onConfirm(note.copy(title = text))}
            ) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    )
}