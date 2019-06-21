package com.stayli.app.utils;

/**
 * Created by  yahuigao
 * Date: 2019/2/22
 * Time: 6:25 PM
 * Description:
 */
public interface PermissionInterface {

    /**
     * 可设置请求权限请求码
     */
    int getPermissionsRequestCode();

    /**
     * 设置需要请求的权限
     */
    String[] getPermissions();

    /**
     * 请求权限成功回调
     */
    void requestPermissionsSuccess();

    /**
     * 请求权限失败回调
     */
    void requestPermissionsFail();
}
