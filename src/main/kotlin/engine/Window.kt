package org.lwjglbookkt.engine

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL11.GL_TRUE
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks

class Window(val title: String, val opts: WindowOptions, val resizeFunc: () -> Unit) {


    var width: Int
    var height: Int
    val windowHandle: Long


    data class WindowOptions(
        val compatibleProfile: Boolean = false,
        val fps: Int = 0,
        val height: Int = 0,
        val width: Int = 0,
        val ups: Int = Engine.TARGET_UPS
    )

    init {

        if (!glfwInit())
            throw IllegalStateException("Unable to initialize GLFW")

        // Configure GLFW
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2)

        if (opts.compatibleProfile) {
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE)
        } else {
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE)
        }

        if (opts.width > 0 && opts.height > 0) {
            width = opts.width
            height = opts.height
        } else {
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE)
            val vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor()) ?: throw Exception("Could not get video mode")
            width = vidMode.width()
            height = vidMode.height()
        }

        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL)
        if (windowHandle == NULL) {
            throw RuntimeException("Failed to create the GLFW window")
        }

        glfwSetFramebufferSizeCallback(windowHandle, { window, w, h -> resized(w, h) })

        glfwSetErrorCallback({ errorCode, msgPtr ->
            System.err.println(
                "Error code [${errorCode}], msg [${
                    MemoryUtil.memUTF8(
                        msgPtr
                    )
                }]"
            )
        })
        glfwSetKeyCallback(windowHandle, { window, key, scancode, action, mods -> keyCallback(key, action) })

        glfwMakeContextCurrent(windowHandle)

        if (opts.fps > 0) {
            glfwSwapInterval(0)
        } else {
            glfwSwapInterval(1)
        }

        glfwShowWindow(windowHandle)

        val arrWidth = IntArray(1)
        val arrHeight = IntArray(1)
        glfwGetFramebufferSize(windowHandle, arrWidth, arrHeight)
        width = arrWidth[0]
        height = arrHeight[0]

    }


    fun keyCallback(key: Int, action: Int) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
            glfwSetWindowShouldClose(windowHandle, true)
        }
    }

    fun cleanup() {
        glfwFreeCallbacks(windowHandle)
        glfwDestroyWindow(windowHandle)
        glfwTerminate()
        glfwSetErrorCallback(null)?.free()

    }

    fun isKeyPressed(keyCode: Int): Boolean {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS
    }

    fun pollEvents() {
        glfwPollEvents()
    }

    protected fun resized(width: Int, height: Int) {
        this.width = width
        this.height = height
        try {
            resizeFunc()
        } catch (ex: Exception) {
            System.err.println("Error calling resize callback $ex")
        }
    }

    fun update() {
        glfwSwapBuffers(windowHandle)
    }

    fun windowShouldClose(): Boolean {
        return glfwWindowShouldClose(windowHandle)
    }


}