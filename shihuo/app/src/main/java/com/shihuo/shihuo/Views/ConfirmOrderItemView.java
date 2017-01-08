package com.shihuo.shihuo.Views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shihuo.shihuo.Activities.OrderDetailActivity;
import com.shihuo.shihuo.R;
import com.shihuo.shihuo.application.Contants;
import com.shihuo.shihuo.models.GoodsDetailModel;
import com.shihuo.shihuo.models.OrderModel;
import com.shihuo.shihuo.util.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cm_qiujiaheng on 2017/1/2.
 * 订单确认里面的 订单展示view
 */

public class ConfirmOrderItemView extends LinearLayout {

    public interface OnItemClickListener {
        /**
         * 评论按钮点击
         *
         * @param orderModel
         */
        void onEvaluate(OrderModel orderModel);

        /**
         * 退货按钮点击
         *
         * @param orderModel
         */
        void onBack(OrderModel orderModel);
    }

    private OnItemClickListener mOnItemClickListener;

    @BindView(R.id.imageView)
    SimpleDraweeView imageView;
    @BindView(R.id.text_order_title)
    TextView textOrderTitle;
    @BindView(R.id.text_order_spec)
    TextView textOrderSpec;
    @BindView(R.id.text_order_size)
    TextView textOrderSize;
    @BindView(R.id.text_item_price)
    TextView textItemPrice;
    @BindView(R.id.text_item_number)
    TextView textItemNumber;
    @BindView(R.id.text_price)
    TextView textPrice;
    @BindView(R.id.btn_evaluate)
    TextView btnEvaluate;
    @BindView(R.id.layout_total_price)
    LinearLayout layoutTotalPrice;
    @BindView(R.id.btn_back)
    TextView btnBack;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.layout_address_detail)
    LinearLayout layoutAddressDetail;

    private int mFromType;

    private OrderModel mOrderModel;

    public ConfirmOrderItemView(Context context) {
        super(context);
        initView();
    }

    public ConfirmOrderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ConfirmOrderItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.confirm_order_item_view, null);
        ButterKnife.bind(this, view);
        addView(view);
    }

    /**
     * 用户的订单详情里面展示调用这个方法(因为使用的模型不一样)
     *
     * @param orderDetail
     */
    public void setOrderDetail(GoodsDetailModel orderDetail) {
        if (orderDetail != null) {
            if (orderDetail.picUrl != null) {
                imageView.setImageURI(AppUtils.parse(Contants.IMAGE_URL
                        + orderDetail.picUrl));
            } else if (orderDetail.goodsPicsList != null) {
                imageView.setImageURI(AppUtils.parse(Contants.IMAGE_URL
                        + orderDetail.goodsPicsList.get(0).picUrl));
            }
            textOrderTitle.setText(orderDetail.goodsName);
            textOrderSpec.setText(orderDetail.specName);
            textItemPrice.setText("￥" + String.valueOf(orderDetail.curPrice));
            textItemNumber.setText(String.format("x %1$s", orderDetail.amount));
            textPrice.setText(String.format("￥%1$s", orderDetail.curPrice * orderDetail.amount));
        }
    }

    /**
     * 商铺的订单详情调用这个函数设置view(因为使用的模型不一样)
     *
     * @param orderData
     * @param fromType  是用户订单详情还是商铺订单详情
     */
    public void setOrderData(OrderModel orderData, int fromType) {
        mFromType = fromType;
        if (orderData != null) {
            mOrderModel = orderData;
            if (orderData.picUrl != null) {
                imageView.setImageURI(AppUtils.parse(Contants.IMAGE_URL
                        + orderData.picUrl));
            }
            textOrderTitle.setText(orderData.goodsName);
            textOrderSpec.setText(orderData.specName);
            textItemPrice.setText("￥" + String.valueOf(orderData.goodsPrice));
            textItemNumber.setText(String.format("x %1$s", orderData.goodsNum));
            textPrice.setText(String.format("￥%1$s", orderData.goodsPrice * orderData.goodsAmount));
            layoutTotalPrice.setVisibility(GONE);
            if (mFromType == OrderDetailActivity.ORDER_FROM_USER) {
                setStatusByUser(orderData);
            } else {
                setStatusByShop(orderData);
            }
        }
    }


    /**
     * 根据用户订单状态设置界面
     */
    private void setStatusByUser(OrderModel mOrderModel) {
        switch (mOrderModel.status) {
            case OrderModel.ORDER_STATUS_UNSHIP:
//                textOrderStatus.setText("待发货");
                btnBack.setVisibility(VISIBLE);//申请退货
                break;
            case OrderModel.ORDER_STATUS_SHIPED:
//                textOrderStatus.setText("待收货");
                btnBack.setVisibility(VISIBLE);//申请退货
                break;
            case OrderModel.ORDER_STATUS_COMPLETED:
//                textOrderStatus.setText("已完成");
                if (TextUtils.isEmpty(mOrderModel.score)) {//显示评价按钮
                    btnEvaluate.setVisibility(VISIBLE);
                } else {
                    ratingbar.setVisibility(VISIBLE);
                    ratingbar.setRating(Float.parseFloat(mOrderModel.score));//显示评价星级别
                }
                break;
            case OrderModel.ORDER_STATUS_BACK:
//                textOrderStatus.setText("退货中");
                break;
            case OrderModel.ORDER_STATUS_BACKED:
//                textOrderStatus.setText("已退货");
                if (TextUtils.isEmpty(mOrderModel.score)) {//显示评价按钮
                    btnEvaluate.setVisibility(VISIBLE);
                } else {
                    ratingbar.setVisibility(VISIBLE);
                    ratingbar.setRating(Float.parseFloat(mOrderModel.score));//显示评价星级别
                }
                break;
            case OrderModel.ORDER_STATUS_PROCESSING:
//                textOrderStatus.setText("处理中");
                break;
            case OrderModel.ORDER_STATUS_CLOSED:
//                textOrderStatus.setText("已关闭");
                btnEvaluate.setVisibility(VISIBLE);
                break;
            default:
                break;
        }
    }

    /**
     * 根据商铺订单状态设置界面
     */
    private void setStatusByShop(OrderModel mOrderModel) {
        switch (mOrderModel.status) {
            case OrderModel.ORDER_STATUS_UNSHIP:
//                textOrderStatus.setText("待发货");
                break;
            case OrderModel.ORDER_STATUS_SHIPED:
//                textOrderStatus.setText("待收货");
                break;
            case OrderModel.ORDER_STATUS_COMPLETED:
//                textOrderStatus.setText("已完成");
                if (TextUtils.isEmpty(mOrderModel.score)) {
                    btnEvaluate.setVisibility(VISIBLE);
                } else {
                    ratingbar.setVisibility(VISIBLE);
                    ratingbar.setRating(Float.parseFloat(mOrderModel.score));//显示评价星级别
                }
                break;
            case OrderModel.ORDER_STATUS_BACK:
//                textOrderStatus.setText("退货中");
                break;
            case OrderModel.ORDER_STATUS_BACKED:
//                textOrderStatus.setText("已退货");
                break;
            case OrderModel.ORDER_STATUS_PROCESSING:
//                textOrderStatus.setText("处理中");
                break;
            case OrderModel.ORDER_STATUS_CLOSED:
//                textOrderStatus.setText("已关闭");
                break;
            default:
                break;
        }
    }


    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @OnClick({R.id.btn_evaluate, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_evaluate://评价
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onEvaluate(mOrderModel);
                }
                break;
            case R.id.btn_back://申请退货
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onBack(mOrderModel);
                }
                break;
        }
    }
}