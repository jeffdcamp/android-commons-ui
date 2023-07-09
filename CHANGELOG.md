Change Log
==========

Version NEXT *(2023-05)*
-------------------------
* Added popBackStack to navigate(routes: List<String>)
* Updated versions
* Javadoc Improvements

Version 1.7.5 *(2023-05)*
-------------------------
* Expanded SavedStateHandleExt
* Fixed PlainTooltipBox
* Updated versions

Version 1.7.4 *(2023-04)*
-------------------------
* Added SavedStateHandleExt
* Fixes for androidxNavigation 2.6.0-beta01

Version 1.7.3 *(2023-04)*
-------------------------
* Support for Compose 1.4.1, Compose M3 1.1.0-beta02
* Kotlin 1.8.20 / Java 17 / Gradle 8.0.2 / AGP 8.0.0
* Added TextUnitExt.nonScaledSp
* Minor improvements to OverflowMenu

Version 1.7.2 *(2023-03)*
-------------------------
* Support for Compose 1.4.0, Compose M3 1.1.0-beta01, Lifecycle 2.6.1
* Added DateRangePickerDialog
* Added Date Validator to DatePickerDialog

  Version 1.7.1 *(2023-03)*
-------------------------
* Support for Compose 1.4.0-rc01, Compose M3 1.1.0-alpha08, Lifecycle 2.6.0
* updated xxxText -> xxxContent for ListItem

Version 1.7.0 *(2023-02)*
-------------------------
* Compose 1.4.3
* Support for Compose 1.4.0-beta02, Compose M3 1.1.0-alpha07, Lifecycle 2.6.0-rc01
* Added AppBarMenuItem.OverflowDivider / Improved AppBarMenuItem leading icon support
* Merged rendering code OverflowMenus from: AppBarMenu / OverflowMenu (OverflowMenuItem is now a sealed class)
* Added support for Divider in OverflowMenus
* Improved spacing on TwoInputDialog
* Added snackbarHost to AppScaffoldAndNavigation
* Added icon to MessageDialog
* Added supportingText to InputDialog

Version 1.6.0 *(2023-02)*
-------------------------
* Kotlin 1.8.10, Compose 1.4.2, AGP 7.4.1
* Support for Compose 1.4.0-beta, Compose M3 1.1.0-alpha06, Lifecycle 2.6.0-beta1
* Added new M3 DatePickerDialog, TimePickerDialog support to DialogUiState 
* Added ListItemTextHeader
* Improvements to ViewModelNav to fix issues with popBackStackWithResult(...)
* Prevent HandleSystemBarColors from trowing exceptions for Compose @Preview 
* Fixed consistency issues with use of supportingText with ClickableTextField and TextFieldDataTextField

Version 1.5.0 *(2023-01)*
-------------------------
* Kotlin 1.8.0, Compose 1.4.0, AGP 7.4.0
* Support filled selected bottom icons
* Removed AppScaffold and AppTopAppBar (Use MainAppScaffold in individual apps and call AppScaffoldAndNavigation(...) with-in)

Version 1.4.0 *(2023-01)*
-------------------------
* Change DropDownMenuDialog to use generic options
* Added HandleSystemBarColors() to handle the colors of the OS system bars
* Added AppTopAppBar.scrollBehavior
* Moved many "Form" Compose widgets to "form" package
* EnumExposedDropdownMenuBox -> DropdownMenuBoxField to support more than just Enum datatypes
* Added FieldValidation
* Added SwitchField
* Other minor cleanup

Version 1.3.1 *(2022-12)*
-------------------------
* Added containerColor to AppScaffold and AppScaffoldAndNavigation
* Added AppBarMenuItem.TextButton, added support to set button colors on AppBarMenuItem.TextButton and AppBarMenuItem.Text

Version 1.3.0 *(2022-11)*
-------------------------
* Added ClickableTextField, DateClickableTextField, TextFieldDataTextField, TextHeader, TextWithSubtitle, TextWithTitle, TimeClickableTextField 

Version 1.2.5 *(2022-11)*
-------------------------
* Added AppSystemBars (support theming of OS system bars)

Version 1.2.4 *(2022-11)*
-------------------------
* Added DropDownMenuDialog
* Fixed issue with MenuOptionsDialogUiState not showing in HandleDialogs
* Added ViewModelNav.PopWithResult

Version 1.2.3 *(2022-11)*
-------------------------
* Fixed Setting.Switch to have the whole row clickable

Version 1.2.2 *(2022-11)*
-------------------------
* Added floatingActionButton and floatingActionButtonPosition to AppScaffold and AppScaffoldAndNavigation
* Apply alpha to confirm button on InputDialog when disabled

Version 1.2.1 *(2022-10)*
-------------------------
* Updated to stable versions of Compose
* Moved ScaffoldAndNavigation -> AppScaffoldAndNavigation (support per-app ToolBar implementation)

Version 1.2.0 *(2022-10)*
-------------------------
* Kotlin 1.7.20
* Fixes for DayNightPasswordTextField with Material3 1.0.0-rc01
* Code cleanup

Version 1.1.1 *(2022-09)*
-------------------------
* Added MultiSelectDialog, DateDialogUiState, TimeDialogUiState to DialogUiState.LibraryDialogs()
* Fixes for EnumExposedDropdownMenuBox with Material3 1.0.0-beta03
* Updated MultiSelectDataItem (MultiSelectDialog) to use @Composable lambdas for text

Version 1.1.0 *(2022-09)*
-------------------------
* Updated dialogs to use @Composable lambdas for text (better support device language switching)

Version 1.0.4 *(2022-09)*
-------------------------
* Android Compose version updates 1.3.1 / Ui 1.3.0-beta02 / Material3 1.0.0-beta02

Version 1.0.3 *(2022-08)*
-------------------------
* UI fixes to DayNightPasswordTextField.kt
* Added DateUiUtil.kt

Version 1.0.2 *(2022-08)*
-------------------------
* Updated versions: Compose UI 1.3.0-beta01 / Material3 1.0.0-beta01

Version 1.0.1 *(2022-08)*
-------------------------
* Updated versions: Kotlin 1.7.10 / Compose Compiler 1.3.0 / Material3 1.0.0-alpha16

Version 1.0.0 *(2022-08)*
-------------------------
* Initial release