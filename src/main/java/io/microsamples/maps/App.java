package io.microsamples.maps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;
import org.mapstruct.Mapper;
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

@Builder
@Data
class ValidateEligibilityAction implements Action {

    private MemberStatus memberStatus;

    @Override
    public void execute(Facts facts) {
        memberStatus.setEligible(true);
    }
}

@Data
@AllArgsConstructor
class MemberStatus {
    private boolean eligible;
}


@Value
@Builder
class Plan {
    private String name;
    private boolean good;
}

@Value
@Builder
class Member {
    private String name;
    private boolean valid;
    private boolean eligible;
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
