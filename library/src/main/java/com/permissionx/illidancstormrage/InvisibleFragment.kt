package com.permissionx.illidancstormrage

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * 隐藏Fragment类
 *
 * 封装运行时权限API
 */
class InvisibleFragment : Fragment() {


    private var callback: PermissionCallback? = null
    //  (布尔,权限字符串集合)->返回空

    fun requestNow(
        cb: PermissionCallback,//
        vararg permissions: String          //可变参数：权限集合
    ) {
        callback = cb //保存传入的回调函数类型
        requestPermissions(permissions, 1)
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.onRequestPermissionsResult(requestCode, permissions, grantResults)",
            "androidx.fragment.app.Fragment"
        )
    )
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            //deniedList = 请求失败权限列表 strList
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                // Iterable<IndexedValue<Int>> IndexedValue类(index，value)
                if (result != PackageManager.PERMISSION_GRANTED) {
                    //如果不成功就添加到失败列表中
                    deniedList.add(permissions[index])
                }
            }
            //如果都请求成功，那么返回true
            val allGranted = deniedList.isEmpty()
            //回调结果
            callback?.let { it(allGranted,deniedList) }
        }
    }
}

//回调函数类型
typealias PermissionCallback = (Boolean, List<String>) -> Unit



