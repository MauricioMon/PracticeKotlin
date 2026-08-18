package com.testforwork.ejercicio1.presentation.userlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.testforwork.ejercicio1.domain.model.User
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Random Users") },
                actions = {
                    IconButton(onClick = { viewModel.onEvent(UserListEvent.Refresh) }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Recargar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                is UserListUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UserListUiState.Error -> {
                    Text(
                        text = "Error: ${state.message}",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                is UserListUiState.Success -> {
                    LazyColumn {
                        items(state.users) { user ->
                            UserRow(user)
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UserRow(user: User) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.pictureUrl,
            contentDescription = user.fullName,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = user.fullName, style = MaterialTheme.typography.titleMedium)
            Text(text = user.email, style = MaterialTheme.typography.bodySmall)
            Text(text = "${user.city}, ${user.country}", style = MaterialTheme.typography.bodySmall)
            Text(
                text = "Lat: ${user.latitude}, Long: ${user.longitude}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        IconButton(onClick = {
            val uri = Uri.parse("geo:${user.latitude},${user.longitude}?q=${user.latitude},${user.longitude}(${user.fullName})")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(mapIntent)
            } else {
                val browserUri = Uri.parse("https://www.google.com/maps?q=${user.latitude},${user.longitude}")
                context.startActivity(Intent(Intent.ACTION_VIEW, browserUri))
            }
        }) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Ver ubicación en el mapa")
        }
    }
}