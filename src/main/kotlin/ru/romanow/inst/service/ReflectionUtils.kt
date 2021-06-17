package ru.romanow.inst.service

import kotlin.reflect.full.declaredMemberProperties

class ReflectionUtils {
    companion object {
        fun getFieldValues(obj: Any, matchNulls: Boolean = false): Array<String> {
            return obj::class.declaredMemberProperties
                .stream()
                .filter { m -> matchNulls || m.getter.call(obj) != null }
                .map { m -> "${m.name} -> ${m.getter.call(obj)}" }
                .toArray { size -> arrayOfNulls<String>(size) }
        }
    }
}