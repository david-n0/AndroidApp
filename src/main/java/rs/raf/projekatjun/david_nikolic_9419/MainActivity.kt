package rs.raf.projekatjun.david_nikolic_9419

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekatjun.david_nikolic_9419.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}