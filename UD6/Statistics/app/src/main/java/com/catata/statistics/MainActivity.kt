package com.catata.statistics

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catata.statistics.ui.theme.StatisticsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StatisticsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    var total by rememberSaveable { mutableStateOf(0) }
    var people by rememberSaveable { mutableStateOf(0) }
    var scooters by rememberSaveable { mutableStateOf(0) }
    var bikes by rememberSaveable { mutableStateOf(0) }
    var cars by rememberSaveable { mutableStateOf(0) }

    val greenColor = Color(0xFF66BB6A)
    val orangeColor = Color(0xFFFFA726)
    val redColor = Color(0xFFEF5350)


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Estadísticas UD3",
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total: $total",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total = 0
                    people = 0
                    scooters = 0
                    bikes = 0
                    cars = 0
                },
                enabled = total > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = redColor
                )
            ) {
                Text("Reiniciar todos")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text("Personas:")
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "$people",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total++
                    people++
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenColor
                )
            ) {
                Text("+1")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total--
                    people--
                },
                enabled = people > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = orangeColor
                )
            ) {
                Text("-1")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total -= people
                    people = 0
                },
                enabled = people > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = redColor
                )
            ) {
                Text("Reset")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text("Patinetes:")
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$scooters",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total++
                    scooters++
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenColor
                )
            ) {
                Text("+1")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total--
                    scooters--
                },
                enabled = scooters > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = orangeColor
                )
            ) {
                Text("-1")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total -= scooters
                    scooters = 0
                },
                enabled = scooters > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = redColor
                )
            ) {
                Text("Reset")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text("Bicicletas:")
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "$bikes",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total++
                    bikes++
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenColor
                )
            ) {
                Text("+1")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total--
                    bikes--
                },
                enabled = bikes > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = orangeColor
                )
            ) {
                Text("-1")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total -= bikes
                    bikes = 0
                },
                enabled = bikes > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = redColor
                )
            ) {
                Text("Reset")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text("Coches:")
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "$cars",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total++
                    cars++
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = greenColor
                )
            ) {
                Text("+1")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total--
                    cars--
                },
                enabled = cars > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = orangeColor
                )
            ) {
                Text("-1")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    total -= cars
                    cars = 0
                },
                enabled = cars > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = redColor
                )
            ) {
                Text("Reset")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Estadísticas")
        Text(
            text = """
            |Personas: ${if (total == 0) 0 else people * 100 / total} %
            |Patinetes: ${if (total == 0) 0 else scooters * 100 / total} %
            |Bicicletas: ${if (total == 0) 0 else bikes * 100 / total} %
            |Coches: ${if (total == 0) 0 else cars * 100 / total} %            
            """.trimMargin()
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
    StatisticsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Content()
        }
    }
}