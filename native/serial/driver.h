#include <stdio.h>

typedef struct DeviceDriver{
FILE* driverFile;
char* type;
};
DeviceDriver* defineDriver(char* type, char* file){
DeviceDriver* driver = malloc(sizeof(DeviceDriver));
driver.type = type;
driver.driverFile = fopen(file,"r");
return driver;
}
