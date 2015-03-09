package org.opentosca.planbuilder.type.plugin.mysqldatabase;

import javax.xml.namespace.QName;

/**
 *
 * Copyright 2014 IAAS University of Stuttgart <br>
 * <br>
 *
 * @author Kalman Kepes - kepeskn@studi.informatik.uni-stuttgart.de
 *
 */
public final class Constants {

	// NodeTypes
	public static final QName mySqlDbType = new QName("http://opentosca.org/types/declarative", "MySQLDatabase");
	public static final QName mySqlServerType = new QName("http://docs.oasis-open.org/tosca/ns/2011/12/ToscaSpecificTypes", "MySQL");
	
	// ArtifactTypes
	public static final QName sqlScriptArtifactType = new QName("http://opentosca.org/types/declarative", "SQLScriptArtifact");

	public static final String[] configureDBScriptParameters = {"Target_RootPassword", "Source_DBPassword", "Source_DBName", "Source_DBUser", "Source_DBPort"};
}
