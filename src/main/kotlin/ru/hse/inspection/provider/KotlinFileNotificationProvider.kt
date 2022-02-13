package ru.hse.inspection.provider

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.EditorNotificationPanel
import com.intellij.ui.EditorNotifications
import ru.hse.inspection.MyBundle
import ru.hse.inspection.icons.Icons

class KotlinFileNotificationProvider : EditorNotifications.Provider<EditorNotificationPanel>(), DumbAware {
    private val key = Key.create<EditorNotificationPanel>("KotlinFileNotificationProvider")
    private val kotlinType = FileTypeManager.getInstance().getFileTypeByExtension("kt")

    override fun getKey(): Key<EditorNotificationPanel> {
        return key
    }

    override fun createNotificationPanel(
        file: VirtualFile,
        fileEditor: FileEditor,
        project: Project
    ): EditorNotificationPanel? {
        return if (file.fileType == kotlinType) {
            EditorNotificationPanel().apply {
                setProject(project)
                setProviderKey(key)
                icon(Icons.KOTLIN)
                text(MyBundle.message("inspectionMessage"))
            }
        } else {
            null
        }
    }
}
