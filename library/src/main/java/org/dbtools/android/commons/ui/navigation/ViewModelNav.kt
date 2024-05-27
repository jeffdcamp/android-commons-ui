package org.dbtools.android.commons.ui.navigation

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Used for typical ViewModels that have navigation
 */
interface ViewModelNav {
    val navigationActionFlow: StateFlow<NavigationAction?>

    fun navigate(typeSafeRoute: Any, popBackStack: Boolean = false)
    fun navigate(typeSafeRoutes: List<Any>)
    fun navigate(typeSafeRoute: Any, navOptions: NavOptions)
    fun navigate(typeSafeRoute: Any, optionsBuilder: NavOptionsBuilder.() -> Unit)
    fun popBackStack(typeSafeRoute: Any? = null, inclusive: Boolean = false)
    fun popBackStackWithResult(resultValues: List<PopResultKeyValue>, typeSafeRoute: Any? = null, inclusive: Boolean = false)

    fun navigate(intent: Intent, options: Bundle? = null, popBackStack: Boolean = false)

    fun navigate(navigationAction: NavigationAction)
    fun resetNavigate(navigationAction: NavigationAction)
}

class ViewModelNavImpl : ViewModelNav {
    private val _navigatorFlow = MutableStateFlow<NavigationAction?>(null)
    override val navigationActionFlow: StateFlow<NavigationAction?> = _navigatorFlow.asStateFlow()

    override fun navigate(typeSafeRoute: Any, popBackStack: Boolean) {
        _navigatorFlow.compareAndSet(null, if (popBackStack) NavigationAction.PopAndNavigate(typeSafeRoute) else NavigationAction.Navigate(typeSafeRoute))
    }

    override fun navigate(typeSafeRoutes: List<Any>) {
        _navigatorFlow.compareAndSet(null, NavigationAction.NavigateMultiple(typeSafeRoutes))
    }

    override fun navigate(typeSafeRoute: Any, navOptions: NavOptions) {
        _navigatorFlow.compareAndSet(null, NavigationAction.NavigateWithOptions(typeSafeRoute, navOptions))
    }

    override fun navigate(typeSafeRoute: Any, optionsBuilder: NavOptionsBuilder.() -> Unit) {
        _navigatorFlow.compareAndSet(null, NavigationAction.NavigateWithOptions(typeSafeRoute, navOptions(optionsBuilder)))
    }

    override fun popBackStack(typeSafeRoute: Any?, inclusive: Boolean) {
        _navigatorFlow.compareAndSet(null, NavigationAction.Pop(typeSafeRoute, inclusive))
    }

    override fun popBackStackWithResult(resultValues: List<PopResultKeyValue>, typeSafeRoute: Any?, inclusive: Boolean) {
        _navigatorFlow.compareAndSet(null, NavigationAction.PopWithResult(resultValues, typeSafeRoute, inclusive))
    }

    override fun navigate(intent: Intent, options: Bundle?, popBackStack: Boolean) {
        _navigatorFlow.compareAndSet(null, if (popBackStack) NavigationAction.PopAndNavigateIntent(intent, options) else NavigationAction.NavigateIntent(intent, options))
    }

    override fun navigate(navigationAction: NavigationAction) {
        _navigatorFlow.compareAndSet(null, navigationAction)
    }

    override fun resetNavigate(navigationAction: NavigationAction) {
        _navigatorFlow.compareAndSet(navigationAction, null)
    }
}

@Composable
fun HandleNavigation(
    viewModelNav: ViewModelNav,
    navController: NavController?
) {
    navController ?: return
    val navigationActionState = viewModelNav.navigationActionFlow.collectAsStateWithLifecycle()
    val navigationAction = navigationActionState.value

    val context = LocalContext.current
    LaunchedEffect(navigationAction) {
        navigationAction?.navigate(context, navController, viewModelNav::resetNavigate)
    }
}
