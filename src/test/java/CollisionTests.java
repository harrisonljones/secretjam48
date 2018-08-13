import model.shapes.Rect;
import org.junit.Assert;
import org.junit.Test;

public class CollisionTests {

    @Test
    public void givenTwoOverlappingRectObjects_whenIsCollisionIsCalled_thenReturnTrue() {
        Rect rect1 = new Rect(100, 100, 100, 100);
        Rect rect2 = new Rect(125, 175, 50, 50);

        Assert.assertTrue(rect1.isCollision(rect2));
        Assert.assertTrue(rect2.isCollision(rect1)); //test commutativity
    }

    @Test
    public void givenTwoNonOverlappingRectObjects_whenIsCollisionIsCalled_thenReturnFalse() {
        Rect rect1 = new Rect(100, 100, 100, 100);
        Rect rect2 = new Rect(125f, 200.1f, 50, 50);

        Assert.assertFalse(rect1.isCollision(rect2));
        Assert.assertFalse(rect2.isCollision(rect1)); //test commutativity
    }

}
