package com.lapin.server.utility;

import com.lapin.common.exception.CommandNotFoundException;
import com.lapin.common.interaction.StatusCodes;
import com.lapin.di.context.ApplicationContext;
import com.lapin.server.App;
import com.lapin.common.commands.Command;

import lombok.Getter;


/**
 * Класс управляет командами
 */
@Getter
public class CommandManager {


    /**
     * Метод запускает выполнение команды
     *
     * @param userCommand команда введенная пользователем
     * @param argument    аргумент команды введенной пользователем
     */

    public static StatusCodes execute(String userCommand, String argument) {
        try {
            Object obj = ApplicationContext.getInstance().getBean(userCommand);
            Command command = (Command) (obj instanceof Command ? obj : null);
            if (command != null) {
                command.execute(argument);
                return StatusCodes.OK;
            }
            else {
                App.logger.error("Команда не найдена!");
            }
        } catch (CommandNotFoundException e) {
            App.logger.error("Команда не найдена!");
        } catch (Exception e) {
            App.logger.error("Не удалось исполнить команду");
        }
        return StatusCodes.SERVER_ERROR;
    }
}
