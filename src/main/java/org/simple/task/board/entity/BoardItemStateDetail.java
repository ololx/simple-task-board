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
package org.simple.task.board.entity;

import java.util.Arrays;

/**
 * The enum Board item state detail.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 28.08.2020 20:22 <p>
 */
public enum BoardItemStateDetail {

    /**
     * New board item state detail.
     */
    NEW("new"),

    /**
     * The In progress.
     */
    IN_PROGRESS("in progress"),

    /**
     * Done board item state detail.
     */
    DONE("done");

    /**
     * The Value.
     */
    String value;

    BoardItemStateDetail(String value) {
        this.value = value;
    }

    /**
     * From string write mode.
     *
     * @param value the state value
     * @return the State
     */
    public static BoardItemStateDetail fromString(String value) {
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

    /**
     * Values as string string [ ].
     *
     * @return the string [ ]
     */
    public static String[] valuesAsString() {
        return Arrays.stream(BoardItemStateDetail.values())
                .map(value -> value.value)
                .toArray(String[]::new);
    }

}