package com.example.task_035

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        setSupportActionBar(toolbar)
        title = "Информация о погоде."

        adapter = ViewPageAdapter(this, OnMainFragmentModel.viewPagerList)
        viewPager.adapter = adapter
    }

    fun initUI() {
        toolbar = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain->{
                moveTaskToBack(true);
                exitProcess(-1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}




