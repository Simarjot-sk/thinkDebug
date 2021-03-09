package com.simarjot.task.network.model.server_response;

/**
 * this class creates an abstraction over response received from server
 * <p>
 * it can have two subclasses, Success and Failure,
 * Success will hold the data received from the server
 * and Failure will hold the reason message for the failure
 * </p>
 *
 * @param <T> the type of data in the success case
 */
abstract public class ServerResponse<T> {
}
