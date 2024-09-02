package com.matin.youtech.sdui

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.matin.youtech.annotaions.ComponentRenderer

class ComponentProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return ComponentProcessor(environment.codeGenerator, environment.logger)
    }
}

class ComponentProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(ComponentRenderer::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()

        if (symbols.iterator().hasNext()) {
            val file = codeGenerator.createNewFile(
                dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray()),
                packageName = "com.matin.youtech.crypto.sdui",
                fileName = "ComponentRendererMap"
            )

            file.bufferedWriter().use { writer ->
                writer.write("package com.matin.youtech.crypto.sdui\n\n")
                writer.write("import com.matin.youtech.annotaions.Component\n")
                writer.write("import com.matin.youtech.crypto.sdui.UIComponent\n\n")
                writer.write("val componentRenderers: Map<Class<out Component>, Class<out UIComponent<out Component>>> = mapOf(\n")

                symbols.forEach { symbol ->
                    val annotation =
                        symbol.annotations.first { it.shortName.asString() == "ComponentRenderer" }
                    val componentClass = annotation.arguments.first().value as KSType
                    writer.write("    ${componentClass.declaration.qualifiedName!!.asString()}::class.java to ${symbol.qualifiedName!!.asString()}::class.java,\n")
                }

                writer.write(")\n")
            }
        }

        return emptyList()
    }
}

