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
package org.simple.task.board.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @project simple-task-board
 * @created 28.08.2020 20:22
 * <p>
 * @author Alexander A. Kropotin
 */
public enum StbState {

    NEW("new"),

    IN_PROGRESS("in progress"),

    DONE("done");

    String value;

    StbState(String value) {
        this.value = value;
    }

    /**
     * From string write mode.
     *
     * @param value the state value
     * @return the State
     */
    static StbState fromString(String value) {
        if (value != null && !value.isBlank()) {
            switch (value.trim().toLowerCase()) {
                case "in progress":
                    return IN_PROGRESS;
                case "done":
                    return DONE;
                case "new":
                default: return NEW;
            }
        }

        return  NEW;
    }

    public static String[] valuesAsString() {
        return Arrays.stream(StbState.values())
                .map(value -> value.value)
                .toArray(String[]::new);
    }
}