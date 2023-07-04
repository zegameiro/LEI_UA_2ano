//
// AED, August 2022 (Tomás Oliveira e Silva)
//
// First practical assignement (speed run)
//
// Compile using either
//   cc -Wall -O2 -D_use_zlib_=0 solution_speed_run.c -lm
// or
//   cc -Wall -O2 -D_use_zlib_=1 solution_speed_run.c -lm -lz
//
// Place your student numbers and names here
//   N.Mec. 108712  Name: Diogo Falcão
//   N.Mec. 108840  Name: José Gameiro

//
// static configuration
//

#define _max_road_size_ 800 // the maximum problem size
#define _min_road_speed_ 2  // must not be smaller than 1, shouldnot be smaller than 2
#define _max_road_speed_ 9  // must not be larger than 9 (only because of the PDF figure)

//
// include files --- as this is a small project, we include the PDF generation code directly from make_custom_pdf.c
//

#include <math.h>
#include <stdio.h>
#include "../P02/elapsed_time.h"
#include "make_custom_pdf.c"

//
// road stuff
//

static int max_road_speed[1 + _max_road_size_]; // positions 0.._max_road_size_

static void init_road_speeds(void)
{
  double speed;
  int i;

  for (i = 0; i <= _max_road_size_; i++)
  {
    speed = (double)_max_road_speed_ * (0.55 + 0.30 * sin(0.11 * (double)i) + 0.10 * sin(0.17 * (double)i + 1.0) + 0.15 * sin(0.19 * (double)i));
    max_road_speed[i] = (int)floor(0.5 + speed) + (int)((unsigned int)random() % 3u) - 1;
    if (max_road_speed[i] < _min_road_speed_)
      max_road_speed[i] = _min_road_speed_;
    if (max_road_speed[i] > _max_road_speed_)
      max_road_speed[i] = _max_road_speed_;
  }
}

//
// description of a solution
//

typedef struct
{
  int n_moves;                        // the number of moves (the number of positions is one more than the number of moves)
  int positions[1 + _max_road_size_]; // the positions (the first one must be zero)
} solution_t;

//
// the (very inefficient) recursive solution given to the students
//

static solution_t solution_1, solution_1_best;
static double solution_1_elapsed_time; // time it took to solve the problem
static unsigned long solution_1_count; // effort dispended solving the problem
// static int visited[_max_road_size_][_max_road_size_][_max_road_speed_]; 3 dimension array to store the visited positions and to use and backtracking approach to solve the problem
// But this approach wasn't good because it took more time then the branch and bound approach

static void solution_1_recursion(int move_number, int position, int speed, int final_position)
// position -> posição no momento
// speed -> velocidade no momento
// final_position -> posição final
{
  int i, new_speed;

  // record move -> esforço que faz
  solution_1_count++;
  solution_1.positions[move_number] = position;
  // is it a solution?
  if (position == final_position && speed == 1)
  {
    // is it a better solution?
    if (move_number < solution_1_best.n_moves)
    {
      solution_1_best = solution_1;
      solution_1_best.n_moves = move_number;
    }
    return; // Funciona como um break na função recursiva
  }
  // branch-and-bound
  if (solution_1_best.positions[move_number] > solution_1.positions[move_number])
    return;
  // backtrack
  // if (visited[move_number][position][speed] == 1) // if the position was already visited, return to the previous position and try another speed
  //  return; 

  // visited[move_number][position][speed] = 1; // mark the position as visited

  // no, try all legal speed
  for (new_speed = speed + 1; new_speed >= speed - 1; new_speed--)
  {
    if (new_speed >= 1 && new_speed <= _max_road_speed_ && position + new_speed <= final_position)
    {
      for (i = 0; i <= new_speed && new_speed <= max_road_speed[position + i]; i++)
        ;

      if (i > new_speed)
      {
        solution_1_recursion(move_number + 1, position + new_speed, new_speed, final_position);
      }
    }
  }
}

static void solve_1(int final_position)
{
  // Clear the visited array
  // for (int i = 0; i < final_position; i++)
  //   for (int j = 0 ; j < final_position; j++)
  //     for (int k = 0; k < _max_road_speed_; k++)
  //       visited[i][j][k] = 0;
    

  if (final_position < 1 || final_position > _max_road_size_)
  {
    fprintf(stderr, "solve_1: bad final_position\n");
    exit(1);
  }
  solution_1_elapsed_time = cpu_time();
  solution_1_count = 0ul;
  solution_1_best.n_moves = final_position;
  solution_1_recursion(0, 0, 0, final_position);
  solution_1_elapsed_time = cpu_time() - solution_1_elapsed_time;
}

static solution_t solution_2, solution_2_best;
static double solution_2_elapsed_time; // time it took to solve the problem
static unsigned long solution_2_count; // effort dispended solving the problem

//----------------------------------------------------------------------------

static void solution_2_recursion(int move_number, int position, int speed, int final_position)
// position -> posição no momento
// speed -> velocidade no momento
// final_position -> posição final
{
  int i, new_speed;
  // already found one solution?
  if (solution_2_best.n_moves != final_position)
  {
    // return because its the best solution
    return;
  }
  // record move -> esforço que faz
  solution_2_count++;
  solution_2.positions[move_number] = position;
  // is it a solution?
  if (position == final_position && speed == 1)
  {
    // is it a better solution?
    if (move_number < solution_2_best.n_moves)
    {
      solution_2_best = solution_2;
      solution_2_best.n_moves = move_number;
    }
    return; // Funciona como um break na função recursiva
  }

  // no, try all legal speed
  for (new_speed = speed + 1; new_speed >= speed - 1; new_speed--)
  {
    if (new_speed >= 1 && new_speed <= _max_road_speed_ && position + new_speed <= final_position)
    {
      for (i = 0; i <= new_speed && new_speed <= max_road_speed[position + i]; i++)
        ;
      if (i > new_speed)
      {
        solution_2_recursion(move_number + 1, position + new_speed, new_speed, final_position);
      }
    }
  }
}

static void solve_2(int final_position)
{
  if (final_position < 1 || final_position > _max_road_size_)
  {
    fprintf(stderr, "solve_2: bad final_position\n");
    exit(1);
  }
  solution_2_elapsed_time = cpu_time();
  solution_2_count = 0ul;
  solution_2_best.n_moves = final_position;
  solution_2_recursion(0, 0, 0, final_position);
  solution_2_elapsed_time = cpu_time() - solution_2_elapsed_time;
}

static solution_t solution_3, solution_3_best;
static double solution_3_elapsed_time; // time it took to solve the problem
static unsigned long solution_3_count; // effort dispended solving the problem

static void solution_3_iterative(int move_number, int position, int speed, int final_position)
{
  int i, new_speed,j;
  int new_pos;
  while (position < final_position) 
  {
    for (new_speed = speed + 1; new_speed >= 1; new_speed--)
    {
      int break_distance = new_speed * (new_speed + 1) / 2;
      if (new_speed >= 1 && new_speed <= _max_road_speed_ && position + break_distance <= final_position)
      {
        // check break distance
        new_pos = position;
        for (i = new_speed ; i >= 1 ; i--) {// de velocidade maxima até 1
          for (j = 0; j <= i && i <= max_road_speed[new_pos + j]; j++);
          if (j <= i) {
            break; // nao respeitou um limite i <= max_road_speed[new_pos + j]
          }
          new_pos = new_pos + i;
        }

        if (i == 0) // se a velocidade for válida
        {
          position += new_speed;
          speed = new_speed;
          solution_3_count++;
          solution_3.positions[move_number++] = position;
          solution_3.n_moves = move_number;
          break;
        }
      }
    }
  }
  solution_3_best = solution_3;
}

static void solve_3(int final_position)
{
  if (final_position < 1 || final_position > _max_road_size_)
  {
    fprintf(stderr, "solve_3: bad final_position\n");
    exit(1);
  }
  solution_3_elapsed_time = cpu_time();
  solution_3_count = 0ul;
  solution_3_best.n_moves = final_position;
  solution_3_iterative(0, 0, 0, final_position);
  solution_3_elapsed_time = cpu_time() - solution_3_elapsed_time;
}

static solution_t solution_4, solution_4_best;
static double solution_4_elapsed_time; // time it took to solve the problem
static unsigned long solution_4_count; // effort dispended solving the problem
static int last_move_number = 0;
static int last_position = 0;
static int last_speed = 0;

// Dynamic programming solution using a iterative solution, meaning that it uses the result of the best solution found and it calculates were does it need to start reducing its speed
static void solution_4_iterative(int move_number, int position, int speed, int final_position)
{
  int i, new_speed,j;
  int new_pos;
  int reducing = 0;
  while (position < final_position)
  {
    for (new_speed = speed + 1; new_speed >= 1; new_speed--)
    {
      int break_distance = new_speed * (new_speed + 1) / 2; // Calculates the distance that the car will travel until it needs to break
      
      if (position + break_distance > final_position) { // If the car is going to break after the final position, it will not be a valid solution
        if (reducing == 0) { 
          last_move_number = move_number;
          last_speed = speed;
          last_position = position;
        }
        reducing = 1;
        continue;
      }
      if (new_speed >= 1 && new_speed <= _max_road_speed_)
      {
        // check break distance
        new_pos = position;
        for (i = new_speed ; i >= 1 ; i--) {// de velocidade maxima até 1
          for (j = 0; j <= i && i <= max_road_speed[new_pos + j]; j++);
          if (j <= i) {
            break; // nao respeitou um limite i <= max_road_speed[new_pos + j]
          }
          new_pos = new_pos + i;
        }

        if (i == 0) // se a velocidade for válida
        {
          position += new_speed;
          speed = new_speed;
          solution_4_count++;
          solution_4.positions[move_number++] = position;
          solution_4.n_moves = move_number;
          break;
        }
      }
    }
  }
  solution_4_best = solution_4;
}

static void solve_4(int final_position)
{
  if (final_position < 1 || final_position > _max_road_size_)
  {
    fprintf(stderr, "solve_3: bad final_position\n");
    exit(1);
  }
  solution_4_elapsed_time = cpu_time();
  solution_4_count = 0ul;
  solution_4_best.n_moves = final_position;
  solution_4_iterative(last_move_number, last_position, last_speed, final_position);
  solution_4_elapsed_time = cpu_time() - solution_4_elapsed_time;
}

//----------------------------------------------------------------------------

//
// example of the slides
//

static void example(void)
{
  int i, final_position;

  srandom(0xAED2022);
  init_road_speeds();
  final_position = 30;
  solve_1(final_position);
  make_custom_pdf_file("example.pdf", final_position, &max_road_speed[0], solution_1_best.n_moves, &solution_1_best.positions[0], solution_1_elapsed_time, solution_1_count, "Branch and Bound");
  printf("mad road speeds:");
  for (i = 0; i <= final_position; i++)
    printf(" %d", max_road_speed[i]);
  printf("\n");
  printf("positions:");
  for (i = 0; i <= solution_1_best.n_moves; i++)
    printf(" solution 1 = %d", solution_1_best.positions[i]);
  printf("\n");
}
//
// main program
//

int main(int argc, char *argv[argc + 1])
{
#define _time_limit_ 3600.0                  // 1 hora de execução
  int n_mec, final_position, print_this_one; // print_this_one -> imprime o pdf // n_mec -> numero de casas
  char file_name[64];

  // generate the example data
  if (argc == 2 && argv[1][0] == '-' && argv[1][1] == 'e' && argv[1][2] == 'x')
  {
    example();
    return 0;
  }
  // initialization
  n_mec = (argc < 2) ? 0xAED2022 : atoi(argv[1]);
  srandom((unsigned int)n_mec);
  init_road_speeds();
  // run all solution methods for all interesting sizes of the problem
  final_position = 1;
  solution_1_elapsed_time = 0.0;
  solution_2_elapsed_time = 0.0;
  solution_3_elapsed_time = 0.0;
  printf("    + --- ---------------- --------- +     + --- ---------------- --------- +     + --- ---------------- --------- +     + --- ---------------- --------- +\n");
  printf("    |               Branch and Bound |     |                plain recursion |     |                plain iterative |     |              dynamic iterative |\n");
  printf("--- + --- ---------------- --------- + --- + --- ---------------- --------- + --- + --- ---------------- --------- + --- + --- ---------------- --------- +\n");
  printf("  n | sol            count  cpu time |   n | sol            count  cpu time |   n | sol            count  cpu time |   n | sol            count  cpu time |\n");
  printf("--- + --- ---------------- --------- + --- + --- ---------------- --------- + --- + --- ---------------- --------- + --- + --- ---------------- --------- +\n");
  while (final_position <= _max_road_size_ /* && final_position <= 20*/)
  {
    print_this_one = (final_position == 10 || final_position == 20 || final_position == 50 || final_position == 100 || final_position == 200 || final_position == 400 || final_position == 800) ? 1 : 0;
    printf("%3d |", final_position);
    // first solution method (branch and bound)
    if (solution_1_elapsed_time < _time_limit_)
    {
      solve_1(final_position);
      if (print_this_one != 0)
      {
        sprintf(file_name, "result_1_%s.pdf", argv[1]);
        make_custom_pdf_file(file_name, final_position, &max_road_speed[0], solution_1_best.n_moves, &solution_1_best.positions[0], solution_1_elapsed_time, solution_1_count, "Branch and Bound Approach(recursive)");
      }
      printf(" %3d %16lu %9.3e | ", solution_1_best.n_moves, solution_1_count, solution_1_elapsed_time);
    }
    else
    {
      solution_1_best.n_moves = -1;
      printf("                                |");
    }
    // second solution method (return first solution)
    print_this_one = (final_position == 10 || final_position == 20 || final_position == 50 || final_position == 100 || final_position == 200 || final_position == 400 || final_position == 800) ? 1 : 0;
    printf("%3d |", final_position);
    if (solution_2_elapsed_time < _time_limit_)
    {
      solve_2(final_position);
      if (print_this_one != 0)
      {
        sprintf(file_name, "result_2_%s.pdf", argv[1]);
        make_custom_pdf_file(file_name, final_position, &max_road_speed[0], solution_2_best.n_moves, &solution_2_best.positions[0], solution_2_elapsed_time, solution_2_count, "Plain recursion");
      }
      printf(" %3d %16lu %9.3e | ", solution_2_best.n_moves, solution_2_count, solution_2_elapsed_time);
    }
    else
    {
      solution_2_best.n_moves = -1;
      printf("                                |");
    }
    // third solution method (iterative with break distance)
    print_this_one = (final_position == 10 || final_position == 20 || final_position == 50 || final_position == 100 || final_position == 200 || final_position == 400 || final_position == 800) ? 1 : 0;
    printf("%3d |", final_position);
    if (solution_3_elapsed_time < _time_limit_)
    {
      solve_3(final_position);
      if (print_this_one != 0)
      {
        sprintf(file_name, "result_3_%s.pdf", argv[1]);
        make_custom_pdf_file(file_name, final_position, &max_road_speed[0], solution_3_best.n_moves, &solution_3_best.positions[0], solution_3_elapsed_time, solution_3_count, "Iterative Implementation");
      }
      printf(" %3d %16lu %9.3e | ", solution_3_best.n_moves, solution_3_count, solution_3_elapsed_time);
    }
    else
    {
      solution_3_best.n_moves = -1;
      printf("                                |");
    }
    // fourth solution method (iterative with break distance)
    print_this_one = (final_position == 10 || final_position == 20 || final_position == 50 || final_position == 100 || final_position == 200 || final_position == 400 || final_position == 800) ? 1 : 0;
    printf("%3d |", final_position);
    if (solution_4_elapsed_time < _time_limit_)
    {
      solve_4(final_position);
      if (print_this_one != 0)
      {
        sprintf(file_name, "result_4_%s.pdf", argv[1]);
        make_custom_pdf_file(file_name, final_position, &max_road_speed[0], solution_4_best.n_moves, &solution_4_best.positions[0], solution_4_elapsed_time, solution_4_count, "Dynamic Iterative Implementation");
      }
      printf(" %3d %16lu %9.3e | ", solution_4_best.n_moves, solution_4_count, solution_4_elapsed_time);
    }
    else
    {
      solution_4_best.n_moves = -1;
      printf("                                |");
    }

    // done
    printf("\n");
    fflush(stdout);
    // new final_position
    if (final_position < 50)
      final_position += 1;
    else if (final_position < 100)
      final_position += 5;
    else if (final_position < 200)
      final_position += 10;
    else
      final_position += 20;
  }
  printf("--- + --- ---------------- --------- + --- + --- ---------------- --------- + --- + --- ---------------- --------- + --- + --- ---------------- --------- +\n");
  return 0;
#undef _time_limit_
}
