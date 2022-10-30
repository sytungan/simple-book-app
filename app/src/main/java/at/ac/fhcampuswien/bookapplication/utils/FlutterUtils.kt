package at.ac.fhcampuswien.bookapplication.utils

import android.content.Context
import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.android.FlutterActivity

object FlutterUtils {
    const val cachedId = "flutter_module_cached"
    const val initialRoute = ""
    lateinit var flutterEngine: FlutterEngine
    fun startFlutterModule(context: Context) {
        val intentWithCached = FlutterActivity
            .withCachedEngine(cachedId)
            .build(context)
        val intentWithoutCached = FlutterActivity
            .withNewEngine()
            .initialRoute(initialRoute)
            .build(context)

        try {
            context.startActivity(intentWithCached)
        }
        catch (e: Throwable) {
            context.startActivity(intentWithoutCached)
        }
    }
}