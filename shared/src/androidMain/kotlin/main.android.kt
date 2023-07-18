import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mlb.ballpark.projectix.common.presentation.RootContent
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun MainView() =
    RootContent(Modifier.fillMaxSize(), listOf(), {}, {}, {}, listOf())
@SuppressLint("UnrememberedMutableState", "SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BPDateRangePicker(
    team: String,
    onDateSelected: (Pair<String, String>) -> Unit,
    onDismissDatePicker: () -> Unit,
) {
    val datePickerState = rememberDateRangePickerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = onDismissDatePicker) {
                Icon(Icons.Filled.Close, contentDescription = null)
            }
            TextButton(
                onClick = {
                    datePickerState.selectedStartDateMillis?.let { start ->
                        datePickerState.selectedEndDateMillis?.let { end ->
                            val formatter = SimpleDateFormat("MM/dd/yyyy")
                            val calendar: Calendar = Calendar.getInstance()
                            calendar.timeInMillis = start
                            calendar.add(Calendar.DAY_OF_MONTH, 1)
                            val startFormatted = formatter.format(calendar.time)
                            calendar.timeInMillis = end
                            calendar.add(Calendar.DAY_OF_MONTH, 1)
                            val endFormatted = formatter.format(calendar.time)
                            onDateSelected(Pair(team, "$startFormatted to $endFormatted"))
                        }
                    }
                    onDismissDatePicker()
                },
                enabled = datePickerState.selectedEndDateMillis != null,
            ) {
                Text(text = "Save")
            }
        }

        DateRangePicker(
            modifier = Modifier.weight(1F),
            state = datePickerState,
        )
    }
}