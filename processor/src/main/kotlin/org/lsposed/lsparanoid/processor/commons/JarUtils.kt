/*
 * Copyright 2023 LSPosed
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lsposed.lsparanoid.processor.commons

import java.io.File
import java.util.WeakHashMap
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream
import java.util.zip.ZipException

private val trackedEntriesMap = WeakHashMap<JarOutputStream, MutableSet<String>>()

private val JarOutputStream.trackedEntries: MutableSet<String>
    get() = trackedEntriesMap.computeIfAbsent(this) { mutableSetOf() }

internal fun JarOutputStream.createFile(name: String, data: ByteArray) {
    val entryName = name.replace(File.separatorChar, '/')
    if (trackedEntries.contains(entryName)) return

    try {
        putNextEntry(JarEntry(entryName))
        write(data)
        closeEntry()
        trackedEntries.add(entryName)
    } catch (e: ZipException) {
        // it's normal to have duplicated files in META-INF
        if (!entryName.startsWith("META-INF")) throw e
    }
}

internal fun JarOutputStream.createDirectory(name: String) {
    try {
        putNextEntry(JarEntry(name.replace(File.separatorChar, '/')))
        closeEntry()
    } catch (ignored: ZipException) {
        // it's normal that the directory already exists
    }
}
