//
// (C) Tomás Oliveira e Silva, AED, November 2021
//
// binary search tests
//

#include <stdio.h>
#include <stdlib.h>

int binary_search_v1(int *data,int data_size,int value)
{ // finds one
  int i_low = 0;
  int i_high = data_size - 1;
  int i_middle;

  while(i_low <= i_high)
  {
    i_middle = (i_low + i_high) / 2;
    if(value == data[i_middle])
      return i_middle; // this may not be the smallest possible index ...
    if(value > data[i_middle])
      i_low = i_middle + 1;
    else
      i_high = i_middle - 1;
  }
  return -1;
}

int binary_search_v2(int *data,int data_size,int value)
{ // finds the first
  int i_low = 0;
  int i_high = data_size - 1;
  int i_middle;

  while(i_low <= i_high)
  {
    i_middle = (i_low + i_high) / 2;
    if(value == data[i_middle])
    {
      // binary search for the smallest index
      i_high = i_middle;
      while(i_low < i_high)
      {
        // loop invariant: data[i_high] == value
        i_middle = (i_low + i_high) / 2;
        if(data[i_middle] == value)
          i_high = i_middle;
        else
          i_low = i_middle + 1;
      }
      return i_high;
    }
    if(value > data[i_middle])
      i_low = i_middle + 1;
    else
      i_high = i_middle - 1;
  }
  return -1;
}

int binary_search_v3(int *data,int data_size,int value)
{ // finds the first
  int i_low = -1;         // off by one
  int i_high = data_size; // off by one
  int i_middle;

  while(i_low + 1 != i_high)
  {
    //
    // loop invariants: data[i_high] >= value and data[i_low] < value
    // (by convention, data[-1] = -infinity and data[data_size] = +infinity)
    //
    i_middle = (i_low + i_high) / 2;
    if(data[i_middle] < value)
      i_low = i_middle;
    else
      i_high = i_middle;
  }
  return (i_high >= data_size || data[i_high] != value) ? -1 : i_high;
}

// Homework challenge: Implement a binary search routine that returns the largest index for which the corresponding
// array data is smaller than or equal to the searched for value. If the searched for value is smaller than the first array
// element return −1.

// int binary_search_v4(int *data, int data_size, int value)
// {
//   int i;
//   for(i = 0;i < data_size && data[i] != value;i++)
//   ;

//   if(i > 0 && i < data_size)
//   {
//     int tmp = data[i];
//     data[i] = data[i - 1];
//     data[--i] = tmp;
//   }
//   return (i < data_size) ? i : -1;
// }

int main(void)
{
# define max_n 1000
  int i,j,k,s,n,m,data[max_n];

  for(n = 1;n <= max_n;n++)
  {
    fprintf(stderr,"%d \r",n);
    //
    // 100 different cases for each n
    //
    for(m = 0;m < 100;m++)
    {
      //
      // first test (all data[] elements are different)
      //
      s = 2;
      for(i = 0;i < n;i++)
      {
        data[i] = s;
        s += 1 + rand() % 5;
      }
      for(i = j = 0;i <= s;i++)
      {

        k = binary_search_v1(data,n,i);
        if((k == -1) != (j >= n || data[j] != i) || (k >= 0 && data[k] != i))
        {
          fprintf(stderr,"unexpected return value of the binary_search_v1() function\n");
          return 1;
        }

        k = binary_search_v2(data,n,i);
        if((k == -1) != (j >= n || data[j] != i) || (k >= 0 && k != j))
        {
          fprintf(stderr,"unexpected return value of the binary_search_v2() function\n");
          return 1;
        }

        k = binary_search_v3(data,n,i);
        if((k == -1) != (j >= n || data[j] != i) || (k >= 0 && k != j))
        {
          fprintf(stderr,"unexpected return value of the binary_search_v3() function\n");
          return 1;
        }

        // k = binary_search_v4(data,n,i);
        // if((k == -1) != (j >= n || data[j] != i) || (k >= 0 && k != j))
        // {
        //   fprintf(stderr,"unexpected return value of the binary_search_v4() function\n");
        //   return 1;
        // }

        if(j < n && data[j] == i)
          j++;
      }
      //
      // second test (some data[] elements may be equal)
      //
      s = 1;
      for(i = 0;i < n;i++)
      {
        data[i] = s;
        s += rand() % 4;
      }
      for(i = j = 0;i <= s;i++)
      {

        k = binary_search_v1(data,n,i);
        if((k == -1) != (j >= n || data[j] != i) || (k >= 0 && data[k] != i))
        {
          fprintf(stderr,"unexpected return value of the binary_search_v1() function\n");
          return 1;
        }

        k = binary_search_v2(data,n,i);
        if((k == -1) != (j >= n || data[j] != i) || (k >= 0 && k != j))
        {
          fprintf(stderr,"unexpected return value of the binary_search_v2() function\n");
          return 1;
        }

        k = binary_search_v3(data,n,i);
        if((k == -1) != (j >= n || data[j] != i) || (k >= 0 && k != j))
        {
          fprintf(stderr,"unexpected return value of the binary_search_v3() function\n");
          return 1;
        }

        // k = binary_search_v4(data,n,i);
        // if((k == -1) != (j >= n || data[j] != i) || (k >= 0 && k != j))
        // {
        //   fprintf(stderr,"unexpected return value of the binary_search_v4() function\n");
        //   return 1;
        // }

        while(j < n && data[j] == i)
          j++;
      }
    }
  }
  //
  // done
  //
  printf("All went well --- 😀\n");
  return 0;
# undef max_n
}
