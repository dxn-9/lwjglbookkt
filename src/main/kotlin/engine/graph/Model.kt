package org.lwjglbookkt.engine.graph

import org.lwjglbookkt.engine.scene.Entity

class Model(val id: String, val meshList: List<Mesh>) {
    val entitiesList = ArrayList<Entity>()

    fun cleanup() {
        meshList.forEach(Mesh::cleanup)
    }

}
