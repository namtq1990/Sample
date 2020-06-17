package library.activity.system

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import library.logger.infrastructure.logV

@Suppress("TooManyFunctions")
abstract class LoggingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        log { "onCreate(savedInstanceState=$savedInstanceState)" }
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        log { "onStart()" }
        super.onStart()
    }

    override fun onRestart() {
        log { "onRestart()" }
        super.onRestart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        log { "onRestoreInstanceState(savedInstanceState=$savedInstanceState)" }
        super.onRestoreInstanceState(savedInstanceState)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        log { "onActivityResult(requestCode=$requestCode, resultCode=$resultCode, data=$data)" }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onNewIntent(intent: Intent) {
        log { "onNewIntent(intent=$intent)" }
        super.onNewIntent(intent)
    }

    override fun onResume() {
        log { "onResume()" }
        super.onResume()
    }

    override fun onPostResume() {
        log { "onPostResume()" }
        super.onPostResume()
    }

    override fun onPause() {
        log { "onPause()" }
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        log { "onSaveInstanceState(outState=$outState)" }
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        log { "onStop()" }
        super.onStop()
    }

    override fun onDestroy() {
        log { "onDestroy()" }
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        log {
            "onRequestPermissionsResult(" +
                "requestCode=$requestCode, " +
                "permissions=${permissions.contentToString()}, " +
                "grantResults=${grantResults.contentToString()}" +
                ")"
        }
    }

    override fun startActivity(intent: Intent?) {
        log { "startActivity(intent=$intent)" }
        super.startActivity(intent)
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        log { "startActivity(intent=$intent, options=$options)" }
        super.startActivity(intent, options)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        log { "startActivityForResult(intent=$intent, requestCode=$requestCode)" }
        super.startActivityForResult(intent, requestCode)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        log { "startActivityForResult(intent=$intent, requestCode=$requestCode, options=$options)" }
        super.startActivityForResult(intent, requestCode, options)
    }

    @Suppress("MagicNumber")
    private fun log(message: () -> String) {
        logV("${javaClass.simpleName}@${hashCode().toString(16)}#${message()}")
    }
}
