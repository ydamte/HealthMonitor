package com.example.healthmonitor
//Programmer: Yeabsera Damte
//Date: 09/13/2024
//Android Studio Koala | 2024.1.1
//macOS Sonoma 14.4.1
//Description: This app helps the user monitor their meals by
//taking the food name as input along with the number of calories,
//weather or not it's a nutritious/healthy food or sugary/processed food,
//and if it requires preparation and it saves each entry into a list.

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthmonitor.ui.theme.HealthMonitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthMonitorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HealthMonitorApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class FoodElement(
    val food: String,
    val calorieCount: Int,
    val fruitsVegetables: Boolean,
    val sugarsCarbs: Boolean,

    //val reminderNotification: Boolean
    val mealPrepReq: Boolean
)

@Composable
fun FoodEntered(FoodElement: FoodElement) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Food: ${FoodElement.food}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Calories: ${FoodElement.calorieCount}")
            Text(text = "Fruits/Vegetables (healthy foods): ${if (FoodElement.fruitsVegetables) "Yes" else "No"}")
            Text(text = "Sugars/Carbohydrates (junk foods): ${if (FoodElement.sugarsCarbs) "Yes" else "No"}")

            //Text(text = "Reminder Status: ${if (FoodElement.reminderNotification) "ACTIVED" else "DEACTIVATED"}")
            Text(text = "Meal Prep Required: ${if (FoodElement.mealPrepReq) "YES" else "NO"}")
        }
    }
}

@Composable
fun HealthMonitorApp(modifier: Modifier = Modifier) {
    var food by remember { mutableStateOf("") }
    var calorieCount by remember { mutableStateOf(0f) }
    var fruitsVegetables by remember { mutableStateOf(false) }
    var sugarsCarbs by remember { mutableStateOf(false) }
    //var reminderNotification by remember { mutableStateOf(false) }
    var mealPrepReq by remember { mutableStateOf(false) }

    var listing by remember { mutableStateOf(listOf<FoodElement>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF87CEFA))
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Food Monitor",
                color = Color(0xFF001F3F),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }

            //user input for food
            TextField(
                value = food,
                onValueChange = { food = it },
                label = {
                    Text(
                        "Enter the name of the food:",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF001F3F))
                    .padding(5.dp)
            )
        //}

        Spacer(modifier = Modifier.height(15.dp))

        //Slider for calorie count of food
        Text(
            text = "Calorie Count: ${calorieCount.toInt()}",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Slider(
            value = calorieCount,
            onValueChange = { calorieCount = it },
            valueRange = 0f..2000f,
            steps = 10,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF001F3F),
                inactiveTrackColor = Color.Gray,
                activeTrackColor = Color(0xFF001F3F)
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        //checkboxes
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = fruitsVegetables,
                onCheckedChange = { fruitsVegetables = it },
                //checkedColor = Color(0xFF001F3F)
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF001F3F),
                    checkmarkColor = Color.Gray
                )
            )
            Text(
                "Fruits/Vegetables (healthy foods)",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = sugarsCarbs,
                onCheckedChange = { sugarsCarbs = it },colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF001F3F),
                    checkmarkColor = Color.Gray
                )
            )
            Text(
                "Sugars/Carbohydrates (junk foods)",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                //text = if (reminderNotification) "Reminder: ACTIVE" else "Reminder: DEACTIVATED",
                text = if (mealPrepReq) "Meal Prep Required: YES" else "Meal Prep Required: NO",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Switch(
                checked = mealPrepReq,
                onCheckedChange = { mealPrepReq = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Gray,
                    uncheckedThumbColor = Color.Gray,
                    checkedTrackColor = Color(0xFF001F3F),
                    uncheckedTrackColor = Color(0xFF001F3F)
                )
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                if (food.isNotEmpty()) {
                    val foodElement = FoodElement(
                        food = food,
                        calorieCount = calorieCount.toInt(),
                        fruitsVegetables = fruitsVegetables,
                        sugarsCarbs = sugarsCarbs,
                        mealPrepReq = mealPrepReq
                    )

                    listing = listing + foodElement
                    food = ""
                    calorieCount = 0f
                    fruitsVegetables = false
                    sugarsCarbs = false
                    mealPrepReq = false
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF001F3F),
                contentColor = Color.White
            )
        ) {
            Text("Add Entry", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(15.dp))

        //print out list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
            items(listing) { FoodElement ->
                FoodEntered(FoodElement)

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HealthMonitorPreview() {
    HealthMonitorTheme {
        HealthMonitorApp()
    }
}