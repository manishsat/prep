package my.prep.practice;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TestRef {
	
	private Set<String> authenticatedOrgIds;
	public void setAuthenticatedOrgIds(Set<String> authenticatedOrgs) {
        System.out.println("setting orgids-3");
    }


    public void setAuthenticatedOrgIds(String authenticatedOrgIds) {
        System.out.println("setting orgids-4");
    }

    public static void main(String[] args) throws Exception{
    	 Map<String, PropertyDescriptor> propertyDescriptorCache = new LinkedHashMap<String, PropertyDescriptor>();;
    	TestRef obj = new TestRef();
    	PropertyDescriptor[] pds =  Introspector.getBeanInfo(TestRef.class).getPropertyDescriptors();
    	for (PropertyDescriptor pd : pds) {
    		if("authenticatedOrgIds".equals(pd.getName())) {
    			System.out.println(pd.getPropertyType());
    			System.out.println(pd.getWriteMethod());
    			System.out.println(pd.getReadMethod());
    			
    			String value = "794a94bcffd94514897c0407717a6143";
    	    	Method m = pd.getWriteMethod();
    	    	m.invoke(obj, value);
    			
    		}
    	}
    	
    }
}
