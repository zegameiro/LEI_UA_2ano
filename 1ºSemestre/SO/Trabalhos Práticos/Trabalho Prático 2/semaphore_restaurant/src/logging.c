/**
 *  \file logging.c (implementation file)
 *
 *  \brief Problem name: Restaurant
 *
 *  \brief Logging the internal state of the problem into a file.
 *
 *  Defined operations:
 *     \li file initialization
 *     \li writing the present full state as a single line at the end of the file.
 *
 *  \author Nuno Lau - December 2022
 *  
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include <sys/types.h>
#include <unistd.h>


#include "probConst.h"
#include "probDataStruct.h"

/* internal functions */

static FILE *openLog(char nFic[], char mode[])
{
    FILE *fic;
    char *fName;                                                                                      /* log file name */

    if ((nFic == NULL) || (strlen (nFic) == 0)) {
        return stdout;
    }
    else  {
        fName = nFic;
    }

    fprintf(stderr,"%d opening log %s %s\n",getpid(),nFic,mode);

    if ((fic = fopen (fName, mode)) == NULL) {
        perror ("error on opening log file");
        exit (EXIT_FAILURE);
    }
    return fic;
}

static void closeLog(FILE *fic)
{
    if(fic==stderr || fic == stdout) {
         fflush(fic);
         return;
    }

    if (fclose (fic) == EOF) {
        perror ("error on closing of log file");
        exit (EXIT_FAILURE);
    }
}

static void printHeader(FILE *fic)
{
    fprintf(fic,"%3s","CH");
    fprintf(fic,"%3s","WT");
    fprintf(fic," ");
    int c;
    for(c=0; c < TABLESIZE; c++) {
        fprintf(fic," %s%02d","C",c);
    }


    fprintf(fic," ");
    fprintf(fic,"%4s","ATT");
    fprintf(fic,"%4s","FIE");
    fprintf(fic,"%4s","1st");
    fprintf(fic,"%4s","las");


    fprintf(fic,"\n");
}

/* external functions */

/**
 *  \brief File initialization.
 *
 *  The function creates the logging file and writes its header.
 *  If <tt>nFic</tt> is a null pointer or a null string, stdout is used.
 *
 *  The file header consists of
 *       \li a title line
 *       \li a blank line.
 *
 *  \param nFic name of the logging file
 */
void createLog (char nFic[])
{
    FILE *fic;                                                                                      /* file descriptor */

    fic = openLog(nFic,"w");

    /* title line + blank line */

    fprintf (fic, "%31cRestaurant - Description of the internal state\n\n", ' ');
    printHeader(fic);

    closeLog(fic);
}

/**
 *  \brief Writing the present full state as a single line at the end of the file.
 *
 *  If <tt>nFic</tt> is a null pointer or a null string, the lines are written to stdout
 *
 *  The following layout is obeyed for the full state in a single line
 *    \li chef state
 *    \li waiter state 
 *    \li clients state 
 *    \li number of clients at table
 *    \li number of clients that finished eating
 *    \li id of first client
 *    \li id of last client
 *
 *  \param nFic name of the logging file
 *  \param p_fSt pointer to the location where the full internal state of the problem is stored
 */
void saveState (char nFic[], FULL_STAT *p_fSt)
{
    FILE *fic;                                                                                      /* file descriptor */

    fic = openLog(nFic,"a");

    fprintf(fic,"%3d",p_fSt->st.chefStat);
    fprintf(fic,"%3d",p_fSt->st.waiterStat);
    fprintf(fic," ");
    int c;
    for(c=0; c < TABLESIZE; c++) {
        fprintf(fic,"%4d",p_fSt->st.clientStat[c]);
    }


    fprintf(fic," ");
    fprintf(fic,"%4d",p_fSt->tableClients);
    fprintf(fic,"%4d",p_fSt->tableFinishEat);
    fprintf(fic,"%4d",p_fSt->tableFirst);
    fprintf(fic,"%4d",p_fSt->tableLast);


    fprintf(fic,"\n");

    closeLog(fic);
}

