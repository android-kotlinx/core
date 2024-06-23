package com.androidx.core.utils.handler

interface Mapper<I, O> {
    fun map(input: I): O
}
//cr velox