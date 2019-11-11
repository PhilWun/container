package org.opentosca.container.core.model.instance;

import java.net.URI;

/**
 * Converts internal service and nodeInstanceIDS (the int values inside the DB) to external URIs
 * which can be used by external services and vice-versa
 */
@Deprecated
public class IdConverter {

    // TODO refactor to match new API

    public final static String containerApiRoot = "/containerapi";
    public final static String nodeInstancePath = "/instancedata/nodeInstances/";
    public final static String relationInstancePath = "/instancedata/relationInstances/";
    public final static String serviceInstancePath = "/instancedata/serviceInstances/";


    public static Integer nodeInstanceUriToID(final URI nodeInstanceID) {
        String path = nodeInstanceID.getPath();

        if (path.contains(nodeInstancePath) && path.contains(containerApiRoot)) {
            path = path.replace(containerApiRoot, "");
            path = path.replace(nodeInstancePath, "");
        }

        try {
            return Integer.parseInt(path);
        }
        catch (final NumberFormatException e) {
            return nodeInstanceUriToID(path);
        }
    }

    /**
     * Returns the integer id of the given URI path
     *
     * @param nodeInstanceIDPath a URI path
     * @return an Integer whether the path points to a nodeInstance or null
     */
    private static Integer nodeInstanceUriToID(final String nodeInstanceIDPath) {

        final String[] paths = nodeInstanceIDPath.split("/");

        // if the paths are at the end are correct we assume a good URI
        if (paths[paths.length - 2].equals("Instances") & paths[paths.length - 4].equals("NodeTemplates")) {
            try {
                return Integer.parseInt(paths[paths.length - 1]);
            }
            catch (final NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    public static Integer relationInstanceUriToID(final URI relationInstanceID) {
        String path = relationInstanceID.getPath();

        if (path.contains(relationInstancePath) && path.contains(containerApiRoot)) {
            path = path.replace(containerApiRoot, "");
            path = path.replace(relationInstancePath, "");
        }

        try {
            return Integer.parseInt(path);
        }
        catch (final NumberFormatException e) {
            return relationInstanceUriToID(path);
        }
    }

    private static Integer relationInstanceUriToID(final String relationInstanceIDPath) {
        final String[] paths = relationInstanceIDPath.split("/");

        // if the paths are at the end are correct we assume a good URI
        if (paths[paths.length - 2].equals("Instances") & paths[paths.length - 4].equals("RelationshipTemplates")) {
            try {
                return Integer.parseInt(paths[paths.length - 1]);
            }
            catch (final NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    public static Integer serviceInstanceUriToID(final URI serviceInstanceID) {
        String path = serviceInstanceID.getPath();

        if (path.contains(containerApiRoot) && path.contains(serviceInstancePath)) {
            path = path.replace(containerApiRoot, "");
            path = path.replace(serviceInstancePath, "");
        }

        try {
            return Integer.parseInt(path);
        }
        catch (final NumberFormatException e) {
            return null;
        }
    }
}
