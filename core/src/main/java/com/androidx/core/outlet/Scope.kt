package com.androidx.core.outlet

import com.androidx.core.utils.scope.inDefaultScope
import com.androidx.core.utils.scope.inIoScope
import com.androidx.core.utils.scope.inMainScope
import com.androidx.core.utils.scope.inUnconfinedScope
import kotlinx.coroutines.Job


fun ioScope(fu:()->Unit): Job =inIoScope(fu)
fun mainScope(fu:()->Unit): Job = inMainScope(fu)
fun defaultScope(fu:()->Unit): Job = inDefaultScope(fu)
fun unconfinedScope(fu:()->Unit): Job =inUnconfinedScope(fu)