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

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * @project simple-task-board
 * @created 05.05.2020 18:02
 * <p>
 * @author Alexander A. Kropotin
 */
public class SimpleTaskBoardTablePanel extends JPanel {

    SimpleTaskBoardTable simpleTaskBoardTable;

    public SimpleTaskBoardTablePanel() {
        super();
        setLayout(new BorderLayout());

        Object[] columnNames = {"number", "state", "content"};
        Object[][] data = new Object[5][3];
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (int i = 0; i < 5; i++) {
            model.addRow(new Object[]{"1", "2", "3"});
        }
        this.simpleTaskBoardTable = new SimpleTaskBoardTable(model);

        this.simpleTaskBoardTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Add header in NORTH slot
        this.add(this.simpleTaskBoardTable.getTableHeader(), BorderLayout.NORTH);

        // Add table itself to CENTER slot
        this.add(this.simpleTaskBoardTable, BorderLayout.CENTER);
    }
}
