//
// Tomás Oliveira e Silva, AED, November 2021
//
// Generic queue (First In First Out) implementation based on an linked list
//

#ifndef _AED_lQueue_
#define _AED_lQueue_

#include <cassert>

template <typename T>
class lQueue
{
  private:
    struct node
    {
      T data;
      node *next;
    };
    node *head;
    node *tail;
    int n;
  public:
    lQueue(void) {
      head = tail = NULL;
      n = 0;
    }
    ~lQueue(void) {
      while(head != NULL)
      {
        node *p = head;
        head = head->next;
        delete p;
      }
    }
    void clear(void)
    {
      while(head != NULL)
      {
        node *p = head;
        head = head->next;
        delete p;
      }
      head = tail = NULL;
      n = 0;
    }
    int size(void) const
    {
      return n;
    }
    int is_full(void) const
    {
      return 0;
    }
    int is_empty(void) const
    {
      return n == 0;
    }
    void enqueue(T &v)
    {
      node *p = new node;
      p->data = v;
      p->next = NULL;
      if(tail == NULL)
        head = tail = p;
      else
      {
        tail->next = p;
        tail = p;
      }
      n++;
    }
    T dequeue(void)
    {
      assert(head != NULL);
      node *p = head;
      T v = p->data;
      head = head->next;
      if(head == NULL)
        tail = NULL;
      delete p;
      n--;
      return v;
    }
    T peek(void)
    {
      assert(head != NULL);
      return head->data;
    }
};

#endif
