/**
 *  \file logging.h (interface file)
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
 */

#ifndef LOGGING_H_
#define LOGGING_H_

#include "probDataStruct.h"

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
extern void createLog (char nFic[]);

/**
 *  \brief Writing the start of Boarding Process and header.
 *
 *  If <tt>nFic</tt> is a null pointer or a null string, the lines are written to stdout
 *
 *  \param nFic name of the logging file
 *  \param p_fSt pointer to the location where the full internal state of the problem is stored
 */
extern void saveState (char nFic[], FULL_STAT *p_fSt);

#endif /* LOGGING_H_ */
