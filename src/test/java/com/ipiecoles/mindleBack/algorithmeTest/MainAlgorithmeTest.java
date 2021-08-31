/*package com.ipiecoles.mindleBack.algorithmeTest;

import com.ipiecoles.mindleBack.entity.listGenres;
import com.ipiecoles.mindleBack.entity.sousGenres;
import org.junit.jupiter.api.BeforeEach;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;


import java.util.Arrays;
import java.util.List;

import static com.ipiecoles.mindleBack.algorithme.MainAlgorithme.GetRandomChoice;


public class MainAlgorithmeTest {


    //List<String> ;

    sousGenres dataTest1 = new sousGenres("bossanova",5,Arrays.asList("jazz", "world-music"));
    sousGenres dataTest2 = new sousGenres("alternative",0, Arrays.asList("alternative"));
    sousGenres dataTest3 = new sousGenres("chill",280, Arrays.asList("ambient"));
    listGenres ListdataTest = new listGenres(Arrays.asList(dataTest1,dataTest2,dataTest3));

    @Test
    public void GetRandomChoiceTest(listGenres ListdataTest){
        //GIVEN
        listGenres ListTest = new listGenres();
        List<sousGenres> ListSousTest = null;
        ListSousTest.set(0,dataTest1);
        ListSousTest.set(1,dataTest2);
        ListSousTest.set(2,dataTest3);
        //ListTest.setListGenres(Arrays.asList(dataTest1,dataTest2,dataTest3));
        ListTest.setListGenres(ListSousTest);
        //WHEN
        Integer RandomChoice = GetRandomChoice(ListdataTest);
        //THEN
        Assertions.assertThat(RandomChoice).isEqualTo(3);

    }

}*/
