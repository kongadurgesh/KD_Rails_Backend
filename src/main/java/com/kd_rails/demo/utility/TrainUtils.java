package com.kd_rails.demo.utility;

import com.kd_rails.demo.exception.InvalidInputTrainIdException;

public class TrainUtils {
    public static Integer validateTrainId(String trainId) {
        try {
            if (trainId.length() == 5)
                return Integer.parseInt(trainId);
            else
                throw new InvalidInputTrainIdException(trainId);
        } catch (Exception e) {
            throw new InvalidInputTrainIdException(trainId);
        }
    }

    public static String getDeletedTrainMessage(String trainId, String routeId) {
        return "Train " + trainId + " has been removed from Route with Route Id: " + routeId;
    }
}
