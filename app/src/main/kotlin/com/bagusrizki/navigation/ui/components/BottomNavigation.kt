package com.bagusrizki.navigation.ui.components
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bagusrizki.navigation.R

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("home", R.drawable.ic_home, "Home"),
        BottomNavItem("profile", R.drawable.ic_profile, "Profile"),
        BottomNavItem("settings", R.drawable.ic_setting, "Settings")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .height(80.dp)
    ) {
        items.forEach { item ->
            val isSelected = when (item.route) {
                "home" -> currentRoute?.startsWith("home") == true
                else -> currentRoute == item.route
            }
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    if (isSelected) {
                        Text(
                            text = item.label,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 12.sp
                        )
                    } else {
                        Text(
                            text = item.label,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 0.sp
                        )
                    }
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val icon: Int,
    val label: String
)
