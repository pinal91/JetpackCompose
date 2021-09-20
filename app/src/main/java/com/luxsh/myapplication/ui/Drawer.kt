package com.luxsh.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luxsh.myapplication.R
import com.luxsh.myapplication.ui.theme.NavigationDrawerTheme


/**
 * Created by Pinal Patel on 03/08/2021.
 * LuxshTech
 */
sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Account : DrawerScreens("Account", "account")
    object Help : DrawerScreens("Help", "help")
    object Retrofit : DrawerScreens("Retrofit", "retrofit")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help,
    DrawerScreens.Retrofit
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(
                start = 24.dp, top = 48.dp,
                end = 24.dp
            )
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
            painter = painterResource(R.drawable.ic_launcher),

            contentDescription = "App icon",
            contentScale = ContentScale.FillBounds,
        )
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Surface(
                shape = RoundedCornerShape(8.dp),
                elevation = 10.dp

            ) {
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clickable {
                            onDestinationClicked(screen.route)
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(

                        modifier = Modifier
                            .size(20.dp)
                            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
                        painter = painterResource(R.drawable.ic_launcher),

                        contentDescription = "App icon",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        fontFamily = FontFamily.SansSerif,
                        text = screen.title,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .clickable {
                                onDestinationClicked(screen.route)
                            }
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)

                    )

                }
            }
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    NavigationDrawerTheme {
        Drawer {}
    }
}