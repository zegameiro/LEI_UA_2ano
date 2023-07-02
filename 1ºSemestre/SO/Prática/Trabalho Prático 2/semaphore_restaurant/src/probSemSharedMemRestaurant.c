/**
 *  \file probSemSharedMemRestaurant.c (implementation file)
 *
 *  \brief Problem name: Restaurant
 *
 *  Synchronization based on semaphores and shared memory.
 *  Implementation with SVIPC.
 *
 *  Generator process of the intervening entities.
 *
 *  Upon execution, one parameter is requested:
 *    \li name of the logging file.
 *
 *  \author Nuno Lau - December 2022
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <string.h>
#include <math.h>

#include "probConst.h"
#include "probDataStruct.h"
#include "logging.h"
#include "sharedDataSync.h"
#include "semaphore.h"
#include "sharedMemory.h"

/** \brief name of chef process */
#define   CHEF       "./chef"

/** \brief name of waiter process */
#define   WAITER     "./waiter"

/** \brief name of client process */
#define   CLIENT     "./client"

/**
 *  \brief Main program.
 *
 *  Its role is starting the simulation by generating the intervening entities processes (chef, waiter and clients)
 *  and waiting for their termination.
 */
int main (int argc, char *argv[])
{
    char nFic[51];                                                                              /*name of logging file */
    char nFicErr[] = "error_        ";                                                     /* base name of error files */
    int shmid,                                                                      /* shared memory access identifier */
        semgid;                                                                     /* semaphore set access identifier */
    unsigned int  m;                                                                             /* counting variables */
    SHARED_DATA *sh;                                                                /* pointer to shared memory region */
    int pidCH,                                                                             /* pilot process identifier */
        pidWT,                                                                     /* hostess process identifier array */
        pidCT[TABLESIZE];                                                     /* passengers processes identifier array */
    int key;                                                           /*access key to shared memory and semaphore set */
    char num[2][12];                                                     /* numeric value conversion (up to 10 digits) */
    int status,                                                                                    /* execution status */
        info;                                                                                               /* info id */
    int c;

    /* getting log file name */
    if(argc==2) {
        strcpy(nFic, argv[1]);
    }
    else strcpy(nFic, "");

    /* composing command line */
    if ((key = ftok (".", 'a')) == -1) {
        perror ("error on generating the key");
        exit (EXIT_FAILURE);
    }
    sprintf (num[1], "%d", key);

    //destroy 
    shmemDestroy(shmemConnect(key));
    semDestroy(semConnect(key));

    /* creating and initializing the shared memory region and the log file */
    if ((shmid = shmemCreate (key, sizeof (SHARED_DATA))) == -1) { 
        perror ("error on creating the shared memory region");
        exit (EXIT_FAILURE);
    }
    if (shmemAttach (shmid, (void **) &sh) == -1) { 
        perror ("error on mapping the shared region on the process address space");
        exit (EXIT_FAILURE);
    }

    /* initialize random generator */
    srandom ((unsigned int) getpid ());                                

    /* initialize problem internal status */
    sh->fSt.st.chefStat   = WAIT_FOR_ORDER;                     /* the chef waits for an order */
    sh->fSt.st.waiterStat = WAIT_FOR_REQUEST;                /* the waiter waits for a request */
    for (c = 0; c < TABLESIZE; c++) {
        sh->fSt.st.clientStat[c] = INIT;                            /* clients are initialized */
    }
    sh->fSt.tableClients = 0;
    sh->fSt.tableFinishEat = 0;

    sh->fSt.foodRequest = 0;
    sh->fSt.foodOrder = 0;
    sh->fSt.foodReady = 0;
    sh->fSt.paymentRequest = 0;

    sh->fSt.tableLast = -1;

    /* create log file */
    createLog (nFic);                                  

    /* initialize semaphore ids */
    sh->mutex           = MUTEX;                                /* mutual exclusion semaphore id */
    sh->friendsArrived  = FRIENDSARRIVED;                                       
    sh->requestReceived = REQUESTRECEIVED;                              
    sh->foodArrived     = FOODARRIVED;                           
    sh->allFinished     = ALLFINISHED;                                      
    sh->waiterRequest   = WAITERREQUEST;                                                      
    sh->waitOrder       = WAITORDER;                                                      

    /* creating and initializing the semaphore set */
    if ((semgid = semCreate (key, SEM_NU)) == -1) { 
        perror ("error on creating the semaphore set");
        exit (EXIT_FAILURE);
    }
    if (semUp (semgid, sh->mutex) == -1) {                   /* enabling access to critical region */
        perror ("error on executing the up operation for semaphore access");
        exit (EXIT_FAILURE);
    }

    /* generation of intervening entities processes */                            
    /* client processes */
    strcpy (nFicErr + 6, "CT");
    for (c = 0; c < TABLESIZE; c++) {           
        if ((pidCT[c] = fork ()) < 0) {
            perror ("error on the fork operation for the client");
            exit (EXIT_FAILURE);
        }
        sprintf(num[0],"%d",c);
        sprintf(nFicErr+8,"%02d",c); 
        if (pidCT[c] == 0)
            if (execl (CLIENT, CLIENT, num[0], nFic, num[1],nFicErr, NULL) < 0) { 
                perror ("error on the generation of the client process");
                exit (EXIT_FAILURE);
            }
    }
    /* waiter process */
    strcpy (nFicErr + 6, "WT");
    if ((pidWT = fork ()) < 0)  {                            
        perror ("error on the fork operation for the waiter");
        exit (EXIT_FAILURE);
    }
    if (pidWT == 0) {
        if (execl (WAITER, WAITER, nFic, num[1], nFicErr, NULL) < 0) {
            perror ("error on the generation of the waiter process");
            exit (EXIT_FAILURE);
        }
    }
    /* chef process */
    strcpy (nFicErr + 6, "CH");
    if ((pidCH = fork ()) < 0) {               
        perror ("error on the fork operation for the chef");
        exit (EXIT_FAILURE);
    }
    if (pidCH == 0)
        if (execl (CHEF, CHEF, nFic, num[1], nFicErr, NULL) < 0) { 
            perror ("error on the generation of the chef process");
            exit (EXIT_FAILURE);
        }

    /* signaling start of operations */
    if (semSignal (semgid) == -1) {
        perror ("error on signaling start of operations");
        exit (EXIT_FAILURE);
    }

    /* waiting for the termination of the intervening entities processes */
    m = 0;
    do {
        info = wait (&status);
        if (info == -1)
        { perror ("error on waiting for an intervening process");
            exit (EXIT_FAILURE);
        }
        m += 1;
    } while (m < TABLESIZE+2);

    /* destruction of semaphore set and shared region */
    if (semDestroy (semgid) == -1) {
        perror ("error on destructing the semaphore set");
        exit (EXIT_FAILURE);
    }
    if (shmemDettach (sh) == -1) { 
        perror ("error on unmapping the shared region off the process address space");
        exit (EXIT_FAILURE);
    }
    if (shmemDestroy (shmid) == -1) { 
        perror ("error on destructing the shared region");
        exit (EXIT_FAILURE);
    }

    return EXIT_SUCCESS;
}
