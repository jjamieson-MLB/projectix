import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mlb.ballpark.projectix.common.presentation.RootContent

@Composable
fun MainView() =
    RootContent(Modifier.fillMaxSize(), listOf(), listOf(), {}, {}, {}, listOf())
