package by.alia.servletproject.service;

import by.alia.servletproject.exception.RepositoryException;
import by.alia.servletproject.exception.ServiceException;
import by.alia.servletproject.model.Person;
import by.alia.servletproject.repository.PersonRepository;
import by.alia.servletproject.repository.RepositoryCreator;

import java.util.List;

public class PersonService {
    public List<Person> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            PersonRepository personRepository = repositoryCreator.getPersonRepository();
            return personRepository.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(Person person) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            PersonRepository personRepository = repositoryCreator.getPersonRepository();
            personRepository.save(person); } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
