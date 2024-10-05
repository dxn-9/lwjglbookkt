package org.lwjglbookkt.engine.scene

import org.lwjglbookkt.engine.graph.Mesh
import org.lwjglbookkt.engine.graph.Model

class Scene(width: Int, height: Int) {

    val modelMap = mutableMapOf<String, Model>()
    val projection = Projection(width, height)


    fun addEntity(entity: Entity) {
        val modelId = entity.modelId
        val model = modelMap[modelId] ?: throw RuntimeException("Could not find model [$modelId]")

        model.entitiesList.add(entity)
    }

    fun addModel(modelId: String, model: Model) {
        modelMap[modelId] = model
    }

    fun resize(width: Int, height: Int) {
        projection.updateProjMatrix(width, height)
    }

    fun cleanup() {
        modelMap.values.forEach(Model::cleanup)
    }
}