package github.chorman0773.sentry.resources;

import java.io.InputStream;
import java.util.Map;

import github.chorman0773.sentry.text.I18N;

public abstract class ResourcePack {

	public ResourcePack() {
		// TODO Auto-generated constructor stub
	}

	public abstract Resource getResource(String resourceKey,ResourceType target);

	public abstract Resource[] getAllResources(ResourceType target);

	protected abstract InputStream getResourceStream(Resource target);

	public abstract Map<String,I18N> getAvailbleLanguages();



}
