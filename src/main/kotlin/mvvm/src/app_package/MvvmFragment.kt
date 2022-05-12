package mvvm.src.app_package

import mvvm.upperCase

fun mvvmFragmentJava(
        pageName: String,
        fragmentPackageName: String,
        packageName: String,
        fragmentLayoutName: String,
) = """
package ${fragmentPackageName};

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;


import ${packageName}.R;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class ${pageName}Fragment extends BaseFragment<${pageName}Presenter> implements ${pageName}Contract.View{

    public static ${pageName}Fragment newInstance() {
        ${pageName}Fragment fragment = new ${pageName}Fragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        Dagger${pageName}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.${fragmentLayoutName}, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
    
    @Override
    public void setData(@Nullable Object data) {

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

    }
}

"""


fun mvvmFragmentKt(
        pageName: String,
        fragmentPackageName: String,
        packageName: String,
        fragmentLayoutName: String,
        needViewModel:Boolean=true
) = """
package $fragmentPackageName


import android.os.Bundle
import ${packageName}.databinding.Fragment${pageName.upperCase()}Binding
import com.lfy.baselibrary.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import ${packageName}.R
${if (needViewModel){""}else{"import com.jdjinsui.baselibrary.vm.BaseViewModel"}}

@AndroidEntryPoint
class ${pageName}Fragment : BaseFragment<Fragment${pageName.upperCase()}Binding,${if(needViewModel){"${pageName}ViewModel"}else{"BaseViewModel"}}>(){
    
    companion object {
        fun newInstance() = ${pageName}Fragment()
    }
    
    override fun getLayout()=R.layout.${fragmentLayoutName}
    
    override fun initData(savedInstanceState: Bundle?) {
         binding.fragment=this
    }
}

"""