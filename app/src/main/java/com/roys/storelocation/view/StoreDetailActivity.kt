package com.roys.storelocation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.roys.storelocation.util.Constants
import com.roys.storelocation.databinding.StoreDetailActivityBinding
import com.roys.storelocation.model.StoreEntity
import com.roys.storelocation.viewmodel.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreDetailActivity: AppCompatActivity() {
    private lateinit var viewModel : StoreViewModel
    private lateinit var binding: StoreDetailActivityBinding
    private var imageUri:Uri? = null
    private var lat:Double? = null
    private var lng:Double? = null

    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val uri = Uri.parse(it.data?.getStringExtra(Constants.IMAGE_CAPTURED))
            Glide.with(this)
                .load(uri)
                .into(binding.sheetImage)
            imageUri = uri
            lat = it.data?.getDoubleExtra(Constants.USER_LOCATION_LAT, 0.0)
            lng = it.data?.getDoubleExtra(Constants.USER_LOCATION_LNG, 0.0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StoreDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[StoreViewModel::class.java]

        initView()
    }

    private fun initView(){
        with(binding){
            takePhoto.setOnClickListener {
                val intent = Intent(this@StoreDetailActivity, CameraActivity::class.java)
                activityResultLauncher.launch(intent)
            }
            btnSubmit.setOnClickListener {
                val storeEntity = lat?.let { it1 ->
                    lng?.let { it2 ->
                        StoreEntity(null, sheetName.text.toString(),
                            it1, it2, it1, it2, imageUri.toString())
                    }
                }
                if (storeEntity != null) {
                    viewModel.insert(storeEntity)
                    finish()
                }
            }
        }
    }
}