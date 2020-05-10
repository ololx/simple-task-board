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
import org.simple.task.board.model.StbComponent;
import org.simple.task.board.model.StbProject;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;

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

        try {
            // create JAXB context and instantiate marshaller
            JAXBContext context = JAXBContext.newInstance(StbProject.class);

            File file = new File(project.getBasePath() + "/.idea/simpleTaskBoard.xml");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists. " + file.getPath());
            }

            // (2) Unmarshaller : Read XML content to Java Object.
            Unmarshaller um = context.createUnmarshaller();

            // XML file create before.
            StbProject deptFromFile = (StbProject) um.unmarshal(new FileReader(file));

            if (deptFromFile == null) return;

            StbComponent component = deptFromFile.getComponent();

            if (component == null) return;

            StbBoard board = component.getBoard();

            if (board == null) return;

            StbBoardItem[] items = board.getItems();

            if (items == null) return;

            for (StbBoardItem item : items) {
                System.err.println(item.getId() + " " + item.getState() + " " + item.getName());
                ((DefaultTableModel) simpleTaskBoardToolWindowPanel.getModel()).insertRow(0, new Object[]{
                        item.getId(), item.getState(), item.getName()
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public Object getData(@NonNls String dataId) {
        if (ProcessesDataKeys.PROCESSES_TASKS.is(dataId)) {
            return this.simpleTaskBoardToolWindowPanel;
        }

        return super.getData(dataId);
    }
}