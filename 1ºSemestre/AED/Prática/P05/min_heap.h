# include <cassert>

template <typename T>

class min_heap
{
    private:
        T *data; // the heap data
        int max_size;  // max heap size 
        int cur_size;  // current heap size
    
    private:
        int parent(int i) 
        {
            return (i - 1) / 2;
        }
        
        int left_child(int i) 
        {
            return 2 * i + 1;
        }
        
        int right_child(int i) 
        {
            return 2 * i + 2;
        }

    public:

        min_heap(int n = 100)
        {
            assert(n >= 10 && n <= 1000000);
            max_size = n;
            cur_size = 0;
            data = new T[n];
        }

        ~min_heap(void) 
        {
            delete[] data;
        }

        int max_size(void) const
        {
            return max_size;
        }

        int size(void) const
        {
            return cur_size;
        }

        T peek(int pos = 1) const
        {
            assert(pos >= 1 && pos <= cur_size);
            return data[pos - 1];
        }

        void enqueue(T v)
        {
            assert(cur_size < max_size - 1);
            data[cur_size++] = v;
            int i = cur_size - 1;
            
            while (i > 0 && data[parent(i)] > data[i]) 
            {
                T temp = data[i];
                data[i] = data[parent(i)];
                data[parent(i)] = temp;
                i = parent(i);
            }
        }

        void remove(int pos)
        {
            assert(pos >= 1 && pos <= cur_size);
            data[pos - 1] = data[--cur_size];
            int i = pos - 1;
            
            while (i > 0 && data[parent(i)] > data[i]) 
            {
                T temp = data[i];
                data[i] = data[parent(i)];
                data[parent(i)] = temp;
                i = parent(i);
            }
            
            while (left_child(i) < cur_size) 
            {
                int j = left_child(i);
                
                if (j + 1 < cur_size && data[j + 1] < data[j]) 
                {
                    j++;
                }
                
                if (data[i] <= data[j]) 
                {
                    break;
                }
                
                T temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i = j;
            }
        }
        
        T dequeue(void) 
        {
            assert(cur_size > 0);
            T temp = data[0];
            remove(0);
            return temp;
        }

        void test_invariants(void)
        {
            for (int i = 0; i < cur_size; i++) 
            {
                if (left_child(i) < cur_size) 
                {
                    assert(data[i] <= data[left_child(i)]);
                }
                if (right_child(i) < cur_size) 
                {
                    assert(data[i] <= data[right_child(i)]);
                }
            }
        }
};
