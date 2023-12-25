# Slippy is a Bottom Bar Library on Android based Jetpack Compose!

## How To Install?

**Step 1.** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://jitpack.io") }
    }
}
```

**Step 2.** Add the dependency

```
dependencies {
	implementation("com.github.furkanayaz:Slippy-Bottom-Bar:1.4")
}
```

***For example to use***

```
SlippyBottomBar(theme = SlippyTheme.LINE, bar = SlippyBar(
        backgroundColor = R.color.white, textStyle = SlippyTextStyle(
            textSize = R.dimen.textSize,
            enabledTextColor = R.color.enabledTextColor,
            disabledTextColor = R.color.disabledTextColor
        ), iconStyle = SlippyIconStyle(
            iconSize = R.dimen.iconSize,
            disabledIconColor = R.color.disabledIconColor,
            enabledIconColor = R.color.enabledIconColor, // When the round style is chosen, it should be white in color.
        ), dividerStyle = SlippyDividerStyle(
            dividerColor = R.color.dividerColor
        )
    ), tabs = listOf(SlippyTab(name = R.string.home, icon = R.drawable.home, action = {
        
    }), SlippyTab(name = R.string.search, icon = R.drawable.search, action = {
        
    }), SlippyTab(name = R.string.record, icon = R.drawable.record, action = {
        
    }), SlippyTab(name = R.string.records, icon = R.drawable.records, action = {
        
    }), SlippyTab(name = R.string.settings, icon = R.drawable.settings, action = {
        
})))
```

25 Dec, 2023 - Furkan Ayaz
