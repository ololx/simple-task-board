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
 * @project simple-task-board
 * @created 18.12.2020 21:32
 * <p>
 * @author Alexander A. Kropotin
 */
public class BoardRepository {

    private File boardFile;

    private JAXBContext context;

    public BoardRepository(File boardFile, Class<?> clazz) {
        Objects.requireNonNull(boardFile, "A file couldn't be null");
        Objects.requireNonNull(clazz, "The clazz couldn't be null");

        try {
            this.boardFile = boardFile;
            this.context = JAXBContext.newInstance(clazz);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public BoardDetail write(BoardDetail board) {
        try {
            JAXBContext context = JAXBContext.newInstance(BoardDetail.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(board, boardFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    public BoardDetail read() {
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
}
