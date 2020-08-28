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
package org.simple.task.board.ui;

import javax.swing.*;

/**
 * @project simple-task-board
 * @created 28.08.2020 20:52
 * <p>
 * @author Alexander A. Kropotin
 */
public class StbChooseCellEditor extends DefaultCellEditor {

    public StbChooseCellEditor(String[] items) {
        super(new JComboBox(items));
    }
}
