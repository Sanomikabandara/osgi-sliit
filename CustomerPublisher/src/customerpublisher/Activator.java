package customerpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import lk.sliit.sa.osgi.persistence.service.PersistenceService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	ServiceRegistration publicServiceRegistration ;
	ServiceReference reference;

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Starting Customer Management Service");
		publicServiceRegistration = bundleContext.registerService(CustomerPublish.class.getName(),new CustomerPublisherImpl(), null);
		reference = bundleContext.getServiceReference(PersistenceService.class.getName());
		PersistenceService service = (PersistenceService)context.getService(reference);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		//Activator.context = null;
		System.out.println("Stop Customer Management Service");
		publicServiceRegistration.unregister();
		bundleContext.ungetService(reference);
	}

}
