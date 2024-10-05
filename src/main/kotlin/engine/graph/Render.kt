package org.lwjglbookkt.engine.graph

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL30.*
import org.lwjglbookkt.engine.scene.Scene
import org.lwjglbookkt.engine.Window


class Render {
    val sceneRender: SceneRender

    init {
        GL.createCapabilities()
        glEnable(GL_DEPTH_TEST)
        sceneRender = SceneRender()
    }

    fun cleanup() {
        sceneRender.cleanup()
    }

    fun render(window: Window, scene: Scene) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        glViewport(0, 0, window.width, window.height)
        sceneRender.render(scene)
    }

}