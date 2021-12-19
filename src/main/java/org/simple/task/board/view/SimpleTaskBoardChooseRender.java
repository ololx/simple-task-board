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
package org.simple.task.board.view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * The type Simple task board choose render.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 28.08.2020 21:17 <p>
 */
public class SimpleTaskBoardChooseRender extends JComboBox implements TableCellRenderer {

    /**
     * Instantiates a new Simple task board choose render.
     *
     * @param items the items
     */
    public SimpleTaskBoardChooseRender(String[] items) {
        super(items);
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {
        setSelectedItem(value);

        return this;
    }
}
