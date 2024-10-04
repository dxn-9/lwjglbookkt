package org.lwjglbookkt.engine.graph

import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import org.lwjglbookkt.engine.Utils

class ShaderProgram(shaderModuleDataList: List<ShaderModuleData>) {

    val programId: Int

    init {
        programId = glCreateProgram()
        if (programId == 0) {
            throw RuntimeException("Could not create Shader")
        }
        val shaderModules = mutableListOf<Int>()
        shaderModuleDataList.forEach { shaderModules.add(createShader(Utils.readFile(it.shaderFile), it.shaderType)) }
        link(shaderModules)
    }

    fun bind() {
        glUseProgram(programId)
    }

    fun cleanup() {
        unbind()
        if (programId != 0)
            glDeleteProgram(programId)
    }

    private fun createShader(shaderCode: String, shaderType: Int): Int {
        val shaderId = glCreateShader(shaderType)
        if (shaderId == 0)
            throw RuntimeException("Error creating shader $shaderType")

        glShaderSource(shaderId, shaderCode)
        glCompileShader(shaderId)

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0)
            throw RuntimeException("Error compiling shader code: ${glGetShaderInfoLog(shaderId, 1024)}")

        glAttachShader(programId, shaderId)

        return shaderId
    }

    private fun link(shaderModules: List<Int>) {
        glLinkProgram(programId)
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0)
            throw RuntimeException("Error linking shader code ${glGetProgramInfoLog(programId, 1024)}")

        shaderModules.forEach { glDetachShader(programId, it) }
        shaderModules.forEach(GL30::glDeleteShader)
    }

    fun unbind() {
        glUseProgram(0)
    }

    fun validate() {
        glValidateProgram(programId)
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0)
            throw RuntimeException("Error validating Shader code: ${glGetProgramInfoLog(programId, 1024)}")
    }

    data class ShaderModuleData(val shaderFile: String, val shaderType: Int)


}