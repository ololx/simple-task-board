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
package org.simple.task.board.resources;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Simple task board stored data path.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 21.12.2020 20:45 <p>
 */
public final class SimpleTaskBoardStoredDataPath {

    /**
     * The constant DATA_FILE_PATH.
     */
    public static final String DATA_FILE_PATH = "/.idea/simpleTaskBoard.xml";

    /**
     * Instantiates a new Simple task board stored data path.
     */
    public SimpleTaskBoardStoredDataPath() {
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public static Path getPath() {
        return Paths.get(DATA_FILE_PATH);
    }

    /**
     * Gets path with prefix input.
     *
     * @param pathPrefix the path prefix (usually is                   a path to project directory)
     * @return the path
     */
    public static Path getPath(String pathPrefix) {
        return Paths.get(pathPrefix + DATA_FILE_PATH);
    }
}
