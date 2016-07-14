package jiayuan.huawei.com.jiayuantestmanager.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import jiayuan.huawei.com.jiayuantestmanager.R;
import jiayuan.huawei.com.jiayuantestmanager.sharepreference.MySharedManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected int getActivityContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void findViewByIds() {

    }

    @Override
    protected void requestService() {
        final String filePath = MySharedManager.getLastExcelPath();
        if (TextUtils.isEmpty(filePath)) {
            new MaterialFilePicker()
                    .withActivity(this)
                    .withRequestCode(1)
                    .withFilterDirectories(false)
                    .withFilter(Pattern.compile(".*\\.xls$"))
                    .withHiddenFiles(true)
                    .start();
        } else {
            Observable.just("Anima")
                    .subscribeOn(Schedulers.io())
                    .delay(3000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("filePath", filePath);
                            startActivity(intent);
                            finish();
                        }
                    });
        }
    }

    @Override
    protected void onMyDestory() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String filePath = String.valueOf(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
            MySharedManager.setLastExcelPath(filePath);
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("filePath", filePath);
            startActivity(intent);
            finish();
        }
    }
}
