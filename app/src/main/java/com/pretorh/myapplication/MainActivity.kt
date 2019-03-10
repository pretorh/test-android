package com.pretorh.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.pretorh.myapplication.persistence.DbBackupHelper
import kotlinx.android.synthetic.main.activity_main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Suppress("ProtectedInFinal")
    @Inject
    protected lateinit var dbBackupHelper: DbBackupHelper

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).injector.inject(this)
        setContentView(R.layout.activity_main_activity)
        setupActionBar()
        setupNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.restore_backup) {
            dbBackupHelper.restore()
            return true
        }
        if (item.itemId == R.id.create_backup) {
            dbBackupHelper.backup()
            return true
        }
        return useTheNavControllerToNavigateBasedOnMenuItemId(item) || super.onOptionsItemSelected(item)
    }

    private fun useTheNavControllerToNavigateBasedOnMenuItemId(item: MenuItem): Boolean {
        val navController = findNavController()
        return item.onNavDestinationSelected(navController)
    }

    private fun setupActionBar() {
        val navController = findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupNavigation() {
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            textActiveNav.text = destination.label
        }
    }

    private fun findNavController() = findNavController(R.id.fragment3)
}
