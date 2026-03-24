package com.example.ashish_appliedmidterm2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ashish_appliedmidterm2.ui.theme.Ashish_AppliedMidterm2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ashish_AppliedMidterm2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Entry point for our Counter Screen
                    CounterScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Stateful version of the CounterScreen.
 * This composable is responsible for connecting the ViewModel to the UI.
 * Requirement 2: The composable reads the state from the ViewModel.
 */
@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel()
) {
    // Requirement 4: The UI recomposes when the state changes.
    // We observe the state from the ViewModel using 'by' delegate.
    val count by viewModel.count

    // Following UDF (Unidirectional Data Flow):
    // State flows DOWN (to CounterContent)
    // Events flow UP (to ViewModel via onIncrement)
    CounterContent(
        count = count,
        onIncrement = { viewModel.onIncrement() }, // Requirement 3: Button presses call a ViewModel function
        modifier = modifier
    )
}

/**
 * Stateless version of the CounterContent.
 * Keeping this separate makes the code "Clean", testable, and allows for easy Previews.
 */
@Composable
fun CounterContent(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Current Count",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            
            Text(
                text = "$count",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 72.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onIncrement,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Increment",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    Ashish_AppliedMidterm2Theme {
        CounterContent(count = 5, onIncrement = {})
    }
}
