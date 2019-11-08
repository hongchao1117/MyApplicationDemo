package com.hongchao.threelevelload.Cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

//三级缓存至网络缓存
public class NetCacheUtils {
    private LocalCacheUtils localCacheUtils;
    private MemoryCacheUtils memoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils mLocalCacheUtils, MemoryCacheUtils mMemoryCacheUtils) {
        localCacheUtils = new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
    }

    /**
     * 从网络下载图片
     * ivPic 显示图片的imageView
     * url 下载图片的网络的网络地址
     */
    public void getBitmapFromNet(ImageView ivPic,String url){
        new BitmapTask().execute(ivPic,url);//启动AsyncTask
    }

    private class BitmapTask extends AsyncTask<Object,Void,Bitmap> {
        private ImageView ivPic;
        private String url;

        //好事操作，存在于子线程当中
        @Override
        protected Bitmap doInBackground(Object[] objects) {
            ivPic = (ImageView) objects[0];
            url = (String) objects[1];
            return downLoadBitmap(url);
        }

        //网络下载图片
        private Bitmap downLoadBitmap(String url) {
            HttpURLConnection connection = null;
            try{
                connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setConnectTimeout(2000);
                connection.setReadTimeout(2000);
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                if (responseCode==200){
                    //图片压缩
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;//宽高压缩为原来的1/2
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream(),null,options);
                    return bitmap;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                connection.disconnect();
            }
            return null;
        }
    }
}
