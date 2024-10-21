package com.uvg.laboratorio8.login

sealed interface LoginEvent {
    data class NameChange( val name: String ): LoginEvent
    data object SaveName: LoginEvent

}