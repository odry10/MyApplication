package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import kotlin.random.Random
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.safeDrawingPadding()) {
                ShoppingCartScreen()
            }
        }
    }
}

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
    Box(Modifier.fillMaxSize().safeDrawingPadding()) {
        Hello("Кирилл")
    }
}
@Preview(showBackground = true)
@Composable
fun TextPreview1() {
    Text(
        text = stringResource(R.string.compose_desc),
        color = Color.Green,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun TextPreview2() {
    Text(
        text = stringResource(R.string.compose_desc),
        style = TextStyle(
            textIndent = TextIndent(firstLine = 48.sp),
            fontSize = 24.sp,
            color = Color.Black,
            background = Color.Green,
            textDecoration = TextDecoration.Underline
        )
    )
}
@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ButtonPreview() {
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color.Gray),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text("Нажми на меня")
    }
}
@Preview(name = "portrait", showSystemUi = true)
@Composable
fun Task1Preview() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 16.dp)
                .fillMaxHeight()
                .aspectRatio(1f)
                .background(Color(0xFFFF5722), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("АБ")
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun Circle1() {
    Box(Modifier.size(240.dp, 120.dp).background(Color.Black)) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun Circle2() {
    Box(Modifier.size(240.dp, 120.dp).background(Color.Blue)) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .padding(8.dp),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF800080))
        )
    }
}
data class Contact(
    val name: String,
    val patronymic: String,
    val surname: String,
    val phone: String?,
    val address: String
)

val contactList = listOf(
    Contact("Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95", "г. Москва, 3-я улица Строителей, д. 25, кв. 12"),
    Contact("Василий", "Егорович", "Кузякин", null, "Ивановская область, дер. Крутово, д. 4"),
    Contact("Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78", "Москва, Большая Никитская, д. 43, кв. 290")
)

// 1) Простая колонка текстов
@Composable
fun ContactColumn(c: Contact) {
    Column {
        Text("Имя: ${c.name}")
        Text("Отчество: ${c.patronymic}")
        Text("Фамилия: ${c.surname}")
        Text("Мобильный телефон: ${c.phone ?: "—"}")
        Text("Адрес: ${c.address}")
    }
}

// Карточка: серый фон, текст по центру
@Composable
fun ContactCard(c: Contact) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Имя: ${c.name}")
        Text("Отчество: ${c.patronymic}")
        Text("Фамилия: ${c.surname}")
        Text("Мобильный телефон: ${c.phone ?: "—"}")
        Text("Адрес: ${c.address}")
    }
}

// 2) Список из трёх карточек
@Composable
fun ContactListColumn(list: List<Contact>) {
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        list.forEach { ContactCard(it) }
    }
}

// 3) Карточка со звездой справа (Row)
@Composable
fun ContactRow(c: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Имя: ${c.name}")
            Text("Отчество: ${c.patronymic}")
            Text("Фамилия: ${c.surname}")
            Text("Мобильный телефон: ${c.phone ?: "—"}")
            Text("Адрес: ${c.address}")
        }
        Text("★", color = Color(0xFFFF5722), fontSize = 28.sp)    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactColumnPreview() { ContactColumn(contactList[0]) }

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ListPreview() { ContactListColumn(contactList) }

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactRowPreview() { ContactRow(contactList[0]) }
@Composable
fun WaterTracker() {
    var waterCount by remember { mutableStateOf(0) }
    var streakDays by remember { mutableStateOf(0) }

    val primaryColor = Color(0xFF3F5F90)
    val secondaryColor = Color(0xFF555F71)
    val buttonTextColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = { waterCount += 250 }
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = {
                streakDays = if (waterCount >= 1500) streakDays + 1 else 0
                waterCount = 0
            }
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Дней подряд (1500 мл и больше): $streakDays",
            fontSize = 18.sp,
            color = secondaryColor
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun WaterTrackerPreview() { WaterTracker() }
data class Cart(
    val products: List<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

@Composable
fun ShoppingCartScreen() {
    val context = LocalContext.current
    var products by remember {
        mutableStateOf(
            listOf(
                Product(0, "Товар #1", 100),
                Product(1, "Товар #2", 150),
                Product(2, "Товар #3", 56)
            )
        )
    }
    val totalSum = products.sumOf { it.price }

    Column {
        products.forEach { product ->
            Text(text = product.name + " - " + product.price + " рублей")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Товаров на сумму: $totalSum рублей")

        AddProductSection {
            val newList = products + Product(
                id = products.size,
                name = "Товар #${products.size + 1}",
                price = Random.nextInt(0, 100)
            )
            products = newList
            // Если сумма заказа больше 500, показываем сообщение о бесплатной доставке
            if (newList.sumOf { it.price } > 500) {
                Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
            }
        }
        RemoveProductSection(visible = products.isNotEmpty()) {
            products = products.dropLast(1)
        }
    }
}

@Composable
fun AddProductSection(onAdd: () -> Unit) {
    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

@Composable
fun RemoveProductSection(visible: Boolean, onRemove: () -> Unit) {
    if (visible) {
        Button(onClick = onRemove) {
            Text(text = "Удалить товар")
        }
    }
}