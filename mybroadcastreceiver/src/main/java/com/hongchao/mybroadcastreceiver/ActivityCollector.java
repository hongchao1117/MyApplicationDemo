package com.hongchao.mybroadcastreceiver;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

//用来管理所有的activity
public class ActivityCollector {
    public static List<Activity> list = new ArrayList<>();

    //添加activity至容器中
    public static void addActivity(Activity activity){
        list.add(activity);
    }
    //删除容器中的activity
    public static void removeActivity(Activity activity){
        list.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:list){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        list.clear();
    }


}
