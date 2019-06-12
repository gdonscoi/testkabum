package br.com.gdonscoi.testkabum.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import br.com.gdonscoi.testkabum.R
import kotlinx.android.synthetic.main.container_appbar_layout.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_view, ListaProdutosFragment())
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}
