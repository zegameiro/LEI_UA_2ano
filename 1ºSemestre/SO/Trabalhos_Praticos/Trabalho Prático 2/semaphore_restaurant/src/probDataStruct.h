/**
 *  \file probDataStruct.h (interface file)
 *
 *  \brief Problem name: Restaurant
 *
 *  Definition of internal data structures.
 *
 *  They specify internal metadata about the status of the intervening entities.
 *
 *  \author Nuno Lau - December 2022
 */

#ifndef PROBDATASTRUCT_H_
#define PROBDATASTRUCT_H_

#include <stdbool.h>

#include "probConst.h"


/**
 *  \brief Definition of <em>state of the intervening entities</em> data type.
 */
typedef struct {
    /** \brief waiter state */
    unsigned int waiterStat;
    /** \brief chef state */
    unsigned int chefStat;
    /** \brief client state array */
    unsigned int clientStat[TABLESIZE];

} STAT;


/**
 *  \brief Definition of <em>full state of the problem</em> data type.
 */
typedef struct
{   /** \brief state of all intervening entities */
    STAT st;

    /** \brief number of clients at table */
    int tableClients; // = 0
    /** \brief number of clients that finished eating */
    int tableFinishEat;

    /** \brief flag of food request from client to waiter */
    int foodRequest; 
    /** \brief flag of food order from waiter to chef */
    int foodOrder;
    /** \brief flag of food ready from chef to waiter */
    int foodReady;
    /** \brief flag of payment request from client to waiter */
    int paymentRequest;

    /** \brief id of first client to arrive */
    int tableFirst;
    /** \brief id of last client to arrive */
    int tableLast;

} FULL_STAT;


#endif /* PROBDATASTRUCT_H_ */
