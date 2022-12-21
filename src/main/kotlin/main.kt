import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application



@Composable
@Preview
fun App() {
	Scaffold(
		topBar = { TopAppBar(title = { Text("Desktop Menu", color = Color(0xFF3700B3)) }, backgroundColor = Color(0xff0f9d58)) },
		content = {
			var mExpanded by remember { mutableStateOf(false) }
			val mCities = listOf("Aadhar Card", "Pan Card", "Electricity Bill", "Ration Card", "Water Bill")
			var mSelectedText by remember { mutableStateOf("") }
			var mTextFieldSize by remember { mutableStateOf(" ")}
			var text by remember { mutableStateOf("") }
			var password by remember { mutableStateOf("") }

			val icon = if (mExpanded)
				Icons.Filled.KeyboardArrowUp
			else
				Icons.Filled.KeyboardArrowDown

			Column(Modifier.padding(20.dp)) {
				Text("Name", textAlign = TextAlign.Center)
				OutlinedTextField(
					value = text,
					onValueChange = { text = it },
					label = { Text("Full Name") }
				)
				Text("Enter Your Password", textAlign = TextAlign.Center)
				OutlinedTextField(
					value = password,
					onValueChange = { password = it },
					label = { Text("Enter password") },
					visualTransformation = PasswordVisualTransformation(),
					keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
				)
				Text("Select Valid Identification ", textAlign = TextAlign.Center)
				OutlinedTextField(
					value = mSelectedText,
					onValueChange = { mSelectedText = it },
					modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates -> mTextFieldSize = coordinates.size.toSize().toString() },
					label = {Text("Label")},
					trailingIcon = {
						Icon(icon,"contentDescription", Modifier.clickable { mExpanded = !mExpanded })
					}
				)


				DropdownMenu(
					expanded = mExpanded,
					onDismissRequest = { mExpanded = false },
//					modifier = Modifier.width(with(LocalDensity.current){mTextFieldSize.width().toDp()})
				) {
					mCities.forEach { label ->
						DropdownMenuItem(onClick = {
							mSelectedText = label
							mExpanded = false
						}) {
							Text(text = label)
						}
					}
				}
				MaterialTheme {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier.fillMaxWidth()) {
						Button(onClick = {
							text = "Choose File"
						}) {
							Text(text)
						}
					}
				}
			}
		}
	)

}

fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		App()
	}
}



