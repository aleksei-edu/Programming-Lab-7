package com.lapin.common.impl;

import com.lapin.common.utility.OutManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelpTest {
    Help help;
    @BeforeEach
    public void CreateHelp(){
        help = new Help();
    }
    @Test
    void execute() {
        help.execute("", null);
        assertEquals("help – вывести справку по доступным командам\n" +
                "remove_by_id – удалить элемент из коллекции по его id\n" +
                "show – вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "update – обновить значение элемента коллекции, id которого равен заданному\n" +
                "exit – завершить программу (без сохранения в файл)\n" +
                "remove_all_by_distance – удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному\n" +
                "add – добавить новый элемент в коллекцию\n" +
                "min_by_distance – вывести любой объект из коллекции, значение поля distance которого является минимальным\n" +
                "remove_lower – удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "execute_script – считать и исполнить скрипт из указанного файла.\n" +
                "save – сохранить коллекцию в файл\n" +
                "add_if_max – добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "info – вывести в стандартный поток вывода информацию о коллекции тип, дата инициализации, количество элементов и т.д.\n" +
                "max_by_creation_date – вывести любой объект из коллекции, значение поля creationDate которого является максимальным\n" +
                "clear – очистить коллекцию\n" +
                "history – вывести последние 10 команд (без их аргументов)\n", OutManager.pop().getSecond());
    }
}