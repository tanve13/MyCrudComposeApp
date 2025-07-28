package com.tanveer.mycrudcomposeapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tanveer.mycrudcomposeapp.model.Note

@Composable
fun NoteListScreen() {
    var notes by remember { mutableStateOf(listOf<Note>()) }
    var noteCounter by remember { mutableStateOf(1) }
    var newNoteText by remember { mutableStateOf("") }
    Column(Modifier.padding(16.dp)) {
        TextField(
            value = newNoteText, onValueChange = { newNoteText = it },
            placeholder = { Text("Enter Note") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (newNoteText.isNotBlank()) {
                notes = notes + Note(
                    id = noteCounter++,
                    title = newNoteText
                )
                newNoteText = ""
            }
        }) {
            Text("Add Note")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(notes.size) { index ->
                val note = notes[index]
                NoteItem(
                    note = note,
                    onEdit = { updatedNote ->
                        notes = notes.map {
                            if (it.id == updatedNote.id) updatedNote else it
                        }
                    },
                    onDelete = { deletedNote ->
                        notes = notes.filter { it.id != deletedNote.id }
                    }
                )
            }
        }
    }


}