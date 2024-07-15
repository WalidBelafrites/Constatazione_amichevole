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
import com.example.constatazione_amichevole.data.Macchina


@Composable
fun GestioneMacchine(
    viewModel: MacchinaViewModel = viewModel(),
    navController: NavHostController
) {
    val macchine by viewModel.macchine.observeAsState(emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf<Macchina?>(null) }

    if (showAddDialog) {
        VehicleDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name, year, color ->
                viewModel.insert(Macchina(name = name, year = year.toInt(), color = color))
                showAddDialog = false
            }
        )
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Vehicles") },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Vehicle")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Vehicle")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            VehicleList(
                vehicles = macchine,
                onEdit = { vehicle -> showEditDialog = vehicle },
                onDelete = { vehicle -> viewModel.delete(vehicle) }
            )
        }
    }
}

@Composable
fun VehicleList(vehicles: List<Macchina>, onEdit: (Macchina) -> Unit, onDelete: (Macchina) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(vehicles) { vehicle ->
            VehicleItem(
                vehicle = vehicle,
                onEdit = onEdit,
                onDelete = onDelete
            )
        }
    }
}

@Composable
fun VehicleItem(vehicle: Macchina, onEdit: (Macchina) -> Unit, onDelete: (Macchina) -> Unit) {
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
                    text = vehicle.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Year: ${vehicle.year}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Color: ${vehicle.color}",
                    fontSize = 16.sp
                )
            }
            IconButton(onClick = { onEdit(vehicle) }) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit Vehicle")
            }
            IconButton(onClick = { onDelete(vehicle) }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Vehicle")
            }
        }
    }
}

@Composable
fun VehicleDialog(
    vehicle: Macchina? = null,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit
) {
    var name by remember { mutableStateOf(vehicle?.name ?: "") }
    var year by remember { mutableStateOf(vehicle?.year?.toString() ?: "") }
    var color by remember { mutableStateOf(vehicle?.color ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = if (vehicle == null) "Add Vehicle" else "Edit Vehicle") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Vehicle Name") }
                )
                TextField(
                    value = year,
                    onValueChange = { year = it },
                    label = { Text("Year") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(
                    value = color,
                    onValueChange = { color = it },
                    label = { Text("Color") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && year.isNotBlank() && color.isNotBlank()) {
                        onConfirm(name, year, color)
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
