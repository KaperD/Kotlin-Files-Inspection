package ru.hse.inspection.services

import com.intellij.openapi.project.Project
import ru.hse.inspection.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
