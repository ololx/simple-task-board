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

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.ScrollPaneFactory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.simple.task.board.presenter.ProcessesDataKeys;
import org.simple.task.board.entity.BoardDetail;
import org.simple.task.board.entity.BoardItemDetail;
import org.simple.task.board.entity.BoardItemStateDetail;
import org.simple.task.board.interactor.StbBoardUtil;

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

    private SimpleTaskBoardTable simpleTaskBoardTable;

    /**
     * Instantiates a new Simple task board panel.
     */
    public SimpleTaskBoardToolWindow() {
        super(true, true);
        this.simpleTaskBoardTable = new SimpleTaskBoardTable();
    }

    @Override
    @Nullable
    public Object getData(@NonNls String dataId) {
        if (ProcessesDataKeys.PROCESSES_TASKS.is(dataId)) {
            return this.simpleTaskBoardTable;
        }

        return super.getData(dataId);
    }

    /**
     * Init.
     *
     * @param project the project
     */
    public void init(Project project) {
        this.simpleTaskBoardTable.getColumnModel()
                .getColumn(1)
                .setCellEditor(new SimpleTaskBoardChooseEditor(BoardItemStateDetail.valuesAsString()));
        this.simpleTaskBoardTable.getColumnModel()
                .getColumn(1)
                .setCellRenderer(new SimpleTaskBoardChooseRender(BoardItemStateDetail.valuesAsString()));
        this.loadData(project);
        final ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar actionToolbar = actionManager.createActionToolbar("SimpleTaskBoard Toolbar",
                (DefaultActionGroup) actionManager.getAction("SimpleTaskBoard.ToolBar"), true);
        setToolbar(actionToolbar.getComponent());
        setContent(ScrollPaneFactory.createScrollPane(this.simpleTaskBoardTable));
    }

    private void loadData(Project project) {
        BoardDetail board = null;
        if (!StbBoardUtil.checkStbBoardExistence(project.getBasePath())) {
            board = new BoardDetail();
            board.setName(project.getName());
            board.setItems(Collections.EMPTY_LIST);
            StbBoardUtil.saveBoard(project.getBasePath(), board);
        } else {
            board = StbBoardUtil.loadBoard(project.getBasePath());
            if (board.getName() == null) {
                board.setName(project.getName());
            }

            if (board.getItems() == null) {
                board.setItems(Collections.EMPTY_LIST);
            }
        }

        for (BoardItemDetail item : board.getItems()) {
            (this.simpleTaskBoardTable.getModel()).addRow(new Object[]{
                    item.getId(),
                    item.getState(),
                    item.getName()
            });
        }
    }
}