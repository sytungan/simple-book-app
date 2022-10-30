package at.ac.fhcampuswien.bookapplication

import android.app.Application
import at.ac.fhcampuswien.bookapplication.utils.FlutterUtils
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Instantiate a FlutterEngine.
        FlutterUtils.flutterEngine = FlutterEngine(this)
        FlutterUtils.flutterEngine.run {
            // Configure an initial route.
            navigationChannel.setInitialRoute(FlutterUtils.initialRoute)
            // Start executing Dart code to pre-warm the FlutterEngine.
            dartExecutor.executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )
            // Cache the FlutterEngine to be used by FlutterActivity or FlutterFragment.
            FlutterEngineCache
                .getInstance()
                .put(FlutterUtils.cachedId, this)
        }
    }
}