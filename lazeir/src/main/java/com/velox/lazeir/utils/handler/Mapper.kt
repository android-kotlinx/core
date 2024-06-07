package com.velox.lazeir.utils.handler

interface Mapper<I, O> {
    fun map(input: I): O
}
//cr velox