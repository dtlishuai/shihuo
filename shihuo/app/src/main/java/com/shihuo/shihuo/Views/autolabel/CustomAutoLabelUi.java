package com.shihuo.shihuo.Views.autolabel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.dpizarro.autolabel.library.AutoLabelUI;
import com.dpizarro.autolabel.library.Label;
import com.shihuo.shihuo.R;
import com.shihuo.shihuo.models.SpecificationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cm_qiujiaheng on 2016/12/11.
 */

public class CustomAutoLabelUi extends AutoLabelUI {

    public interface LabelClickListner {
        void onLabelClick(SpecificationModel specificationModel);
    }

    private LabelClickListner labelClickListner;

    private List<SpecificationModel> specificationModels = new ArrayList<>();

    public SpecificationModel getCheckedSpecificationModel() {
        return checkedSpecificationModel;
    }

    private SpecificationModel checkedSpecificationModel;//当前选中的属性

    public CustomAutoLabelUi(Context context) {
        super(context);
    }

    public CustomAutoLabelUi(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAutoLabelUi(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean addLabel(SpecificationModel specificationModel) {
        String labelName = "";
        if (!TextUtils.isEmpty(specificationModel.specName)) {
            labelName = specificationModel.specName;
        }
        if (!TextUtils.isEmpty(specificationModel.circleName)) {
            labelName = specificationModel.circleName;
        }
        if (!TextUtils.isEmpty(specificationModel.storeName)) {
            labelName = specificationModel.storeName;
        }
        boolean success = this.addLabel(labelName);

        specificationModels.add(specificationModel);
        if (specificationModels.size() == 1) {
            checkedSpecificationModel = specificationModel;
            setCheckedLabel(0);
        }
        return success;
    }

    protected void setCheckedLabel(int position) {
        labelArrayList.get(position).setBackgroundRes(R.drawable.autolabel_bg_checked);
    }

    @Override
    public void onClickLabel(Label label) {
        super.onClickLabel(label);
        int position = labelArrayList.indexOf(label);
        label.setBackgroundRes(R.drawable.autolabel_bg_checked);
        if (labelClickListner != null) {
            checkedSpecificationModel = specificationModels.get(position);
            labelClickListner.onLabelClick(checkedSpecificationModel);
        }

    }

    public LabelClickListner getLabelClickListner() {
        return labelClickListner;
    }

    public void setLabelClickListner(LabelClickListner labelClickListner) {
        this.labelClickListner = labelClickListner;
    }

    @Override
    public void clear() {
        super.clear();
        specificationModels.clear();
        labelArrayList.clear();
    }
}

