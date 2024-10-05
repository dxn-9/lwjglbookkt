package org.lwjglbookkt.engine.scene

import org.joml.*

class Entity(val id: String, val modelId: String) {
    val modelMatrix = Matrix4f()
    val position = Vector3f()
    val rotation = Quaternionf()
    var scale = 1f


    fun setPosition(x: Float, y: Float, z: Float) {
        position.x = x
        position.y = y
        position.z = z
    }

    fun setRotation(x: Float, y: Float, z: Float, angle: Float) {
        rotation.fromAxisAngleRad(x, y, z, angle)
    }

    fun updateModelMatrix() {
        modelMatrix.translationRotateScale(position, rotation, scale)
    }

}