package ru.hse.inspection

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.idea.KotlinFileType

class KotlinFileInspection : LocalInspectionTool() {
    private val inspectionMessage: String = MyBundle.message("inspectionMessage")

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor>? {
        return if (file.fileType == KotlinFileType.INSTANCE) {
            arrayOf(
                manager.createProblemDescriptor(
                    file,
                    inspectionMessage,
                    null,
                    ProblemHighlightType.WEAK_WARNING,
                    isOnTheFly,
                    false
                )
            )
        } else {
            null
        }
    }
}
