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
package org.simple.task.board.stub;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ex.ToolWindowEx;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;
import org.simple.task.board.model.StbBoard;
import org.simple.task.board.ui.SimpleTaskBoardToolWindow;

import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;

/**
 * The type Simple task board initializer.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 05.05.2020 14:47 <p>
 */
public class SimpleTaskBoardInitializer implements ProjectComponent {

    private final Project project;

    private final SimpleTaskBoardToolWindow panel;

    /**
     * Instantiates a new Simple task board manager.
     *
     * @param project the project
     */
    protected SimpleTaskBoardInitializer(@NotNull final Project project) {
       this.project = project;
        Object[] columnNames = {"number", "state", "content"};
        Object[][] data = new Object[5][3];
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (int i = 0; i < 5; i++) {
            model.addRow(new Object[]{i*1, i*2, i*3});
        }

       this.panel = new SimpleTaskBoardToolWindow(project, model);
    }

    @Override
    public void initComponent() {
        ProjectInitializationHandler.runWhenInitialized(this.project, new Runnable() {
            @Override
            public void run() {
                initToolWindow();
            }
        });
    }

    private void initToolWindow() {
        final ToolWindowManagerEx manager = ToolWindowManagerEx.getInstanceEx(this.project);
        ToolWindowEx simpleTaskBoardToolWindow = (ToolWindowEx) manager.registerToolWindow(
                SimpleTaskBoardToolWindow.ID,
                false,
                ToolWindowAnchor.RIGHT,
                this.project,
                true
        );
        final ContentFactory contentFactory = ServiceManager.getService(ContentFactory.class);
        final Content content = contentFactory.createContent(
                panel.getComponent(),
                project.getName(),
                false
        );
        ContentManager contentManager = simpleTaskBoardToolWindow.getContentManager();
        contentManager.addContent(content);
    }
}
