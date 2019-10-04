package io.microsamples.maps;

import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

@Mapper
interface FromToMapper {
    FromToMapper INSTANCE = Mappers.getMapper( FromToMapper.class );
    ToBean mapFromToTo(FromBean fromBean);
    List<ToBean> mapFromToTo(List<FromBean> fromBeans);
}

@Data
class FromBean {
    private String firstName;
    private String lastName;
}
@Data
class ToBean {
    private String firstName;
    private String lastName;
}
