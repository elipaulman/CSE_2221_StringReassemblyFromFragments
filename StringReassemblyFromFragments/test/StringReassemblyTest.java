import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Elijah Paulman
 *
 */
public class StringReassemblyTest {

    /*
     * tests of overlap
     */

    /**
     * Tests small overlap.
     */
    @Test
    public void testOverlapComputer() {
        String str1 = "comput";
        String str2 = "ter";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(1, overlap);
    }

    /**
     * Tests large overlap.
     */
    @Test
    public void testOverlapPhotosynthesis() {
        String str1 = "photosynth";
        String str2 = "synthesis";
        int overlap = StringReassembly.overlap(str1, str2);
        final int five = 5;
        assertEquals(five, overlap);
    }

    /**
     * Tests no overlap.
     */
    @Test
    public void testOverlapWelcomeGoodbye() {
        String str1 = "Welcome";
        String str2 = "goodbye";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(0, overlap);
    }

    /*
     * tests of combination
     */

    /**
     * Tests small combination.
     */
    @Test
    public void testCombinationComputer() {
        String str1 = "comput";
        String str2 = "ter";
        int overlap = 1;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("computer", combine);
    }

    /**
     * Tests large combination.
     */
    @Test
    public void testCombinationPhotosynthesis() {
        String str1 = "Photosynth";
        String str2 = "otosynthesis";
        final int five = 8;
        int overlap = five;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Photosynthesis", combine);
    }

    /**
     * Tests combination with empty string.
     */
    @Test
    public void testCombinationComputerNoCombo() {
        String str1 = "computer";
        String str2 = "";
        int overlap = 0;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("computer", combine);
    }

    /*
     * tests of addToSetAvoidingSubstrings
     */

    /**
     * Tests with adding shout into out.
     */
    @Test
    public void testAddToSetAvoidingSubstrings1OutShout() {
        Set<String> strSet = new Set1L<>();
        strSet.add("did");
        strSet.add("you");
        strSet.add("out");
        String str = "shout";
        Set<String> expect = new Set1L<>();
        expect.add("did");
        expect.add("you");
        expect.add("shout");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    /**
     * Tests with adding cream into scream.
     */
    @Test
    public void testAddToSetAvoidingSubstrings2ScreamCream() {
        Set<String> strSet = new Set1L<>();
        strSet.add("did");
        strSet.add("you");
        strSet.add("scream");
        String str = "cream";
        Set<String> expect = new Set1L<>();
        expect.add("did");
        expect.add("you");
        expect.add("scream");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    /*
     * tests of printWithLineSeparators
     */

    /**
     * Tests with 2 separators.
     */
    @Test
    public void testPrintWithLineSeparators1() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "Test of ~ Print ~ With Line Seperators";
        String expect = "Test of " + "\n" + " Print " + "\n"
                + " With Line Seperators";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

    /**
     * Tests with one separator.
     */
    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "Test of print with line ~ seperators";
        String expect = "Test of print with line " + "\n" + " seperators";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2);
    }

    /**
     * Tests with 2 line separators and colon.
     */
    @Test
    public void testPrintWithLineSeparators3() {
        SimpleWriter out = new SimpleWriter1L("testLineSeperators.txt");
        SimpleReader in = new SimpleReader1L("testLineSeperators.txt");
        String text = "Hello ~ My name is: ~ Elijah";
        String expect = "Hello " + "\n" + " My name is: " + "\n" + " Elijah";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

    /*
     * tests of assemble
     */
    /**
     * Tests fragments of string.
     */
    @Test
    public void testAssemble1() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Hello ho");
        strSet.add("o how are");
        strSet.add("are you");
        strSet.add("re you doing?");
        Set<String> expect = new Set1L<>();
        expect.add("Hello how are you doing?");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    /**
     * Insert of no overlap random text jumble.
     */
    @Test
    public void testAssemble2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Hello ho");
        strSet.add("o how are");
        strSet.add("ow are you");
        strSet.add("ou doing");
        strSet.add("hwljrhelgwj");
        strSet.add("today?");
        Set<String> expect = new Set1L<>();
        expect.add("Hello how are you doing");
        expect.add("hwljrhelgwj");
        expect.add("today?");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

}
