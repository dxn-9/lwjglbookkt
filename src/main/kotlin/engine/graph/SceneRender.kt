package org.lwjglbookkt.engine.graph

import org.lwjgl.opengl.GL30.*
import org.lwjglbookkt.engine.scene.Scene

class SceneRender {
    val shaderProgram: ShaderProgram
    val uniformsMap: UniformsMap

    init {
        val shaderModuleDataList: ArrayList<ShaderProgram.ShaderModuleData> = ArrayList()
        shaderModuleDataList.add(ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER))
        shaderModuleDataList.add(ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER))
        shaderProgram = ShaderProgram(shaderModuleDataList)
        uniformsMap = UniformsMap(shaderProgram.programId)

        createUniforms()
    }

    fun cleanup() {
        shaderProgram.cleanup()
    }

    fun createUniforms() {
        uniformsMap.createUniform("projectionMatrix")
    }

    fun render(scene: Scene) {
        shaderProgram.bind()
        scene.meshMap.values.forEach { mesh ->
            glBindVertexArray(mesh.vaoId)
            glDrawElements(GL_TRIANGLES, mesh.numVertices, GL_UNSIGNED_INT, 0)

        }
        glBindVertexArray(0)

        uniformsMap.setUniform("projectionMatrix", scene.projection.projMatrix)

        shaderProgram.unbind()

    }

}