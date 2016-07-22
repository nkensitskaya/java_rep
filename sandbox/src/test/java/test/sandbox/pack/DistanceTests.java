package test.sandbox.pack;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void testDistance(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,3);

        Assert.assertEquals(p1.distance(p2),4.242640687119285);
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);

        Assert.assertEquals(p1.distance(p2),0.0);
    }

    @Test
    public void testDistanceFailed(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,3);

        Assert.assertEquals(p1.distance(p2),5);
    }

}
