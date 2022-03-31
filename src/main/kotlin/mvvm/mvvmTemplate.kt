package mvvm

import com.android.tools.idea.wizard.template.*
import java.io.File

/**
 * @CreateDate:     2021/4/1
 * @Author:         Creator
 * @Description:    MVPArms模板配置
 */
val MVVMTemplate
    get() = template {
        name = "MVVM Template"
        description = "一键创建 MVVM 单个页面所需要的全部组件"
        minApi = 19

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)


        val pageName = stringParameter {
            name = "Page Name"
            default = "Main"
            help = "请填写页面名,如填写 Main,会自动生成 MainActivity, MainViewModel 等文件"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
        }

        val packageName = stringParameter {
            name = "Root Package Name"
            default = "com.mycompany.myapp"
            constraints = listOf(Constraint.PACKAGE)
            help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:me.jessyan.arms"
        }

        //是否需要生成Activity
        val needActivity = booleanParameter {
            name = "Generate Activity"
            default = false
            help = "是否需要生成 Activity ? 不勾选则不生成"
        }

        //布局名
        val activityLayoutName = stringParameter {
            name = "Activity Layout Name"
            default = "activity_main"
            visible = { needActivity.value }
            help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(pageName.value.toLowerCase())}" }
        }

        //是否需要Activity的布局
        val generateActivityLayout = booleanParameter {
            name = "Generate Activity Layout"
            default = true
            visible = { needActivity.value }
            help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
        }



        val activityPackageName = stringParameter {
            name = "Activity Package Name"
            default = "Activity Package Name"
            visible = { needActivity.value }
            help = "Activity 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { "${packageName.value}.${pageName.value.toLowerCase()}" }
        }

        //Fragment
        //是否需要生成Fragment
        val needFragment = booleanParameter {
            name = "Generate Fragment"
            default = true
            help = "是否需要生成 Fragment ? 不勾选则不生成"
        }


        //布局名
        val fragmentLayoutName = stringParameter {
            name = "Fragment Layout Name"
            default = "fragment_main"
            visible = { needFragment.value }
            help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${fragmentToLayout(pageName.value.toLowerCase())}" }
        }

        //是否需要Fragment的布局
        val generateFragmentLayout = booleanParameter {
            name = "Generate Fragment Layout"
            default = true
            visible = { needFragment.value }
            help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
        }

        val fragmentPackageName = stringParameter {
            name = "Fragment Package Name"
            default = "function Package Name"
            constraints = listOf(Constraint.PACKAGE)
            visible = { needFragment.value }
            help = "Fragment 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
            suggest = {"${packageName.value}.${pageName.value.toLowerCase()}"}
        }


//        val needContract = booleanParameter {
//            name = "Generate Contract"
//            default = true
//            help = "是否需要生成 Contract ? 不勾选则不生成"
//        }
//
//
//        val contractPackageName = stringParameter {
//            name = "Contract Package Name"
//            default = "Contract Package Name"
//            constraints = listOf(Constraint.PACKAGE)
//            visible = { needContract.value }
//            help = "Contract 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//            suggest = { "${packageName.value}.mvp.contract" }
//        }


        val needRepository = booleanParameter {
            name = "Generate Repository"
            default = true
            help = "是否需要生成 Repository ? 不勾选则不生成"
        }


        val repositoryPackageName = stringParameter {
            name = "Repository Package Name"
            default = "Repository Package Name"
            constraints = listOf(Constraint.PACKAGE)
            visible = { needRepository.value }
            help = "Repository 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
            suggest = {"${packageName.value}.${pageName.value.toLowerCase()}"}
        }

        val needViewModel = booleanParameter {
            name = "Generate ViewModel"
            default = true
            help = "是否需要生成 ViewModel ? 不勾选则不生成"
        }


        val viewModelPackageName = stringParameter {
            name = "ViewModel Package Name"
            default = "ViewModel Package Name"
            constraints = listOf(Constraint.PACKAGE)
            visible = { needViewModel.value }
            help = "ViewModel 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
            suggest =  {"${packageName.value}.${pageName.value.toLowerCase()}"}
        }


        widgets(
                TextFieldWidget(pageName),
                PackageNameWidget(packageName),
                CheckBoxWidget(needActivity),
                TextFieldWidget(activityLayoutName),
                CheckBoxWidget(generateActivityLayout),
                TextFieldWidget(activityPackageName),
                CheckBoxWidget(needFragment),
                TextFieldWidget(fragmentLayoutName),
                CheckBoxWidget(generateFragmentLayout),
                TextFieldWidget(fragmentPackageName),


                CheckBoxWidget(needRepository),
                TextFieldWidget(repositoryPackageName),
                CheckBoxWidget(needViewModel),
                TextFieldWidget(viewModelPackageName),
                LanguageWidget()
        )

        thumb { File("template_blank_activity.png") }


        recipe = { data: TemplateData ->
            mvvmRecipe(
                    data as ModuleTemplateData,
                    pageName.value,
                    packageName.value,
                    needActivity.value,
                    activityLayoutName.value,
                    generateActivityLayout.value,
                    activityPackageName.value,
                    needFragment.value,
                    fragmentLayoutName.value,
                    generateFragmentLayout.value,
                    fragmentPackageName.value,
                    needRepository.value,
                    needViewModel.value,
                    repositoryPackageName.value,
                    viewModelPackageName.value
            )
        }
    }


