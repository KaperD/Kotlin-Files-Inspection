package ru.hse.inspection

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class MyPluginTest : BasePlatformTestCase() {

    private fun doTest(fileName: String, text: String) {
        myFixture.configureByText(fileName, text)
        myFixture.enableInspections(KotlinFileInspection())
        myFixture.checkHighlighting()
    }

    fun `test ordinary kotlin file`() {
        doTest(
            "main.kt",
            """
                <weak_warning descr="${MyBundle.message("inspectionMessage")}">fun main() {
                    println("Hello World!")
                }
                </weak_warning>
            """.trimIndent()
        )
    }

    fun `test gradle kotlin file`() {
        doTest(
            "build.gradle.kts",
            """
                <weak_warning descr="${MyBundle.message("inspectionMessage")}">plugins {
                    id("java")
                    id("org.jetbrains.kotlin.jvm") version "1.6.10"
                }
                </weak_warning>
            """.trimIndent()
        )
    }
}
