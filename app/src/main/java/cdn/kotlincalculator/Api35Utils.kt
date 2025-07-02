package cdn.kotlincalculator

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController

class Api35Utils {
    companion object {
        fun applySystemBarInsets(
            activity: Activity,
            keepStatusBar: Boolean = true,
            keepNavigationBar: Boolean = true,
            darkStatusBarIcon: Boolean? = null,
            systemBarColor: Int? = null,
        ) {

            val targetSdkVersion = activity.packageManager.getPackageInfo(activity.packageName, 0).applicationInfo?.targetSdkVersion ?: -1

            if (Build.VERSION.SDK_INT >= 35 && targetSdkVersion >= 35) {

                val window = activity.window

                window.decorView.setOnApplyWindowInsetsListener { view, insets ->

                    val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
                    val navigationBarInsets = insets.getInsets(WindowInsets.Type.navigationBars())

                    if(keepStatusBar){

                        // Keep top statusBar's height
                            view.setPadding(
                                view.paddingLeft,
                                statusBarInsets.top,
                                view.paddingRight,
                                view.paddingBottom
                            )

                        // Set statusBar icon color
                        if(darkStatusBarIcon == null){
                            // Do nothing
                        } else if (darkStatusBarIcon == true) {
                            //  ->  dark
                            window.insetsController!!.setSystemBarsAppearance(
                                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                            )
                        } else if(darkStatusBarIcon == false) {
                            //  ->  light
                            window.insetsController!!.setSystemBarsAppearance(
                                0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                            )
                        }
                    }

                    if(keepNavigationBar){

                        // Keep bottom navigationBar's height
                            view.setPadding(
                                view.paddingLeft,
                                view.paddingTop,
                                view.paddingRight,
                                navigationBarInsets.bottom
                            )
                    }

                    // Global background color
                    if(systemBarColor != null){
                        view.setBackgroundColor(systemBarColor)
                    }

                    insets
                }
            }
        }
    }
}