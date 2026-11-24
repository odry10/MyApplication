package com.example.myapplication

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Hello(name: String?) {
    Text(text = if (name != null) "Привет, $name!" else "Имя не задано")
}
@Preview(name = "portrait", showSystemUi = true,
    device = "spec:width=411dp,height=891dp,orientation=portrait")
@Preview(name = "landscape", showSystemUi = true,
    device = "spec:width=891dp,height=411dp,orientation=landscape")
@Preview(name = "round", showSystemUi = true, showBackground = true,
    backgroundColor = 0xFFFFFF00,
    device = "spec:width=200dp,height=200dp,isRound=true")
@Composable
fun HelloPreview() {
    Hello("Мир")
}