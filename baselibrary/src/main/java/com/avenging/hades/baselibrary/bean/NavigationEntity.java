package com.avenging.hades.baselibrary.bean;

/**
 * Created by zhanghehe on 2017/6/25.
 */

public class NavigationEntity extends BaseEntity {

    private int iconResId;

    public NavigationEntity(String id, String name,int iconResId) {
        super(id, name);
        this.iconResId=iconResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
