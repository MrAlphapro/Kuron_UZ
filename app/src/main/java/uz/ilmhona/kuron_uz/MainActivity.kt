package uz.ilmhona.kuron_uz

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView
import uz.ilmhona.kuron_uz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var checkedTheme = 0
    lateinit var mAdView : AdView

    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAppTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        title = resources.getString(R.string.app_name)

        sharedPreferences = getSharedPreferences("SAMPLE_PREFERENCE", MODE_PRIVATE);
        editor = sharedPreferences?.edit();

//        binding.appBarMain.night.setOnClickListener {
//            val dialog = Dialog(this)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            if (dialog.window != null) {
//                val colorDrawable = ColorDrawable(Color.TRANSPARENT)
//                dialog.window!!.setBackgroundDrawable(colorDrawable)
//            }
//            dialog.setContentView(R.layout.uploaded_dialog)
//            dialog.setCancelable(false)
//            dialog.findViewById<TextView>(R.id.progress).text = "resources.getString(R.string.SMScodeConfirmed)"
//            dialog.findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
//                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
////                    updateView(progress + i)
//                    dialog.findViewById<TextView>(R.id.progress).textSize = (progress + i).toFloat()
//                    editor?.putInt("FONT_SIZE", i)
//                    editor?.commit()
//                }
//
//                override fun onStartTrackingTouch(seekBar: SeekBar) {}
//                override fun onStopTrackingTouch(seekBar: SeekBar) {}
//            })
//            dialog.findViewById<View>(R.id.ok).setOnClickListener {
//                dialog.dismiss()
//            }
//            val size = sharedPreferences?.getInt("FONT_SIZE", 0)
//            dialog.findViewById<SeekBar>(R.id.seekBar).progress = size!!
//            dialog.show()
//        }
        
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
//                startActivity(Intent(this, SettingActivity::class.java))
                showChangeThemeAlertDialog()
            }
            R.id.namoz -> {
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=uz.muslim.men_ham_namoz_uq")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=uz.muslim.men_ham_namoz_uq")
                        )
                    )
                }
            }
//            R.id.info -> {
//                val adb = AlertDialog.Builder(this)
//                adb.setTitle(resources.getString(R.string.app_name))
//                adb.setMessage("Версия ${BuildConfig.VERSION_NAME}"
////                            Email: sabrvahidoyat@gmail.com
//                )
//                adb.setIcon(R.drawable.ic_outline_info)
//                adb.setPositiveButton(
//                    "Ok"
//                ) { dialogInterface, i -> dialogInterface.cancel() }
//                adb.create()
//                adb.show()
//            }
            R.id.like -> {
                val apppPackageName =
                    packageName // getPackageName() from Context or Activity object

                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$apppPackageName")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$apppPackageName")
                        )
                    )
                }

            }
            R.id.share -> {
                val sharingintent = Intent(Intent.ACTION_SEND)
                val appPackageName = packageName
                sharingintent.type = "text/plain"
                sharingintent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                sharingintent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=$appPackageName"
                )
                startActivity(Intent.createChooser(sharingintent, "Share via"))
            }
            R.id.apps -> {
                //            case R.id.other:
                try {
                    startActivity(
                        Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("market://dev?id=8352354729490482056")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/dev?id=8352354729490482056")
                        )
                    )
                }
            }
        }
        return true
    }

    private fun showChangeThemeAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.subject))
        builder.setSingleChoiceItems(
            resources.getStringArray(R.array.subjectArrays),
            checkedTheme
        ) { dialog, which ->
            checkedTheme = which
            when (which) {
                0 -> setAppTheme(0)
                1 -> setAppTheme(1)
                else -> setAppTheme(2)
            }
            dialog.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun loadAppTheme() {
        val themePreference = getSharedPreferences("Themes", MODE_PRIVATE)
        checkedTheme = themePreference.getInt("ThemeNo", 0)
        setAppTheme(checkedTheme)
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

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}