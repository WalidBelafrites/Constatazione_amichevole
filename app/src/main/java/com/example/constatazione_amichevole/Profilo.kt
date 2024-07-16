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
import com.example.constatazione_amichevole.data.FirebaseAuthViewModel
import com.example.constatazione_amichevole.data.Macchina
import com.example.constatazione_amichevole.data.Profilo
import com.example.constatazione_amichevole.data.ProfiloViewModel
import generatePdf


@Composable
fun Profilo(
    viewModel: ProfiloViewModel = viewModel(),
    navController: NavHostController
) {




    val profilo by viewModel.profilo.observeAsState(emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf<Profilo?>(null) }
    var profiloToAddOrUpdate by remember { mutableStateOf<Profilo?>(null) }





    if (showAddDialog) {
        ProfiloDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name, surname, email, phone_number, assurance ->
                val profilo = Profilo(
                    name = name,
                    surname = surname,
                    email = email,
                    phone_number = phone_number,
                    assurance = assurance
                )
                viewModel.insert(profilo)
                profiloToAddOrUpdate = profilo
                showAddDialog = false
            }
        )
    }

    if (showEditDialog != null) {
        ProfiloDialog(
            showEditDialog,
            { showEditDialog = null },
            { name, surname, email, phone_number, assurance ->
                val profilo = Profilo(
                    id = showEditDialog!!.id,
                    name = name,
                    surname = surname,
                    email = email,
                    phone_number = phone_number,
                    assurance = assurance,
                )
                viewModel.update(profilo)
                profiloToAddOrUpdate = profilo
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
            ProfiloList(
                profiles = profilo,
                onEdit = { profilo -> showEditDialog = profilo },
                onDelete = { profilo -> viewModel.delete(profilo) }
            )
        }
    }
}




@Composable
fun ProfiloList(profiles: List<Profilo>, onEdit: (Profilo) -> Unit, onDelete: (Profilo) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(profiles) { profile ->
            ProfiloItem(
                profile  = profile,
                onEdit = onEdit,
                onDelete = onDelete
            )
        }
    }
}

@Composable
fun ProfiloItem(profile: Profilo, onEdit: (Profilo) -> Unit, onDelete: (Profilo) -> Unit) {
    val authViewModel: FirebaseAuthViewModel = viewModel()
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
                    text = profile.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = profile.surname,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "email: ${authViewModel.currentUser.value?.email.toString()}",
                    fontSize = 16.sp
                )
                Text(
                    text = "telefono: ${profile.phone_number}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Numero Assicuranza: ${profile.assurance}",
                    fontSize = 16.sp
                )

            }
            IconButton(onClick = { onEdit(profile) }) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit Constatazione")
            }
            IconButton(onClick = { onDelete(profile) }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Constatazione")
            }
        }
    }
}

@Composable
fun ProfiloDialog(
    profile: Profilo? = null,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String,String,String) -> Unit,
) {

    val authViewModel: FirebaseAuthViewModel = viewModel()
    var name by remember { mutableStateOf(profile?.name ?: "") }
    var surname by remember { mutableStateOf(profile?.surname ?: "") }
    var email by remember { mutableStateOf(authViewModel.currentUser.value?.email.toString()) }
    var phone_number by remember { mutableStateOf(profile?.phone_number ?: "") }
    var assurance by remember { mutableStateOf(profile?.assurance ?: "") }
    //var selectedMacchina by remember { mutableStateOf<Macchina?>(null) }

    var expanded by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = if (profile == null) "Aggiungere Profilo" else "Modificare Profilo") },
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
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                TextField(
                    value = phone_number,
                    onValueChange = { phone_number = it },
                    label = { Text("Numero di telefono") }
                )

                TextField(
                    value = assurance,
                    onValueChange = { assurance = it },
                    label = { Text("Numero Assicuranza") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && surname.isNotBlank() && email.isNotBlank() && assurance.isNotBlank()) {
                        onConfirm(name, surname, email, phone_number, assurance)

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
