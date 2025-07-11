package com.example.expentra.screens

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expentra.ui.theme.ExpentraTheme


@Composable
fun ExpentraHomeScreen() {
    val paradiso = Color(0xFF388B85)
    val hitPink = Color(0xFFFFAB7B)
    val backgroundColor = MaterialTheme.colorScheme.background

    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            BottomNavWithFab(paradiso)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header Background Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(paradiso)
                    .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
                    .height(210.dp) // adjust as needed
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Good afternoon,",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                    Text(
                        text = "Enjelin Morgeana",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Notification Icon with Dot
                // Notification Icon with blurry background and dot
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.15f)) // light glass effect
                        .blur(0.5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    // Hit Pink Notification Dot
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(hitPink, CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp)
                    )
                }

            }
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = backgroundColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(y = (-120).dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    var selected by remember { mutableStateOf("Monthly") }

                    // Toggle Weekly/Monthly
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        listOf("Weekly", "Monthly").forEach { label ->
                            Text(
                                text = label,
                                color = if (selected == label) Color.Black else Color.Gray,
                                fontWeight = if (selected == label) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                                    .clickable { selected = label }
                                    .background(
                                        if (selected == label) Color(0xFFE8F5F3) else Color.Transparent,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }

                    BalanceCard(balanceAmount = if (selected == "Monthly") "$ 2,548.00" else "$ 720.00")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IncomeExpenseCard(
                            "Income",
                            "$1,840.00",
                            isIncome = true,
                            modifier = Modifier.weight(1f)
                        )
                        IncomeExpenseCard(
                            "Expenses",
                            "$284.00",
                            isIncome = false,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }


            // Transactions
            // Transactions inside a Card
            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = (-95).dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Transactions History", color = MaterialTheme.colorScheme.onBackground ,fontWeight = FontWeight.Bold)
                    Text("See all", color = MaterialTheme.colorScheme.secondary, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)) {
                        TransactionItem("Upwork", "+ $850.00", "Today", Color(0xFF4CAF50))
                        TransactionItem("Transfer", "- $85.00", "Yesterday", Color.Red)
                        TransactionItem("Paypal", "+ $1,406.00", "Jan 30, 2022", Color(0xFF4CAF50))
                        TransactionItem("Youtube", "- $11.99", "Jan 16, 2022", Color.Red)
                        TransactionItem("Upwork", "+ $850.00", "Today", Color(0xFF4CAF50))
                        TransactionItem("Transfer", "- $85.00", "Yesterday", Color.Red)
                        TransactionItem("Paypal", "+ $1,406.00", "Jan 30, 2022", Color(0xFF4CAF50))
                        TransactionItem("Youtube", "- $11.99", "Jan 16, 2022", Color.Red)
                    }
                }
                Spacer(modifier = Modifier.height(80.dp))
            }
            // Bottom Nav
//            BottomNavWithFab(paradiso)
        }


    }
}

@Composable
fun IncomeExpenseCard(
    title: String,
    amount: String,
    isIncome: Boolean,
    modifier: Modifier = Modifier
) {
    val iconBackgroundColor = if (isIncome) Color(0xFFDAF5F0) else Color(0xFFFFE3E3)
    val iconTint = if (isIncome) Color(0xFF388B85) else Color.Red

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(iconBackgroundColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isIncome) Icons.Filled.ArrowDownward else Icons.Filled.ArrowUpward,
                contentDescription = null,
                tint = iconTint
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(title, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.bodyMedium)
        Text(amount,color = MaterialTheme.colorScheme.onBackground ,fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun BalanceCard(balanceAmount: String) {
    val numeric = balanceAmount.replace("$", "").replace(",", "").toFloatOrNull() ?: 0f
    val animatedBalance by animateIntAsState(
        targetValue = numeric.toInt(),
        animationSpec = tween(durationMillis = 1000),
        label = "Balance Animation"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Total Balance", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        Text(
            text = "$ ${String.format("%,d", animatedBalance)}.00",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}


@Composable
fun TransactionItem(title: String, amount: String, date: String, amountColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray, shape = CircleShape)
        ) {
            // You can put an image or icon here
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onBackground)
            Text(date, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
        }

        Text(amount, color = amountColor, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BottomNavWithFab(primaryColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Home, contentDescription = null, tint = primaryColor)
            Icon(imageVector = Icons.Filled.BarChart, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.width(48.dp)) // for FAB
            Icon(Icons.Filled.CreditCard, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
            Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
        }

        FloatingActionButton(
            onClick = { /* TODO */ },
            contentColor = primaryColor,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpentraHomeScreenPreview() {
    ExpentraTheme {
        ExpentraHomeScreen()
    }
}
