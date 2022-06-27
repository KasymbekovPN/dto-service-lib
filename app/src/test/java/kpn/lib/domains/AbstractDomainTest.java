package kpn.lib.domains;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

class AbstractDomainTest {
    
    private static final String DEFAULT_GETTING_RESULT = "-";
    private static final String KEY__NOT_ID = "notId";
    private static final String KEY__ID = "id";
    private static final String ID_AS_STRING = "123";
    private static final long ID_AS_LONG = 123L;

    @Test
    void shouldCheckDefaultGetting(){
        assertThat(new TestDomain().getId()).isNull();
    }

    @Test
    void shouldCheckSettingGetting(){
        TestDomain domain = createDomain(ID_AS_LONG);

        assertThat(domain.getId()).isEqualTo(ID_AS_LONG);
    }

    @Test
    void shouldCheckFailInDeepGetting_byQueue_whenQueueIsEmpty(){
        String result = new TestDomain().getInDeep(new ArrayDeque<>());

        assertThat(result).isEqualTo(DEFAULT_GETTING_RESULT);
    }

    @Test
    void shouldCheckFailInDeepGetting_byQueue_whenGetterIsAbsent(){
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.addFirst(KEY__NOT_ID);
        String result = new TestDomain().getInDeep(queue);

        assertThat(result).isEqualTo(DEFAULT_GETTING_RESULT);
    }

    @Test
    void shouldCheckInDeepGetting_byQueue(){
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.addFirst(KEY__ID);
        String result = createDomain(ID_AS_LONG).getInDeep(queue);

        assertThat(result).isEqualTo(ID_AS_STRING);
    }

    @Test
    void shouldCheckInDeepGetting_byArgs(){
        String result = createDomain(ID_AS_LONG).getInDeep(KEY__ID);

        assertThat(result).isEqualTo(ID_AS_STRING);
    }

    @Test
    void shouldCheckEqualily(){
        assertThat(createDomain(ID_AS_LONG)).isEqualTo(createDomain(ID_AS_LONG));
    }

    @Test
    void shouldCheckInequality(){
        assertThat(createDomain(ID_AS_LONG)).isNotEqualTo(createDomain(ID_AS_LONG + 1));
    }

    @Test
    void shouldCheckHashCodes_equlityObjects(){
        assertThat(createDomain(ID_AS_LONG)).hasSameHashCodeAs(createDomain(ID_AS_LONG));
    }

    @Test
    void shouldCheckHashCodes_inequlityObjects(){
        assertThat(createDomain(ID_AS_LONG).hashCode()).isNotEqualTo(createDomain(ID_AS_LONG + 1).hashCode());
    }

    private TestDomain createDomain(long id){
        TestDomain domain = new TestDomain();
        domain.setId(id);
        return domain;
    }

    private static class TestDomain extends AbstractDomain<Long>{

        private static final Map<String, Function<GetterArg<Long>, String>> GETTERS = Map.of(
            "id",
            arg -> {
                return arg.getDomain().getId().toString();
            }
        );

        @Override
        public String getInfo() {return null;}

        @Override
        protected Map<String, Function<GetterArg<Long>, String>> takeGetters() {
            return GETTERS;
        }
    }
}
