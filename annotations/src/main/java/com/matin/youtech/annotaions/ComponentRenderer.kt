package com.matin.youtech.annotaions

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ComponentRenderer(val component: KClass<out Component>)