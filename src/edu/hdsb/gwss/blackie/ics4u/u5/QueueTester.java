/*
 * Name:    Kyle Blackie
 * Date:    May 5, 2017
 * Version: 1.0
 * Description: Program that gets data from an xml file and sorts it and creates
 * a new xml file.
 */
package edu.hdsb.gwss.blackie.ics4u.u5;

/**
 *
 * @author 1blackiekyl
 */
public class QueueTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue q = new Queue();
        assert (q.back() == -1);
        assert (q.front() == -1);
        assert (q.capacity() == Queue.DEFAULT_SIZE);
        assert (q.size() == 0);
        assert (q.isEmpty());
        assert (!q.isFull());
        q.displayQueue();

        for (int i = 0; i < q.capacity(); i++) {
            q.enqueue(i);
            assert (q.back() == i);
            assert (q.front() == 0);
            assert (q.capacity() == Queue.DEFAULT_SIZE);
            assert (q.size() == i + 1);
            if (i == q.capacity() - 1) {
                assert (q.isFull());
            }
            assert (!q.isEmpty());

            q.displayQueue();
        }
        for (int i = q.size(); i > 0; i--) {
            q.dequeue();
            assert (q.capacity() == Queue.DEFAULT_SIZE);
            assert (q.size() == i - 1);
            if (i > 1) {
                assert (q.front() == q.capacity() - i + 1);
            }
            q.displayQueue();

        }
    }

}
