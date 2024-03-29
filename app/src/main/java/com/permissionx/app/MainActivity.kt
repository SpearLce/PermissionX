package com.permissionx.app

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permissionx.illidancstormrage.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionX.request(
            this,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//可变参数
        ) { allGranted, deniedList ->
            if (allGranted) {
                call()
            } else {
                Toast.makeText(this, "拒绝了 $deniedList", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}