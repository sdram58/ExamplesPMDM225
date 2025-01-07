package com.catata.signup.ui.screens.signup

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catata.signup.R
import com.catata.signup.ui.SignUpContent



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Variable de estado que permitirá mostrar/ocultar el AlertDialog
        var dialogVisible by rememberSaveable { mutableStateOf(false) }
        // Variable de estado para indicar si hay errores en los campos al pulsar el botón de Registro
        var errors by rememberSaveable { mutableStateOf(false) }

        Text(
            text = "Regístrate en la APP",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.red))
                .padding(vertical = 8.dp)
        )

        // Si hay errores se mostrará un texto indicándolo
        if (errors) {
            Text(
                text = "Existen errores. Revisa los datos",
                color = colorResource(id = R.color.red),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        // Contenedor con todos los campos del formulario
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .weight(weight = 9f, fill = true)
                .verticalScroll(rememberScrollState())
        ) {
            // Se han creado componentes propios personalizados para que todos los campos tengan la
            //  misma apariencia y ahorrar código en este componente contenedor

            var email by rememberSaveable { mutableStateOf("") }
            MyFieldHeader(text = "EMAIL")
            MyTextField(
                keyboardType = KeyboardType.Text,
                label = { Text("user@domain.com") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email"
                    )
                },
                value = email,
                onValueChange = { email = it }
            )

            var name by rememberSaveable { mutableStateOf("") }
            MyFieldHeader(text = "Nombre")
            MyTextField(
                keyboardType = KeyboardType.Text,
                label = { Text("Introduce tu nombre") },
                value = name,
                onValueChange = { name = it }
            )

            var surname1 by rememberSaveable { mutableStateOf("") }
            MyFieldHeader(text = "Primer apellido")
            MyTextField(
                keyboardType = KeyboardType.Text,
                label = { Text("Introduce tu primer apellido") } ,
                value = surname1,
                onValueChange = { surname1 = it }
            )

            var surname2 by rememberSaveable { mutableStateOf("") }
            MyFieldHeader(text = "Segundo apellido")
            MyTextField(
                keyboardType = KeyboardType.Text,
                label = { Text("Introduce tu segundo apellido") },
                value = surname2,
                onValueChange = { surname2 = it }
            )

            var phone by rememberSaveable { mutableStateOf("") }
            MyFieldHeader(text = "Teléfono")
            MyTextField(
                keyboardType = KeyboardType.Phone,
                label = { Text("Introduce tu número de teléfono") },
                trailingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Teléfono") },
                value = phone,
                onValueChange = {
                    if (it.isEmpty() || it.matches(Regex("^\\d+\$"))) {
                        phone = it
                    }
                }
            )

            val birthday = rememberDatePickerState()
            MyFieldHeader(text = "Fecha de nacimiento")
            DatePicker(
                state = birthday,
                modifier = Modifier
                    .padding(16.dp),
                showModeToggle = true,
                title = null,
            )

            // Variables de estado para saber si se han seleccionado o no cada una de las Chips
            var dragonBallChip by rememberSaveable { mutableStateOf(false) }
            var narutoChip by rememberSaveable { mutableStateOf(false) }
            var deathNoteChip by rememberSaveable { mutableStateOf(false) }
            var onePieceChip by rememberSaveable { mutableStateOf(false) }
            var akiraChip by rememberSaveable { mutableStateOf(false) }
            var monsterChip by rememberSaveable { mutableStateOf(false) }
            MyFieldHeader(text = "Selecciona tus favoritos")
            Row {
                MyFilterChip(
                    label = "Dragon Ball",
                    selected = dragonBallChip,
                    onClick = { dragonBallChip = !dragonBallChip },
                    modifier = Modifier.weight(5f)
                )
                MyFilterChip(
                    label = "Naruto",
                    selected = narutoChip,
                    onClick = { narutoChip = !narutoChip },
                    modifier = Modifier.weight(5f)
                )
                MyFilterChip(
                    label = "Death Note",
                    selected = deathNoteChip,
                    onClick = { deathNoteChip = !deathNoteChip },
                    modifier = Modifier.weight(5f)
                )
            }
            Row {
                MyFilterChip(
                    label = "Akira",
                    selected = akiraChip,
                    onClick = { akiraChip = !akiraChip },
                    modifier = Modifier.weight(5f)
                )
                MyFilterChip(
                    label = "One Piece",
                    selected = onePieceChip,
                    onClick = { onePieceChip = !onePieceChip },
                    modifier = Modifier.weight(5f)
                )
                MyFilterChip(
                    label = "Monster",
                    selected = monsterChip,
                    onClick = { monsterChip = !monsterChip },
                    modifier = Modifier.weight(5f)
                )
            }

            // Variable de estado para indicar si el botón de registro se activa o no
            val registerButtonState by remember {
                derivedStateOf {
                    email.isNotEmpty() && name.isNotEmpty() && surname1.isNotEmpty() &&
                    surname2.isNotEmpty() && phone.isNotEmpty() &&  birthday.selectedDateMillis != null &&
                    (dragonBallChip || narutoChip || deathNoteChip || onePieceChip || akiraChip || monsterChip)
                }
            }
            // Variable de estado para indicar si el botón de borrar se activa o no
            val deleteButtonState by remember {
                derivedStateOf {
                    email.isNotEmpty() || name.isNotEmpty() || surname1.isNotEmpty() ||
                    surname2.isNotEmpty() || phone.isNotEmpty() || birthday.selectedDateMillis != null ||
                    dragonBallChip || narutoChip || deathNoteChip || onePieceChip || akiraChip || monsterChip
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(top = 18.dp)
                    .fillMaxSize()
            ) {
                // Si se pulsa el botón se borrarán todos los campos
                Button(
                    onClick = {
                        email = ""
                        name = ""
                        surname1 = ""
                        surname2 = ""
                        phone = ""
                        birthday.selectedDateMillis = null
                        dragonBallChip = false
                        narutoChip = false
                        deathNoteChip = false
                        akiraChip = false
                        onePieceChip = false
                        monsterChip = false
                    },
                    enabled = deleteButtonState
                ) {
                    Text(text = "Borrar formulario")
                }

                // Si lso campos tienen el formato se borrarán y mostrará el AlertDialog
                //  si hay errores se habilitará el mensaje superior indicándolo
                Button(
                    onClick = {
                        if (checkFields(email, name, surname1, surname2)) {
                            dialogVisible = true
                            email = ""
                            name = ""
                            surname1 = ""
                            surname2 = ""
                            phone = ""
                            birthday.selectedDateMillis = null
                            dragonBallChip = false
                            narutoChip = false
                            deathNoteChip = false
                            akiraChip = false
                            onePieceChip = false
                            monsterChip = false

                            errors = false
                        } else {
                            errors = true
                        }
                    },
                    enabled = registerButtonState
                ) {
                    Text(text = "Registrarse")
                }
            }
        }

        // AlertDialog que se muestra si los datos son correctos
        if (dialogVisible) {
            AlertDialog(
                onDismissRequest = { dialogVisible = false },
                confirmButton = {
                    TextButton(onClick = { dialogVisible = false }) {
                        Text(text = "Aceptar")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Aceptar"
                        )
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = "Aceptar"
                    )
                },
                title = { Text("Registro en la APP") },
                text = { Text("El registro en la APP se ha realizado correctamente") },
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            thickness = 5.dp,
            color = colorResource(id = R.color.red),
        )

        // Foto y nombre
        AppHeader(modifier = Modifier.weight(1f))
    }
}

// Función para comprobar los campos
fun checkFields(
    email: String,
    name: String,
    surname1: String,
    surname2: String
): Boolean {
    if (!email.matches(Regex("^[a-z_]+\\.?[a-z_]+@[a-z]+\\.[a-z]{2,3}+\$"))) return false
    // permite nombres compuestos tipo: "José Ángel" y apellidos tipo: "de la Torre"
    if (!name.matches(Regex("^([A-zÁ-Úá-úÑñ\\-\']{2,}\\s?)+\$"))) return false
    if (!surname1.matches(Regex("^([A-zÁ-Úá-úÑñ\\-\']{2,}\\s?)+\$"))) return false
    if (!surname2.matches(Regex("^([A-zÁ-Úá-úÑñ\\-\']{2,}\\s?)+\$"))) return false
    return true
}

// Componente propio para la cabecera de cada campo
@Composable
fun MyFieldHeader(text: String) {
    Text(
        text = text,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(
                top = 18.dp,
                bottom = 4.dp
            )
            .fillMaxWidth()
    )
}

// Componente propio para cada campo

@Composable
fun MyTextField(
    keyboardType: KeyboardType,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            autoCorrect = false,
            imeAction = ImeAction.Next
        ),
        label = label,
        trailingIcon = trailingIcon,
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

// Componente propio para las Chips con la funcionalidad de seleccionarlas/desesleccionarlas
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFilterChip(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = label,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        leadingIcon = {
            if (selected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Seleccionado",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        },
        modifier = Modifier
            .padding(
                vertical = 0.dp,
                horizontal = 8.dp
            )
            .then(modifier)
    )
}

// Componente propio con la imagen y su nombre del desarrollador de la APP
@Composable
fun AppHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
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
                    color = colorResource(id = R.color.red),
                    shape = CircleShape
                )
                .width(60.dp)
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
    SignUpContent {
        SignUp(it)
    }
}