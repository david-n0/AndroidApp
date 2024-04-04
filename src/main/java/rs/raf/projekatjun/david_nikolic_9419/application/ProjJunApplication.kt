package rs.raf.projekatjun.david_nikolic_9419.application


import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import rs.raf.projekat2.david_nikolic_9419.modules.coreModule
import rs.raf.projekat2.david_nikolic_9419.modules.recipeModule
import timber.log.Timber

class ProjJunApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            recipeModule
        )
        startKoin {
            androidLogger()
            // Use application context
            androidContext(this@ProjJunApplication)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

}