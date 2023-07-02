//
// Tomás Oliveira e Silva, AED, November 2021
//
// Generic deque implementation based on a circular buffer
//

#ifndef _AED_deque_
#define _AED_deque_

#include <cassert>

template <typename T>
class deque
{
  private:
    T *data; // the deque data
    int max_size;  // max deque size 
    int cur_size;  // current deque size
    int head; // index of the first element
    int tail;  // index of the last element

  private:
    int increment_index(int i) 
    {
      return(i + 1 < max_size) ? i + 1 : 0;
    }
    
    int decrement_index(int i) 
    {
      return(i - 1 >= 0) ? i - 1 : max_size - 1;
    }

  public:

    deque(int n = 100)
    {
      assert(n >= 10 && n <= 1000000);
      max_size = n;
      cur_size = 0;
      head = 1;    // cur_size = tail - head + 1 , head = index of the first element
      tail = 0;
      data = new T[n];
    }

    ~deque(void) 
    {
      delete[] data;
    }

    void clear(void)
    { 
      cur_size = 0;
      head = 1;
      tail = 0;
    }

    int size(void)
    {
      return cur_size;
    }

    int is_full(void)
    {
      return(cur_size == max_size) ? 1 : 0;
    }

    int is_empty(void)
    {
      return(cur_size == 0) ? 1 : 0;
    }

    void insert_at_head(T v)
    {
      assert(cur_size < max_size);
      data[head] = v;
      head = decrement_index(head);
      cur_size++;
    }

    T remove_head(void)
    {
      assert(cur_size > 0);
      int old_head = head;
      T v = data[old_head];
      head = increment_index(head);
      cur_size--;
      return v;
    }

    T peek_head(void)
    {
      assert(cur_size < max_size);
      return data[head];
    }

    void insert_at_tail(T v)
    {
      assert(cur_size > 0);
      data[tail] = v;
      tail = increment_index(tail);
    }

    T remove_tail(void)
    {
      assert(cur_size > 0);
      int old_tail = tail;
      tail = decrement_index(tail);
      cur_size--;
      return data[old_tail];
    }

    T peek_tail(void)
    {
      assert(cur_size < max_size);
      return data[tail];
    }
};

#endif
