package com.github.lifuyuan123.mvvmtemplatenew.services

import com.intellij.openapi.project.Project
import com.github.lifuyuan123.mvvmtemplatenew.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
