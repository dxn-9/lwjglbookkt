package org.lwjglbookkt

import org.lwjglbookkt.engine.graph.Mesh
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL11.*
import org.lwjglbookkt.engine.Engine
import org.lwjglbookkt.engine.IAppLogic
import org.lwjglbookkt.engine.Scene
import org.lwjglbookkt.engine.Window
import org.lwjglbookkt.engine.graph.Render


class Main : IAppLogic {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val main = Main()
            val gameEng = Engine("chapter-02", Window.WindowOptions(width = 500, height = 500), main)
            gameEng.start()
        }
    }

    private var window: Long = 0;
    override fun cleanup() {
//        TODO("Not yet implemented")
    }

    override fun init(window: Window, scene: Scene, render: Render) {
        val positions = arrayOf(
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
        ).toFloatArray()

        val mesh = Mesh(positions, 3)
        scene.addMesh("triangle", mesh)
    }

    override fun input(window: Window, scene: Scene, diffTimeMillis: Long) {
//        TODO("Not yet implemented")
    }

    override fun update(window: Window, scene: Scene, diffTimeMillis: Long) {
//        TODO("Not yet implemented")
    }


}
