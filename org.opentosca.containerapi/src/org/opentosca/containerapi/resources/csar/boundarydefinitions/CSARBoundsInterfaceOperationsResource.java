package org.opentosca.containerapi.resources.csar.boundarydefinitions;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.opentosca.containerapi.osgi.servicegetter.ToscaServiceHandler;
import org.opentosca.containerapi.resources.utilities.ResourceConstants;
import org.opentosca.containerapi.resources.utilities.Utilities;
import org.opentosca.containerapi.resources.xlink.Reference;
import org.opentosca.containerapi.resources.xlink.References;
import org.opentosca.containerapi.resources.xlink.XLinkConstants;
import org.opentosca.core.model.csar.id.CSARID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSARBoundsInterfaceOperationsResource {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(CSARBoundsInterfaceOperationsResource.class);
	CSARID csarID;
	String intName;
	
	UriInfo uriInfo;
	
	
	public CSARBoundsInterfaceOperationsResource(CSARID csarID, String intName) {
		
		this.csarID = csarID;
		this.intName = intName;
		
		if (null == ToscaServiceHandler.getToscaEngineService()) {
			LOG.error("The ToscaEngineService is not alive.");
		}
	}
	
	/**
	 * Builds the references of the Boundary Definitions of a CSAR.
	 * 
	 * @param uriInfo
	 * @return Response
	 */
	@GET
	@Produces(ResourceConstants.LINKED_XML)
	public Response getReferencesXML(@Context UriInfo uriInfo) {
		this.uriInfo = uriInfo;
		return Response.ok(getReferences().getXMLString()).build();
	}
	
	/**
	 * Builds the references of the Boundary Definitions of a CSAR.
	 * 
	 * @param uriInfo
	 * @return Response
	 */
	@GET
	@Produces(ResourceConstants.LINKED_JSON)
	public Response getReferencesJSON(@Context UriInfo uriInfo) {
		this.uriInfo = uriInfo;
		return Response.ok(getReferences().getJSONString()).build();
	}
	
	private References getReferences() {
		
		References refs = new References();
		
		List<String> ops = ToscaServiceHandler.getToscaEngineService().getToscaReferenceMapper().getBoundaryOperationsOfCSARInterface(csarID, intName);
		
		for (String op : ops) {
			refs.getReference().add(new Reference(Utilities.buildURI(uriInfo.getAbsolutePath().toString(), op), XLinkConstants.SIMPLE, op));
		}
		
		// selflink
		refs.getReference().add(new Reference(uriInfo.getAbsolutePath().toString(), XLinkConstants.SIMPLE, XLinkConstants.SELF));
		return refs;
	}
	
	/**
	 * Returns a PublicPlan for a given Index.
	 * 
	 * @param planName
	 * @return the PublicPlan
	 */
	@Path("{OperationName}")
	public CSARBoundsInterfaceOperationResource getPublicPlan(@PathParam("OperationName") String op) {
		return new CSARBoundsInterfaceOperationResource(csarID, intName, op);
	}
	
}
