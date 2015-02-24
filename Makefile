CC = gcc
CFLAGS =  -Wall -Werror -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g


tpdatagram: tpdatagram.c
	$(CC) $(CFLAGS) -o $@ $^

%.o: %.c
	gcc  $(CFLAGS) -c $^
