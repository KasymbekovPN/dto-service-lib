package kpn.lib.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.collection.ImmutableCollection;
import kpn.lib.services.parts.deleting.service.DeletingDecorator;
import kpn.lib.services.parts.deleting.service.DeletingService;
import kpn.lib.services.parts.loading.service.LoadingDecorator;
import kpn.lib.services.parts.loading.service.LoadingService;
import kpn.lib.services.parts.predicate.service.PredicateDecorator;
import kpn.lib.services.parts.predicate.service.PredicateService;
import kpn.lib.services.parts.saving.service.SavingDecorator;
import kpn.lib.services.parts.saving.service.SavingService;
import kpn.lib.services.result.ImmutableServiceResult;
import kpn.lib.services.result.ServiceResult;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class DTOServicesImplTest {
    private final TestDomain domain = new TestDomain(1L);
    private final Function<ServiceResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();
    private final ServiceResult<TestDomain> result = new ImmutableServiceResult<>(
        new ImmutableCollection<TestDomain>(
            domain
        )
    );
    
    @Test
    void shouldCheckSaver(){
        DTOServicesImpl<Long, TestDomain, TestPredicate, TestResult<TestDomain>> service = new DTOServicesImpl<>(
            createSavingService(),
            null,
            null,
            null,
            converter
        );
        TestResult<TestDomain> expectedResult
            = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>(domain)));
        
        SavingService<TestDomain, TestResult<TestDomain>> saver = service.saver();

        assertThat(saver.getClass()).isEqualTo(SavingDecorator.class);
        assertThat(saver.save(domain)).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckLoader(){
        DTOServicesImpl<Long, TestDomain, TestPredicate, TestResult<TestDomain>> service = new DTOServicesImpl<>(
            null,
            createLoadingService(),
            null,
            null,
            converter
        );
        TestResult<TestDomain> expectedResult
            = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>(domain)));
        
        LoadingService<Long, TestResult<TestDomain>> loader = service.loader();

        assertThat(loader.getClass()).isEqualTo(LoadingDecorator.class);
        assertThat(loader.all()).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckDeleter(){
        DTOServicesImpl<Long, TestDomain, TestPredicate, TestResult<TestDomain>> service = new DTOServicesImpl<>(
            null,
            null,
            createDeletingService(),
            null,
            converter
        );
        TestResult<TestDomain> expectedResult
            = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>(domain)));
        
        DeletingService<Long, TestResult<TestDomain>> deleter = service.deleter();

        assertThat(deleter.getClass()).isEqualTo(DeletingDecorator.class);
        assertThat(deleter.all()).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckPredicate(){
        DTOServicesImpl<Long, TestDomain, TestPredicate, TestResult<TestDomain>> service = new DTOServicesImpl<>(
            null,
            null,
            null,
            createPredicareService(),
            converter
        );
        TestResult<TestDomain> expectedResult
            = converter.apply(new ImmutableServiceResult<>(new ImmutableCollection<TestDomain>(domain)));
        
        PredicateService<TestPredicate, TestResult<TestDomain>> executor = service.executor();

        assertThat(executor.getClass()).isEqualTo(PredicateDecorator.class);
        assertThat(executor.execute(new TestPredicate())).isEqualTo(expectedResult);
    }

    private SavingService<TestDomain, ServiceResult<TestDomain>> createSavingService() {
        TestSavingService service = Mockito.mock(TestSavingService.class);
        Mockito
            .when(service.save(Mockito.anyObject()))
            .thenReturn(result);
        return service;
    }

    private LoadingService<Long, ServiceResult<TestDomain>> createLoadingService() {
        TestLoadingService service = Mockito.mock(TestLoadingService.class);
        Mockito
            .when(service.all())
            .thenReturn(result);
        return service;
    }

    private DeletingService<Long, ServiceResult<TestDomain>> createDeletingService() {
        TestDeletingService service = Mockito.mock(TestDeletingService.class);
        Mockito
            .when(service.all())
            .thenReturn(result);
        return service;
    }

    private PredicateService<TestPredicate, ServiceResult<TestDomain>> createPredicareService() {
        TestPredicateService service = Mockito.mock(TestPredicateService.class);
        Mockito
            .when(service.execute(Mockito.anyObject()))
            .thenReturn(result);
        return service;
    }

    private abstract class TestSavingService implements SavingService<TestDomain, ServiceResult<TestDomain>>{}
    private abstract class TestLoadingService implements LoadingService<Long, ServiceResult<TestDomain>> {}
    private abstract class TestDeletingService implements DeletingService<Long, ServiceResult<TestDomain>> {}
    private abstract class TestPredicateService implements PredicateService<TestPredicate, ServiceResult<TestDomain>> {}
}