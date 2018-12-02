package com.piotrjasina.solidity;

import com.piotrjasina.Utils;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

import static com.piotrjasina.Utils.sourceCodeHash;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolidityFileRepositoryTest {

    @Autowired
    private SolidityFileRepository solidityFileRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        String testSourceCode = getTestSourceCode();
        solidityFileRepository.save(new SolidityFile(
                testSourceCode,
                sourceCodeHash(testSourceCode),
                new HashSet<>(Arrays.asList(new Function("dsap", "dsa"), new Function("dsa", "dsa")))));
    }

    @After
    public void tearDown() {
        solidityFileRepository.deleteAll();

    }

    @Test
    public void shouldNotSaveSourceCodeWhenDuplicate() throws Exception {
        //given
        String testSourceCode = getTestSourceCode();

        SolidityFile solidityFile = new SolidityFile(
                testSourceCode,
                sourceCodeHash(testSourceCode),
                new HashSet<>(Arrays.asList(new Function("dsap", "dsa"), new Function("dsa", "dsa"))));

        //then
        expectedException.expect(DuplicateKeyException.class);

        //when
        solidityFileRepository.save(solidityFile);
    }

    @Test
    public void shouldSaveSourceCode() throws Exception {
        //given
        String expectedSourceCode = "dsadsadsadadklujsadoisajfsdkljgdfkl";
        String expectedSourceCodeHash = sourceCodeHash(expectedSourceCode);
        HashSet<Function> expectedFunctions =
                new HashSet<>(Arrays.asList(
                        new Function("dsap", "dsa"),
                        new Function("dsa", "dsa"))
                );

        SolidityFile solidityFile = new SolidityFile(
                expectedSourceCode,
                expectedSourceCodeHash,
                expectedFunctions);

        //when
        SolidityFile actualSolidityFile = solidityFileRepository.save(solidityFile);


        //then
        assertNotNull(actualSolidityFile.getId());
        assertThat(actualSolidityFile.getSourceCode(), equalTo(expectedSourceCode));
        assertThat(actualSolidityFile.getSourceCodeHash(), equalTo(expectedSourceCodeHash));
        assertThat(actualSolidityFile.getFunctions(), equalTo(expectedFunctions));
    }


    private String getTestSourceCode() throws Exception {
        try (InputStream inputStream = new FileInputStream(new File("src/test/resources/Test.sol"))) {
            return Utils.convertToString(inputStream);
        }

    }
}