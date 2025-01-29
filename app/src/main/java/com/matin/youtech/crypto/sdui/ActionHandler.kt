package com.matin.youtech.crypto.sdui

interface ActionHandler {
    fun handle(a: Action)
}

class RealActionHandler : ActionHandler {

    override fun handle(a: Action) {
        when (a) {
            is Action.ShowModal -> {
                // Show modal
            }

            is Action.Navigation -> {
                // Navigate
            }

            Action.NoOperation -> {}

        }
    }
}