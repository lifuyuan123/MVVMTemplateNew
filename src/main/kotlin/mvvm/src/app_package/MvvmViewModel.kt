package mvvm.src.app_package

fun mvvmViewModelJava(
        pageName: String,
        contractPackageName: String,
        modelPackageName: String,
        needActivity: Boolean,
        needFragment: Boolean
) = """
package ${modelPackageName};

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

${scopeStrImportJava(needActivity, needFragment)}
import javax.inject.Inject;

import ${contractPackageName}.${pageName}Contract;


${scopeStr(needActivity, needFragment)}
public class ${pageName}Model extends BaseModel implements ${pageName}Contract.Model{
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ${pageName}Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}
"""


fun mvvmViewModelKt(
        viewModelPackageName: String,
        pageName: String) = """
package $viewModelPackageName



import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ${pageName}ViewModel @Inject constructor() : BaseViewModel(){

    @Inject lateinit var repository: ${pageName}Repository

   
}

"""

fun mvvmViewModelKt(
        viewModelPackageName: String,
        pageName: String,
        packageName:String) = """
package $viewModelPackageName



import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.lfy.baselibrary.vm.BaseViewModel

@HiltViewModel
class ${pageName}ViewModel @Inject constructor(private val repository: ${pageName}Repository) : BaseViewModel(){

}

"""