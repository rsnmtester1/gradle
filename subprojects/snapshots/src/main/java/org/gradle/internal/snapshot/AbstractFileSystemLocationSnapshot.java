/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.snapshot;

import java.nio.file.Paths;
import java.util.List;

public abstract class AbstractFileSystemLocationSnapshot implements FileSystemLocationSnapshot {
    private final String absolutePath;
    private final String name;

    public AbstractFileSystemLocationSnapshot(String absolutePath, String name) {
        this.absolutePath = absolutePath;
        this.name = name;
    }

    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPrefix() {
        return getName();
    }

    @Override
    public FileSystemNode update(String path, MetadataSnapshot snapshot) {
        return this;
    }

    protected static MissingFileSnapshot missingSnapshotForAbsolutePath(String filePath) {
        return new MissingFileSnapshot(filePath, getFileNameForAbsolutePath(filePath));
    }

    protected static String getFileNameForAbsolutePath(String filePath) {
        return Paths.get(filePath).getFileName().toString();
    }

    @Override
    public FileSystemNode withPrefix(String newPrefix) {
        return new SnapshotFileSystemNode(newPrefix, this);
    }

    @Override
    public void collect(int depth, List<String> prefixes) {
    }
}
