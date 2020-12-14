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

import com.intellij.ui.table.JBTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * The type Simple task board table.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 05.05.2020 18:02 <p>
 */
public class StbTable extends JBTable {

    /**
     * Instantiates a new Simple task board table.
     */
    public StbTable() {
        Object[] columnNames = {"number", "state", "name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        setModel(model);
    }

    /**
     * Instantiates a new Simple task board table.
     *
     * @param tableModel the table model
     */
    public StbTable(TableModel tableModel) {
        super(tableModel);
    }

    @Override
    public DefaultTableModel getModel() {
        return (DefaultTableModel) dataModel;
    }
}
