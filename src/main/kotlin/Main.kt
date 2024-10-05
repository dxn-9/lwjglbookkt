package org.lwjglbookkt

import org.lwjglbookkt.engine.graph.Mesh
import org.lwjglbookkt.engine.Engine
import org.lwjglbookkt.engine.IAppLogic
import org.lwjglbookkt.engine.scene.Scene
import org.lwjglbookkt.engine.Window
import org.lwjglbookkt.engine.graph.Render


class Main : IAppLogic {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val main = Main()
            val gameEng = Engine("chapter-02", Window.WindowOptions(width = 800, height = 500), main)
            gameEng.start()
        }
    }

    private var window: Long = 0;
    override fun cleanup() {
//        TODO("Not yet implemented")
    }

    override fun init(window: Window, scene: Scene, render: Render) {
        val positions = arrayOf(
            -0.5f, 0.5f, -1.0f,
            -0.5f, -0.5f, -1.0f,
            0.5f, -0.5f, -1.0f,
            0.5f, 0.5f, -1.0f,
        ).toFloatArray()
        val colors = arrayOf(
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f
        ).toFloatArray()
        val indices = arrayOf(0, 1, 3, 3, 1, 2).toIntArray()

        val mesh = Mesh(positions, colors, indices)
        scene.addMesh("triangle", mesh)
    }

    override fun input(window: Window, scene: Scene, diffTimeMillis: Long) {
//        TODO("Not yet implemented")
    }

    override fun update(window: Window, scene: Scene, diffTimeMillis: Long) {
//        TODO("Not yet implemented")
    }


}
