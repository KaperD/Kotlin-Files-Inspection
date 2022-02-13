package com.github.kaperd.kotlinfilesinspection.services

import com.intellij.openapi.project.Project
import com.github.kaperd.kotlinfilesinspection.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
