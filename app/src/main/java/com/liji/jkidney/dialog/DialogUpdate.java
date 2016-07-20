package com.liji.jkidney.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.os.CancellationSignal;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.autoupdatesdk.AppUpdateInfo;
import com.baidu.autoupdatesdk.AppUpdateInfoForInstall;
import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.CPUpdateDownloadCallback;
import com.liji.jkidney.R;

/**
 * @2Do: 版本更新
 * @Author M2
 * @Version v 1.0
 * @Date 2016/6/28 0028
 */
public class DialogUpdate extends Dialog {
    Context mContext;
    ImageView mUmengUpdateWifiIndicator;

    Button mUmengUpdateIdClose;

    TextView mUmengUpdateContent;

    CheckBox mUmengUpdateIdCheck;

    ProgressBar mPbLoad;

    Button mUmengUpdateIdOk;

    Button mUmengUpdateIdCancel;

    Button mUmengUpdateIdIgnore;

    LinearLayout mUmengUpdateFrame;

    public DialogUpdate(Context context) {
        super(context);
        this.mContext = context;
    }

    public DialogUpdate(Context context, int theme) {
        super(context, theme);
    }

    /* 强制更新 */
    private boolean forceUpdate = false;

    public DialogUpdate(boolean forceUpdate, final AppUpdateInfo info, final AppUpdateInfoForInstall infoForInstall,
                        Context context) {
        super(context, R.style.MyDialog);
        this.setContentView(R.layout.dialog_update);
        mContext = context;
        this.forceUpdate = forceUpdate;
        mUmengUpdateWifiIndicator = (ImageView) findViewById(R.id.umeng_update_wifi_indicator);
        mUmengUpdateIdClose = (Button) findViewById(R.id.umeng_update_id_close);
        mUmengUpdateContent = (TextView) findViewById(R.id.umeng_update_content);
        mUmengUpdateIdCheck = (CheckBox) findViewById(R.id.umeng_update_id_check);
        mPbLoad = (ProgressBar) findViewById(R.id.pb_load);
        mUmengUpdateIdOk = (Button) findViewById(R.id.umeng_update_id_ok);
        mUmengUpdateIdCancel = (Button) findViewById(R.id.umeng_update_id_cancel);
        mUmengUpdateIdIgnore = (Button) findViewById(R.id.umeng_update_id_ignore);
        mUmengUpdateFrame = (LinearLayout) findViewById(R.id.umeng_update_frame);

        if (infoForInstall != null && !TextUtils.isEmpty(infoForInstall.getInstallPath())) {//本地有最新更新包

            Spanned spanned = Html.fromHtml(infoForInstall.getAppSName() + "：最新版本=" + infoForInstall.getAppVersionName()
                    + "<br/> 已在wifi下载，点击可直接更新 " + "<br/>更新日志:" + infoForInstall.getAppChangeLog());

            mUmengUpdateContent.setText(spanned);
            mUmengUpdateIdOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BDAutoUpdateSDK.cpUpdateInstall(mContext.getApplicationContext(), infoForInstall.getInstallPath());
                }
            });

        } else if (info != null) {//有更新，本地无下载，去下载，并监听下载进度
            Spanned spanned = Html.fromHtml(info.getAppSname() + "：最新版本=" + info.getAppVersionName()
                    + " <br/>更新日志：<br/>" + info.getAppChangeLog());
            mUmengUpdateContent.setText(spanned);
            mUmengUpdateIdOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BDAutoUpdateSDK.cpUpdateDownload(mContext, info, new UpdateDownloadCallback());
                }
            });
        }
        if (!forceUpdate) {
            mUmengUpdateIdCancel.setVisibility(View.VISIBLE);
            mUmengUpdateIdCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
        }
//        if (1 == AbDeviceUtil.getNetWorkType(mContext)) {
//            mUmengUpdateWifiIndicator.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onBackPressed() {
        //        super.onBackPressed();
    }

    private class UpdateDownloadCallback implements CPUpdateDownloadCallback {

        @Override
        public void onDownloadComplete(String apkPath) {
            BDAutoUpdateSDK.cpUpdateInstall(mContext.getApplicationContext(), apkPath);
        }

        @Override
        public void onStart() {
            mPbLoad.setVisibility(View.VISIBLE);
            mUmengUpdateIdOk.setVisibility(View.GONE);
            //            txt_log.setText(txt_log.getText() + "\n Download onStart");
        }

        @Override
        public void onPercent(int percent, long rcvLen, long fileSize) {
            mPbLoad.setProgress(percent);
            //            txt_log.setText(txt_log.getText() + "\n Download onPercent: " + percent + "%");
        }

        @Override
        public void onFail(Throwable error, String content) {
            mPbLoad.setVisibility(View.GONE);
            Toast.makeText(mContext, "下载失败，请检查网络 ！", Toast.LENGTH_SHORT).show();

            //            txt_log.setText(txt_log.getText() + "\n Download onFail: " + content);
        }

        @Override
        public void onStop() {
            //            txt_log.setText(txt_log.getText() + "\n Download onStop");
        }

    }

    public interface OnCancelListener extends CancellationSignal.OnCancelListener {

        Context getContext();

    }
}
