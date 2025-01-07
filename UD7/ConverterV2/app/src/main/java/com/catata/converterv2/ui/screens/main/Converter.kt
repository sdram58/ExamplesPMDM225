package com.catata.converterv2.ui.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catata.converterv2.R
import com.catata.converterv2.ui.ConverterContent

@Composable
fun Converter(modifier: Modifier) {
    var numberToConvert by rememberSaveable { mutableStateOf("") }
    var toBinary by rememberSaveable { mutableStateOf(false) }
    var numberConverted by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader()

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            thickness = 5.dp,
            //DividerDefaults.Thickness,
            color = colorResource(id = R.color.teal)
        )

        Text(
            text = "Introduce an integer",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = numberToConvert,
            onValueChange = {
                // Solo se actualiza la variable de estado si se pulsa un número
                if (it.isEmpty() || it.matches(Regex("^\\d+\$"))) {
                    numberToConvert = it
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false,
                imeAction = ImeAction.Done
            ),
            label = { Text("Number to convert") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Numbers,
                    contentDescription = "Number"
                )
            },
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {

            Text(
                text = "To decimal",
                color = if (toBinary) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        color = if (toBinary) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f)
                    )
                    .padding(8.dp)

            )
            Switch(
                checked = toBinary,
                onCheckedChange = { toBinary = it },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "To binary",
                color = if (toBinary) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        color = if (toBinary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                    )
                    .padding(8.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Result:")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = numberConverted)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    numberConverted = try {
                        if (toBinary) {
                            Integer.toBinaryString(numberToConvert.toInt())
                        } else {
                            if (numberToConvert.length<=20) {
                                numberToConvert.toInt(2).toString()
                            } else {
                                throw NumberFormatException()
                            }
                        }
                    } catch (e: NumberFormatException) {
                        if (toBinary) {
                            "Number is too long"
                        } else if (!numberToConvert.matches(Regex("^[01]+\$"))) {
                            "You can set ones and zeros"
                        } else {
                            "Number is too long"
                        }
                    }
                },
                enabled = numberToConvert.isNotBlank()
            ) {
                Text(text = "Convert")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    numberToConvert = ""
                },
                enabled = numberToConvert.isNotBlank()
            ) {
                Text(text = "Borrar")
            }
        }
    }
}

// header with username and picture
@Composable
fun AppHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            20.dp,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Image(
            painter = painterResource(id = R.drawable.rick),
            contentDescription = "Foto de Rick",
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 3.dp,
                    color = colorResource(id = R.color.teal),
                    shape = CircleShape
                )
                .width(80.dp)
        )
        Text(
            text = "Rick Sanchez",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ContentPreview() {
    ConverterContent {
        Converter(it)
    }
}