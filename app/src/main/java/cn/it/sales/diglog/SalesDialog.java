package cn.it.sales.diglog;


import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.ball.SalesJiLuManager;
import cn.it.sales.bean.JiLuXiaoShou;
import cn.it.sales.bean.ShangPin;

/**
 * Created by Administrator on 2016/5/18.
 */
public class SalesDialog {

    //剥离
    //即使view界面不能见，此方法也能够调用

    private Context mContext;//上下文
    private ShangPin mShangPin;//商品列表
    private JiLuXiaoShou mJiLuXiaoShou;//记录销售列表
    AlertDialog mAlertDialog;
    private Button mSure, mCancel;
    private List<ShangPin> mListShangPin;
    private TextView mTextShangPin, mTextJiaGe, mTextZheKou, mTextKuChun;//dialog上用于显示信息的文本组件
    private EditText mEditShuLiang;//dialog上编辑出售数量的组件
    private String mShangPinMingCheng;//商品名称
    private int mKuChunShuLiang;//库存数量
    private int mJiaGe;//商品价格
    private int mZheKou;//折扣
    private int mChuShouShuLiang;//出售数量

    private int mPosition;

    private SalesJiLuManager.OnSalesListener mSalesListener;


    public SalesDialog(){

    }

    /***
     * 调用构造，创建对象
     * @param context
     * @param position
     * @param shangpin
     * @param jiLuXiaoShou
     * @param listener
     */
    public SalesDialog(Context context,int position,ShangPin shangpin,JiLuXiaoShou jiLuXiaoShou,SalesJiLuManager.OnSalesListener listener){
        mPosition=position;
        mShangPin=shangpin;
        mJiLuXiaoShou = jiLuXiaoShou;
        mContext = context;
        mSalesListener = listener;
    }

    /***
     * 显示对话框，处理结果在回调中触发
     */
    public void show(){

        AlertDialog.Builder builder= new AlertDialog.Builder(mContext);
        builder.setTitle("销售记录");
        builder.setIcon(null);
        View view = LayoutInflater.from(mContext).inflate(R.layout.sales_dialog,null);
        builder.setView(view);

        initView(view);
        initButtonEvent(view);
        mAlertDialog = builder.create();
        mAlertDialog.show();


    }

    private void initButtonEvent(View view) {
        mSure = ((Button) view.findViewById(R.id.bt_sure));
        mCancel = (Button) view.findViewById(R.id.bt_cancel);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.bt_sure){
                    //获取界面输入，添加到mJiLuXiaoShou
                    int kuCunShuLiang= mShangPin.getKuCunShuLiang();

                    mJiLuXiaoShou.setXiaoShouJiage(mJiaGe);
                    // 获取输入框的内容

                   String edit =  mEditShuLiang.getText().toString();
                    // 判断输入框的内容是否为空
                    if(!TextUtils.isEmpty(edit)) {
                        mChuShouShuLiang = Integer.parseInt(edit);

                    }else {
                        Toast.makeText(mContext,"确认出售数量",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    mJiLuXiaoShou.setXiaoShouShuLiang(mChuShouShuLiang);

                    SalesJiLuManager manager = new SalesJiLuManager();
                  //  getInputToJiLuXiaoShou();
                    //触发activity传递的监听器

                    if (mSalesListener!=null && kuCunShuLiang >= mChuShouShuLiang){
                      // manager.getSalesNumber(mChuShouShuLiang,mShangPin,mJiLuXiaoShou);

                       mSalesListener.onSelectSales(mPosition);
                        //关闭dialog 对话框
                        mAlertDialog.dismiss();

                    }else{
                        Toast.makeText(mContext, "库存量不足请重新选择", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (mSalesListener!=null){

                        mSalesListener.onSelectCancle();
                        //关闭dialog 对话框
                        mAlertDialog.dismiss();
                    }
                }

            }
        };

        mCancel.setOnClickListener(listener);
        mSure.setOnClickListener(listener);
    }

//    private void getInputToJiLuXiaoShou() {
//
//    }

    private void initView(View view) {

        mTextShangPin = ((TextView) view.findViewById(R.id.text_mingcheng));//名称组件
        mShangPinMingCheng = mShangPin.getMingCheng();
        mTextShangPin.setText("商品名称" + mShangPinMingCheng);

        //显示价格
        mTextJiaGe = ((TextView) view.findViewById(R.id.text_jiage));
        mJiaGe = mShangPin.getLingShouJia();
        mTextJiaGe.setText("价格" + mJiaGe);
        //库存
        mTextKuChun = ((TextView) view.findViewById(R.id.text_kuchun));
        mKuChunShuLiang = mShangPin.getKuCunShuLiang();
        mTextKuChun.setText("库存" + mKuChunShuLiang);
        //显示折扣
        mTextZheKou = ((TextView) view.findViewById(R.id.text_zhekou));
        mZheKou = mShangPin.getZheKeLv();
        mTextZheKou.setText("折扣" + mZheKou);
        //得到出售数量

        mEditShuLiang = ((EditText) view.findViewById(R.id.edit_jianshu));
        mEditShuLiang.setText(""+1);
    }

}
