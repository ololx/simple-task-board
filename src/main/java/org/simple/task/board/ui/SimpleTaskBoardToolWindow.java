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

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.ScrollPaneFactory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.simple.task.board.actions.ProcessesDataKeys;

import javax.swing.table.TableModel;

/**
 * The type Simple task board tool window.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 05.05.2020 14:44 <p>
 */
public class SimpleTaskBoardToolWindow extends SimpleToolWindowPanel {

    /**
     * The constant ID.
     */
    public static final String ID = "SimpleTaskBoard";

    private SimpleTaskBoardTable simpleTaskBoardToolWindowPanel;

    /**
     * Instantiates a new Simple task board panel.
     *
     * @param project the project
     */
    public SimpleTaskBoardToolWindow(Project project) {
        super(true, true);
        this.simpleTaskBoardToolWindowPanel = new SimpleTaskBoardTable();
        final ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar actionToolbar = actionManager.createActionToolbar("SimpleTaskBoard Toolbar",
                (DefaultActionGroup) actionManager.getAction("SimpleTaskBoard.ToolBar"), true);
        setToolbar(actionToolbar.getComponent());
        setContent(ScrollPaneFactory.createScrollPane(this.simpleTaskBoardToolWindowPanel));
    }

    /**
     * Instantiates a new Simple task board tool window.
     *
     * @param project        the project
     * @param taskBoardModel the task board model
     */
    public SimpleTaskBoardToolWindow(Project project, TableModel taskBoardModel) {
        super(true, true);
        this.simpleTaskBoardToolWindowPanel = new SimpleTaskBoardTable(taskBoardModel);
        final ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar actionToolbar = actionManager.createActionToolbar("SimpleTaskBoard Toolbar",
                (DefaultActionGroup) actionManager.getAction("SimpleTaskBoard.ToolBar"), true);
        setToolbar(actionToolbar.getComponent());
        setContent(ScrollPaneFactory.createScrollPane(this.simpleTaskBoardToolWindowPanel));
    }

    @Nullable
    public Object getData(@NonNls String dataId) {
        if (ProcessesDataKeys.PROCESSES_TASKS.is(dataId)) {
            return this.simpleTaskBoardToolWindowPanel;
        }

        return super.getData(dataId);
    }
}