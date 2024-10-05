package org.lwjglbookkt.engine.scene

import org.lwjglbookkt.engine.graph.Mesh

class Scene(width: Int, height: Int) {

    val meshMap = mutableMapOf<String, Mesh>()
    val projection = Projection(width, height)

    fun cleanup() {
        meshMap.values.forEach(Mesh::cleanup)
    }

    fun addMesh(meshId: String, mesh: Mesh) {
        meshMap[meshId] = mesh
    }

    fun resize(width: Int, height: Int) {
        projection.updateProjMatrix(width, height)
    }

}