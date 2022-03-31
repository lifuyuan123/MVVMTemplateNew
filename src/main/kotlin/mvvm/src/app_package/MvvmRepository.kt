package mvvm.src.app_package

fun mvvmRepositoryJava(
        pageName: String,
        contractPackageName: String,
        presenterPackageName: String,
        needActivity: Boolean,
        needFragment: Boolean
) = """
package ${presenterPackageName};

import android.app.Application;

import com.jess.arms.integration.AppManager;
${scopeStrImportJava(needActivity, needFragment)}
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import javax.inject.Inject;

import ${contractPackageName}.${pageName}Contract;


${scopeStr(needActivity, needFragment)}
public class ${pageName}Presenter extends BasePresenter<${pageName}Contract.Model, ${pageName}Contract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ${pageName}Presenter (${pageName}Contract.Model model, ${pageName}Contract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}

"""


fun mvvmRepositoryKt(
        repositoryPackageName: String,
        pageName: String,
        packageName:String

) = """
package $repositoryPackageName

import ${packageName}.api.Service
import javax.inject.Inject

class ${pageName}Repository @Inject constructor(private val service: Service)  {
    

}
"""