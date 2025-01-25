package com.matin.youtech.crypto.sdui

sealed class Action {
    data class Navigation(val destination: String) : Action()
    data class ShowModal(val destination: String) : Action()
    data object NoOperation : Action()
}