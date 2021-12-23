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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 * The type Abstract simple task board data file.
 *
 * @author Alexander A. Kropotin
 * @project simple -task-board
 * @created 18.12.2020 21:32 <p>
 */
public class AbstractSimpleTaskBoardDataFile {

    private File dataFile;

    private JAXBContext context;

    /**
     * Instantiates a new Abstract simple task board data file.
     *
     * @param dataFile the data file
     * @param clazz    the clazz
     */
    public AbstractSimpleTaskBoardDataFile(File dataFile, Class<?> clazz) {
        Objects.requireNonNull(dataFile, "A file couldn't be null");
        Objects.requireNonNull(clazz, "The clazz couldn't be null");

        try {
            this.dataFile = dataFile;
            this.context = JAXBContext.newInstance(clazz);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write board detail.
     *
     * @param board the board
     * @return the board detail
     */
    public BoardDetail write(BoardDetail board) {
        try {
            JAXBContext context = JAXBContext.newInstance(BoardDetail.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(board, dataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    /**
     * Read board detail.
     *
     * @return the board detail
     */
    public BoardDetail read() {
        BoardDetail boardFromFile = null;
        try {
            JAXBContext context = JAXBContext.newInstance(BoardDetail.class);
            Unmarshaller um = context.createUnmarshaller();
            FileReader fileReader = new FileReader(dataFile);
            boardFromFile = (BoardDetail) um.unmarshal(fileReader);
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return boardFromFile;
    }
}
