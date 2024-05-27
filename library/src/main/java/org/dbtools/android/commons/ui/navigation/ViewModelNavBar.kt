package org.dbtools.android.commons.ui.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Used for MainScreen ViewModels that that have bottom NavigationBars or NavigationRails
 */
interface ViewModelNavBar<T : Enum<T>> {
    val navigatorFlow: StateFlow<ViewModelNavBarNavigator?>
    val selectedNavBarFlow: StateFlow<T?>

    fun navigate(typeSafeRoute: Any, popBackStack: Boolean = false)
    fun navigate(typeSafeRoutes: List<Any>)
    fun navigate(typeSafeRoute: Any, navOptions: NavOptions)
    fun navigate(typeSafeRoute: Any, optionsBuilder: NavOptionsBuilder.() -> Unit)
    fun navigate(intent: Intent, options: Bundle? = null)
    fun onNavBarItemSelected(selectedItem: T, typeSafeRoute: Any? = null)
    fun navBarNavigation(typeSafeRoute: Any, reselected: Boolean)
    fun resetNavigate(viewModelNavBarNavigator: ViewModelNavBarNavigator)
}

class ViewModelNavBarImpl<T : Enum<T>>(
    startNavBarItem: T?,
    private val navBarConfig: NavBarConfig<T>? = null,
) : ViewModelNavBar<T> {
    private val _navigatorFlow = MutableStateFlow<ViewModelNavBarNavigator?>(null)
    override val navigatorFlow: StateFlow<ViewModelNavBarNavigator?> = _navigatorFlow.asStateFlow()

    private val _selectedNavBarFlow = MutableStateFlow<T?>(startNavBarItem)
    override val selectedNavBarFlow: StateFlow<T?> = _selectedNavBarFlow.asStateFlow()

    override fun navigate(typeSafeRoute: Any, popBackStack: Boolean) {
        _navigatorFlow.compareAndSet(null, if (popBackStack) ViewModelNavBarNavigator.PopAndNavigate(typeSafeRoute) else ViewModelNavBarNavigator.Navigate(typeSafeRoute))
    }

    override fun navigate(typeSafeRoutes: List<Any>) {
        _navigatorFlow.compareAndSet(null, ViewModelNavBarNavigator.NavigateMultiple(typeSafeRoutes))
    }

    override fun navigate(typeSafeRoute: Any, navOptions: NavOptions) {
        _navigatorFlow.compareAndSet(null, ViewModelNavBarNavigator.NavigateWithOptions(typeSafeRoute, navOptions))
    }

    override fun navigate(typeSafeRoute: Any, optionsBuilder: NavOptionsBuilder.() -> Unit) {
        _navigatorFlow.compareAndSet(null, ViewModelNavBarNavigator.NavigateWithOptions(typeSafeRoute, navOptions(optionsBuilder)))
    }

    override fun navigate(intent: Intent, options: Bundle?) {
        _navigatorFlow.compareAndSet(null, ViewModelNavBarNavigator.NavigateIntent(intent, options))
    }

    override fun navBarNavigation(typeSafeRoute: Any, reselected: Boolean) {
        _navigatorFlow.compareAndSet(null, ViewModelNavBarNavigator.NavBarNavigate(typeSafeRoute, reselected))
    }

    override fun resetNavigate(viewModelNavBarNavigator: ViewModelNavBarNavigator) {
        _navigatorFlow.compareAndSet(viewModelNavBarNavigator, null)
    }

    override fun onNavBarItemSelected(selectedItem: T, typeSafeRoute: Any?) {
        val navRoute = typeSafeRoute ?: navBarConfig?.getRouteByNavItem(selectedItem)

        if (navRoute != null) {
            val reselected = _selectedNavBarFlow.value == selectedItem
            navBarNavigation(navRoute, reselected)
            _selectedNavBarFlow.value = selectedItem
        } else {
            Logger.e { "route not found for selectedItem [$selectedItem].  Make sure either the selectedItem is defined in the NavBarConfig OR the 'route' is supplied to this function" }
        }
    }
}

sealed class ViewModelNavBarNavigator {
    abstract fun <T : Enum<T>> navigate(context: Context, navController: NavController, viewModelNav: ViewModelNavBar<T>): Boolean

    class NavBarNavigate(private val typeSafeRoute: Any, private val reselected: Boolean) : ViewModelNavBarNavigator() {
        override fun <T : Enum<T>> navigate(context: Context, navController: NavController, viewModelNav: ViewModelNavBar<T>): Boolean {
            if (reselected) {
                // clear back stack
                navController.popBackStack(typeSafeRoute, inclusive = false)
            }

            navController.navigate(typeSafeRoute) {
                // Avoid multiple copies of the same destination when reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }

            viewModelNav.resetNavigate(this)
            return false
        }
    }

    class Navigate(private val typeSafeRoute: Any) : ViewModelNavBarNavigator() {
        override fun <T : Enum<T>> navigate(context: Context, navController: NavController, viewModelNav: ViewModelNavBar<T>): Boolean {
            navController.navigate(typeSafeRoute)

            viewModelNav.resetNavigate(this)
            return false
        }
    }

    class NavigateMultiple(private val typeSafeRoutes: List<Any>) : ViewModelNavBarNavigator() {
        override fun <T : Enum<T>> navigate(context: Context, navController: NavController, viewModelNav: ViewModelNavBar<T>): Boolean {
            typeSafeRoutes.forEach { typeSafeRoute ->
                navController.navigate(typeSafeRoute)
            }

            viewModelNav.resetNavigate(this)
            return false
        }
    }

    class NavigateWithOptions(private val typeSafeRoute: Any, private val navOptions: NavOptions) : ViewModelNavBarNavigator() {
        override fun <T : Enum<T>> navigate(context: Context, navController: NavController, viewModelNav: ViewModelNavBar<T>): Boolean {
            navController.navigate(typeSafeRoute, navOptions)

            viewModelNav.resetNavigate(this)
            return false
        }
    }

    class NavigateIntent(val intent: Intent, val options: Bundle? = null) : ViewModelNavBarNavigator() {
        override fun <T : Enum<T>> navigate(context: Context, navController: NavController, viewModelNav: ViewModelNavBar<T>): Boolean {
            try {
                context.startActivity(intent, options)
            } catch (ignore: Exception) {
                Logger.e(ignore) { "Failed to startActivity for intent (${intent.data})" }
            }
            viewModelNav.resetNavigate(this)
            return false
        }
    }

    class PopAndNavigate(private val typeSafeRoute: Any) : ViewModelNavBarNavigator() {
        override fun <T : Enum<T>> navigate(context: Context, navController: NavController, viewModelNav: ViewModelNavBar<T>): Boolean {
            val stackPopped = navController.popBackStack()
            navController.navigate(typeSafeRoute)

            viewModelNav.resetNavigate(this)
            return stackPopped
        }
    }
}

interface NavBarConfig<T : Enum<T>> {
    fun getRouteByNavItem(navBarItem: T): Any?
}

class DefaultNavBarConfig<T : Enum<T>>(
    private val navBarItemRouteMap: Map<T, Any>,
) : NavBarConfig<T> {
    override fun getRouteByNavItem(navBarItem: T): Any? = navBarItemRouteMap[navBarItem]
}

@Composable
fun <T : Enum<T>> HandleNavBarNavigation(
    viewModelNavBar: ViewModelNavBar<T>,
    navController: NavController?
) {
    navController ?: return
    val navigator by viewModelNavBar.navigatorFlow.collectAsStateWithLifecycle()

    val context = LocalContext.current
    LaunchedEffect(navigator) {
        navigator?.navigate(context, navController, viewModelNavBar)
    }
}
