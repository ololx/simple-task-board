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
package org.simple.task.board.interactor;

import org.simple.task.board.entity.BoardDetail;
import org.simple.task.board.resources.SimpleTaskBoardStoredDataPath;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @project simple-task-board
 * @created 11.05.2020 19:49
 * <p>
 * @author Alexander A. Kropotin
 */
public interface StbBoardUtil {

    static boolean checkStbBoardExistence(String filePath) {
        return new File(filePath + "/.idea/simpleTaskBoard.xml").exists();
    }

    static File loadFileOrCreateIfNotExist(String projectPath) {
        File file = SimpleTaskBoardStoredDataPath.getPath(projectPath).toFile();
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists. " + file.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    static BoardDetail loadBoard(String filePath) {
        File boardFile = loadFileOrCreateIfNotExist(filePath);

        BoardDetail boardFromFile = null;
        try {
            JAXBContext context = JAXBContext.newInstance(BoardDetail.class);
            Unmarshaller um = context.createUnmarshaller();
            boardFromFile = (BoardDetail) um.unmarshal(new FileReader(boardFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return boardFromFile;
    }

    static void saveBoard(String filePath, BoardDetail board) {
        File boardFile = loadFileOrCreateIfNotExist(filePath);

        try {
            JAXBContext context = JAXBContext.newInstance(BoardDetail.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(board, boardFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
