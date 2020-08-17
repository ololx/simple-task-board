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
import org.simple.task.board.model.StbBoard;
import org.simple.task.board.model.StbBoardItem;
import org.simple.task.board.util.StbBoardUtil;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;

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


        StbBoard board = null;
        if (!StbBoardUtil.chekStbBoardExistance(project.getBasePath())) {

            board = new StbBoard();
            board.setName(project.getName());
            board.setItems(Collections.EMPTY_LIST);
            StbBoardUtil.saveBoard(project.getBasePath(), board);
        } else {
            board = StbBoardUtil.loadBoard(project.getBasePath());
            if (board == null) board.setName(project.getName());
            if (board.getItems() == null) board.setItems(Collections.EMPTY_LIST);
        }

        for (StbBoardItem item : board.getItems()) {
            ((DefaultTableModel) this.simpleTaskBoardToolWindowPanel.getModel()).addRow(new Object[]{
                    item.getId(),
                    item.getState(),
                    item.getName(),
            });
        }

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