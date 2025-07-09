package com.kd_rails.demo.utility;

import com.kd_rails.demo.exception.EmptySourceAndDestinationException;
import com.kd_rails.demo.exception.InvalidInputSourceAndDestinationException;

public class RouteUtils {
    public static boolean validateSourceAndDestination(String source, String destination) {
        return source.matches("^[A-Za-z]+$")
                && destination.matches("^[A-Za-z]+$");

    }

    public static boolean isNullSourceAndDestination(String source, String destination) {
        return source == null || destination == null;

    }

    public static boolean isEmptySourceAndDestination(String source, String destination) {
        return source.isEmpty() || destination.isEmpty();
    }

    public static boolean isEmptySource(String source) {
        return source.isEmpty();
    }

    public static boolean isEmptyDestination(String destination) {
        return destination.isEmpty();
    }

    public static void validate(String source, String destination) {
        if (RouteUtils.isNullSourceAndDestination(source, destination)) {
            throw new EmptySourceAndDestinationException();
        }

        else if (RouteUtils.isEmptySourceAndDestination(source, destination)) {
            throw new EmptySourceAndDestinationException();
        }

        else if (!RouteUtils.validateSourceAndDestination(source, destination)) {
            throw new InvalidInputSourceAndDestinationException(source, destination);
        }
    }
}
