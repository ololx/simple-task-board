/**
 * Copyright 2020 the project simple-task-board authors
 * and the original author or authors annotated by {@author}
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.simple.task.board.interactor;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * The type Stored file.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 17.12.2020 20:31 <p>
 */
public class StoredFile extends File implements SimpleTaskBoardDataFile {

    /**
     * Creates a new {@code File} instance by converting the given
     * pathname string into an abstract pathname.  If the given string is
     * the empty string, then the result is the empty abstract pathname.
     *
     * @param pathname A pathname string
     * @throws NullPointerException If the {@code pathname} argument is {@code null}
     */
    public StoredFile(@NotNull String pathname) {
        super(pathname);

        try {
            this.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
