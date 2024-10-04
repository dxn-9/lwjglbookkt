package org.lwjglbookkt.engine.graph

import org.lwjgl.opengl.GL30.*
import org.lwjglbookkt.engine.Scene

class SceneRender {
    val shaderProgram: ShaderProgram

    init {
        val shaderModuleDataList: ArrayList<ShaderProgram.ShaderModuleData> = ArrayList()
        shaderModuleDataList.add(ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER))
        shaderModuleDataList.add(ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER))
        shaderProgram = ShaderProgram(shaderModuleDataList)
    }

    fun cleanup() {
        shaderProgram.cleanup()
    }

    fun render(scene: Scene) {
        shaderProgram.bind()
        scene.meshMap.values.forEach { mesh ->
            glBindVertexArray(mesh.vaoId)
            glDrawElements(GL_TRIANGLES, mesh.numVertices, GL_UNSIGNED_INT, 0)

        }
        glBindVertexArray(0)

        shaderProgram.unbind()

    }

}