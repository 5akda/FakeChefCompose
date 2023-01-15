package iam5akda.fakechef_compose.game.view.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iam5akda.fakechef_compose.common.extension.lettersAndDigits
import iam5akda.fakechef_compose.design_system.theme.FakeChefTheme
import iam5akda.fakechef_compose.design_system.utils.ComposeTools
import iam5akda.fakechef_compose.game.R

@Composable
internal fun RegisterScreen(
    onClickJoin: (code: String, name: String) -> Unit
) {
    RegisterLayoutContent(onClickJoin = onClickJoin)
}

@Composable
private fun RegisterLayoutContent(
    onClickJoin: (code: String, name: String) -> Unit
) {
    var roomCodeText by remember { mutableStateOf("") }
    var yourNameText by remember { mutableStateOf("") }

    FakeChefTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                val focusManager = LocalFocusManager.current

                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth(),
                    value = roomCodeText,
                    onValueChange = { roomCodeText = it.lettersAndDigits() },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp
                    ),
                    label = {
                        Text(text = stringResource(id = R.string.label_room_code))
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_room_24),
                            contentDescription = null
                        )
                    }
                )
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                    value = yourNameText,
                    onValueChange = { yourNameText = it.lettersAndDigits() },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp
                    ),
                    label = {
                        Text(text = stringResource(id = R.string.label_your_name))
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_name_24),
                            contentDescription = null
                        )
                    }
                )
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
                onClick = {
                    onClickJoin.invoke(roomCodeText, yourNameText)
                }
            ) {
                Text(modifier = Modifier
                    .padding(vertical = 2.dp),
                    text = stringResource(id = R.string.button_join_room),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Preview(
    uiMode = ComposeTools.PREVIEW_IN_NIGHT,
    showBackground = true,
    device = Devices.PIXEL_3
)
@Composable
private fun RegisterScreenPreview() {
    RegisterLayoutContent(
        onClickJoin = {_,_->}
    )
}