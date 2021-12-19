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
package org.simple.task.board.assembly;

import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.util.DisposeAwareRunnable;

/**
 * The type Project initialization handler.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 05.05.2020 09:12 <p>
 */
public class ProjectInitializationHandler {

    /**
     * Run when initialized.
     *
     * @param project  the project
     * @param runnable the runnable
     */
    public static void runWhenInitialized(final Project project, final Runnable runnable) {
        if (project.isDisposed()) return;

        if (!project.isInitialized()) {
            StartupManager.getInstance(project)
                    .registerPostStartupActivity(DisposeAwareRunnable.create(runnable, project));

            return;
        }

        runDumbAware(project, runnable);
    }

    /**
     * Run dumb aware.
     *
     * @param project  the project
     * @param runnable the runnable
     */
    public static void runDumbAware(final Project project, final Runnable runnable) {
        if (DumbService.isDumbAware(runnable)) {
            runnable.run();
        } else {
            DumbService.getInstance(project)
                    .runWhenSmart(DisposeAwareRunnable.create(runnable, project));
        }
    }
}
