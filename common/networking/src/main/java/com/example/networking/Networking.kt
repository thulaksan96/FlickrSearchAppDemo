package com.example.networking

interface Networking {
    fun <T> createService(clazz: Class<T>): T
}