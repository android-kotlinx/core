package com.androidx.core.utils.network_handler

interface Mapper<I, O> {
    fun map(input: I): O
}
//cr velox