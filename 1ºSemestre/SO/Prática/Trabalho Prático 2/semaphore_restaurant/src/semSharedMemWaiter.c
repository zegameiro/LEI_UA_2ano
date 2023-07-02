/**
 *  \file semSharedMemWaiter.c (implementation file)
 *
 *  \brief Problem name: Restaurant
 *
 *  Synchronization based on semaphores and shared memory.
 *  Implementation with SVIPC.
 *
 *  Definition of the operations carried out by the waiter:
 *     \li waitForClientOrChef
 *     \li informChef
 *     \li takeFoodToTable
 *     \li receivePayment
 *
 *  \author Nuno Lau - December 2022
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/types.h>
#include <string.h>
#include <math.h>
#include <assert.h>

#include "probConst.h"
#include "probDataStruct.h"
#include "logging.h"
#include "sharedDataSync.h"
#include "semaphore.h"
#include "sharedMemory.h"

/** \brief logging file name */
static char nFic[51];

/** \brief shared memory block access identifier */
static int shmid;

/** \brief semaphore set access identifier */
static int semgid;

/** \brief pointer to shared memory region */
static SHARED_DATA *sh;

/** \brief waiter waits for next request */
static int waitForClientOrChef ();

/** \brief waiter takes food order to chef */
static void informChef();

/** \brief waiter takes food to table */
static void takeFoodToTable ();

/** \brief waiter receives payment */
static void receivePayment ();

#define FOODREQ   1
#define FOODREADY 2
#define BILL      3


/**
 *  \brief Main program.
 *
 *  Its role is to generate the life cycle of one of intervening entities in the problem: the waiter.
 */
int main (int argc, char *argv[])
{
    int key;                                            /*access key to shared memory and semaphore set */
    char *tinp;                                                       /* numerical parameters test flag */

    /* validation of command line parameters */
    if (argc != 4) { 
        freopen ("error_WT", "a", stderr);
        fprintf (stderr, "Number of parameters is incorrect!\n");
        return EXIT_FAILURE;
    }
    else { 
        freopen (argv[3], "w", stderr);
        setbuf(stderr,NULL);
    }

    strcpy (nFic, argv[1]);
    key = (unsigned int) strtol (argv[2], &tinp, 0);
    if (*tinp != '\0')
    { fprintf (stderr, "Error on the access key communication!\n");
        return EXIT_FAILURE;
    }

    /* connection to the semaphore set and the shared memory region and mapping the shared region onto the
       process address space */
    if ((semgid = semConnect (key)) == -1) { 
        perror ("error on connecting to the semaphore set");
        return EXIT_FAILURE;
    }
    if ((shmid = shmemConnect (key)) == -1) { 
        perror ("error on connecting to the shared memory region");
        return EXIT_FAILURE;
    }
    if (shmemAttach (shmid, (void **) &sh) == -1) { 
        perror ("error on mapping the shared region on the process address space");
        return EXIT_FAILURE;
    }

    /* initialize random generator */
    srandom ((unsigned int) getpid ());              

    /* simulation of the life cycle of the waiter */
    int req, nReq=0;
    while(nReq<3) {
        req = waitForClientOrChef();
        switch(req) {
            case FOODREQ:
                   informChef();
                   break;
            case FOODREADY:
                   takeFoodToTable();
                   break;
            case BILL:
                   receivePayment();
                   break;
        }
        nReq++;
    }

    /* unmapping the shared region off the process address space */
    if (shmemDettach (sh) == -1) {
        perror ("error on unmapping the shared region off the process address space");
        return EXIT_FAILURE;;
    }

    return EXIT_SUCCESS;
}

/**
 *  \brief waiter waits for next request 
 *
 *  Waiter updates state and waits for request from client or from chef, then reads request.
 *  The internal state should be saved.
 *
 *  \return type of request (FOODREQ, FOODREADY, PAYREQ)
 */
static int waitForClientOrChef()
{
    int ret=0;

    if (semDown (semgid, sh->mutex) == -1)  {                        /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    /* insert your code here */
    sh->fSt.st.waiterStat = WAIT_FOR_REQUEST;
    saveState(nFic, &(sh->fSt));
 
    if (semUp (semgid, sh->mutex) == -1)      /* exit critical region */
    {                                             
        perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    /* insert your code here */
    if( semDown(semgid, sh->waiterRequest) == -1)  // wait for request from client or chef
    {
        perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }
    
    if (semDown (semgid, sh->mutex) == -1)  {                                                  /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    /* insert your code here */
    if(sh->fSt.foodRequest == 1)   // If the flag foodRequest has been raised (changed from 0 to 1))
        ret = FOODREQ;
    else if(sh->fSt.foodReady == 1) //  If the flag foodReady has been raised (changed from 0 to 1))
        ret = FOODREADY;
    else if(sh->fSt.paymentRequest == 1) // If the flag paymentRequest has been raised (changed from 0 to 1))
        ret = BILL;

    // mete tudo a zero -> já leu o pedido, já entregou a comida e já fez tudo - pois é ciclo while
    sh->fSt.paymentRequest = 0;
    sh->fSt.foodReady = 0;
    sh->fSt.foodRequest = 0;

    if (semUp (semgid, sh->mutex) == -1) {                                                  /* exit critical region */
     perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    return ret;

}

/**
 *  \brief waiter takes food order to chef 
 *
 *  Waiter updates state and then takes food request to chef.
 *  The internal state should be saved.
 *
 */
static void informChef ()
{
    if (semDown (semgid, sh->mutex) == -1)  {                                                  /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    /* insert your code here */
    sh->fSt.st.waiterStat = INFORM_CHEF;
    saveState(nFic, &(sh->fSt));

    if (semUp (semgid, sh->mutex) == -1)                                                   /* exit critical region */
    { perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    /* insert your code here */
    semUp(semgid, sh->waitOrder); // Take food order to chef
}

/**
 *  \brief waiter takes food to table 
 *
 *  Waiter updates its state and takes food to table, allowing the meal to start.
 *  The internal state should be saved.
 *
 */
static void takeFoodToTable ()
{

    if (semDown (semgid, sh->mutex) == -1)  {                                                  /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    /* insert your code here */

    sh->fSt.st.waiterStat = TAKE_TO_TABLE;
    saveState(nFic, &(sh->fSt));
    
    for (int i = 0 ; i < TABLESIZE ; i++) 
        semUp(semgid, sh->foodArrived);  // Acorda os clientes que estao a espera de comida
    
    semUp(semgid, sh->requestReceived); 
    
    if (semUp (semgid, sh->mutex) == -1)  {                                                  /* exit critical region */
     perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }
}

/**
 *  \brief waiter receives payment 
 *
 *  Waiter updates its state and receives payment from last client.
 *  The internal state should be saved.
 *
 */
static void receivePayment ()
{
    if (semDown (semgid, sh->mutex) == -1)  {                                                  /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    /* insert your code here */
    sh->fSt.st.waiterStat = RECEIVE_PAYMENT;
    saveState(nFic, &(sh->fSt));

    semUp(semgid, sh->requestReceived);

    if (semUp (semgid, sh->mutex) == -1)  {                                                  /* exit critical region */
     perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }
}
