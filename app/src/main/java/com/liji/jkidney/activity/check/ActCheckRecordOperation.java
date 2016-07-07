package com.liji.jkidney.activity.check;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.CheckTypeId;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.MCheckType;
import com.liji.jkidney.model.check.MCheckTypeDetail;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JTimeUtils;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.app.ThemeManager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 新增一个尿蛋白定量检测数据
 */
@ContentView(R.layout.activity_act_check_niaodanbai_add)
public class ActCheckRecordOperation extends ActBase {


    public static String INTENT_USER = "user";
    public static String INTENT_CHECK = "check";
    public static String INTENT_TYPE = "type";
    /**
     * 区分是添加记录还是修改记录
     */
    public static String INTENT_ISADD = "isAddRecord";

    /**
     * 肾功能
     **/
    @ViewInject(R.id.tv_shengongneng_time)
    TextView tvShengongnengTime;
    @ViewInject(R.id.ll_shengongneng_time)
    LinearLayout llShengongnengTime;
    @ViewInject(R.id.et_shengongneng_type1)
    EditText etShengongnengType1;
    @ViewInject(R.id.et_shengongneng_type2)
    EditText etShengongnengType2;
    @ViewInject(R.id.et_shengongneng_type3)
    EditText etShengongnengType3;
    @ViewInject(R.id.et_shengongneng_type4)
    EditText etShengongnengType4;
    @ViewInject(R.id.et_shengongneng_type5)
    EditText etShengongnengType5;
    @ViewInject(R.id.et_shengongneng_type6)
    EditText etShengongnengType6;
    @ViewInject(R.id.et_shengongneng_type7)
    EditText etShengongnengType7;
    @ViewInject(R.id.et_shengongneng_type8)
    EditText etShengongnengType8;
    @ViewInject(R.id.et_shengongneng_type9)
    EditText etShengongnengType9;
    @ViewInject(R.id.ll_shengongneng)
    LinearLayout llShengongneng;
    String niaosudan = "";
    String jigan = "";
    String niaosuan = "";
    String s_zongdanbai = "";
    String s_baidanbai = "";
    String s_qiudanbai = "";
    String s_baiqiudanbaibi = "";
    String s_zongdangucun = "";
    String guangyisuC = "";


    @ViewInject(R.id.tv_tizhong_time)
    TextView tvTizhongTime;
    @ViewInject(R.id.ll_tizhong_time)
    LinearLayout llTizhongTime;
    @ViewInject(R.id.et_tizhong_type1)
    EditText etTizhongType1;
    @ViewInject(R.id.ll_tizhong)
    LinearLayout llTizhong;
    String tizhong = "";

    @ViewInject(R.id.tv_xuetang_time)
    TextView tvXuetangTime;
    @ViewInject(R.id.ll_xuetang_time)
    LinearLayout llXuetangTime;
    @ViewInject(R.id.et_xuetang_type1)
    EditText etXuetangType1;
    @ViewInject(R.id.ll_xuetang)
    LinearLayout llXuetang;
    String xuetang = "";


    @ViewInject(R.id.tv_xueya_time)
    TextView tvXueyaTime;
    @ViewInject(R.id.ll_xueya_time)
    LinearLayout llXueyaTime;
    @ViewInject(R.id.et_xueya_type1)
    EditText etXueyaType1;
    @ViewInject(R.id.et_xueya_type2)
    EditText etXueyaType2;
    @ViewInject(R.id.ll_xueya)
    LinearLayout llXueya;
    String shuzhangya;
    String shousuoya;

    /**
     * 检查种类
     */
    private int type = CheckTypeId.Gangongneng;

    /**
     * 是否是添加记录还是修改记录
     */
    private boolean isAddRecord = true;
    MCheckType checkData = new MCheckType();

    @ViewInject(R.id.headview)
    CustomeHeadView headview;

    /**
     * 尿蛋白
     **/
    @ViewInject(R.id.ll_niaodanbai)
    LinearLayout llNiaodanbai;
    @ViewInject(R.id.ll_niaodanbai_time)
    LinearLayout llNiaodanbaiTime;
    @ViewInject(R.id.et_niaodanbai_type1)
    EditText etNiaodanbaiType1;
    @ViewInject(R.id.et_niaodanbai_type2)
    EditText etNiaodanbaiType2;
    @ViewInject(R.id.tv_niaodanbai_time)
    TextView tvNiaodanbaiTime;


    String time;
    String niaoliang;
    String danbai;


    Dialog.Builder builder = null;
    boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
    MyUser user = new MyUser();
    /**
     * 肝功能
     **/
    @ViewInject(R.id.tv_gangongneng_time)
    TextView tvGangongnengTime;
    @ViewInject(R.id.ll_gangongneng_time)
    LinearLayout llGangongnengTime;
    @ViewInject(R.id.et_gangongneng_type1)
    EditText etGangongnengType1;
    @ViewInject(R.id.et_gangongneng_type2)
    EditText etGangongnengType2;
    @ViewInject(R.id.et_gangongneng_type3)
    EditText etGangongnengType3;
    @ViewInject(R.id.et_gangongneng_type4)
    EditText etGangongnengType4;
    @ViewInject(R.id.et_gangongneng_type5)
    EditText etGangongnengType5;
    @ViewInject(R.id.et_gangongneng_type6)
    EditText etGangongnengType6;
    @ViewInject(R.id.et_gangongneng_type7)
    EditText etGangongnengType7;
    @ViewInject(R.id.et_gangongneng_type8)
    EditText etGangongnengType8;
    @ViewInject(R.id.et_gangongneng_type9)
    EditText etGangongnengType9;
    @ViewInject(R.id.et_gangongneng_type10)
    EditText etGangongnengType10;
    @ViewInject(R.id.et_gangongneng_type11)
    EditText etGangongnengType11;
    @ViewInject(R.id.et_gangongneng_type12)
    EditText etGangongnengType12;
    @ViewInject(R.id.et_gangongneng_type13)
    EditText etGangongnengType13;
    @ViewInject(R.id.et_gangongneng_type14)
    EditText etGangongnengType14;
    @ViewInject(R.id.ll_gangongneng)
    LinearLayout llGangongneng;
    String gubingzhuananmei = "";
    String gucaozhuananmei = "";
    String guanxianzhuantaimei = "";
    String jianxinglinsuanmei = "";
    String zongdanhongsu = "";
    String zhijiedanhongsu = "";
    String jianjiedanhongsu = "";
    String zongdanzhisuan = "";
    String qianbaidanbai = "";
    String zongdanbai = "";
    String baidanbai = "";
    String qiudanbai = "";
    String baiqiudanbaibi = "";
    String zongdangucun = "";


    @Override
    protected void initView(Bundle savedInstanceState) {

        user = (MyUser) this.getIntent().getSerializableExtra(INTENT_USER);
        if (user == null) {
            user = User.getCurrentUser(this);
        }
        checkData = (MCheckType) this.getIntent().getSerializableExtra(INTENT_CHECK);
        type = this.getIntent().getIntExtra(INTENT_TYPE, CheckTypeId.Gangongneng);
        isAddRecord = this.getIntent().getBooleanExtra(INTENT_ISADD, true);


        //检查时间
        time = JTimeUtils.getCurrentTime();
        showCheckTypeView(time);


        if (isAddRecord) {
            headview.setTitle("添加记录");
        } else {
            headview.setTitle("修改记录");
            setDefaultData(checkData);

            //删除
            headview.setRightImgAction2(R.drawable.ic_delete, new XCallbackListener() {
                @Override
                protected void callback(Object... obj) {
                    builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog) {
                        @Override
                        public void onPositiveActionClicked(DialogFragment fragment) {
                            doDelete();
                            super.onPositiveActionClicked(fragment);
                        }

                        @Override
                        public void onNegativeActionClicked(DialogFragment fragment) {
                            super.onNegativeActionClicked(fragment);
                        }
                    };

                    ((SimpleDialog.Builder) builder).message("是否删除该条记录？")
                            .positiveAction("删除")
                            .negativeAction("取消");
                    DialogFragment fragment = DialogFragment.newInstance(builder);
                    fragment.show(getSupportFragmentManager(), null);
                }
            });
        }


        //返回
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });


        //保存
        headview.setRightImgAction(R.drawable.ic_confirm, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                doSubmit();
            }

        });


        llGangongnengTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
        llNiaodanbaiTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
        llShengongnengTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
        llXueya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
        llXuetang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
        llTizhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });

    }

    /**
     * 删除该条记录
     */
    private void doDelete() {
        MCheckType danbai = new MCheckType();
        danbai.remove("author");
        danbai.delete(ActCheckRecordOperation.this, "" + checkData.getObjectId(), new DeleteListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActCheckRecordOperation.this, "删除成功！");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActCheckRecordOperation.this, "删除失败：" + s);
            }
        });
    }

    /**
     * 显示何种检查对应的view
     */
    private void showCheckTypeView(String time) {
        defaultGone();
        switch (type) {
            case CheckTypeId.Gangongneng://肝功能
                llGangongneng.setVisibility(View.VISIBLE);
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                llShengongneng.setVisibility(View.VISIBLE);
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                llNiaodanbai.setVisibility(View.VISIBLE);
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                break;
            case CheckTypeId.Tizhong:// 体重
                llTizhong.setVisibility(View.VISIBLE);
                break;
            case CheckTypeId.Xuetang:// 血糖
                llXuetang.setVisibility(View.VISIBLE);
                break;
            case CheckTypeId.Xueya:// 血压
                llXueya.setVisibility(View.VISIBLE);
                break;
        }
        showCheckTime(time);

    }

    /**
     * 显示自动获取时间
     */
    private void showCheckTime(String time) {
        switch (type) {
            case CheckTypeId.Gangongneng://肝功能
                tvGangongnengTime.setText(time + "");
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                tvShengongnengTime.setText("" + time);
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                tvNiaodanbaiTime.setText(time + "");
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                break;
            case CheckTypeId.Tizhong:// 体重
                tvTizhongTime.setText("" + time);
                break;
            case CheckTypeId.Xuetang:// 血糖
                tvXuetangTime.setText("" + time);
                break;
            case CheckTypeId.Xueya:// 血压
                tvXueyaTime.setText("" + time);
                break;
        }

    }

    private void defaultGone() {
        llNiaodanbai.setVisibility(View.GONE);
        llGangongneng.setVisibility(View.GONE);
        llShengongneng.setVisibility(View.GONE);
        llTizhong.setVisibility(View.GONE);
        llXueya.setVisibility(View.GONE);
        llXuetang.setVisibility(View.GONE);
    }

    /**
     * 选择时间
     *
     * @return
     */
    public void setTime() {

        builder = new DatePickerDialog.Builder(isLightTheme ? R.style.Material_App_Dialog_DatePicker_Light : R.style.Material_App_Dialog_DatePicker) {
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                DatePickerDialog dialog = (DatePickerDialog) fragment.getDialog();
                time = (dialog.getFormattedDate(new SimpleDateFormat("yyyy-MM-dd")));
                showCheckTime(time);
                super.onPositiveActionClicked(fragment);
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                super.onNegativeActionClicked(fragment);
            }
        };
        builder.positiveAction("确定")
                .negativeAction("取消");
        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(), null);

    }


    private void doSubmit() {
        MCheckType checkType = new MCheckType();
        List<MCheckTypeDetail> mCheckTypeDetailList = new ArrayList<>();

        switch (type) {
            case CheckTypeId.Gangongneng://肝功能

                String[] typeName = new String[]{
                        getResources().getString(R.string.jianchashijian),
                        getResources().getString(R.string.gubingzhuananmei),
                        getResources().getString(R.string.gucaozhuananmei),
                        getResources().getString(R.string.guanxianzhuantaimei),
                        getResources().getString(R.string.jianxinglinsuanmei),
                        getResources().getString(R.string.zongdanhongsu),
                        getResources().getString(R.string.zhijiedanhongsu),
                        getResources().getString(R.string.jianjiedanhongsu),
                        getResources().getString(R.string.zongdanzhisuan),
                        getResources().getString(R.string.qianbaidanbai),
                        getResources().getString(R.string.zongdanbai),
                        getResources().getString(R.string.baidanbai),
                        getResources().getString(R.string.qiudanbai),
                        getResources().getString(R.string.baiqiudanbaibi),
                        getResources().getString(R.string.zongdangucun)

                };


                if (TextUtils.isEmpty(time)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[0]);
                    return;
                }
                gubingzhuananmei = etGangongnengType1.getText().toString().trim();
                if (TextUtils.isEmpty(gubingzhuananmei)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[1]);
                    return;
                }

                gucaozhuananmei = etGangongnengType2.getText().toString().trim();
                if (TextUtils.isEmpty(gucaozhuananmei)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[2]);
                    return;
                }

                guanxianzhuantaimei = etGangongnengType3.getText().toString().trim();
                if (TextUtils.isEmpty(guanxianzhuantaimei)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[3]);
                    return;
                }
                jianxinglinsuanmei = etGangongnengType4.getText().toString().trim();
                if (TextUtils.isEmpty(jianxinglinsuanmei)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[4]);
                    return;
                }
                zongdanhongsu = etGangongnengType5.getText().toString().trim();
                if (TextUtils.isEmpty(zongdanhongsu)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[5]);
                    return;
                }
                zhijiedanhongsu = etGangongnengType6.getText().toString().trim();
                if (TextUtils.isEmpty(zhijiedanhongsu)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[6]);
                    return;
                }
                jianjiedanhongsu = etGangongnengType7.getText().toString().trim();
                if (TextUtils.isEmpty(jianjiedanhongsu)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[7]);
                    return;
                }
                zongdanzhisuan = etGangongnengType8.getText().toString().trim();
                if (TextUtils.isEmpty(zongdanzhisuan)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[8]);
                    return;
                }
                qianbaidanbai = etGangongnengType9.getText().toString().trim();
                if (TextUtils.isEmpty(qianbaidanbai)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[9]);
                    return;
                }
                zongdanbai = etGangongnengType10.getText().toString().trim();
                if (TextUtils.isEmpty(zongdanbai)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[10]);
                    return;
                }
                baidanbai = etGangongnengType11.getText().toString().trim();
                if (TextUtils.isEmpty(baidanbai)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[11]);
                    return;
                }
                qiudanbai = etGangongnengType12.getText().toString().trim();
                if (TextUtils.isEmpty(qiudanbai)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[12]);
                    return;
                }
                baiqiudanbaibi = etGangongnengType13.getText().toString().trim();
                if (TextUtils.isEmpty(baiqiudanbaibi)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[13]);
                    return;
                }
                zongdangucun = etGangongnengType14.getText().toString().trim();
                if (TextUtils.isEmpty(zongdangucun)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeName[14]);
                    return;
                }

                String[] typeValye = new String[]{
                        gubingzhuananmei,
                        gucaozhuananmei,
                        guanxianzhuantaimei,
                        jianxinglinsuanmei,
                        zongdanhongsu,
                        zhijiedanhongsu,
                        jianjiedanhongsu,
                        zongdanzhisuan,
                        qianbaidanbai,
                        zongdanbai,
                        baidanbai,
                        qiudanbai,
                        baiqiudanbaibi,
                        zongdangucun};
                for (int i = 0; i < typeValye.length; i++) {
                    MCheckTypeDetail checkGangongneng = new MCheckTypeDetail();
                    checkGangongneng.setTime(time);
                    checkGangongneng.setType(typeName[i + 1]);
                    checkGangongneng.setValue(Double.valueOf(typeValye[i]));
                    mCheckTypeDetailList.add(checkGangongneng);
                }


                break;
            case CheckTypeId.Shengongneng:// 肾功能
                String[] typeShenName = new String[]{
                        getResources().getString(R.string.jianchashijian),
                        getResources().getString(R.string.niaosudan),
                        getResources().getString(R.string.jigan),
                        getResources().getString(R.string.zongdanbai),
                        getResources().getString(R.string.baidanbai),
                        getResources().getString(R.string.qiudanbai),
                        getResources().getString(R.string.baiqiudanbaibi),
                        getResources().getString(R.string.zongdangucun),
                        getResources().getString(R.string.guangyisuC),
                        getResources().getString(R.string.niaosuan)
                };


                if (TextUtils.isEmpty(time)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[0]);
                    return;
                }
                niaosudan = etShengongnengType1.getText().toString().trim();
                if (TextUtils.isEmpty(niaosudan)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[1]);
                    return;
                }

                jigan = etShengongnengType2.getText().toString().trim();
                if (TextUtils.isEmpty(jigan)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[2]);
                    return;
                }

                s_zongdanbai = etShengongnengType3.getText().toString().trim();
                if (TextUtils.isEmpty(s_zongdanbai)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[3]);
                    return;
                }
                s_baidanbai = etShengongnengType4.getText().toString().trim();
                if (TextUtils.isEmpty(s_baidanbai)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[4]);
                    return;
                }
                s_qiudanbai = etShengongnengType5.getText().toString().trim();
                if (TextUtils.isEmpty(s_qiudanbai)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[5]);
                    return;
                }
                s_baiqiudanbaibi = etShengongnengType6.getText().toString().trim();
                if (TextUtils.isEmpty(s_baiqiudanbaibi)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[6]);
                    return;
                }
                s_zongdangucun = etShengongnengType7.getText().toString().trim();
                if (TextUtils.isEmpty(s_zongdangucun)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[7]);
                    return;
                }
                guangyisuC = etShengongnengType8.getText().toString().trim();
                if (TextUtils.isEmpty(guangyisuC)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[8]);
                    return;
                }
                niaosuan = etShengongnengType9.getText().toString().trim();
                if (TextUtils.isEmpty(niaosuan)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeShenName[9]);
                    return;
                }


                String[] typeShenValye = new String[]{
                        niaosudan,
                        jigan,
                        s_zongdanbai,
                        s_baidanbai,
                        s_qiudanbai,
                        s_baiqiudanbaibi,
                        s_zongdangucun,
                        guangyisuC,
                        niaosuan};
                for (int i = 0; i < typeShenValye.length; i++) {
                    MCheckTypeDetail checkGangongneng = new MCheckTypeDetail();
                    checkGangongneng.setTime(time);
                    checkGangongneng.setType(typeShenName[i + 1]);
                    checkGangongneng.setValue(Double.valueOf(typeShenValye[i]));
                    mCheckTypeDetailList.add(checkGangongneng);
                }

                break;

            case CheckTypeId.Niaodanbai:// 尿蛋白

                String[] typeNiaodanbaiName = new String[]{
                        getResources().getString(R.string.jianchashijian),
                        getResources().getString(R.string.niaoliang),
                        getResources().getString(R.string.niaodanbaidingling)

                };


                if (TextUtils.isEmpty(time)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeNiaodanbaiName[0]);
                    return;
                }
                //24小时总尿量
                niaoliang = etNiaodanbaiType1.getText().toString().trim();
                if (TextUtils.isEmpty(niaoliang)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeNiaodanbaiName[1]);
                    return;
                }
                //24小时尿蛋白总量
                danbai = etNiaodanbaiType2.getText().toString().trim();
                if (TextUtils.isEmpty(danbai)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeNiaodanbaiName[2]);
                    return;
                }

                String[] typeNiaodanbaiValye = new String[]{
                        niaoliang,
                        danbai
                };

                for (int i = 0; i < typeNiaodanbaiValye.length; i++) {
                    //24小时总尿量
                    MCheckTypeDetail checkNiaodanbai = new MCheckTypeDetail();
                    checkNiaodanbai.setTime(time);
                    checkNiaodanbai.setType(typeNiaodanbaiName[i + 1]);
                    checkNiaodanbai.setValue(Double.valueOf(typeNiaodanbaiValye[i]));
                    mCheckTypeDetailList.add(checkNiaodanbai);
                }

                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                break;
            case CheckTypeId.Tizhong:// 体重
                String[] typeTizhongName = new String[]{
                        getResources().getString(R.string.jianchashijian),
                        getResources().getString(R.string.tizhong)
                };
                tizhong = etTizhongType1.getText().toString().trim();
                if (TextUtils.isEmpty(tizhong)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeTizhongName[1]);
                    return;
                }
                MCheckTypeDetail checkNiaodanbai = new MCheckTypeDetail();
                checkNiaodanbai.setTime(time);
                checkNiaodanbai.setType(typeTizhongName[1]);
                checkNiaodanbai.setValue(Double.valueOf(tizhong));
                mCheckTypeDetailList.add(checkNiaodanbai);
                break;
            case CheckTypeId.Xuetang:// 血糖
                String[] typeXuetangName = new String[]{
                        getResources().getString(R.string.jianchashijian),
                        getResources().getString(R.string.xuetang)
                };
                xuetang = etXuetangType1.getText().toString().trim();
                if (TextUtils.isEmpty(xuetang)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeXuetangName[1]);
                    return;
                }
                MCheckTypeDetail xuetangC = new MCheckTypeDetail();
                xuetangC.setTime(time);
                xuetangC.setType(typeXuetangName[1]);
                xuetangC.setValue(Double.valueOf(xuetang));
                mCheckTypeDetailList.add(xuetangC);
                break;
            case CheckTypeId.Xueya:// 血压
                String[] typeXueyaName = new String[]{
                        getResources().getString(R.string.jianchashijian),
                        getResources().getString(R.string.shousuoya),
                        getResources().getString(R.string.shuzhangya)

                };


                if (TextUtils.isEmpty(time)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeXueyaName[0]);
                    return;
                }
                //24小时总尿量
                shousuoya = etXueyaType1.getText().toString().trim();
                if (TextUtils.isEmpty(shousuoya)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeXueyaName[1]);
                    return;
                }
                //24小时尿蛋白总量
                shuzhangya = etXueyaType2.getText().toString().trim();
                if (TextUtils.isEmpty(shuzhangya)) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "请输入" + typeXueyaName[2]);
                    return;
                }

                String[] typeXueyaValye = new String[]{
                        shousuoya,
                        shuzhangya
                };

                for (int i = 0; i < typeXueyaValye.length; i++) {
                    //24小时总尿量
                    MCheckTypeDetail xueya = new MCheckTypeDetail();
                    xueya.setTime(time);
                    xueya.setType(typeXueyaName[i + 1]);
                    xueya.setValue(Double.valueOf(typeXueyaValye[i]));
                    mCheckTypeDetailList.add(xueya);
                }

                break;
        }

        checkType.setType(type);
        checkType.setAuthor(user);
        checkType.setList(mCheckTypeDetailList);

        if (isAddRecord) {
            checkType.save(ActCheckRecordOperation.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "导入成功！");
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "导入失败：" + s);
                }
            });
        } else {
            checkType.update(ActCheckRecordOperation.this, "" + checkData.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "修改成功！");
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    JToastUtils.showToast(ActCheckRecordOperation.this, "修改失败：" + s);
                }
            });
        }

    }

    /**
     * 修改记录，默认数据
     *
     * @param defaultData
     */
    public void setDefaultData(MCheckType defaultData) {
        switch (type) {
            case CheckTypeId.Gangongneng://肝功能
                tvGangongnengTime.setText("" + defaultData.getList().get(0).getTime());
                etGangongnengType1.setText("" + defaultData.getList().get(0).getValue());
                etGangongnengType2.setText("" + defaultData.getList().get(1).getValue());
                etGangongnengType3.setText("" + defaultData.getList().get(2).getValue());
                etGangongnengType4.setText("" + defaultData.getList().get(3).getValue());
                etGangongnengType5.setText("" + defaultData.getList().get(4).getValue());
                etGangongnengType6.setText("" + defaultData.getList().get(5).getValue());
                etGangongnengType7.setText("" + defaultData.getList().get(6).getValue());
                etGangongnengType8.setText("" + defaultData.getList().get(7).getValue());
                etGangongnengType9.setText("" + defaultData.getList().get(8).getValue());
                etGangongnengType10.setText("" + defaultData.getList().get(9).getValue());
                etGangongnengType11.setText("" + defaultData.getList().get(10).getValue());
                etGangongnengType12.setText("" + defaultData.getList().get(11).getValue());
                etGangongnengType13.setText("" + defaultData.getList().get(12).getValue());
                etGangongnengType14.setText("" + defaultData.getList().get(13).getValue());
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                tvShengongnengTime.setText("" + defaultData.getList().get(0).getTime());
                etShengongnengType1.setText("" + defaultData.getList().get(0).getValue());
                etShengongnengType2.setText("" + defaultData.getList().get(1).getValue());
                etShengongnengType3.setText("" + defaultData.getList().get(2).getValue());
                etShengongnengType4.setText("" + defaultData.getList().get(3).getValue());
                etShengongnengType5.setText("" + defaultData.getList().get(4).getValue());
                etShengongnengType6.setText("" + defaultData.getList().get(5).getValue());
                etShengongnengType7.setText("" + defaultData.getList().get(6).getValue());
                etShengongnengType8.setText("" + defaultData.getList().get(7).getValue());
                etShengongnengType9.setText("" + defaultData.getList().get(8).getValue());
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                tvNiaodanbaiTime.setText("" + defaultData.getList().get(0).getTime());
                etNiaodanbaiType1.setText("" + defaultData.getList().get(0).getValue());
                etNiaodanbaiType2.setText("" + defaultData.getList().get(1).getValue());
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                break;
            case CheckTypeId.Tizhong:// 体重
                tvTizhongTime.setText("" + defaultData.getList().get(0).getTime());
                etTizhongType1.setText("" + defaultData.getList().get(0).getValue());
                break;
            case CheckTypeId.Xuetang:// 血糖
                tvXuetangTime.setText("" + defaultData.getList().get(0).getTime());
                etXuetangType1.setText("" + defaultData.getList().get(0).getValue());
                break;
            case CheckTypeId.Xueya:// 血压
                tvXueyaTime.setText("" + defaultData.getList().get(0).getTime());
                etXueyaType1.setText("" + defaultData.getList().get(0).getValue());
                etXueyaType2.setText("" + defaultData.getList().get(1).getValue());
                break;
        }

    }

}
