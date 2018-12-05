#include <stdio.h>

#define UNSPECIFIED 0
#define INTERFACE 1
#define DEVICE 2
#define BOTH 3

typedef struct __SerialDevice{
	char* uuidSequence;
	char* label;
	int type;
	FILE* stream;
	int port;
} SerialDevice;
void fillSerialInfo(SerialDevice *bus,int port);
SerialDevice* openSerialBus(int port);

int checkOpen(int port);
SerialDevice* getOpen(int port);
