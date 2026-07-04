/*
 * Copyright (C) 2012 Paul Burke
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

package com.ipaulpro.afilechooser;

import java.io.File;

/**
 * Holder for a File and its directory status to avoid disk I/O on the UI thread.
 */
public class FileItem {
    public final File file;
    public final boolean isDirectory;

    public FileItem(File file, boolean isDirectory) {
        this.file = file;
        this.isDirectory = isDirectory;
    }
}
