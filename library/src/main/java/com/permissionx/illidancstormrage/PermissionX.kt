package com.permissionx.illidancstormrage

import androidx.fragment.app.FragmentActivity

/**
 * 对外接口
 */
object PermissionX {
    private const val TAG = "PermissionX"

    fun request(
        activity: FragmentActivity, //传入context，请求所在activity，继承FragmentActivity
        vararg permissions: String, //权限参数
        callback: PermissionCallback //请求回调
    ) {
        //获取 invisibleFragment 隐藏碎片对象

        //碎片管理器
        val fragmentManager = activity.supportFragmentManager
        //获取隐藏的frag(InvisibleFragment) - byTAG
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            //InvisibleFragment 已创建
            existedFragment as InvisibleFragment
        } else {
            //InvisibleFragment 未创建
            // new 对象
            val invisibleFragment = InvisibleFragment()
            // 碎片管理器开启事务：添加以(TAG = "PermissionX")标签的碎片
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            //返回对象
            invisibleFragment
        }

        //调用api
        //调用 请求权限的方法
        fragment.requestNow(callback, *permissions)
    }
}