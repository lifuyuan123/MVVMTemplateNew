package mvvm

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import mvvm.androidManifest.manifestTemplateXml
import mvvm.src.app_package.*
import mvvm.res.layout.templateXml

/**
 * @CreateDate:     2021/4/1
 * @Author:         Creator
 * @Description:    菜单
 */
fun RecipeExecutor.mvvmRecipe(
        moduleData: ModuleTemplateData,
        pageName: String,
        packageName: String,
        needActivity: Boolean,
        activityLayoutName: String,
        generateActivityLayout: Boolean,
        activityPackageName: String,
        needFragment: Boolean,
        fragmentLayoutName: String,
        generateFragmentLayout: Boolean,
        fragmentPackageName: String,
        needRepository: Boolean,
        needViewModel: Boolean,
        repositoryPackageName: String,
        viewModelPackageName: String
) {

    val (projectData, srcOut, resOut,manifestOut) = moduleData

    val ktOrJavaExt = projectData.language.extension

    val packgeNames=packageName.split(".")
    val packageRealName=if (packgeNames.size>2) "${packgeNames[0]}.${packgeNames[1]}.${packgeNames[2]}" else packageName


    if (needActivity && needFragment){
        throw IllegalArgumentException("activity和fragment不可同时选择")
    }


    if (needActivity) {
        mergeXml(manifestTemplateXml(packageRealName,activityPackageName,"${pageName}Activity"), manifestOut.resolve("AndroidManifest.xml"))
    }

    if (needActivity && generateActivityLayout) {
        save(templateXml("${activityPackageName}.${pageName}Activity","activity"), resOut.resolve("layout/${activityLayoutName}.xml"))
    }

    if (needFragment && generateFragmentLayout) {
        save(templateXml("${fragmentPackageName}.${pageName}Fragment"), resOut.resolve("layout/${fragmentLayoutName}.xml"))
    }


    if (needActivity) {
        if (ktOrJavaExt == "java")
            save(mvvmActivityJava(packageRealName, pageName, activityPackageName, activityLayoutName), srcOut.resolve("${pageName.toLowerCase()}/${pageName}Activity.${ktOrJavaExt}"))
        else
            save(mvvmActivityKt(packageRealName, pageName, activityPackageName,  activityLayoutName,needViewModel), srcOut.resolve("${pageName.toLowerCase()}/${pageName}Activity.${ktOrJavaExt}"))
    }

    if (needFragment) {
        if (ktOrJavaExt == "java")
            save(mvvmFragmentJava(pageName, fragmentPackageName,  packageRealName, fragmentLayoutName), srcOut.resolve("${pageName.toLowerCase()}/${pageName}Fragment.${ktOrJavaExt}"))
        else
            save(mvvmFragmentKt(pageName,  fragmentPackageName,  packageRealName, fragmentLayoutName,needViewModel), srcOut.resolve("${pageName.toLowerCase()}/${pageName}Fragment.${ktOrJavaExt}"))
    }

    if (needRepository) {
        if (ktOrJavaExt == "java")
            save(mvvmRepositoryJava(repositoryPackageName,"","",needActivity,needFragment), srcOut.resolve("mvp/presenter/${pageName}Presenter.${ktOrJavaExt}"))
        else
            save(mvvmRepositoryKt(repositoryPackageName,pageName,packageRealName), srcOut.resolve("${pageName.toLowerCase()}/${pageName}Repository.${ktOrJavaExt}"))
    }


    if (needViewModel) {
        if (ktOrJavaExt == "java")
            save(mvvmViewModelJava(viewModelPackageName, "", "", needActivity, needFragment), srcOut.resolve("${pageName}/${pageName}ViewModel.${ktOrJavaExt}"))
        else
            save(mvvmViewModelKt(viewModelPackageName,pageName,packageRealName), srcOut.resolve("${pageName.toLowerCase()}/${pageName}ViewModel.${ktOrJavaExt}"))
    }




}