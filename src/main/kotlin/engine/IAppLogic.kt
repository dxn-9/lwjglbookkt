package org.lwjglbookkt.engine

import org.lwjglbookkt.engine.graph.Render

interface IAppLogic {
    fun cleanup()
    fun init(window: Window, scene: Scene, render: Render)
    fun input(window: Window, scene: Scene, diffTimeMillis: Long)
    fun update(window: Window, scene: Scene, diffTimeMillis: Long)


}