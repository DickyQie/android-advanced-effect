package com.lidong.photopicker.intent;

import android.content.Context;
import android.content.Intent;

import com.lidong.photopicker.ImageConfig;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.SelectModel;

import java.util.ArrayList;

/**
 * 选择照片
 */
public class PhotoPickerIntent extends Intent{

    public PhotoPickerIntent(Context packageContext) {
        super(packageContext, PhotoPickerActivity.class);
    }

    public void setShowCarema(boolean bool){
        this.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, bool);
    }

    public void setMaxTotal(int total){
        this.putExtra(PhotoPickerActivity.EXTRA_SELECT_COUNT, total);
    }

    /**
     * 选择
     * @param model
     */
    public void setSelectModel(SelectModel model){
        this.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, Integer.parseInt(model.toString()));
    }

    /**
     * 已选择的照片地址
     * @param imagePathis
     */
    public void setSelectedPaths(ArrayList<String> imagePathis){
        this.putStringArrayListExtra(PhotoPickerActivity.EXTRA_DEFAULT_SELECTED_LIST, imagePathis);
    }

    /**
     * 显示相册图片的属性
     * @param config
     */
    public void setImageConfig(ImageConfig config){
        this.putExtra(PhotoPickerActivity.EXTRA_IMAGE_CONFIG, config);
    }
}
