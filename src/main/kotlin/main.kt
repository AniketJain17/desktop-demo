@file:Suppress("KotlinConstantConditions")

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
import androidx.compose.runtime.*
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
import java.io.File
import java.nio.file.Paths
import javax.swing.JFileChooser


//fun Route.uploadFile(){
//	post("file"){
//		val multipart = call.receiveMultipart()
//		multipart.forEachPart { part ->
//			when(part){
//				is PartData.FormItem -> unit
//				is PartData.FileItem -> {
//					if(part.name == "image"){
//						part.save("","")
//					}
//				}
//				else -> unit
//			}
//		}
//	}
//}

//fun openFile(view: View) {
//	val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//	intent.addCategory(Intent.CATEGORY_OPENABLE)
//	intent.type = "text/plain"
//	startActivityForResult(intent, OPEN_REQUEST_CODE)
//}
//
//fun saveFile(view: View) {
//	val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//	intent.addCategory(Intent.CATEGORY_OPENABLE)
//	intent.type = "text/plain"
//
//	startActivityForResult(intent, SAVE_REQUEST_CODE)
//}

// Using AWT From JAVA *************************************************************************************************
//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//private fun FileDialog1(
//	parent: Frame? = null,
//	onCloseRequest: (result: String?) -> Unit
//) = AwtWindow(
//	create = {
//		object : FileDialog(parent, "Choose a file", LOAD) {
//			override fun setVisible(isselected: Boolean) {
//				super.setVisible(isselected)
//				if (isselected) {
//					onCloseRequest(file)
//					val path: String = FileDialog.
//					val f = File(path)
//					println(file)
//					print(parent)
//				}
//			}
//		}
//	},
//	dispose = FileDialog::dispose
//
//)
//**********************************************************************************************************************

fun selectFile(pathState: MutableState<String?>) {
	JFileChooser().apply {
		if (showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			pathState.value = selectedFile.path
		}
	}
}

//private fun moveFile(src: File, dest: File): Boolean {
//	return src.renameTo(dest)
//}
//fun copyFile(src: File, dest: File) {
//	FileInputStream(src).channel.use { sourceChannel ->
//		FileOutputStream(dest).channel
//			.use { destChannel -> destChannel.transferFrom(sourceChannel, 0, sourceChannel.size()) }
//	}
//}

// Kotlin
//private fun moveDir(src: Path, dest: Path): Boolean {
//	if (src.toFile().isDirectory) {
//		for (file in src.toFile().listFiles()) {
//			moveDir(file.toPath(), dest.resolve(src.relativize(file.toPath())))
//		}
//	}
//	return try {
//		Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING)
//		true
//	} catch (e: IOException) {
//		false
//	}
//}

@Composable
@Preview
fun App() {
	Scaffold(
		topBar = { TopAppBar(title = { Text("Menu", color = Color(0xFF3700B3)) }, backgroundColor = Color(0xff0f9d58)) },
		content = {
			var mExpanded by remember { mutableStateOf(false) }
			val mCities = listOf("Aadhaar Card", "Pan Card", "Electricity Bill", "Ration Card", "Water Bill")
			var mSelectedText by remember { mutableStateOf("") }
			var mTextFieldSize by remember { mutableStateOf(" ")}
			var text by remember { mutableStateOf("") }
			var password by remember { mutableStateOf("") }
			val sourcePath = remember { mutableStateOf<String?>(null) }
			val targetPath = remember { mutableStateOf<String?>(null) }

			//File Choosing option  ************************************************************************************
//			var isFileChooserOpen by remember { mutableStateOf(false) }
//
//			if (isFileChooserOpen) {
//				FileDialog1(
//					onCloseRequest = {
//						isFileChooserOpen = false
//					}
//				)
//			}
			//**********************************************************************************************************

			val icon = if (mExpanded)
				Icons.Filled.KeyboardArrowUp
			else
				Icons.Filled.KeyboardArrowDown

			//Full Name Text Area **************************************************************************************
			Column(Modifier.padding(20.dp)) {
				Text("Full Name", textAlign = TextAlign.Center)
				OutlinedTextField(
					value = text,
					onValueChange = { text = it },
					label = { Text("Full Name") }
				)

				//Password Text Area ***********************************************************************************
				Text("Enter Your Password", textAlign = TextAlign.Center)
				OutlinedTextField(
					value = password,
					onValueChange = { password = it },
					label = { Text("Enter password") },
					visualTransformation = PasswordVisualTransformation(),
					keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
				)

				//Identification dropdown menu Text Area ***************************************************************
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
				//******************************************************************************************************

				//Choose File ******************************************************************************************
				MaterialTheme {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier.fillMaxWidth()) {
						Button(onClick = {
//							isFileChooserOpen = true
							selectFile(sourcePath)
							println(sourcePath.value)

						}) {
							Text("Choose File")
						}
					}
				}
				//******************************************************************************************************

				MaterialTheme {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier.fillMaxWidth()) {
						Button(onClick = {
							//Creating New Folder using text
							val folder = "C:\\Intern"
							val fileName= text
							val f = File(folder, fileName)
							f.mkdir()
							//Creating New Folder inside Name for identification
							val folder1 = folder+"\\"+fileName
							val fileName1= mSelectedText
							val f1 = File(folder1, fileName1)
							f1.mkdir()
							val destinePath: String  =folder1+"\\"+fileName1
							val destination:String = destinePath as String


							val source= sourcePath.value as String
							println(source)
							println(destination)


							val sourcePath1 = Paths.get(source)
							val targetPath1 = Paths.get(destination)
//							Files.move(sourcePath1, targetPath1, StandardCopyOption.REPLACE_EXISTING)
							File(source).let { sourceFile ->
								sourceFile.copyTo(File(destination))
								sourceFile.delete()




//								fun addNewCourse(
//									courseName: String?,
//									courseDuration: String?,
//									courseDescription: String?,
//									courseTracks: String?
//								) {
//									val db = this.writableDatabase
//
//									val values = ContentValues()
//
//									values.put(NAME_COL, courseName)
//									values.put(DURATION_COL, courseDuration)
//									values.put(DESCRIPTION_COL, courseDescription)
//									values.put(TRACKS_COL, courseTracks)
//
//									db.insert(TABLE_NAME, null, values)
//
//									db.close()
//								}
							}

						}) {
							Text("SUBMIT")
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



