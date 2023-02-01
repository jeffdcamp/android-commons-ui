Change Log
==========

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