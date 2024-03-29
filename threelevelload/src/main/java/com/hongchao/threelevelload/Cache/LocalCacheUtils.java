package com.hongchao.threelevelload.Cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//三级缓存之本地缓存
public class LocalCacheUtils {
    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/WerbNews";
    /**
     * 从本地读取图片
      */
    public Bitmap getBitmapFromLocal(String url){
        String fileName = null;//把图片的url当做文件名，并进行md5加密
        try{
            fileName =url;
            File file = new File(CACHE_PATH,fileName);
            Bitmap bitmap = BitmapFactory.decodeStream((new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从网络获取图片后，保存至本地缓存
     */
    public void setBitmapToLocal(String url,Bitmap bitmap){
        try{
            String fileName = url;
            File file = new File(CACHE_PATH,fileName);
            //通过得到文件的父文件，判断父文件是否存在
            File parentFile = file.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            //吧图片保存至本地
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
