package by.anyatsal.chefsboutique.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.data.Recipe
import by.anyatsal.chefsboutique.utils.Constants
import by.anyatsal.chefsboutique.utils.Utils.showMessageIfEmpty
import kotlinx.android.synthetic.main.activity_create_recipe.*
import kotlinx.android.synthetic.main.activity_search.bottom_app_bar

class CreateRecipeActivity : BaseActivity() {

    private val TAKE_PHOTO = 2
    private val GALLERY = 3

    companion object {
        const val EXTRA_REPLY = "REPLY"
        lateinit var category: String

        fun getLaunchIntent(from: Context) = Intent(from, CreateRecipeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        setSupportActionBar(bottom_app_bar)
        bottom_app_bar.setNavigationOnClickListener(this)
        btn_save.setOnClickListener(this)
        img_create_recipe.setOnClickListener(this)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Constants.categories
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        create_recipe_category.adapter = adapter

        create_recipe_category.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    category = create_recipe_category.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_save -> {
                if (!ifEmpty())
                    loadToDB()
                else
                    showMessageIfEmpty(v)
            }
            img_create_recipe -> {
                showPhotoAlert()
            }
            else ->
                onBackPressed()
        }
    }

    private fun loadToDB() {
        val replyIntent = Intent()
        val recipe = Recipe(
            name = create_recipe_name.text.toString(),
            description = create_recipe_description.text.toString(),
            category = category,
            imageRes = img_create_recipe.toString(),
            ingredients = create_recipe_ingredients.text.toString()
        )
        replyIntent.putExtra(EXTRA_REPLY, recipe)
        setResult(Activity.RESULT_OK, replyIntent)
        finish()
    }

    private fun ifEmpty() =
        create_recipe_name.text.isNullOrEmpty() && create_recipe_description.text.isNullOrEmpty() && create_recipe_ingredients.text.isNullOrEmpty()

    private fun showPhotoAlert() {
        val selectItems = arrayOf(
            resources.getString(R.string.take_photo),
            resources.getString(R.string.choose_from_gallery),
            resources.getString(R.string.cancel)
        )
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setItems(selectItems) { dialog, which ->
            when (which) {
                0 -> dispatchTakePictureIntent()
                1 -> pickImageFromGallery()
                2 -> dialog.cancel()
            }
        }
        alertDialog.show()
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, TAKE_PHOTO)
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY)
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 1) {
            fm.popBackStack()
        } else {
            startActivity(MainActivity.getLaunchIntent(this))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            img_create_recipe.setImageBitmap(imageBitmap)
        } else if (requestCode == GALLERY && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            val imageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
            img_create_recipe.setImageBitmap(imageBitmap)
        }
    }
}