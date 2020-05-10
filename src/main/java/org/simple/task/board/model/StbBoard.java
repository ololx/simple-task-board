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

import javax.xml.bind.annotation.*;

/**
 * @project simple-task-board
 * @created 10.05.2020 09:28
 * <p>
 * @author Alexander A. Kropotin
 */
@XmlType(name = "board")
public class StbBoard {

    String name;

    StbBoardItem[] items;

    public String getName() {
        return this.name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public StbBoardItem[] getItems() {
        return this.items;
    }

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public void setItems(StbBoardItem[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return String.format("name = %s, items:\n%s", this.name, this.items);
    }
}
