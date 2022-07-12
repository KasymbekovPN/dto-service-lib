package kpn.lib.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import kpn.lib.aspect.DefaultAspectResult;
import kpn.lib.aspect.AspectResult;
import kpn.lib.aspect.deleting.DeletingAspect;
import kpn.lib.aspect.deleting.DeletingAspectDecorator;
import kpn.lib.aspect.loading.LoadingAspect;
import kpn.lib.aspect.loading.LoadingAspectDecorator;
import kpn.lib.aspect.predicate.PredicateAspect;
import kpn.lib.aspect.predicate.PredicateAspectDecorator;
import kpn.lib.aspect.saving.SavingAspect;
import kpn.lib.aspect.saving.SavingAspectDecorator;
import kpn.lib.executor.DefaultExecutorResult;
import kpn.utils.TestDomain;
import kpn.utils.TestPredicate;
import kpn.utils.TestResult;
import kpn.utils.TestToResultConverter;

class DtoServiceTest {
    private final TestDomain domain = new TestDomain(1L);
    private final Function<AspectResult<TestDomain>, TestResult<TestDomain>> converter = new TestToResultConverter();
    private final AspectResult<TestDomain> result = new DefaultAspectResult<>(
        new DefaultExecutorResult<TestDomain>(
            domain
        )
    );
    
    @Test
    void shouldCheckSaver(){
        DtoService<Long, TestDomain, TestPredicate, TestResult<TestDomain>> service = new DtoService<>(
            createSavingService(),
            null,
            null,
            null,
            converter
        );
        TestResult<TestDomain> expectedResult
            = converter.apply(new DefaultAspectResult<>(new DefaultExecutorResult<TestDomain>(domain)));
        
        SavingAspect<TestDomain, TestResult<TestDomain>> saver = service.saver();

        assertThat(saver.getClass()).isEqualTo(SavingAspectDecorator.class);
        assertThat(saver.save(domain)).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckLoader(){
        DtoService<Long, TestDomain, TestPredicate, TestResult<TestDomain>> service = new DtoService<>(
            null,
            createLoadingService(),
            null,
            null,
            converter
        );
        TestResult<TestDomain> expectedResult
            = converter.apply(new DefaultAspectResult<>(new DefaultExecutorResult<TestDomain>(domain)));
        
        LoadingAspect<Long, TestResult<TestDomain>> loader = service.loader();

        assertThat(loader.getClass()).isEqualTo(LoadingAspectDecorator.class);
        assertThat(loader.all()).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckDeleter(){
        DtoService<Long, TestDomain, TestPredicate, TestResult<TestDomain>> service = new DtoService<>(
            null,
            null,
            createDeletingService(),
            null,
            converter
        );
        TestResult<TestDomain> expectedResult
            = converter.apply(new DefaultAspectResult<>(new DefaultExecutorResult<TestDomain>(domain)));
        
        DeletingAspect<Long, TestResult<TestDomain>> deleter = service.deleter();

        assertThat(deleter.getClass()).isEqualTo(DeletingAspectDecorator.class);
        assertThat(deleter.all()).isEqualTo(expectedResult);
    }

    @Test
    void shouldCheckPredicate(){
        DtoService<Long, TestDomain, TestPredicate, TestResult<TestDomain>> service = new DtoService<>(
            null,
            null,
            null,
            createPredicareService(),
            converter
        );
        TestResult<TestDomain> expectedResult
            = converter.apply(new DefaultAspectResult<>(new DefaultExecutorResult<TestDomain>(domain)));
        
        PredicateAspect<TestPredicate, TestResult<TestDomain>> executor = service.executor();

        assertThat(executor.getClass()).isEqualTo(PredicateAspectDecorator.class);
        assertThat(executor.execute(new TestPredicate())).isEqualTo(expectedResult);
    }

    private SavingAspect<TestDomain, AspectResult<TestDomain>> createSavingService() {
        TestSavingService service = Mockito.mock(TestSavingService.class);
        Mockito
            .when(service.save(Mockito.anyObject()))
            .thenReturn(result);
        return service;
    }

    private LoadingAspect<Long, AspectResult<TestDomain>> createLoadingService() {
        TestLoadingService service = Mockito.mock(TestLoadingService.class);
        Mockito
            .when(service.all())
            .thenReturn(result);
        return service;
    }

    private DeletingAspect<Long, AspectResult<TestDomain>> createDeletingService() {
        TestDeletingService service = Mockito.mock(TestDeletingService.class);
        Mockito
            .when(service.all())
            .thenReturn(result);
        return service;
    }

    private PredicateAspect<TestPredicate, AspectResult<TestDomain>> createPredicareService() {
        TestPredicateService service = Mockito.mock(TestPredicateService.class);
        Mockito
            .when(service.execute(Mockito.anyObject()))
            .thenReturn(result);
        return service;
    }

    private abstract class TestSavingService implements SavingAspect<TestDomain, AspectResult<TestDomain>>{}
    private abstract class TestLoadingService implements LoadingAspect<Long, AspectResult<TestDomain>> {}
    private abstract class TestDeletingService implements DeletingAspect<Long, AspectResult<TestDomain>> {}
    private abstract class TestPredicateService implements PredicateAspect<TestPredicate, AspectResult<TestDomain>> {}
}