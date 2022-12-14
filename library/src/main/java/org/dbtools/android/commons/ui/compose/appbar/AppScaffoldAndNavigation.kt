package org.dbtools.android.commons.ui.compose.appbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.dbtools.android.commons.ui.compose.appnavbar.AppBottomNavigationItem
import org.dbtools.android.commons.ui.compose.appnavbar.AppNavigationDrawerItem
import org.dbtools.android.commons.ui.compose.appnavbar.AppNavigationDrawerLabel
import org.dbtools.android.commons.ui.compose.appnavbar.AppNavigationRailItem

@Composable
fun AppScaffoldAndNavigation(
    topAppBar: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    hideNavigation: Boolean = false,
    navBarData: AppNavBarData? = null,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable (PaddingValues) -> Unit,
) {
    when {
        hideNavigation -> {
            Scaffold(
                topBar = topAppBar,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition,
                containerColor = containerColor,
                modifier = modifier
            ) { innerPadding ->
                AppScaffoldContentWrapper(innerPadding, content = content)
            }
        }
        navBarData?.appNavBarType == AppNavBarType.NAV_DRAWER -> {
            // NavDrawer
            navBarData.navDrawer()?.invoke {
                Scaffold(
                    topBar = topAppBar,
                    bottomBar = navBarData.bottomBar(),
                    floatingActionButton = floatingActionButton,
                    floatingActionButtonPosition = floatingActionButtonPosition,
                    containerColor = containerColor,
                    modifier = modifier
                ) { innerPadding ->
                    AppScaffoldContentWrapper(innerPadding, navBarData, content)
                }
            }
        }
        else -> {
            // NavBar / NavRail
            Scaffold(
                topBar = topAppBar,
                bottomBar = navBarData?.bottomBar() ?: {},
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition,
                containerColor = containerColor,
                modifier = modifier
            ) { innerPadding ->
                AppScaffoldContentWrapper(innerPadding, navBarData, content)
            }
        }
    }
}

/**
 * Content within a Scaffold for using either
 */
@Composable
private fun AppScaffoldContentWrapper(
    innerPadding: PaddingValues,
    navBarData: AppNavBarData? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Box(modifier = Modifier.padding(innerPadding)) {
        when (navBarData?.appNavBarType) {
            AppNavBarType.NAV_RAIL -> {
                // Content with NavigationRail
                Row {
                    navBarData.navRail()?.invoke()
                    content(innerPadding)
                }
            }
            else -> {
                // Content
                content(innerPadding)
            }
        }
    }
}

@Preview(group = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Preview(group = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
private fun AboutScaffoldPreview() {
    MaterialTheme {
        AppScaffold("Screen Title") {
            Text(text = "Content goes here")
        }
    }
}

@Preview(group = "Navigation", widthDp = 400, heightDp = 800, showBackground = true)
@Composable
private fun AboutScaffoldPhoneWithNavPreview() {
    val navBarData = previewAppNavBarData(NavBarItem.PEOPLE, AppNavBarType.NAV_BAR)

    MaterialTheme {
        AppScaffold("Screen Title", navBarData = navBarData) {
            Text(text = "Content goes here")
        }
    }
}

@Preview(group = "Navigation", widthDp = 600, heightDp = 1000, showBackground = true)
@Composable
private fun AboutScaffoldTabletWithNavPreview() {
    val navBarData = previewAppNavBarData(NavBarItem.PEOPLE, AppNavBarType.NAV_RAIL)

    MaterialTheme {
        AppScaffold("Screen Title", navBarData = navBarData) {
            Text(text = "Content goes here")
        }
    }
}

@Preview(group = "Navigation", widthDp = 1200, heightDp = 800, showBackground = true)
@Composable
private fun AboutScaffoldDesktopWithNavPreview() {
    val navBarData = previewAppNavBarData(NavBarItem.PEOPLE, AppNavBarType.NAV_DRAWER)

    MaterialTheme {
        AppScaffold("Screen Title", navBarData = navBarData) {
            Text(text = "Content goes here")
        }
    }
}

private fun previewAppNavBarData(selectedItem: NavBarItem, appNavBarType: AppNavBarType): AppNavBarData {
    return AppNavBarData(
        appNavBarType = appNavBarType,
        navBar = {
            NavigationBar {
                AppBottomNavigationItem(NavBarItem.PEOPLE, Icons.Outlined.Face, selectedItem, text = "People") {  }
                AppBottomNavigationItem(NavBarItem.ABOUT, Icons.Outlined.Info, selectedItem, text = "About") {  }
            }
        },
        navRail = {
            NavigationRail {
                AppNavigationRailItem(NavBarItem.PEOPLE, Icons.Outlined.Face, selectedItem, text = "People") {  }
                AppNavigationRailItem(NavBarItem.ABOUT, Icons.Outlined.Info, selectedItem, text = "About") {  }
            }
        },
        navDrawer = { appScaffoldContent ->
            PermanentNavigationDrawer(
                drawerContent = {
                    AppNavigationDrawerLabel("My App")
                    AppNavigationDrawerItem(NavBarItem.PEOPLE, Icons.Outlined.Face, selectedItem, text = "People") {  }
                    AppNavigationDrawerItem(NavBarItem.ABOUT, Icons.Outlined.Info, selectedItem, text = "About") {  }
                }
            ) {
                appScaffoldContent()
            }
        }
    )
}

private enum class NavBarItem {
    PEOPLE,
    ABOUT;
}
