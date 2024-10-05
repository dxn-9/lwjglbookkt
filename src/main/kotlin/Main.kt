package org.lwjglbookkt

import org.joml.Vector4f
import org.lwjgl.glfw.GLFW.*
import org.lwjglbookkt.engine.graph.Mesh
import org.lwjglbookkt.engine.Engine
import org.lwjglbookkt.engine.IAppLogic
import org.lwjglbookkt.engine.scene.Scene
import org.lwjglbookkt.engine.Window
import org.lwjglbookkt.engine.graph.Model
import org.lwjglbookkt.engine.graph.Render
import org.lwjglbookkt.engine.scene.Entity


class Main : IAppLogic {

    lateinit var cubeEntity: Entity
    val displInc = Vector4f()
    var rotation = 0.0f

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val main = Main()
            val gameEng = Engine("lwjgl game dev", Window.WindowOptions(width = 800, height = 500), main)
            gameEng.start()
        }
    }

    override fun cleanup() {
//        TODO("Not yet implemented")
    }

    override fun init(window: Window, scene: Scene, render: Render) {
        val positions = arrayListOf(
            // VO
            -0.5f, 0.5f, 0.5f,
            // V1
            -0.5f, -0.5f, 0.5f,
            // V2
            0.5f, -0.5f, 0.5f,
            // V3
            0.5f, 0.5f, 0.5f,
            // V4
            -0.5f, 0.5f, -0.5f,
            // V5
            0.5f, 0.5f, -0.5f,
            // V6
            -0.5f, -0.5f, -0.5f,
            // V7
            0.5f, -0.5f, -0.5f,
        ).toFloatArray()
        val colors = arrayListOf(
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,
        ).toFloatArray();
        val indices = arrayListOf(
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            4, 0, 3, 5, 4, 3,
            // Right face
            3, 2, 7, 5, 3, 7,
            // Left face
            6, 1, 0, 6, 0, 4,
            // Bottom face
            2, 1, 6, 2, 6, 7,
            // Back face
            7, 6, 4, 7, 4, 5,
        ).toIntArray();

        val meshList = arrayListOf<Mesh>()
        val mesh = Mesh(positions, colors, indices)
        meshList.add(mesh)
        val cubeModelId = "cube-model"
        val model = Model(cubeModelId, meshList)
        scene.addModel(cubeModelId, model)

        cubeEntity = Entity("cube-entity", cubeModelId)
        cubeEntity.setPosition(0f, 0f, -2f)
        scene.addEntity(cubeEntity)
    }

    override fun input(window: Window, scene: Scene, diffTimeMillis: Long) {
        displInc.zero()

        when {
            window.isKeyPressed(GLFW_KEY_UP) -> displInc.y = 1f
            window.isKeyPressed(GLFW_KEY_DOWN) -> displInc.y = -1f
        }

        when {
            window.isKeyPressed(GLFW_KEY_LEFT) -> displInc.x = -1f
            window.isKeyPressed(GLFW_KEY_RIGHT) -> displInc.x = 1f
        }

        when {
            window.isKeyPressed(GLFW_KEY_A) -> displInc.z = -1f
            window.isKeyPressed(GLFW_KEY_Q) -> displInc.z = 1f
        }

        when {
            window.isKeyPressed(GLFW_KEY_Z) -> displInc.w = -1f
            window.isKeyPressed(GLFW_KEY_X) -> displInc.w = 1f
        }

        displInc.mul(diffTimeMillis / 1000.0f)

        val entityPos = cubeEntity.position
        cubeEntity.setPosition(displInc.x + entityPos.x, displInc.y + entityPos.y, displInc.z + entityPos.z)
        cubeEntity.scale += displInc.w
        cubeEntity.updateModelMatrix()

    }

    override fun update(window: Window, scene: Scene, diffTimeMillis: Long) {
        rotation += 1.5f
        if (rotation > 360f) {
            rotation = 0f
        }
        cubeEntity.setRotation(1f, 1f, 1f, Math.toRadians(rotation.toDouble()).toFloat())
        cubeEntity.updateModelMatrix()

    }


}
