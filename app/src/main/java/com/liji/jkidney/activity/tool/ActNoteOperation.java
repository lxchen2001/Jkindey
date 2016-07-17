package com.liji.jkidney.activity.tool;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.Type;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.tool.MNote;
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

import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 记事本操作
 */
@ContentView(R.layout.activity_note_operation)
public class ActNoteOperation extends ActBase {


    public static final String INTENT_TYPE = "type";
    public static final String INTENT_Data = "data";
    @ViewInject(R.id.item_title)
    EditText itemTitle;
    @ViewInject(R.id.tv_time)
    TextView tvTime;
    @ViewInject(R.id.ll_time)
    LinearLayout llTime;
    @ViewInject(R.id.item_content)
    EditText itemContent;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    private int type = Type.Note_Add;
    Dialog.Builder builder = null;
    boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
    MyUser user = new MyUser();
    MNote mNote = new MNote();
    private String time = "";

    @Override
    protected void initView(Bundle savedInstanceState) {
        user = User.getCurrentUser(ActNoteOperation.this);
        type = this.getIntent().getIntExtra(INTENT_TYPE, Type.Note_Add);
        mNote = (MNote) this.getIntent().getSerializableExtra(INTENT_Data);
        //检查时间
        time = JTimeUtils.getCurrentTime();
        if (type == Type.Note_Add) {
            headView.setTitle("新增笔记");
            tvTime.setText("" + time);
        } else {
            setData(mNote);
            headView.setTitle("修改笔记");
            //删除
            headView.setRightImgAction2(R.drawable.ic_delete, new XCallbackListener() {
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

                    ((SimpleDialog.Builder) builder).message("是否删除该条笔记？")
                            .positiveAction("删除")
                            .negativeAction("取消");
                    DialogFragment fragment = DialogFragment.newInstance(builder);
                    fragment.show(getSupportFragmentManager(), null);
                }
            });
        }

        //保存
        headView.setRightImgAction(R.drawable.ic_confirm, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                doSubmit();
            }

        });


        //返回
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        llTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });

    }

    private void setData(MNote note) {
        itemTitle.setText("" + note.getTitle());
        itemContent.setText("" + note.getContent());
        tvTime.setText("" + note.getTime());
    }

    /**
     * 保存笔记（新增和修改保存）
     */
    private void doSubmit() {

        MNote note = new MNote();
        note.setAuthor(user);
        note.setTime(time);
        if (TextUtils.isEmpty(itemTitle.getText().toString().trim())) {
            JToastUtils.showToast(ActNoteOperation.this, "请输入标题");
            return;
        }
        note.setTitle(itemTitle.getText().toString().trim());

        if (TextUtils.isEmpty(itemContent.getText().toString().trim())) {
            JToastUtils.showToast(ActNoteOperation.this, "请输入内容");
            return;
        }
        note.setContent(itemContent.getText().toString().trim());

        if (type == Type.Note_Add) {
            note.save(ActNoteOperation.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    JToastUtils.showToast(ActNoteOperation.this, "添加成功");
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    JToastUtils.showToast(ActNoteOperation.this, "添加失败：" + s);
                }
            });

        } else {
            note.update(ActNoteOperation.this, "" + mNote.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    JToastUtils.showToast(ActNoteOperation.this, "修改成功");
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    JToastUtils.showToast(ActNoteOperation.this, "修改失败：" + s);
                }
            });

        }

    }

    /**
     * 修改笔记
     */
    private void doDelete() {
        MNote delete=new MNote();
        delete.remove("author");
        delete.delete(ActNoteOperation.this, "" + mNote.getObjectId(), new DeleteListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActNoteOperation.this, "删除成功");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActNoteOperation.this, "删除失败：" + s);
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
                tvTime.setText("" + time);
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


}
