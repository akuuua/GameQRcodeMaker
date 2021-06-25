package SCD2_2021_Exam.database;

import GameQRcodeMaker.database.series;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class serisesTest {

    @Test(expected = java.lang.NullPointerException.class)
    public void emptySetJson(){
        //Empty json
        series s = new series();
        s.setJson(null);
        assertEquals(s.begin_at,null);
        assertEquals(s.description,null);
        assertEquals(s.winner_id,null);
        assertEquals(s.season,null);
        assertEquals(s.slug,null);

    }


}
