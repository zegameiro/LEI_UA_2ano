/**
 *  \file sharedDataSync.h (interface file)
 *
 *  \brief Problem name: Restaurant
 *
 *  Synchronization based on semaphores and shared memory.
 *  Implementation with SVIPC.
 *
 *  \brief Definition of the shared data and the synchronization devices.
 *
 *  Both the format of the shared data, which represents the full state of the problem, and the identification of
 *  the different semaphores, which carry out the synchronization among the intervening entities, are provided.
 *
 *  \author Nuno Lau - December 2022
 */

#ifndef SHAREDDATASYNC_H_
#define SHAREDDATASYNC_H_

#include "probConst.h"
#include "probDataStruct.h"

/**
 *  \brief Definition of <em>shared information</em> data type.
 */
typedef struct
        { /** \brief full state of the problem */
          FULL_STAT fSt;

          /* semaphores ids */
          /** \brief identification of critical region protection semaphore - val = 1 */
          unsigned int mutex;
          /** \brief identification of semaphore used by clients to wait for friends to arrive - val = 0 */
          unsigned int friendsArrived;
          /** \brief identification of semaphore used by client to wait for waiter after a request - val = 0 */
          unsigned int requestReceived;
          /** \brief identification of semaphore used by clients to wait for food - val = 0 */
          unsigned int foodArrived;
          /** \brief identification of semaphore used by clients to wait for friends to finish eating - val = 0  */
          unsigned int allFinished;
          /** \brief identification of semaphore used by waiter to wait for requests - val = 0  */
          unsigned int waiterRequest;
          /** \brief identification of semaphore used by chef to wait for order - val = 0  */
          unsigned int waitOrder;

        } SHARED_DATA;

/** \brief number of semaphores in the set */
#define SEM_NU               (7)

#define MUTEX                  1
#define FRIENDSARRIVED         2
#define REQUESTRECEIVED        3
#define FOODARRIVED            4
#define ALLFINISHED            5
#define WAITERREQUEST          6
#define WAITORDER              7

#endif /* SHAREDDATASYNC_H_ */