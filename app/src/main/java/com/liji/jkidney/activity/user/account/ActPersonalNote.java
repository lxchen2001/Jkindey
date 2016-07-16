package com.liji.jkidney.activity.user.account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 个人说明
 */
@ContentView(R.layout.activity_personal_note)
public class ActPersonalNote extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.et_note)
    EditText etNote;

    public String note = "";
    public static String NOTE = "note";

    @Override
    protected void initView(Bundle savedInstanceState) {
        note = this.getIntent().getStringExtra(NOTE);
        etNote.setText("" + note);
        headview.setTitle("个人签名");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });
        headview.setRightImgAction(R.drawable.ic_confirm, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                doSubmit();
            }
        });

    }

    private void doSubmit() {
        note = etNote.getText().toString().trim();
        Intent intent = new Intent(ActPersonalNote.this, ActUserInfoUpdate.class);
        intent.putExtra(NOTE, note);
        setResult(RESULT_OK, intent);
        finish();
    }

}
