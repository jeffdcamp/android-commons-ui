# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [3.1.0] - 2025-12-23

### Changed

- Updated versions

## [3.0.0] - 2025-09-01

### Changed

- Kotlin 2.2.10
- Kotlin datetime 0.7.x (kotlinx.datetime.Instant to kotlin.time.Instant)
- Updated build scripts
- Updated versions


## [2.4.0] - 2025-04-10

### Changed

- Android SDK 36
- Improved Navigation Action to support direct calls to the NavController
- Improved support for long text in MessageDialog
- Improved support for multiple lines in DayNightTextField and InputDialog
- Updated versions

## [2.3.0] - 2025-02-22

### Changed

- Added backgroundColor to ListItemTextHeader
- Added color to ImageScrim
- Updated versions


## [2.2.2] - 2024-11-01

### Changed

- Improved navigation logging
- Updated versions

## [2.2.1] - 2024-10-16

### Added

- Added supportingContent, leadingContent, trailingContent to MenuOptionsDialogItem

### Changed

- Fixed spacing for supporting text MenuOptionsDialog

## [2.2.0] - 2024-10-09

### Added

- Added CheckboxField

### Changed

- Compose UI 1.7.3

## [2.1.0] - 2024-09-14

### Changed

- Renamed Clicked() -> Click()
- Compose UI 1.7.1
- Navigation Compose 1.3.0


## [2.0.0] - 2024-09-05

### Added

- NavController.popBackStackOrFinishActivity
- Context.requireActivity()

### Removed

- Android SDK 35
- Removed ListNavType

### Changed

- Compose UI 1.7.0
- Navigation Compose 1.3.0

## [2.0.0-beta03] - 2024-06-12

### Changed

- Renamed navigation classes from NavXxxx -> NavigationXxxx
- Compose UI 1.7.0-beta03
- Navigation Compose 1.3.0-beta03

## [2.0.0-beta02] - 2024-06-04

### Changed

- Compose UI 1.7.0-beta02
- Navigation Compose 1.3.0-beta02

### Added 

- Added Type Safety to NavigationAction and ViewModelNav using interface NavRoute
- Added ListItemExt.transparentContainer() function extension

### Removed

- Removed NavComposeRoute, NavRoute, SavedStateHandleExt


## [2.0.0-beta01] - 2024-05-25

### Changed

- Changed NavigationAction, ViewModelNav, ViewModelNavBar to support Type Safe Navigation (navigation-compose 1.3.0-beta01)
- Added RadioButtonTextItem

### Removed

- Removed NavComposeRoute, NavRoute, SavedStateHandleExt

## [1.12.0] - 2024-05-25

### Changed

- Improved spacing on MenuOptionsDialog items
- Compose fixes for Compose M3 1.3.0-beta01
- Kotlin 2.0.0
- Compose M3 1.3.0-beta01 / Compose UI 1.7.0-beta01
- Compose Navigation 2.8.0-beta01

## [1.11.0] - 2024-03-16

### Changed

- Changed MultiSelectDialogUiState to have Checkbox before Text
- Changed DropdownMenuBoxField to allow selectedOptionFlow to be nullable
- Improved dependencies (Changed all plugins to alias)

## [1.10.1] - 2024-02-29

### Changed

- Prevent crash when trying to navigate to an Intent that can't be handled
- Improved label formatting on TextWithTitle

## [1.10.0] - 2024-02-26

### Added

- Added MediaDownloadUtil, MediaDownloadIconButton for working with downloads with Media3

### Changed

- Changed OverflowMenuItemCustom and OverflowMenuItem.MenuItemCustom to have leadingContent and trailingContent return Unit
- Added keyboardOptions, keyboardActions to FlowTextField

### Removed

- Removed AppNavigation code (use Compose Navigation Suite instead)  

## [1.9.0] - 2024-02-07

### Added

- Added Modifier.semantics { heading() } to AppBarTitle
- Added OverflowMenuItemCustom to allow custom leadingContent/trailingContent

### Changed

- Compose M3 1.2.0 / ComposeUi 1.6.1 / Other version updates


## [1.9.0-rc01] - 2024-01-29

### Added

- Added support for Custom AppBarMenuItem

### Changed

- Compose M3 1.2.0-rc01 / ComposeUi 1.6.0 / Other version updates
- Fixed scrolling issues with RadioDialog and MenuOptionsDialog
- Added padding for OverflowMenuItemsContent text
- Improvements for Compose previews in Library

## [1.9.0-beta02] - 2024-01-11

### Changed

- Kotlin 1.9.22 / Compose M3 1.2.0-beta02 / ComposeUi 1.6.0-rc01 / Other version updates

## [1.9.0-beta01] - 2023-12-13

### Changed

- Compose M3 1.2.0-beta01 / ComposeUi 1.6.0-beta03 / Other version updates

## [1.9.0-alpha13] - 2023-11-20

### Added

- Added ImageScrimBottomUp

### Changed

- Kotlin 1.9.21 / Compose M3 1.2.0-alpha12 / ComposeUi 1.6.0-beta02 / Other version updates
- Gradle 8.5

## [1.9.0-alpha12] - 2023-11-20

### Added

- Added ImageScrim, ImageScrimTopDown, ImageScrimStartEnd, ImageScrimEndStart

### Changed

- Added more options to ImageKitAsset

## [1.9.0-alpha11] - 2023-11-18

### Added

- Added ImagekitUrlBuilder, ImageKitAssetInterceptor to support imagekit.io and coil

### Changed

- Kotlin 1.9.20 / Compose M3 1.2.0-alpha11 / ComposeUi 1.6.0-beta01
- Improved support for UI UseCase by changing showMessageDialog to be used anywhere (not just in ViewModels)
- Added contentDescription and iconTintColor to OverflowMenu

## [1.9.0-alpha10] - 2023-10-14

### Changed

- Renamed FormTextField to FlowTextField 

## [1.9.0-alpha09] - 2023-10-14

### Added

- FlowTextField and PasswordFlowTextField 
- Updated versions
- Updated format of changelog

### Changed

- Better support for error text in DateClickableTextField, TimeClickableTextField, SwitchField
- Deprecated TextFieldDataTextField / TextFieldData (use FlowTextField)

## [1.9.0-alpha08] - 2023-09-28

### Changed

- Changed all NavigationAction classes to "data class"
- Updated versions
- Updated format of changelog
 
Version 1.9.0-alpha07 *(2023-09)*
-------------------------
* Added supportingText to Dialogs and consistency fixes to all dialogs
* Added NavigationAction

Version 1.9.0-alpha06 *(2023-09)*
-------------------------
* Improvements to AppBarIcon Tooltip

Version 1.9.0-alpha05 *(2023-09)*
-------------------------
* Kotlin 1.9.10
* Material3 Compose 1.2.0-alpha07

Version 1.9.0-alpha04 *(2023-08)*
-------------------------
* Added contentWindowInsets to AppScaffoldAndNavigation (passed on to the Scaffold)

Version 1.9.0-alpha03 *(2023-08)*
-------------------------
* Improved support for Compose Navigation Animations when creating routes
* Material3 Compose 1.2.0-alpha06
* Updated other versions

Version 1.9.0-alpha02 *(2023-08)*
-------------------------
* Compose UI 1.5.0
* Material3 Compose 1.2.0-alpha05
* Navigation Compose 2.7.0
* Support DateClickableTextField and TimeClickableTextField without a Flow
* Changed Timber to Kermit Logger

Version 1.9.0-alpha01 *(2023-07)*
-------------------------
* Compose UI 1.5.0-rc01
* Material3 Compose 1.2.0-alpha04
* Navigation Compose 2.7.0-rc01
* Fixes/Changes to support above
* Added SmallTextButton 
* Added ContentAlpha

Version 1.8.2 *(2023-07)*
-------------------------
* Kotlin 1.9.0
* Improved TextHeader to match ListItemTextHeader 

Version 1.8.1 *(2023-07)*
-------------------------
* Added DropdownMenuBoxField without a Flow for selectedOption

Version 1.8.0 *(2023-05)*
-------------------------
* Added popBackStack to navigate(routes: List<String>)
* Improvements to dialogs to better support M3
* Added ProgressIndicatorDialog
* Improvements to ListItemTextHeader
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