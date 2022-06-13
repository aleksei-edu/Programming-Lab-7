package com.lapin.common.impl;

import com.lapin.common.client.Client;
import com.lapin.common.utility.CommandManager;
import com.lapin.common.utility.OutManager;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;
import com.lapin.network.config.NetworkConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelpTest {
    Help help;

    @BeforeEach
    void setUp() {
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        NetworkConfigurator config = new NetworkConfigurator() {};
        Client client = new Client(config);
        CommandManager commandManager = new CommandManager(client);
        help = new Help();
    }

    @Test
    void execute() {
        help.execute("", null);
        assertEquals("add – добавить новый элемент в коллекцию\n" +
                "add_if_max – добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "clear – очистить коллекцию\n" +
                "execute_script – считать и исполнить скрипт из указанного файла.\n" +
                "exit_server – завершает работу серверного приложения (с сохранением в файл)\n" +
                "help – вывести справку по доступным командам\n" +
                "history – вывести последние 10 команд (без их аргументов)\n" +
                "info – вывести в стандартный поток вывода информацию о коллекции тип, дата инициализации, количество элементов и т.д.\n" +
                "max_by_creation_date – вывести любой объект из коллекции, значение поля creationDate которого является максимальным\n" +
                "min_by_distance – вывести любой объект из коллекции, значение поля distance которого является минимальным\n" +
                "remove_all_by_distance – удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному\n" +
                "remove_by_id – удалить элемент из коллекции по его id\n" +
                "remove_lower – удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "save – сохранить коллекцию в файл\n" +
                "show – вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "update – обновить значение элемента коллекции, id которого равен заданному", OutManager.pop().getSecond());
    }
}