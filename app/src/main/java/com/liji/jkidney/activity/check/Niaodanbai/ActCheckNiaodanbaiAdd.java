package com.liji.jkidney.activity.check.Niaodanbai;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.MCheckTypeNiaodanbai;
import com.liji.jkidney.model.check.MCheckTypeNiaodanbaiDetail;
import com.liji.jkidney.model.check.M_Check_niaodanbai;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JTimeUtils;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.ThemeManager;
import com.rey.material.app.TimePickerDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.SaveListener;

/**
 * 新增一个尿蛋白定量检测数据
 */
@ContentView(R.layout.activity_act_check_niaodanbai_add)
public class ActCheckNiaodanbaiAdd extends ActBase {


    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.ll_time)
    LinearLayout llTime;
    @ViewInject(R.id.et_type1)
    EditText etType1;
    @ViewInject(R.id.et_type2)
    EditText etType2;
    @ViewInject(R.id.tv_time)
    TextView tvTime;


    String time;
    String niaoliang;
    String danbai;

    Dialog.Builder builder = null;
    boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;

    MyUser user = new MyUser();

    @Override
    protected void initView(Bundle savedInstanceState) {

        user = (MyUser) this.getIntent().getSerializableExtra("user");
        if (user == null) {
            user = User.getCurrentUser(this);
        }
        headview.setTitle("导入记录");
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


        //检查时间
        time = JTimeUtils.getCurrentTime();
        tvTime.setText(time + "");

        llTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });

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
                tvTime.setText(time + "");
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

        if (TextUtils.isEmpty(time)) {
            JToastUtils.showToast(ActCheckNiaodanbaiAdd.this, "请输入检查时间");
            return;
        }

        //24小时总尿量
        niaoliang = etType1.getText().toString().trim();
        if (TextUtils.isEmpty(niaoliang)) {
            JToastUtils.showToast(ActCheckNiaodanbaiAdd.this, "请输入24小时总尿量");
            return;
        }

        //24小时尿蛋白总量
        danbai = etType2.getText().toString().trim();
        if (TextUtils.isEmpty(danbai)) {
            JToastUtils.showToast(ActCheckNiaodanbaiAdd.this, "请输入24小时尿蛋白总量");
            return;
        }

        MCheckTypeNiaodanbai checkTypeNiaodanbai = new MCheckTypeNiaodanbai();

        List<MCheckTypeNiaodanbaiDetail> mCheckTypeNiaodanbaiDetailList = new ArrayList<>();

        //24小时总尿量
        MCheckTypeNiaodanbaiDetail niaoliangC = new MCheckTypeNiaodanbaiDetail();
        niaoliangC.setTime(time);
        niaoliangC.setType("24小时总尿量");
        niaoliangC.setValue(Double.valueOf(niaoliang));
        mCheckTypeNiaodanbaiDetailList.add(niaoliangC);

        //24小时尿蛋白总量
        MCheckTypeNiaodanbaiDetail danbaiC = new MCheckTypeNiaodanbaiDetail();
        danbaiC.setTime(time);
        danbaiC.setType("24小时尿蛋白总量");
        danbaiC.setValue(Double.valueOf(danbai));
        mCheckTypeNiaodanbaiDetailList.add(danbaiC);

        checkTypeNiaodanbai.setAuthor(user);
        checkTypeNiaodanbai.setList(mCheckTypeNiaodanbaiDetailList);


        checkTypeNiaodanbai.save(ActCheckNiaodanbaiAdd.this, new SaveListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActCheckNiaodanbaiAdd.this, "导入成功！");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActCheckNiaodanbaiAdd.this, "导入失败：" + s);
            }
        });


    }


}
