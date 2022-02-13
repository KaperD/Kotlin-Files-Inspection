package ru.hse.inspection

import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixture4TestCase
import com.intellij.testFramework.fixtures.impl.CodeInsightTestFixtureImpl
import org.junit.Test

class MyPluginTest : LightPlatformCodeInsightFixture4TestCase() {

    @Test
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

    @Test
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

    @Test
    fun `test not kotlin file`() {
        doTest(
            "main.java",
            """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Hello World!");
                    }
                }
            """.trimIndent()
        )
    }

    private fun doTest(fileName: String, text: String) {
        (myFixture as? CodeInsightTestFixtureImpl)?.canChangeDocumentDuringHighlighting(true)
        myFixture.configureByText(fileName, text)
        myFixture.enableInspections(KotlinFileInspection())
        myFixture.checkHighlighting(false, false, true, true)
    }
}
