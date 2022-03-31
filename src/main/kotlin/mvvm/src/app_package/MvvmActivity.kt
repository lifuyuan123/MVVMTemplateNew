package mvvm.src.app_package


fun mvvmActivityJava(
        packageName: String,
        pageName: String,
        activityPackageName: String,
        activityLayoutName: String
) = """
package ${activityPackageName};


import android.os.Bundle;
import ${packageName}.databinding.Activity${pageName}Binding
import com.lfy.baselibrary.ui.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import ${packageName}.R;


public class ${pageName}Activity extends BaseActivity<${pageName}Presenter> implements ${pageName}Contract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${pageName}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.${activityLayoutName}; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
"""


fun mvvmActivityKt(
        packageName: String,
        pageName: String,
        activityPackageName: String,
        activityLayoutName: String,
        needViewModel:Boolean=true

) = """
package $activityPackageName

import android.os.Bundle
import ${packageName}.databinding.Activity${pageName}Binding
import com.lfy.baselibrary.ui.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import ${packageName}.R
${if (needViewModel){""}else{"import com.jdjinsui.baselibrary.vm.BaseViewModel"}}

@AndroidEntryPoint
class ${pageName}Activity : BaseActivity<Activity${pageName}Binding,${if(needViewModel){"${pageName}ViewModel"}else{"BaseViewModel"}}>() {

    override fun getLayout() = R.layout.${activityLayoutName}


    override fun initData(savedInstanceState: Bundle?) {
        binding.activity=this
    
    }


 
}

"""