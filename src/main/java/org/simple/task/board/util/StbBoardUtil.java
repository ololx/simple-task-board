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
package org.simple.task.board.util;

import org.simple.task.board.model.StbBoard;

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

    static boolean chekStbBoardExistance(String filePath) {
        return new File(filePath + "/.idea/simpleTaskBoard.xml").exists();
    }

    static File loadFileOrCreateIfNotExist(String filePath) {
        File file = new File(filePath + "/.idea/simpleTaskBoard.xml");
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

    static StbBoard loadBoard(String filePath) {
        File boardFile = loadFileOrCreateIfNotExist(filePath);

        StbBoard boardFromFile = null;
        try {
            JAXBContext context = JAXBContext.newInstance(StbBoard.class);
            Unmarshaller um = context.createUnmarshaller();
            boardFromFile = (StbBoard) um.unmarshal(new FileReader(boardFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return boardFromFile;
    }

    static void saveBoard(String filePath, StbBoard board) {
        File boardFile = loadFileOrCreateIfNotExist(filePath);

        try {
            JAXBContext context = JAXBContext.newInstance(StbBoard.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(board, boardFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
