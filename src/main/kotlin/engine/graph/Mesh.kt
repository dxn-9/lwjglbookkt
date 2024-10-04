package org.lwjglbookkt.engine.graph

import org.lwjgl.opengl.GL30
import org.lwjgl.system.MemoryStack
import org.lwjgl.opengl.GL30.*

class Mesh(positions: FloatArray, val numVertices: Int) {
    val vaoId: Int
    val vboIdList: List<Int>

    init {
        MemoryStack.stackPush().use { stack ->
            vboIdList = arrayListOf()
            vaoId = glGenVertexArrays()
            glBindVertexArray(vaoId)

            // Positions VBO
            val vboId = glGenBuffers()
            vboIdList.add(vboId)
            val positionsBuffer = stack.callocFloat(positions.size)
            positionsBuffer.put(0, positions)
            glBindBuffer(GL_ARRAY_BUFFER, vboId)
            glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW)
            glEnableVertexAttribArray(0)
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

            glBindBuffer(GL_ARRAY_BUFFER, 0)
            glBindVertexArray(0)

        }
    }

    fun cleanup() {
        vboIdList.forEach(GL30::glDeleteBuffers)
        glDeleteVertexArrays(vaoId)
    }


}