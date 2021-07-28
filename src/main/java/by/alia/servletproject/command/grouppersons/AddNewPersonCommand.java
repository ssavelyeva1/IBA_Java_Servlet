package by.alia.servletproject.command.grouppersons;

import by.alia.servletproject.command.Command;
import by.alia.servletproject.command.CommandResult;
import by.alia.servletproject.exception.IncorrectDataException;
import by.alia.servletproject.exception.ServiceException;
import by.alia.servletproject.model.Person;
import by.alia.servletproject.service.PersonService;
import by.alia.servletproject.util.pages.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.alia.servletproject.command.grouppersons.constant.GroupConstant.*;

import java.util.List;
import java.util.Optional;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class AddNewPersonCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddNewPersonCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IncorrectDataException {
        PersonService personService = new PersonService();
        Optional<String> newName = of(request)
                .map(httpServletRequest ->
                        httpServletRequest.getParameter(NEWNAME));
        Optional<String> newPhone = of(request)
                .map(httpServletRequest ->
                        httpServletRequest.getParameter(NEWPHONE));
        Optional<String> newEmail = of(request)
                .map(httpServletRequest ->
                        httpServletRequest.getParameter(NEWEMAIL));

        if (isEmpty(newName.get()) || isEmpty(newPhone.get()) || isEmpty(newEmail.get())) {
            logger.info("missing parameter for new person in addition mode");
            request.setAttribute(ERROR_MESSAGE, ERROR_MESSAGE_TEXT);
        } else {
            Person newperson = new Person(newName.get(), newPhone.get(), newEmail.get());
            personService.save(newperson);
        }

        List<Person> clients = personService.findAll();

        if (!clients.isEmpty()) {
            request.setAttribute(LISTGROUP, clients);
        }

        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }
}