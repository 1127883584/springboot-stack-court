package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.CriminalCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.Transient;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CriminalCaseRepositoryTest {

    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @Before
    public void setUp() throws Exception{
        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("caseTwo",1530310725));
        criminalCases.add(new CriminalCase("caseThree",1530413265));
        criminalCases.add(new CriminalCase("caseOne",1531320725));
        criminalCases.add(new CriminalCase("caseOne",1532320725));
        criminalCaseRepository.saveAll(criminalCases);
    }

    @Test
    public void should_return_error_when_save_case_some_attribute_are_null(){
        CriminalCase criminalCase = new CriminalCase();
        Assertions.assertThrows(DataIntegrityViolationException.class,()->
                criminalCaseRepository.saveAndFlush(criminalCase)
        );
    }

    @Test
    public void should_return_case_when_query_case_by_id(){
        CriminalCase criminalCase = criminalCaseRepository.findById(1).get();
        assertNotEquals(null, criminalCase);
    }

    @Test
    public void should_return_case_when_query_case_order_by_incidentTime_desc(){
        List<CriminalCase> criminalCase = criminalCaseRepository.findAllByOrderByIncidentTimeDesc();
        assertTrue(criminalCase.get(0).getIncidentTime() > criminalCase.get(1).getIncidentTime());
    }

    @Test
    public void should_return_cases_when_query_case_by_name(){
        List<CriminalCase> criminalCase = criminalCaseRepository.findAllByName("caseOne");
        assertEquals(2, criminalCase.size());
    }

    @Test
    public void should_return_cases_after_delete_when_delete_case_by_id(){
        criminalCaseRepository.deleteById(2);
        assertEquals(3, criminalCaseRepository.findAll().size());
    }

}