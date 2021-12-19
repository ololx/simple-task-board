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

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * The type Board item detail.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 10.05.2020 09:07 <p>
 */
@XmlType(name = "item")
public class BoardItemDetail {

    private Long id;

    private BoardItemStateDetail state;

    private String name;

    /**
     * Instantiates a new Board item detail.
     */
    public BoardItemDetail() {
    }

    /**
     * Instantiates a new Board item detail.
     *
     * @param id    the id
     * @param state the state
     * @param name  the name
     */
    public BoardItemDetail(Long id, String state, String name) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(state);
        Objects.requireNonNull(name);
        this.id = id;
        this.state = BoardItemStateDetail.fromString(state);
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @XmlAttribute(name = "id")
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    @XmlAttribute(name = "state")
    public String getState() {
        return this.state.value;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        Objects.requireNonNull(state);
        this.state = BoardItemStateDetail.fromString(state);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @XmlValue
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name != null ? name.trim() : null;
    }

    @Override
    public String toString() {
        return String.format(
                "[id: %s,%nstate: %s, name: %s]",
                this.id,
                this.state,
                this.name
        );
    }
}
