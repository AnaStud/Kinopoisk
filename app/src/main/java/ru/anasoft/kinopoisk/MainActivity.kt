package ru.anasoft.kinopoisk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.anasoft.kinopoisk.databinding.ActivityMainBinding
import ru.anasoft.kinopoisk.view.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }
}