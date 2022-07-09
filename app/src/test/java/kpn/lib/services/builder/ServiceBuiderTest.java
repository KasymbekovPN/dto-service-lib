package kpn.lib.services.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.code.Code;
import kpn.lib.collection.Collection;
import kpn.lib.exceptions.DTOServiceException;
import kpn.lib.services.DTOServices;
import kpn.lib.services.parts.deleting.executor.DeletingAllExecutor;
import kpn.lib.services.parts.deleting.executor.DeletingByIdExecutor;
import kpn.lib.services.parts.deleting.service.DeletingService;
import kpn.lib.services.parts.loading.executor.LoadingAllExecutor;
import kpn.lib.services.parts.loading.executor.LoadingByIdExecutor;
import kpn.lib.services.parts.loading.service.LoadingService;
import kpn.lib.services.parts.predicate.executor.PredicateExecutor;
import kpn.lib.services.parts.predicate.service.PredicateService;
import kpn.lib.services.parts.saving.executor.SavingExecutor;
import kpn.lib.services.parts.saving.service.SavingService;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class ServiceBuiderTest {
    private final Function<ServiceResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();

    private static final String SAVER_SET_AS_SERVICE = "saver set as service";
    private static final String SAVER_SET_AS_EXECUTOR = "saver set as executor";

    private static final String LOADER_SET_AS_SERVICE = "loader set as service";
    private static final String LOADER_BY_ID_SET_AS_EXECUTOR = "loader by id set as executor";
    private static final String LOADER_ALL_SET_AS_EXECUTOR = "loader all set as executor";

    private static final String DELETER_SET_AS_SERVICE = "deleter set as service";
    private static final String DELETER_BY_ID_SET_AS_EXECUTOR = "deleter by id set as executor";
    private static final String DELETER_ALL_SET_AS_EXECUTOR = "deleter all set as executor";

    private static final String PREDICATE_EXECUTOR_SET_AS_SERVICE = "predicate executor set as service";
    private static final String PREDICATE_EXECUTOR_SET_AS_EXECUTOR = "predicate executor set as executor";
    
    @Test
    void shouldCheckSaver_notSet() throws NoSuchFieldException, SecurityException{
        ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>> buider = new ServiceBuider<>(converter);
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services = buider.build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(Code.SERVICE_SAVING_UNSUPPORTED.getValue()));
        TestResult<TestDomain> result = services.saver().save(new TestDomain());
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckSaver_setAsService(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .saver(createSavingService())
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(SAVER_SET_AS_SERVICE));
        TestResult<TestDomain> result = services.saver().save(new TestDomain());
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestSavingService createSavingService(){
        TestSavingService service = Mockito.mock(TestSavingService.class);
        Mockito
            .when(service.save(Mockito.anyObject()))
            .thenReturn(new ImmutableServiceResult<>(SAVER_SET_AS_SERVICE));
        return service;
    }
    private abstract class TestSavingService implements SavingService<TestDomain, ServiceResult<TestDomain>>{}

    @Test
    void shouldCheckSaver_setAsExecutor(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .saverBuider()
                .executor(new TestSavingExecutor())
                .build()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(SAVER_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.saver().save(new TestDomain());
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestSavingExecutor implements SavingExecutor<TestDomain>{
        @Override
        public Collection<TestDomain> save(TestDomain domain) throws DTOServiceException {
            throw new DTOServiceException(SAVER_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckLoader_notSet(){
        ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>> buider = new ServiceBuider<>(converter);
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services = buider.build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(Code.SERVICE_LOADING_ALL_UNSUPPORTED.getValue()));
        TestResult<TestDomain> result = services.loader().all();
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckLoader_setAsService(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .loader(createLoadingService())
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(LOADER_SET_AS_SERVICE));
        TestResult<TestDomain> result = services.loader().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestLoadingService createLoadingService(){
        TestLoadingService service = Mockito.mock(TestLoadingService.class);
        Mockito
            .when(service.all())
            .thenReturn(new ImmutableServiceResult<>(LOADER_SET_AS_SERVICE));
        return service;
    }
    private abstract class TestLoadingService implements LoadingService<Long, ServiceResult<TestDomain>>{}

    @Test
    void shouldCheckLoader_setAsByIdExecutor(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .loaderBuilder()
                .executorById(new TestLoadingByIdExecutor())
                .build()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(LOADER_BY_ID_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.loader().byId(1L);
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestLoadingByIdExecutor implements LoadingByIdExecutor<Long, TestDomain>{
        @Override
        public Collection<TestDomain> load(Long id) throws DTOServiceException {
            throw new DTOServiceException(LOADER_BY_ID_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckLoader_setAsAllExecutor(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .loaderBuilder()
                .executorAll(new TestLoadingAllExecutor())
                .build()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(LOADER_ALL_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.loader().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestLoadingAllExecutor implements LoadingAllExecutor<TestDomain>{
        @Override
        public Collection<TestDomain> load() throws DTOServiceException {
            throw new DTOServiceException(LOADER_ALL_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckDeleter_notSet(){
        ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>> buider = new ServiceBuider<>(converter);
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services = buider.build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(Code.SERVICE_DELETING_ALL_UNSUPPORTED.getValue()));
        TestResult<TestDomain> result = services.deleter().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckDeleter_setAsService(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .deleter(createDeletingService())
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(DELETER_SET_AS_SERVICE));
        TestResult<TestDomain> result = services.deleter().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestDeletingService createDeletingService(){
        TestDeletingService service = Mockito.mock(TestDeletingService.class);
        Mockito
            .when(service.all())
            .thenReturn(new ImmutableServiceResult<>(DELETER_SET_AS_SERVICE));
        return service;
    }
    private abstract class TestDeletingService implements DeletingService<Long, ServiceResult<TestDomain>>{}

    @Test
    void shouldCheckDeleter_setAsByIdExecutor(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .deleterBuilder()
                .executorById(new TestDeletingByIdExecutor())
                .build()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(DELETER_BY_ID_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.deleter().byId(1L);
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestDeletingByIdExecutor implements DeletingByIdExecutor<Long, TestDomain>{
        @Override
        public Collection<TestDomain> delete(Long id) throws DTOServiceException {
            throw new DTOServiceException(DELETER_BY_ID_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckDeleter_setAsAllExecutor(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .deleterBuilder()
                .executorAll(new TestDeletingAllExecutor())
                .build()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(DELETER_ALL_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.deleter().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestDeletingAllExecutor implements DeletingAllExecutor<TestDomain>{
        @Override
        public Collection<TestDomain> delete() throws DTOServiceException {
            throw new DTOServiceException(DELETER_ALL_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckPredicateExecutor_notSet(){
        ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>> buider = new ServiceBuider<>(converter);
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services = buider.build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(Code.SERVICE_PREDICATE_UNSUPPORTED.getValue()));
        TestResult<TestDomain> result = services.executor().execute(new TestPredicate());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckPredicateExecutor_setAsService(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .predicateExecutor(createPredicateService())
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(PREDICATE_EXECUTOR_SET_AS_SERVICE));
        TestResult<TestDomain> result = services.executor().execute(new TestPredicate());
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestPredicateService createPredicateService(){
        TestPredicateService service = Mockito.mock(TestPredicateService.class);
        Mockito
            .when(service.execute(Mockito.anyObject()))
            .thenReturn(new ImmutableServiceResult<>(PREDICATE_EXECUTOR_SET_AS_SERVICE));
        return service;
    }
    private abstract class TestPredicateService implements PredicateService<TestPredicate, ServiceResult<TestDomain>>{}

    @Test
    void shouldCheckPredicateExecutor_setAsExecutor(){
        DTOServices<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .predicateExecutorBuidler()
                .executor(new TestPredicateExecutor())
                .build()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new ImmutableServiceResult<>(PREDICATE_EXECUTOR_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.executor().execute(new TestPredicate());
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestPredicateExecutor implements PredicateExecutor<TestPredicate, TestDomain>{
        @Override
        public Collection<TestDomain> execute(TestPredicate predicate) throws DTOServiceException {
            throw new DTOServiceException(PREDICATE_EXECUTOR_SET_AS_EXECUTOR);
        }
    }
}
