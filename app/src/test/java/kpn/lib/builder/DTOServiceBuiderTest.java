package kpn.lib.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.aspect.deleting.DeletingAspect;
import kpn.lib.aspect.loading.LoadingAspect;
import kpn.lib.aspect.predicate.PredicateAspect;
import kpn.lib.aspect.saving.SavingAspect;
import kpn.lib.buider.ServiceBuider;
import kpn.lib.code.ErrorCode;
import kpn.lib.exception.DTOException;
import kpn.lib.executor.deleting.CompletelyDeletingExecutor;
import kpn.lib.executor.loading.CompletelyLoadingExecutor;
import kpn.lib.executor.predicate.PredicateExecutor;
import kpn.lib.executor.saving.SavingExecutor;
import kpn.lib.service.Service;
import kpn.lib.executor.loading.ByIdLoadingExecutor;
import kpn.lib.executor.ExecutorResult;
import kpn.lib.executor.deleting.ByIdDeletingExecutor;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class DTOServiceBuiderTest {
    private final Function<AspectResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();

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
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services = buider.build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(ErrorCode.SERVICE_SAVING_UNSUPPORTED.getValue()));
        TestResult<TestDomain> result = services.saver().save(new TestDomain());
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckSaver_setAsService(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .savingAspect(createSavingService())
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(SAVER_SET_AS_SERVICE));
        TestResult<TestDomain> result = services.saver().save(new TestDomain());
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestSavingService createSavingService(){
        TestSavingService service = Mockito.mock(TestSavingService.class);
        Mockito
            .when(service.save(Mockito.anyObject()))
            .thenReturn(new DefaultAspectResult<>(SAVER_SET_AS_SERVICE));
        return service;
    }
    private abstract class TestSavingService implements SavingAspect<TestDomain, AspectResult<TestDomain>>{}

    @Test
    void shouldCheckSaver_setAsExecutor(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .savingAspectBuider()
                .executor(new TestSavingExecutor())
                .and()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(SAVER_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.saver().save(new TestDomain());
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestSavingExecutor implements SavingExecutor<TestDomain>{
        @Override
        public ExecutorResult<TestDomain> save(TestDomain domain) throws DTOException {
            throw new DTOException(SAVER_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckLoader_notSet(){
        ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>> buider = new ServiceBuider<>(converter);
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services = buider.build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(ErrorCode.SERVICE_LOADING_ALL_UNSUPPORTED.getValue()));
        TestResult<TestDomain> result = services.loader().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckLoader_setAsService(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .loadingAspect(createLoadingService())
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(LOADER_SET_AS_SERVICE));
        TestResult<TestDomain> result = services.loader().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestLoadingService createLoadingService(){
        TestLoadingService service = Mockito.mock(TestLoadingService.class);
        Mockito
            .when(service.all())
            .thenReturn(new DefaultAspectResult<>(LOADER_SET_AS_SERVICE));
        return service;
    }
    private abstract class TestLoadingService implements LoadingAspect<Long, AspectResult<TestDomain>>{}

    @Test
    void shouldCheckLoader_setAsByIdExecutor(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .loadingAspectBuilder()
                .executorById(new TestLoadingByIdExecutor())
                .and()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(LOADER_BY_ID_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.loader().byId(1L);
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestLoadingByIdExecutor implements ByIdLoadingExecutor<Long, TestDomain>{
        @Override
        public ExecutorResult<TestDomain> load(Long id) throws DTOException {
            throw new DTOException(LOADER_BY_ID_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckLoader_setAsAllExecutor(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .loadingAspectBuilder()
                .executorAll(new TestLoadingAllExecutor())
                .and()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(LOADER_ALL_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.loader().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestLoadingAllExecutor implements CompletelyLoadingExecutor<TestDomain>{
        @Override
        public ExecutorResult<TestDomain> load() throws DTOException {
            throw new DTOException(LOADER_ALL_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckDeleter_notSet(){
        ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>> buider = new ServiceBuider<>(converter);
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services = buider.build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(ErrorCode.SERVICE_DELETING_ALL_UNSUPPORTED.getValue()));
        TestResult<TestDomain> result = services.deleter().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckDeleter_setAsService(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .deletingAspect(createDeletingService())
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(DELETER_SET_AS_SERVICE));
        TestResult<TestDomain> result = services.deleter().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestDeletingService createDeletingService(){
        TestDeletingService service = Mockito.mock(TestDeletingService.class);
        Mockito
            .when(service.all())
            .thenReturn(new DefaultAspectResult<>(DELETER_SET_AS_SERVICE));
        return service;
    }
    private abstract class TestDeletingService implements DeletingAspect<Long, AspectResult<TestDomain>>{}

    @Test
    void shouldCheckDeleter_setAsByIdExecutor(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .deletingAspectBuilder()
                .executorById(new TestDeletingByIdExecutor())
                .and()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(DELETER_BY_ID_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.deleter().byId(1L);
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestDeletingByIdExecutor implements ByIdDeletingExecutor<Long, TestDomain>{
        @Override
        public ExecutorResult<TestDomain> delete(Long id) throws DTOException {
            throw new DTOException(DELETER_BY_ID_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckDeleter_setAsAllExecutor(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .deletingAspectBuilder()
                .executorAll(new TestDeletingAllExecutor())
                .and()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(DELETER_ALL_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.deleter().all();
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestDeletingAllExecutor implements CompletelyDeletingExecutor<TestDomain>{
        @Override
        public ExecutorResult<TestDomain> delete() throws DTOException {
            throw new DTOException(DELETER_ALL_SET_AS_EXECUTOR);
        }
    }

    @Test
    void shouldCheckPredicateExecutor_notSet(){
        ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>> buider = new ServiceBuider<>(converter);
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services = buider.build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(ErrorCode.SERVICE_PREDICATE_UNSUPPORTED.getValue()));
        TestResult<TestDomain> result = services.executor().execute(new TestPredicate());
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckPredicateExecutor_setAsService(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .predicateAspect(createPredicateService())
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(PREDICATE_EXECUTOR_SET_AS_SERVICE));
        TestResult<TestDomain> result = services.executor().execute(new TestPredicate());
        assertThat(result).isEqualTo(expectedResult);
    }

    private TestPredicateService createPredicateService(){
        TestPredicateService service = Mockito.mock(TestPredicateService.class);
        Mockito
            .when(service.execute(Mockito.anyObject()))
            .thenReturn(new DefaultAspectResult<>(PREDICATE_EXECUTOR_SET_AS_SERVICE));
        return service;
    }
    private abstract class TestPredicateService implements PredicateAspect<TestPredicate, AspectResult<TestDomain>>{}

    @Test
    void shouldCheckPredicateExecutor_setAsExecutor(){
        Service<Long, TestDomain, TestPredicate, TestResult<TestDomain>> services 
            = new ServiceBuider<Long, TestDomain, TestPredicate, TestResult<TestDomain>>(converter)
            .predicateAspectBuidler()
                .executor(new TestPredicateExecutor())
                .and()
            .build();

        TestResult<TestDomain> expectedResult = converter.apply(new DefaultAspectResult<>(PREDICATE_EXECUTOR_SET_AS_EXECUTOR));
        TestResult<TestDomain> result = services.executor().execute(new TestPredicate());
        assertThat(result).isEqualTo(expectedResult);
    }

    private static class TestPredicateExecutor implements PredicateExecutor<TestPredicate, TestDomain>{
        @Override
        public ExecutorResult<TestDomain> execute(TestPredicate predicate) throws DTOException {
            throw new DTOException(PREDICATE_EXECUTOR_SET_AS_EXECUTOR);
        }
    }
}
