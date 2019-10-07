/*
 * Name:    Kyle Blackie
 * Date:    May 4, 2017
 * Version: 1.0
 * Description: Program to test stack class
 */
package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 *
 * @author 1blackiekyl
 */
public class StackTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Stack s = new Stack();

        assert (s.top() == -1);
        assert (s.pop() == -1);
        assert (s.size() == 0);
        assert (s.capacity() == Stack.DEFAULT_SIZE);
        assert (s.isEmpty());
        assert (!s.isFull());

        for (int i = 0; i < Stack.DEFAULT_SIZE; i++) {
            s.push(i);
            assert (s.top() == i);
            assert (s.size() == i + 1);
            assert (s.capacity() == Stack.DEFAULT_SIZE);
            assert (!s.isEmpty());
            if (i == Stack.DEFAULT_SIZE - 1) {
                assert (s.isFull());
            } else {
                assert (!s.isFull());
            }
        }

        for (int i = Stack.DEFAULT_SIZE - 1; i >= 0; i--) {
            s.pop();
            assert (s.top() == i - 1);
            assert (s.size() == i);
            assert (s.capacity() == Stack.DEFAULT_SIZE);
            assert (!s.isFull());

            if (i == 0) {
                assert (s.isEmpty());
            } else {
                assert (!s.isEmpty());
            }
        }
        
//        for (int i = 0; i < Stack.DEFAULT_SIZE; i++) {
//            s.push(i);
//            s.push(i + 1);
//            s.pop();
//            System.out.println(s.top());
//            assert (s.top() == i);
//            assert (s.size() == i + 1);
//            assert (s.capacity() == Stack.DEFAULT_SIZE);
//            assert (!s.isEmpty());
//        }

        s.makeEmpty();

        assert (s.top() == -1);
        assert (s.pop() == -1);
        assert (s.size() == 0);
        assert (s.capacity() == Stack.DEFAULT_SIZE);
        assert (s.isEmpty());
        assert (!s.isFull());

    }

}