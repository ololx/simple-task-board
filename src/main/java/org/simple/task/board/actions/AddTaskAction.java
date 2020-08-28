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
package org.simple.task.board.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.table.JBTable;
import org.simple.task.board.model.StbBoard;
import org.simple.task.board.ui.StbTable;

import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * The type Add new task action.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 09.05.2020 14:24 <p>
 */
public class AddTaskAction extends AnAction {

    /**
     * The constant ID.
     */
    public static final String ID = "SimpleTaskBoard.ToolBar.AddNewTask";

    @Override
    public void actionPerformed(AnActionEvent e) {
        StbTable table = ProcessesTaskAction.getEventComponent(e);
        if (table == null) return;

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int[] selectedRowIndexes = table.getSelectedRows();

        if (selectedRowIndexes.length == 0) {
            model.addRow(new Object[table.getColumnCount()]);
        } else {
            model.insertRow(selectedRowIndexes[selectedRowIndexes.length - 1], new Object[table.getColumnCount()]);
        }
    }
}