package org.lwjglbookkt.engine

import org.lwjglbookkt.engine.graph.Render

class Engine(windowTitle: String, opts: Window.WindowOptions, val appLogic: IAppLogic) {
    val window: Window = Window(windowTitle, opts, { resize() })
    val targetFps: Int = opts.fps
    val targetUps: Int = opts.ups
    val render = Render()
    val scene = Scene()
    var running = true

    companion object {
        const val TARGET_UPS = 30
    }

    init {
        appLogic.init(window, scene, render)
    }

    fun run() {
        var initialTime = System.currentTimeMillis()
        val timeU = 1000.0f / targetUps
        val timeR = if (targetFps > 0) 1000.0f / targetFps else 0.0f
        var deltaUpdate = 0.0
        var deltaFps = 0.0

        var updateTime = initialTime

        while (running && !window.windowShouldClose()) {
            window.pollEvents()

            val now = System.currentTimeMillis()
            deltaUpdate += (now - initialTime) / timeU
            deltaFps += (now - initialTime) / timeR

            if (targetFps <= 0 || deltaFps >= 1) {
                appLogic.input(window, scene, now - initialTime)
            }

            if (deltaUpdate >= 1) {
                val diffTimeMilis = now - updateTime
                appLogic.update(window, scene, diffTimeMilis)
                updateTime = now
                deltaUpdate--
            }

            if (targetFps <= 0 || deltaFps >= 1) {
                render.render(window, scene)
                deltaFps--
                window.update()
            }
            initialTime = now
        }

        cleanup()
    }

    fun start() {
        running = true
        run()
    }

    fun stop() {
        running = false
    }

    fun cleanup() {
        appLogic.cleanup()
        render.cleanup()
        scene.cleanup()
        window.cleanup()
    }

    fun resize() {
        //TODO
    }


}