package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.CriminalCase;
import com.tw.apistackbase.model.Procuratorate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcuratorateRepositoryTest {

    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @Before
    public void setUp() throws Exception{
        List<Procuratorate> procuratorates = new ArrayList<>();
        procuratorates.add(new Procuratorate("proTwo"));
        procuratorates.add(new Procuratorate("proThree"));
        procuratorates.add(new Procuratorate("proOne"));
        procuratorates.add(new Procuratorate("proFour"));
        procuratorateRepository.saveAll(procuratorates);
    }

    @Test
    public void should_return_error_when_save_case_the_same_name(){
        Procuratorate procuratorate = new Procuratorate("proTwo");
        Assertions.assertThrows(DataIntegrityViolationException.class,()->
                procuratorateRepository.saveAndFlush(procuratorate)
        );
    }

    @Test
    public void should_return_procuratorate_when_query_procuratorates_by_id(){
        Procuratorate procuratorate = procuratorateRepository.findById(1).get();
        assertNotEquals(null, procuratorate);
    }

    @Test
    public void should_return_error_when_save_case_with_null_procuratorate(){
        CriminalCase criminalCase = new CriminalCase("caseFour",1531310725, null,null);
        Assertions.assertThrows(ConstraintViolationException.class,()->
                criminalCaseRepository.saveAndFlush(criminalCase)
        );
    }

}