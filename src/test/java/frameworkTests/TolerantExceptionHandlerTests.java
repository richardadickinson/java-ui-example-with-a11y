package frameworkTests;

import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;
import utils.webDriver.interactions.TolerantExceptionHandler;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class TolerantExceptionHandlerTests {

    @Test
    public void testThrowsInputExceptionWhenNoTolerantExceptionsAreListed() {
        TolerantExceptionHandler handler = new TolerantExceptionHandler(new ArrayList<>());
        NumberFormatException inputException = new NumberFormatException();
        assertThrows(NumberFormatException.class, ()-> handler.propagateExceptionIfNotIgnored(inputException));
    }
    @Test
    public void testThrowsInputExceptionIfNotOnTolerantExceptionList() {
         List<String> tolerantList = Arrays.asList("StaleElementException", "ElementClickInterceptedException", "ElementNotInteractableException");
         TolerantExceptionHandler handler = new TolerantExceptionHandler(tolerantList);
         NumberFormatException inputException = new NumberFormatException();

         assertThrows(NumberFormatException.class, ()-> handler.propagateExceptionIfNotIgnored(inputException));
    }

    @Test
    public void testReturnsInputExceptionWhenOnTheTolerantExceptionList() throws Throwable {
        List<String> tolerantList = Arrays.asList("StaleElementReferenceException", "ElementClickInterceptedException", "ElementNotInteractableException");
        TolerantExceptionHandler handler = new TolerantExceptionHandler(tolerantList);
        StaleElementReferenceException inputException = new StaleElementReferenceException("Hello");

        assertEquals(inputException, handler.propagateExceptionIfNotIgnored(inputException));
    }

    @Test
    public void testReturnsInputExceptionMatchedOnPackageName() throws Throwable {
        List<String> tolerantList = List.of("org.openqa.selenium.StaleElementReferenceException");
        TolerantExceptionHandler handler = new TolerantExceptionHandler(tolerantList);
        StaleElementReferenceException inputException = new StaleElementReferenceException("Hello");

        assertEquals(inputException, handler.propagateExceptionIfNotIgnored(inputException));
    }

    @Test
    public void testIgnoresInvalidValuesInTolerantExceptionList() throws Throwable {
        List<String> tolerantList = new ArrayList<>();
        tolerantList.add("NonExistentException");
        tolerantList.add(InvalidClassException.class.getName());
        tolerantList.add(ArithmeticException.class.getName());
        TolerantExceptionHandler handler = new TolerantExceptionHandler(tolerantList);
        ArithmeticException inputException = new ArithmeticException();

        assertEquals(inputException, handler.propagateExceptionIfNotIgnored(inputException));
    }

    @Test
    public void testIgnoresNonThrowableClassesInTolerantExceptionList() throws Throwable {
        List<String> tolerantList = new ArrayList<>();
        tolerantList.add("java.lang.String");
        tolerantList.add(InvalidClassException.class.getName());
        tolerantList.add(ArithmeticException.class.getName());
        TolerantExceptionHandler handler = new TolerantExceptionHandler(tolerantList);
        ArithmeticException inputException = new ArithmeticException();

        assertEquals(inputException, handler.propagateExceptionIfNotIgnored(inputException));
    }


}
