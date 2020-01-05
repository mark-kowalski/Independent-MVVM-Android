package dev.kowalski.independentmvvm

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import dev.kowalski.independentmvvm.view.search.SearchFragment

class MainActivity : AppCompatActivity(), SearchFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.nav_host)
            .setGraph(R.navigation.nav_graph, intent.extras)
    }

    override fun onSupportNavigateUp() = findNavController(this, R.id.nav_host).navigateUp()

    companion object {

        // must be the same as defined in nav_graph.xml
        private const val HAS_CONNECTION = "has_connection"

        fun start(activity: Activity, hasConnection: Boolean) {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra(HAS_CONNECTION, hasConnection)

            activity.startActivity(intent)
            activity.finish()
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
