package org.lwjglbookkt.engine.graph

import org.joml.Matrix4f
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryStack

class UniformsMap(val programId: Int) {
    val uniforms = HashMap<String, Int>()

    fun createUniform(uniformName: String) {
        val uniformLocation = glGetUniformLocation(programId, uniformName)
        if (uniformLocation < 0)
            throw RuntimeException("Could not find uniform [$uniformName] in programId [$programId]")

        uniforms[uniformName] = uniformLocation

    }

    fun setUniform(uniformName: String, value: Matrix4f) {
        MemoryStack.stackPush().use { stack ->
            val location = uniforms[uniformName] ?: throw RuntimeException("Could not find uniform [$uniformName]")
            glUniformMatrix4fv(location, false, value.get(stack.mallocFloat(16)))
        }
    }
}