package com.example.constatazione_amichevole

import MacchinaViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.constatazione_amichevole.data.Constatazione
import com.example.constatazione_amichevole.data.ConstatazioneViewModel
import com.example.constatazione_amichevole.data.Macchina
import generatePdf


@Composable
fun CreaConstatazione(
    viewModel: ConstatazioneViewModel = viewModel(),
    navController: NavHostController,
    macchinaViewModel: MacchinaViewModel = viewModel()
) {



    val constatazione by viewModel.constatazione.observeAsState(emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf<Constatazione?>(null) }
    val macchine by macchinaViewModel.macchine.observeAsState(emptyList())
    var constatazioneToAddOrUpdate by remember { mutableStateOf<Constatazione?>(null) }





    if (showAddDialog) {
        ConstatazioneDialog(
            macchine = macchine,
            onDismiss = { showAddDialog = false },
            onConfirm = { name, surname, phone_number, assurance, accident_description , macchinaId, macchinaNome, nameB, surnameB, phone_numberB, assuranceB ->
                val constatazione = Constatazione(
                    name = name,
                    surname = surname,
                    phone_number = phone_number,
                    assurance = assurance,
                    accident_description = accident_description,
                    macchinaId = macchinaId,
                    macchinaNome = macchinaNome,
                    nameB = nameB,
                    surnameB = surnameB,
                    phone_numberB = phone_numberB,
                    assuranceB = assuranceB
                )
                viewModel.insert(constatazione)
                constatazioneToAddOrUpdate = constatazione
                showAddDialog = false
            }
        )
    }

    if (showEditDialog != null) {
        ConstatazioneDialog(
            showEditDialog,
            macchine,
            { showEditDialog = null },
            { name, surname, phone_number, assurance, accident_description, macchinaId, macchinaNome, nameB, surnameB, phone_numberB, assuranceB ->
                val constatazione = Constatazione(
                    id = showEditDialog!!.id,
                    name = name,
                    surname = surname,
                    phone_number = phone_number,
                    assurance = assurance,
                    accident_description = accident_description,
                    macchinaId = macchinaId,
                    macchinaNome = macchinaNome,
                    nameB = nameB,
                    surnameB = surnameB,
                    phone_numberB = phone_numberB,
                    assuranceB = assuranceB
                )
                viewModel.update(constatazione)
                constatazioneToAddOrUpdate = constatazione
                showEditDialog = null
            },
        )
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestisci Le Constatazioni") },
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

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                // Check if constatazioneToAddOrUpdate is not null
                constatazioneToAddOrUpdate?.let { constatazione ->

                    generatePdf(constatazione, "C:\\Users\\walid\\OneDrive\\Bureau\\Constatazione_amichevole\\app\\src\\main\\java\\com\\example\\constatazione_amichevole\\utils\\output.pdf")
                }
            }
        ) {
            Text("Genera PDF")
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
                    text = "Numero del conducente: ${cai.phone_number}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Numero Assicuranza: ${cai.assurance}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Descrizione dell'incidente: ${cai.accident_description}",
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
    macchine: List<Macchina>,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String,String,String, Int?, String?, String,String,String,String) -> Unit,
) {

    var name by remember { mutableStateOf(cai?.name ?: "") }
    var surname by remember { mutableStateOf(cai?.surname ?: "") }
    var phone_number by remember { mutableStateOf(cai?.phone_number ?: "") }
    var assurance by remember { mutableStateOf(cai?.assurance ?: "") }
    var accident_description by remember { mutableStateOf(cai?.accident_description ?: "") }
    var selectedMacchina by remember { mutableStateOf<Macchina?>(null) }
    var nameB by remember { mutableStateOf(cai?.nameB ?: "") }
    var surnameB by remember { mutableStateOf(cai?.surnameB ?: "") }
    var phone_numberB by remember { mutableStateOf(cai?.phone_numberB ?: "") }
    var assuranceB by remember { mutableStateOf(cai?.assuranceB ?: "") }

    var expanded by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = if (cai == null) "Aggiungere Constatazione" else "Modificare Constatazione") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") }
                )
                TextField(
                    value = surname,
                    onValueChange = { surname = it },
                    label = { Text("Cognome") }
                )
                TextField(
                    value = phone_number,
                    onValueChange = { phone_number = it },
                    label = { Text("Numero del conducente") }
                )

                TextField(
                    value = assurance,
                    onValueChange = { assurance = it },
                    label = { Text("Numero Assicuranza") }
                )
                TextField(
                    value = accident_description,
                    onValueChange = { accident_description = it },
                    label = { Text("Descrizione dell'incidente") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Seleziona Macchina:")
                Box {
                    TextField(
                        value = selectedMacchina?.name ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Macchina") },
                        trailingIcon = {
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Filled.ArrowDropDown, contentDescription = "Seleziona Macchina")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        macchine.forEach { macchina ->
                            DropdownMenuItem(onClick = {
                                selectedMacchina = macchina
                                expanded = false
                            }) {
                                Text(text = macchina.name)
                            }
                        }
                    }
                }

                TextField(
                    value = nameB,
                    onValueChange = { nameB = it },
                    label = { Text("Nome B") }
                )
                TextField(
                    value = surnameB,
                    onValueChange = { surnameB = it },
                    label = { Text("Cognome B") }
                )
                TextField(
                    value = phone_numberB,
                    onValueChange = { phone_numberB = it },
                    label = { Text("Numero del conducente B") }
                )
                TextField(
                    value = assuranceB,
                    onValueChange = { assuranceB = it },
                    label = { Text("Numero Assicuranza B") }
                )
            }
        },

        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && surname.isNotBlank() && phone_number.isNotBlank() && assurance.isNotBlank() && accident_description.isNotBlank()) {
                        onConfirm(name, surname, phone_number, assurance, accident_description, selectedMacchina?.id, selectedMacchina?.name, nameB,surnameB, phone_numberB, assuranceB)

                    }
                }
            ) {
                Text("Conferma")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancella")
            }
        }
    )
}
