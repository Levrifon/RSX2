#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/types.h> 
#include <unistd.h>
#include <netdb.h>
#include <string.h>
#include <stdio.h>
#include <strings.h>

int main(int argc, char**argv)
{
   int sockfd,i,size;
   struct sockaddr_in servaddr;

   sockfd=socket(AF_INET,SOCK_DGRAM,0);

   servaddr.sin_family = AF_INET;
   servaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
   servaddr.sin_port=htons(1234);
   for(i = 0 ; i < 8 ; i++) {
   	servaddr.sin_zero[i] = 0;
   }
   size = sizeof(servaddr);
   bind(sockfd,(struct sockaddr *)&servaddr,sizeof(servaddr));
   sendto(sockfd,argv[1],strlen(argv[1]),0,(struct sockaddr *)&servaddr,size);
	return 1;
}
