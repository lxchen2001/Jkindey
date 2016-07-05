package com.liji.jkidney.activity.check.Niaodanbai;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.M_Check_niaodanbai;
import com.liji.jkidney.model.user.MyUser;
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

import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 修改记录
 */
@ContentView(R.layout.activity_check_niaodanbai_update)
public class ActCheckNiaodanbaiUpdate extends ActBase {

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
    M_Check_niaodanbai check_niaodanbai;
    MyUser user = new MyUser();
    @Override
    protected void initView(Bundle savedInstanceState) {

        user = (MyUser)this.getIntent().getSerializableExtra("user");
        if (user == null) {
            user = User.getCurrentUser(this);
        }
        check_niaodanbai = (M_Check_niaodanbai) this.getIntent().getSerializableExtra("check");
        time = check_niaodanbai.getTime();
        niaoliang = check_niaodanbai.getValueNiaoliang().toString();
        danbai = check_niaodanbai.getValueDanbai().toString();
        headview.setTitle("修改记录");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        tvTime.setText("" + time);
        etType1.setText("" + (niaoliang));
        etType2.setText("" + (danbai));


        //修改
        headview.setRightImgAction(R.drawable.my_edit_selector, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                doUpdate();
            }


        });

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


    //修改
    private void doUpdate() {

        setValue();
        M_Check_niaodanbai update = new M_Check_niaodanbai();
        update.setAuthor(user);
        update.setTime(time);
        update.setValueDanbai(Double.valueOf(danbai));
        update.setValueNiaoliang(Double.valueOf(niaoliang));
        update.update(ActCheckNiaodanbaiUpdate.this, "" + check_niaodanbai.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActCheckNiaodanbaiUpdate.this, "修改成功！");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActCheckNiaodanbaiUpdate.this, "修改失败：" + s);
            }
        });

    }

    private void setValue() {
        if (TextUtils.isEmpty(time)) {
            JToastUtils.showToast(ActCheckNiaodanbaiUpdate.this, "请输入检查时间");
            return;
        }

        //24小时总尿量
        niaoliang = ((etType1.getText().toString().trim()));
        if (TextUtils.isEmpty(niaoliang)) {
            JToastUtils.showToast(ActCheckNiaodanbaiUpdate.this, "请输入24小时总尿量");
            return;
        }

        //24小时尿蛋白总量
        danbai = etType2.getText().toString().trim();
        if (TextUtils.isEmpty(danbai)) {
            JToastUtils.showToast(ActCheckNiaodanbaiUpdate.this, "请输入24小时尿蛋白总量");
            return;
        }

    }

    //删除
    private void doDelete() {
        M_Check_niaodanbai delete = new M_Check_niaodanbai();
        delete.remove("author");
        delete.delete(ActCheckNiaodanbaiUpdate.this, "" + check_niaodanbai.getObjectId(), new DeleteListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActCheckNiaodanbaiUpdate.this, "删除成功！");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActCheckNiaodanbaiUpdate.this, "删除失败：" + s);
            }
        });

    }
}
