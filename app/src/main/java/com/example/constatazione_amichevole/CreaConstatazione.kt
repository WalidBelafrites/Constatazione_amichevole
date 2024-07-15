package com.example.constatazione_amichevole

import MacchinaViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.constatazione_amichevole.data.Constatazione
import com.example.constatazione_amichevole.data.ConstatazioneViewModel
import com.example.constatazione_amichevole.data.Macchina


@Composable
fun CreaConstatazione(
    viewModel: ConstatazioneViewModel = viewModel(),
    navController: NavHostController
) {
    val constatazione by viewModel.constatazione.observeAsState(emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf<Constatazione?>(null) }

    if (showAddDialog) {
        ConstatazioneDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name, surname, phone_number, assurance, accident_description ->
                viewModel.insert(Constatazione(name = name, surname = surname, phone_number = phone_number, assurance = assurance, accident_description
                = accident_description))
                showAddDialog = false
            }
        )
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Constatazione") },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Filled.Add, contentDescription = "Aggiungere Constatazione")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Aggiungere Constatazione")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ConstatazioneList(
                cais = constatazione,
                onEdit = { constatazione -> showEditDialog = constatazione },
                onDelete = { constatazione -> viewModel.delete(constatazione) }
            )
        }
    }
}

@Composable
fun ConstatazioneList(cais: List<Constatazione>, onEdit: (Constatazione) -> Unit, onDelete: (Constatazione) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(cais) { cai ->
            ConstatazioneItem(
                cai  = cai,
                onEdit = onEdit,
                onDelete = onDelete
            )
        }
    }
}

@Composable
fun ConstatazioneItem(cai: Constatazione, onEdit: (Constatazione) -> Unit, onDelete: (Constatazione) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cai.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = cai.surname,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "phone number: ${cai.phone_number}",
                    fontSize = 16.sp
                )
                Text(
                    text = "insurance: ${cai.assurance}",
                    fontSize = 16.sp
                )
                Text(
                    text = "incident description: ${cai.accident_description}",
                    fontSize = 16.sp
                )
            }
            IconButton(onClick = { onEdit(cai) }) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit Constatazione")
            }
            IconButton(onClick = { onDelete(cai) }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Constatazione")
            }
        }
    }
}

@Composable
fun ConstatazioneDialog(
    cai: Constatazione? = null,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String,String,String) -> Unit
) {

    var name by remember { mutableStateOf(cai?.name ?: "") }
    var surname by remember { mutableStateOf(cai?.surname ?: "") }
    var phone_number by remember { mutableStateOf(cai?.phone_number ?: "") }
    //var vehicule by remember { mutableStateOf(cai?.vehicule ?: "") }
    var assurance by remember { mutableStateOf(cai?.assurance ?: "") }
    var accident_description by remember { mutableStateOf(cai?.accident_description ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = if (cai == null) "Aggiungere Constatazione" else "Modificare Constatazione") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Driver Name") }
                )
                TextField(
                    value = surname,
                    onValueChange = { surname = it },
                    label = { Text("Driver Surname") }
                )
                TextField(
                    value = phone_number,
                    onValueChange = { phone_number = it },
                    label = { Text("Driver phone number") }
                )
                /*TextField(
                    value = vehicule,
                    onValueChange = { vehicule = it },
                    label = { Text("Driver Surname") }
                )*/
                TextField(
                    value = assurance,
                    onValueChange = { assurance = it },
                    label = { Text("Driver assurance") }
                )
                TextField(
                    value = accident_description,
                    onValueChange = { accident_description = it },
                    label = { Text("Describe incident") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    //TODO: VEHICULE
                    if (name.isNotBlank() && surname.isNotBlank() && phone_number.isNotBlank() && assurance.isNotBlank() && accident_description.isNotBlank()) {
                        onConfirm(name, surname, phone_number, assurance, accident_description)
                    }
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
