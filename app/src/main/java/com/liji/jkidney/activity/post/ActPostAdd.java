package com.liji.jkidney.activity.post;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JLogUtils;
import com.liji.jkidney.utils.JTimeUtils;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * 发表主题列表
 */
@ContentView(R.layout.activity_post_add)
public class ActPostAdd extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headView;
    @ViewInject(R.id.et_title)
    EditText etTitle;
    @ViewInject(R.id.et_content)
    EditText etContent;
    @ViewInject(R.id.tv_time)
    TextView tvTime;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;

    private String title;
    private String content;
    private String time;

    private List<PhotoInfo> mPhotoList;

    PostAddPhotoAda postAddPhotoAda;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    FunctionConfig functionConfig;
    private LinearLayoutManager mLayoutManager;
    MyUser user;

    @Override
    protected void initView(Bundle savedInstanceState) {

        user = User.getCurrentUser(ActPostAdd.this);
        headView.setTitle("发表主题");
        headView.setBackgroundColor(getResources().getColor(R.color.color_tab_post));
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });
        //保存
        headView.setRightImgAction(R.drawable.ic_confirm, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                doSubmit();
            }

        });


        //配置功能
        functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        mPhotoList = new ArrayList<>();
        postAddPhotoAda = new PostAddPhotoAda(ActPostAdd.this, getSupportFragmentManager(), mPhotoList, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {

            }
        });

        //设置水平布局
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setAdapter(postAddPhotoAda);

        time = JTimeUtils.getCurrentTime(0);
        tvTime.setText("" + time);

    }

    private void doSubmit() {
        time = tvTime.getText().toString().trim();
        title = etTitle.getText().toString().trim();
        content = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            JToastUtils.showToast(ActPostAdd.this, "请输入主题内容");
            return;
        }

        final KProgressHUD kdPro = KProgressHUD.create(ActPostAdd.this);
        kdPro.setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                .setLabel("正在发表")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0f)
                .setMaxProgress(100)
                .show();

        List<String> fileUrl = new ArrayList<>();
        for (int i = 0; i < postAddPhotoAda.getDatas().size(); i++) {
            fileUrl.add(postAddPhotoAda.getDatas().get(i).getPhotoPath());
        }

        String[] filePath = new String[fileUrl.size()];
        for (int j = 0; j < fileUrl.size(); j++) {
            filePath[j] = fileUrl.get(j);
        }

        final int lenth = filePath.length;
        if (lenth != 0) {
            BmobFile.uploadBatch(ActPostAdd.this, filePath, new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> list, List<String> list1) {

                    if (list1.size() == lenth) {//图片上传成功，继续操作
                        M_Post post = new M_Post();
                        post.setTitle(title);
                        post.setContent(content);
                        post.setAuthor(user);
                        post.setTime(time);
                        post.setAddress("中国");
                        post.setPostImg(list1);
                        post.save(ActPostAdd.this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                kdPro.dismiss();
                                JToastUtils.showToast(ActPostAdd.this, "发表成功！");
                                finish();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                kdPro.dismiss();
                                JToastUtils.showToast(ActPostAdd.this, "发表失败： " + s);
                            }
                        });

                    }
                }

                @Override
                public void onProgress(int i, int i1, int i2, int i3) {
                    kdPro.setProgress(i3);
                }

                @Override
                public void onError(int i, String s) {
                    kdPro.dismiss();
                    JToastUtils.showToast(ActPostAdd.this, "发表失败： " + s);

                }
            });
        } else {
            M_Post post = new M_Post();
            post.setTitle(title);
            post.setContent(content);
            post.setAuthor(user);
            post.setTime(time);
            post.setAddress("中国");
            post.save(ActPostAdd.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    kdPro.dismiss();
                    JToastUtils.showToast(ActPostAdd.this, "发表成功！");
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    kdPro.dismiss();
                    JToastUtils.showToast(ActPostAdd.this, "发表失败： " + s);
                }
            });

        }


    }

    /**
     * 打开相机和相册功能
     */
    public void openChoiceDialog(Context context, FragmentManager fragmentManager) {
        ActionSheet.createBuilder(context, fragmentManager)
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("打开相册", "拍照")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0://相册
                                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY,
                                        functionConfig,
                                        mOnHanlderResultCallback);
                                break;

                            case 1://相机
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                                break;
                        }

                    }
                })
                .show();

    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, final List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.addAll(resultList);
                postAddPhotoAda.notifyDataSetChanged();
                for (int i = 0; i < postAddPhotoAda.getDatas().size(); i++) {
                    JLogUtils.D("i: " + i + " dir: " + postAddPhotoAda.getDatas().get(i).getPhotoPath());
                }
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
        }
    };


}
