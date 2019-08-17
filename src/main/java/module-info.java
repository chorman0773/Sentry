import java.nio.file.spi.FileSystemProvider;

import github.chorman0773.sentry.GameBasic;

/**
 * 
 */
/**
 * @author connor
 *
 */
module github.chorman0773.sentry {
	exports github.chorman0773.sentry.event;
	exports github.chorman0773.sentry.annotation;
	exports github.chorman0773.sentry.linterface;
	exports github.chorman0773.sentry.save;
	exports github.chorman0773.sentry.save.nbt;
	exports github.chorman0773.sentry.save.shade;
	exports github.chorman0773.sentry.resources;
	exports github.chorman0773.sentry.cci;
	exports github.chorman0773.sentry.rollback;
	exports github.chorman0773.sentry.cci.core;
	exports github.chorman0773.sentry.authentication;
	exports github.chorman0773.sentry;
	exports github.chorman0773.sentry.login;
	exports github.chorman0773.sentry.text;
	exports github.chorman0773.sentry.test;
	exports github.chorman0773.sentry.generic;
	exports github.chorman0773.sentry.serial;

	requires transitive github.lightningcreations.lclib;
	requires transitive gson;
	requires transitive java.desktop;
	requires transitive java.instrument;
	requires transitive java.xml;
	requires java.sql;
	requires transitive github.lightningcreations.lcjei;
	uses GameBasic;
	provides FileSystemProvider with github.chorman0773.sentry.login.fs.GameFilesystemProvider;
}