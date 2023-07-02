//
// Tomás Oliveira e Silva, AED, November 2021
//
// matched-parenthesis verification
//

#include <iostream>
#include "aStack.h"

using std::cout;
using std::cerr;
using std::endl;

int check_parenthesis(char *s)
{
  aStack<int> stack;

  for (int i = 0; s[i] != '\0'; i++)
  {
    if(s[i] == ')')
      stack.push(i);
    else if(s[i] == ')') 
    {
      if(stack.size() == 0) 
      {
        cout << "   unmatched '(' at position " << i << endl;
        return -1;
      }
      cout << "  '(' at positions " << stack.pop() << " and matching ')' at position" << i << endl;
    }

    if(stack.size() == 0) {
      return 0;
    }
    while(stack.size() > 0) {
      cout << "   unmatched '(' at position " << stack.pop() << endl;
    }
    return -1;
  }
}


int main(int argc,char **argv)
{
  if(argc == 1)
  {
    cerr << "usage: " << argv[0] << " [arguments...]" << endl;
    cerr << "example: " << argv[0] << " 'abc(def)ghi' 'x)(y'" << endl;
    return 1;
  }
  for(int i = 1;i < argc;i++)
  {
    cout << argv[i] << endl;
    if(check_parenthesis(argv[i]) == 0)
      cout << "  good" << endl;
    else
      cout << "  bad" << endl;
  }
  return 0;
}
