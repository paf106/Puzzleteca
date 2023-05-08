package com.pablo.puzzleteca.ui.view

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.pablo.puzzleteca.R
import com.pablo.puzzleteca.data.model.Puzzle
import com.pablo.puzzleteca.databinding.ActivityAddEditPuzzleBinding
import com.pablo.puzzleteca.ui.view.components.utils.DatePickerFragment
import com.pablo.puzzleteca.ui.view.components.utils.TimePickerFragment
import com.pablo.puzzleteca.ui.viewmodel.AddPuzzleViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddEditPuzzleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditPuzzleBinding
    private val addPuzzleViewModel: AddPuzzleViewModel by viewModels()
    val askForPicture =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            binding.ivPhoto.setImageBitmap(bitmap)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditPuzzleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Nuevo puzzle"
        setupUI()
    }

    fun setupUI() {
        binding.tfHechoEnContent.setOnClickListener { showTimePickerDialog() }
        binding.tfFechaCompraContent.setOnClickListener { showDatePickerDialog() }
        binding.ivPhoto.setOnClickListener { askForPicture.launch() }
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelected(time: String) = binding.tfHechoEnContent.setText(time)


    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }

        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) =
        binding.tfFechaCompraContent.setText("$day/$month/$year")

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_edit_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun bitmapToFile(bitmap: Bitmap): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val a = binding.ivPhoto.drawable as BitmapDrawable

        when (item.itemId) {

            R.id.mnuSave -> {
                val puzzle = Puzzle(
                    binding.tfNameContent.text.toString(),
                    bitmapToFile(a.bitmap).toString(),
                    binding.tfMarcaContent.text.toString(),
                    binding.tfHechoEnContent.text.toString(),
                    binding.tfComentarioContent.text.toString(),
                    binding.tfPrestadoAContent.text.toString(),
                    binding.chkPrestado.isSelected,
                    binding.tfPiezasContent.text.toString().toInt(),
                    binding.tfFechaCompraContent.text.toString(),
                    binding.tfNReferenciaContent.text.toString().toInt(),
                    binding.tfColeccionContent.text.toString(),
                    binding.tfLugarCompraContent.text.toString(),
                    false
                )
                addPuzzleViewModel.addPuzzle(puzzle)
                Toast.makeText(this, bitmapToFile(a.bitmap).toString(), Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}