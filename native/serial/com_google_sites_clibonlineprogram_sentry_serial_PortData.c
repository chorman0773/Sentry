
#include "com_google_sites_clibonlineprogram_sentry_serial_PortData.h"

#ifndef CORE_h
#include "Core.h"
#include "SerialLib.h"
#include "/serial/serialbus.h"
#endif

JNIEXPORT jstring JNICALL Java_com_google_sites_clibonlineprogram_sentry_serial_PortData_getLabel0
  (JNIEnv *env, jclass cl, jint port){
	int target = (int) port;
	SerialDevice device;
	char *ret;
	jclass exc;
	if(checkOpen(target))
		device = getOpen(target);
	else
		device = openSerialBus(target);
	if(device == NULL){
		exc = (*env)->GetClass("java/io/IOException");
		(*env)->ThrowNew(env,exc,"Can't Find the given Port");
		return NULL;
	}
	ret = device.label;
	return (*env)->NewStringUTF(ret);
}

JNIEXPORT jint JNICALL Java_com_google_sites_clibonlineprogram_sentry_serial_PortData_getType0
  (JNIEnv *env, jclass cl, jint port){
	int target = (int) port;
		SerialDevice device;
		int ret;
		jclass exc;
		if(checkOpen(target))
			device = getOpen(target);
		else
			device = openSerialBus(target);
		if(device == NULL){
			exc = (*env)->GetClass("java/io/IOException");
			(*env)->ThrowNew(env,exc,"Can't Find the given Port");
			return NULL;
		}
		ret = device.type;
		return (jint) ret;
}
