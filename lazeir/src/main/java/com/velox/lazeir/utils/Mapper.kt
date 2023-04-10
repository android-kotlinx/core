package com.velox.lazeir.utils

interface Mapper<I, O> {
    fun map(input: I): O
}
//cr velox