package de.syntax_institut.musikApp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import de.syntax_institut.musikApp.data.Datasource
import de.syntax_institut.musikApp.databinding.DetailActivityBinding

/**
 * Dies ist die Klasse für das zugehörige Layout detail_activity
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding

    /**
     * Die lifecycle methode onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)

        // Die Informationen werden aus dem intent Objekt geholt
        // TODO Schreibe hier deinen Code
        val imageResource = intent.extras?.getInt("imageResource", 0)
        // Die Informationen werden aus dem intent Objekt geholt
        // TODO Schreibe hier deinen Code
        val stringResource = intent.getIntExtra("stringResource", 0)
        // Die Informationen werden zugewiesen (nach Test auf null)
        // TODO Schreibe hier deinen Code
        if (imageResource != null) {
            binding.ivCoverDetail.setImageResource(imageResource)
        }
        binding.tvTitleDetail.setText(stringResource)
        // Share Button
        binding.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Hör dir mal den Song an!")
            intent.type = "text/plain"
            val intent2 = Intent.createChooser(intent, null)
            startActivity(intent2)
        }
        // Next-Button
        binding.ibNext.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Hör dir mal den Song an!")
            intent.type = "text/plain"
            val intent2 = Intent.createChooser(intent, null)
            startActivity(intent2)
        }
        binding.ibPrevious.setOnClickListener {
            changeSong()
        }
        binding.ibNext.setOnClickListener {
            changeSong()
        }
    }
    private fun changeSong() {
       val intentSong = Intent(this,DetailActivity::class.java)
       val saveSong = Datasource(this).loadSongs().random()
       intentSong.putExtra("imageResource",saveSong.imageResource)
       intentSong.putExtra("stringResource",saveSong.stringResource)
        startActivity(intentSong)
        finish()
    }
}