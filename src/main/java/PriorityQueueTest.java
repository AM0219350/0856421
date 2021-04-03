import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

//0856421
public class PriorityQueueTest {
    static Stream<Arguments> stringIntAndListProvider(){
        return Stream.of(
                Arguments.of(new int[]{3,1,2},new int[] {1,2,3}),
                Arguments.of(new int[]{-3,-1,-2,5},new int[] {-3,-2,-1,5}),
                Arguments.of(new int[]{3,-2,-5,-1,2},new int[] {-5,-2,-1,2,3}),
                Arguments.of(new int[]{-3,1,11,0,9,3},new int[] {-3,0,1,3,9,11}),
                Arguments.of(new int[]{3,7,2,-1,-2},new int[] {-2,-1,2,3,7})
        );
    }
    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("stringIntAndListProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        //int index = 0;
        Integer s;
        int[] result = new int[random_array.length];
        for(int i = 0; i < random_array.length; i++){
            test.offer(random_array[i]);
        }
        int i = 0;
        while((s = test.poll())!= null){
            result[i] = s;
            i++;
        }
        assertArrayEquals(correct_array , result);
    }
    @Test
    void whenExceptionThrown_thenOfferEisNull(){
        Exception exception = assertThrows(java.lang.NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.offer(null);
        });
        String expected = "java.lang.NullPointerException";
        assertEquals(expected,exception.toString());
    }
    @Test
    void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne(){
        Exception exception = assertThrows(java.lang.IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        });
        String expected = "java.lang.IllegalArgumentException";
        assertEquals(expected,exception.toString());
    }
    @Test
    void whenExceptionThrown_thenNoElementCanRemove(){
        Exception exception = assertThrows(java.util.NoSuchElementException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            boolean removeCheck = test.remove(7);
            if( removeCheck == false){
                throw  new NoSuchElementException();
            }
        });
        String expected = "java.util.NoSuchElementException";
        assertEquals(expected,exception.toString());
    }
}
