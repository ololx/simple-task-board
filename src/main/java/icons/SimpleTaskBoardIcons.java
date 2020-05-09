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
package icons;

import com.intellij.ui.IconManager;

import javax.swing.*;

/**
 * The type Simple task board icons.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 09.05.2020 14:05 <p>
 */
public final class SimpleTaskBoardIcons {

    /**
     * The type Tool bar actions.
     */
    public static class ToolBarActions {

        /**
         * The constant AddNewTask.
         */
        /* 16x16*/ public static final Icon AddNewTask = load("/icons/AddNewTask.svg");

        /**
         * The constant DeleteTasks.
         */
        /* 16x16*/ public static final Icon DeleteTasks = load("/icons/DeleteTasks.svg");
    }

    /**
     * Instantiates a new Simple task board icons.
     */
    public SimpleTaskBoardIcons() {
    }

    private static Icon load(String path) {
        return IconManager.getInstance().getIcon(path, SimpleTaskBoardIcons.class);
    }

    private static Icon load(String path, Class<?> clazz) {
        return IconManager.getInstance().getIcon(path, clazz);
    }
}

