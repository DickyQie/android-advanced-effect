package com.lidong.photopicker;

/**
 * 照片选择类型
 * Created by foamtrace on 2015/8/25.
 */
public enum SelectModel {
    SINGLE(PhotoPickerActivity.MODE_SINGLE),
    MULTI(PhotoPickerActivity.MODE_MULTI);

    private int model;

    SelectModel(int model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.valueOf(this.model);
    }
}
