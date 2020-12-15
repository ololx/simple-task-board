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
package org.simple.task.board.presenter;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.simple.task.board.view.SimpleTaskBoardTable;

/**
 * The type Processes task action.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 06.05.2020 18:40 <p>
 */
public class ProcessesTaskAction extends AnAction {

    /**
     * Get event component jb table.
     *
     * @param e the e
     * @return the jb table
     */
    public static SimpleTaskBoardTable getEventComponent(AnActionEvent e){
        return ProcessesDataKeys.PROCESSES_TASKS.getData(e.getDataContext());
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Notifications.Bus.notify(new Notification("", "header", "Common action", NotificationType.INFORMATION));
    }
}