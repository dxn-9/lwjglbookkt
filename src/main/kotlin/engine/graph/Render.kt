package org.lwjglbookkt.engine.graph

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjglbookkt.engine.Scene
import org.lwjglbookkt.engine.Window


class Render {
    init {
        GL.createCapabilities()
    }

    fun cleanup() {
        TODO()
    }

    fun render(window: Window, scene: Scene) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
    }

}