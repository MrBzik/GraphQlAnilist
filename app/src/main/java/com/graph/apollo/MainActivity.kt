package com.graph.apollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.graph.apollo.ui.screens.CharactersListTab
import com.graph.apollo.ui.screens.SeriesTab
import com.graph.apollo.ui.theme.ApolloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ApolloTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    TabNavigator(CharactersListTab) {
                        Scaffold(
                            content = { padding ->
                                Box(modifier = Modifier.padding(bottom = padding.calculateBottomPadding())) {
                                    CurrentTab()
                                }
                            },
                            bottomBar = {
                                NavigationBar() {
                                    TabNavigationItem(CharactersListTab)
                                    TabNavigationItem(SeriesTab)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}





@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = {
            Text(text = tab.options.title)
        },
        icon = {
            Icon(painter = tab.options.icon!!, contentDescription = tab.options.title)
        }
    )
}