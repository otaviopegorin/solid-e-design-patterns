import org.example.model.BankStatement;
import org.example.parser.BankStatementCSVParser;
import org.example.parser.BankStatementParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class BankStatementCSVParserTest {

    private final static BankStatementParser parser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine(){
        final String line = "10-01-2024,90,Aposta";

        BankStatement result = parser.parseFrom(line);
        BankStatement expected = new BankStatement(LocalDate.of(2024, Month.JANUARY,10), 90D, "Aposta");

        Assertions.assertEquals(result.getDate(),expected.getDate());
        Assertions.assertEquals(result.getValue(), expected.getValue());
        Assertions.assertEquals(result.getDescription(), expected.getDescription());
    }

    @Test
    public void shouldParseAnyCorrectLines(){
        List<String> lines = Arrays.asList("10-01-2024,90,Aposta","23-01-2022,1000,Pensão","31-07-2021,200,Jogo do bixo");

        List<BankStatement> expected = Arrays.asList(new BankStatement(LocalDate.of(2024, Month.JANUARY,10), 90D, "Aposta"),
                new BankStatement(LocalDate.of(2022, Month.JANUARY,23), 1000D, "Pensão"),
                new BankStatement(LocalDate.of(2021, Month.JULY,31), 200D, "Jogo do bixo"));
        List<BankStatement> result = parser.parseFromLines(lines);
        Assertions.assertNotNull(result);

        double[] amountExpected = expected.stream().mapToDouble(BankStatement::getValue).toArray();
        double[] amountResult = result.stream().mapToDouble(BankStatement::getValue).toArray();
        Assertions.assertArrayEquals(amountExpected,amountResult);

        Object[] dateExpected = expected.stream().map(BankStatement::getDate).toArray();
        Object[] dateResult = expected.stream().map(BankStatement::getDate).toArray();
        Assertions.assertArrayEquals(dateExpected,dateResult);

        Object[] descriptionExpected = expected.stream().map(BankStatement::getDescription).toArray();
        Object[] descriptionResulted = expected.stream().map(BankStatement::getDescription).toArray();
        Assertions.assertArrayEquals(descriptionExpected,descriptionResulted);
    }

}
