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
import org.simple.task.board.ui.StbTable;

import javax.swing.table.DefaultTableModel;

/**
 * The type Delete task action.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 09.05.2020 14:24 <p>
 */
public class DeleteTaskAction extends AnAction {

    /**
     * The constant ID.
     */
    public static final String ID = "SimpleTaskBoard.ToolBar.DeleteTask";

    @Override
    public void actionPerformed(AnActionEvent e) {
        StbTable table = ProcessesTaskAction.getEventComponent(e);
        if (table == null) return;

        DefaultTableModel model = table.getModel();
        int[] selectedRowIndexes = table.getSelectedRows();

        if (selectedRowIndexes.length == 0) {
            model.removeRow(table.getRowCount() - 1);
        } else {
            for (int i = selectedRowIndexes.length - 1; i >= 0; i--) {
                model.removeRow(selectedRowIndexes[i]);
            }
        }
    }
}