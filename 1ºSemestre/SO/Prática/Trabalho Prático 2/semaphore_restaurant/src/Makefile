CC = gcc
CFLAGS = -Wall

SUFFIX = $(shell getconf LONG_BIT)

CHEF   = semSharedMemChef
WAITER = semSharedMemWaiter
CLIENT = semSharedMemClient
MAIN   = probSemSharedMemRestaurant

OBJS = sharedMemory.o semaphore.o logging.o

.PHONY: all ct ct_ch all_bin \
	main client waiter chef \
	client_bin waiter_bin chef_bin \
	clean cleanall

all:		client         waiter      chef       main clean
ct:			client         waiter_bin  chef_bin   main clean
ct_wt:		client         waiter      chef_bin   main clean
all_bin:	client_bin     waiter_bin  chef_bin   main clean

chef:	$(CHEF).o $(OBJS)
	$(CC) -o ../run/$@ $^ -lm

waiter:		$(WAITER).o $(OBJS)
	$(CC) -o ../run/$@ $^

client:	$(CLIENT).o $(OBJS)
	$(CC) -o ../run/$@ $^ -lm

main:		$(MAIN).o $(OBJS)
	$(CC) -o ../run/$(MAIN) $^ -lm

chef_bin:
	cp ../run/chef_bin_$(SUFFIX) ../run/chef

waiter_bin:
	cp ../run/waiter_bin_$(SUFFIX) ../run/waiter

client_bin:
	cp ../run/client_bin_$(SUFFIX) ../run/client

clean:
	rm -f *.o

cleanall:	clean
	rm -f ../run/$(MAIN) ../run/chef ../run/waiter ../run/client

