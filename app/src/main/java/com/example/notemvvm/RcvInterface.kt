package com.example.notemvvm

interface RcvInterface {
    fun clickItem(pos: Int)

    fun longCLickItem(pos: Int): Boolean
}