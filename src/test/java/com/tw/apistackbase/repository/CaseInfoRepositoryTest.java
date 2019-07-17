package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.CaseInfo;
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

    @Before
    public void setUp() {
        List<CaseInfo> caseInfos = new ArrayList<>();
        caseInfos.add(new CaseInfo("caseTwoSubjective","caseTwoObjective"));
        caseInfos.add(new CaseInfo("caseThreeSubjective","caseTwoObjective"));
        caseInfos.add(new CaseInfo("caseOneSubjective","caseTwoObjective"));
        caseInfos.add(new CaseInfo("caseOneSubjective","caseTwoObjective"));
        caseInfoRepository.saveAll(caseInfos);
    }

    @Test
    public void should_return_error_when_save_case_info_some_attribute_are_null(){
        CaseInfo caseInfo = new CaseInfo();
        Assertions.assertThrows(DataIntegrityViolationException.class,()->
                caseInfoRepository.saveAndFlush(caseInfo)
        );
    }

    @Test
    public void should_return_case_when_query_case_by_id(){
        CaseInfo caseInfo = caseInfoRepository.findById(1).get();
        assertNotEquals(null, caseInfo);
    }
}