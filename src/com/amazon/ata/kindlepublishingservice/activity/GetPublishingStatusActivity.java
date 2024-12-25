package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.models.requests.GetPublishingStatusRequest;
import com.amazon.ata.kindlepublishingservice.models.response.GetPublishingStatusResponse;
import com.amazon.ata.kindlepublishingservice.converters.PublishingStatusItemConverter;

import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class GetPublishingStatusActivity {
    private final PublishingStatusDao publishingStatusDao;
    @Inject
    public GetPublishingStatusActivity(PublishingStatusDao publishingStatusDao) {
        // Constructor
        // Initialize any necessary dependencies here, such as DynamoDB or AWS SDK clients.
        // Example: DynamoDBMapper dynamoDBMapper = DynamoDBMapper.builder().build();
        // Example: AmazonDynamoDBClient dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();
    this.publishingStatusDao = publishingStatusDao;

    }


    /**
     * Accepts a publishingRecordId and returns the publishing status history of the book submission from the
     * PublishingStatus table.
     * When a SUCCESSFUL PublishingStatus has been reached, the PublishingStatusRecord should contain a bookId,
     * if the book already exists, each PublishingStatusRecord will have a bookId.
     * Converts the PublishingStatusItem list to a PublishingStatusRecord list.
     *
     * @param publishingStatusRequest contains the ID to be queried
     * @return GetPublishingStatusResponse containing the PublishingStatusRecord list.
     */
    // You may use the provided PublishingStatusDao to interact with the PublishingStatus table.

    // Example:
    // PublishingStatusItem publishingStatusItem = publishingStatusDao.getPublishingStatus(publishingStatusRequest.getPublishingRecordId());

    // Create a GetPublishingStatusResponse with the retrieved publishing status history
    // GetPublishingStatusResponse response = new GetPublishingStatusResponse(publishingStatusItem);

    // Return the response
    // return response;
    // Example:
    // Get the publishing status history for the given publishingRecordId
    // PublishingStatusItem publishingStatusItem = publishingStatusDao.getPublishingStatus(publishingStatusRequest.getPublishingRecordId());

    // Convert the PublishingStatusItem to a PublishingStatusRecord
    // PublishingStatusRecord publishingStatusRecord = PublishingStatusRecord.fromPublishingStatusItem(publishingStatusItem);

    // Create a GetPublishingStatusResponse with the PublishingStatusRecord list
    // GetPublishingStatusResponse response = new GetPublishingStatusResponse(publishingStatusRecord);

    // Return the response
    // return response;
         // Placeholder return statement for demonstration purposes

    /*public GetPublishingStatusResponse execute(GetPublishingStatusRequest publishingStatusRequest) {

        // TODO: Implement the logic to retrieve publishing status based on the provided publishingRecordId.
    String publishingRecordId = publishingStatusRequest.getPublishingRecordId();// Obtiene el id de publicacion del request
        List<PublishingStatusItem> statusItems = publishingStatusDao.getPublishingStatusItems(publishingRecordId); //
        // Obtiene la lista de estados de publicacion para el id de publicacion

        return GetPublishingStatusResponse.builder()
                // Construye la respuesta con la lista de estados de publicacion
        .withPublishingStatusHistory(PublishingStatusItemConverter.toPublishingStatusRecord(statusItems))
                .build();

    }*/
    public GetPublishingStatusResponse execute(GetPublishingStatusRequest publishingStatusRequest) {
        String publishingRecordId = publishingStatusRequest.getPublishingRecordId();
        List<PublishingStatusItem> statusItems = publishingStatusDao.getPublishingStatusItems(publishingRecordId);

        return GetPublishingStatusResponse.builder()
                .withPublishingStatusHistory(PublishingStatusItemConverter.toPublishingStatusRecord(statusItems))
                .build();
    }
}
