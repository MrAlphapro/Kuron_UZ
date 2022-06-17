package uz.ilmhona.kuron_uz.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.messaging.FirebaseMessaging
import uz.ilmhona.kuron_uz.MainActivity
import uz.ilmhona.kuron_uz.R

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    var checkedTheme = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAppTheme()
        setContentView(R.layout.activity_splash)

        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
        if (!sharedPreferences.getBoolean("kuron_uz", false)) {
            FirebaseMessaging.getInstance().subscribeToTopic("kuron_uz")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val edit = sharedPreferences.edit()
                        edit.putBoolean("kuron_uz", true)
                        edit.apply()
                    }
                }
        }

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.

    }

    private fun setAppTheme(themeNo: Int) {
        when (themeNo) {
            0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        val editor = getSharedPreferences("Themes", MODE_PRIVATE).edit()
        editor.putInt("ThemeNo", checkedTheme)
        editor.apply()
    }

    private fun loadAppTheme() {
        val themePreference = getSharedPreferences("Themes", MODE_PRIVATE)
        checkedTheme = themePreference.getInt("ThemeNo", 0)
        setAppTheme(checkedTheme)
    }
}