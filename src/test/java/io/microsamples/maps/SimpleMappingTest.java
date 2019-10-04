package io.microsamples.maps;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleMappingTest {

    private EasyRandom easyRandom = new EasyRandom();

    @Test
    void fromToMappingTest(){

        final List<FromBean> fromBeans = easyRandom.objects(FromBean.class, 13)
                .collect(Collectors.toList());

        final List<ToBean> tos = FromToMapper.INSTANCE.mapFromToTo(fromBeans);

        assertThat(tos).hasSameSizeAs(fromBeans);
        assertThat(tos).extracting("firstName").contains(fromBeans.get(0).getFirstName());
    }
}
