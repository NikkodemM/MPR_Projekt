package pl.edu.pjatk.MPR_Projekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_Projekt.Model.Cat;
import pl.edu.pjatk.MPR_Projekt.Repository.CatRepository;
import pl.edu.pjatk.MPR_Projekt.Services.CatService;
import pl.edu.pjatk.MPR_Projekt.Services.StringUtilsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CatServiceTest {
    private CatService service;
    private StringUtilsService stringUtilsService;
    private CatRepository repository;

    @BeforeEach
    public void setUp() {
        this.stringUtilsService = Mockito.mock(StringUtilsService.class);
        this.repository = Mockito.mock(CatRepository.class);
        this.service = new CatService(repository, stringUtilsService);
        Mockito.clearInvocations(repository);
    }
    @Test
    public void createSetsCatToUpperCase() {
        Cat cat = new Cat("John", 2, 1L);
        this.service.createCat(cat);

        verify(stringUtilsService, times(1)).toUpperCase(any());
    }
}
