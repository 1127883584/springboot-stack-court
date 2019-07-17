package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.CriminalCase;
import com.tw.apistackbase.model.Procuratorate;
import com.tw.apistackbase.model.Prosecutor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
public class ProsecutorRepositoryTest {

    @Autowired
    private ProsecutorRepository prosecutorRepository;

    @Before
    public void setUp() throws Exception{
        List<Prosecutor> prosecutors = new ArrayList<>();
        prosecutors.add(new Prosecutor("prosecutorTwo"));
        prosecutors.add(new Prosecutor("prosecutorThree"));
        prosecutors.add(new Prosecutor("prosecutorOne"));
        prosecutors.add(new Prosecutor("prosecutorOne"));
        prosecutorRepository.saveAll(prosecutors);
    }

    @Test
    public void should_return_error_when_save_prosecutor_name_is_null(){
        Prosecutor prosecutor = new Prosecutor();
        Assertions.assertThrows(DataIntegrityViolationException.class,()->
                prosecutorRepository.saveAndFlush(prosecutor)
        );
    }

}