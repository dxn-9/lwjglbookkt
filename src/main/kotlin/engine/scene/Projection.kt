package org.lwjglbookkt.engine.scene

import org.joml.Matrix4f


class Projection(width: Int, height: Int) {
    companion object {
        val FOV: Float = Math.toRadians(60.0).toFloat()
        val Z_FAR = 1000.0f
        val Z_NEAR = 0.01f
    }

    val projMatrix: Matrix4f

    init {
        projMatrix = Matrix4f()
        updateProjMatrix(width, height)
    }

    fun updateProjMatrix(width: Int, height: Int) {
        projMatrix.setPerspective(FOV, width / height.toFloat(), Z_NEAR, Z_FAR)
    }

}