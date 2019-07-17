package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.CaseInfo;
import com.tw.apistackbase.model.CriminalCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CaseInfoRepositoryTest {

    @Autowired
    private CaseInfoRepository caseInfoRepository;

    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @Before
    public void setUp() {
        List<CaseInfo> caseInfos = new ArrayList<>();
        caseInfos.add(new CaseInfo("caseTwoSubjective","caseTwoObjective"));
        caseInfos.add(new CaseInfo("caseThreeSubjective","caseTwoObjective"));
        caseInfos.add(new CaseInfo("caseOneSubjective","caseTwoObjective"));
        caseInfos.add(new CaseInfo("caseOneSubjective","caseTwoObjective"));
        caseInfoRepository.saveAll(caseInfos);

        List<CriminalCase> criminalCases = new ArrayList<>();
        criminalCases.add(new CriminalCase("caseTwo",1530310725, null));
        criminalCases.add(new CriminalCase("caseThree",1530413265, null));
        criminalCases.add(new CriminalCase("caseOne",1531320725, null));
        criminalCases.add(new CriminalCase("caseOne",1532320725, null));
        criminalCaseRepository.saveAll(criminalCases);
    }

    @Test
    public void should_return_error_when_save_case_info_some_attribute_are_null(){
        CaseInfo caseInfo = new CaseInfo();
        Assertions.assertThrows(DataIntegrityViolationException.class,()->
                caseInfoRepository.saveAndFlush(caseInfo)
        );
    }

    @Test
    public void should_return_case_info_when_query_case_info_by_id(){
        CaseInfo caseInfo = caseInfoRepository.findById(1).get();
        assertNotEquals(null, caseInfo);
    }

    @Test
    public void should_return_corresponding_case_info_when_query_case_info_by_case(){
        CaseInfo caseInfo = new CaseInfo("caseFourSubjective","caseFourObjective");
        CriminalCase criminalCase = new CriminalCase("caseFour",1531310725, null);
        criminalCase.setCaseInfo(caseInfo);
        criminalCaseRepository.save(criminalCase);
        List<CriminalCase> criminalCases = criminalCaseRepository.findAll();
        CriminalCase criminalCaseNew = criminalCases.get(criminalCases.size() - 1);
        assertEquals(caseInfo, criminalCaseNew.getCaseInfo());
    }
}