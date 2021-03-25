package com.company.sherlock_valid_string;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void isValid1() {
        String result = Solution.isValid("abccc");
        assertEquals("NO", result);
    }

    @Test
    public void isValid2() {
        String result = Solution.isValid("aabbcd");
        assertEquals("NO", result);
    }

    @Test
    public void isValid3() {
        String result = Solution.isValid("abcdefghhgfedecba");
        assertEquals("YES", result);
    }


    @Test
    public void isValid4() {
        String result = Solution.isValid("ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd");
        assertEquals("YES", result);
    }


    @Test
    public void isValid5() {
        Scanner in = new Scanner(System.in);
        try {
            in = new Scanner(new FileReader("src/com/company/sherlock_valid_string/input.txt"));
        } catch (FileNotFoundException e) {
            //use System.in
        }
        String text = in.nextLine();
        String result = Solution.isValid(text);

        assertEquals("YES", result);
    }





}