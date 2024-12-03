package com.catata.numericalconverter

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catata.numericalconverter.ui.theme.NumericalConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            NumericalConverterTheme {
                Scaffold (
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Content(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Content(modifier: Modifier) {
    var decimalNumber by rememberSaveable { mutableStateOf("") }
    var binaryResult by rememberSaveable { mutableStateOf("") }
    var hexadecimalResult by rememberSaveable { mutableStateOf("") }

    val colorBtnHexadecimal = Color(0xFF7B8108)
    val colorBtnBinary = Color(0xFF1F8491)

//    Integer.toBinaryString(3)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Conversor numérico",
            fontSize = 40.sp,
            color = Color(0xFFFF8000)
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (decimalNumber.isEmpty()) {
            Button(
                onClick = {
                    decimalNumber = "${(1..10000).random()}"
                    binaryResult = ""
                    hexadecimalResult = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA726),
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(id = R.string.btn_start))
            }
        } else {
            Button(
                onClick = {
                    decimalNumber = ""
                    binaryResult = ""
                    hexadecimalResult = ""
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEF5350),
                    contentColor = Color.White
                ),
                enabled = decimalNumber.isNotEmpty()
            ) {
                Text(stringResource(id = R.string.btn_delete))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = decimalNumber,
            fontSize = 60.sp,
            color = Color(0xFFFF8000)
        )
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    binaryResult = "b${Integer.toBinaryString(decimalNumber.toInt())}"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorBtnBinary,
                    contentColor = Color.White
                ),
                enabled = decimalNumber.length!=0
            ) {
                Text(stringResource(id = R.string.convert_binary))
            }

            Text(
                text = binaryResult,
                fontSize = 20.sp,
                color = colorBtnBinary
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    hexadecimalResult = "0x${Integer.toHexString(decimalNumber.toInt()).uppercase()}"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorBtnHexadecimal,
                    contentColor = Color.White
                ),
                enabled = decimalNumber.isNotEmpty()
            ) {
                Text(stringResource(id = R.string.convert_to_hexadecimal))
            }

            Text(
                text = hexadecimalResult,
                fontSize = 20.sp,
                color = colorBtnHexadecimal
            )
        }
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
    NumericalConverterTheme {
        Scaffold (
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Content(modifier = Modifier.padding(innerPadding))
        }
    }
}