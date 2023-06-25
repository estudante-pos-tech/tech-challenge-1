package rm349040.techchallenge1.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class converts one object of a type into another object of another type.
 */
@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public <T, S> S fromDtoToDomain(T source, Class<S> destination) {
        return modelMapper.map(source, destination);
    }

    public <T, S> S fromDomainToDto(T source, Class<S> destination) {
        return modelMapper.map(source,destination);
    }

}
