package org.lwjglbookkt.engine

import org.lwjglbookkt.engine.graph.Mesh

class Scene {

    val meshMap = mutableMapOf<String, Mesh>()

    fun cleanup() {
        meshMap.values.forEach(Mesh::cleanup)
    }

    fun addMesh(meshId: String, mesh: Mesh) {
        meshMap[meshId] = mesh
    }

}