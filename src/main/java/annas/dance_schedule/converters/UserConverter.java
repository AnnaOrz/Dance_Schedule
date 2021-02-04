package annas.dance_schedule.converters;

import annas.dance_schedule.model.User;
import annas.dance_schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(String source) {
        return userRepository.getOne(Long.parseLong(source));
    }
}

