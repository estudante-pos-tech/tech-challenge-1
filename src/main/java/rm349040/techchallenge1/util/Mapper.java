package rm349040.techchallenge1.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Generally, this class converts one object of a type T into another object of another type S.<br>
 * Sometimes, it copies properties from an object of a type T into another object of the same tye T.
 */
@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public <T, S> S toDomain(T source, Class<S> destination) {
        return modelMapper.map(source, destination);
    }

    public <T, S> S toDto(T source, Class<S> destination) {
        return modelMapper.map(source,destination);
    }

    /**
     * Turns destination's settings equal to source's settings.
     * @param source the instance we read settings from
     * @param destination the instance we write settingg to
     * @param <T> a type
     */
    public <T> void identify(T source, T destination){
        modelMapper.map(source,destination);
    }

}
