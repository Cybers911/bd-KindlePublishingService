package com.amazon.ata.kindlepublishingservice.converters;

import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatusRecord;

import java.util.ArrayList;
import java.util.List;

public class PublishingStatusItemConverter {
    private PublishingStatusItemConverter() {}

    /**
     * Converts a list of {@link PublishingStatusItem} to a list of {@link PublishingStatusRecord}. If bookId is null,
     * it will return a PublishingStatusRecord without a bookId.
     * @param publishingStatusItems the item to be converted
     * @return the built PublishingStatusRecord
     */
    public static List<PublishingStatusRecord> toPublishingStatusRecord(List<PublishingStatusItem> publishingStatusItems) {
        List<PublishingStatusRecord> recordList = new ArrayList<>();

        for (PublishingStatusItem statusItem : publishingStatusItems) {
            if (statusItem.getBookId() == null) {
                recordList.add(PublishingStatusRecord.builder()
                        .withStatus(statusItem.getStatus().name())
                        .withStatusMessage(statusItem.getStatusMessage())
                        .build());
            } else {
                recordList.add(PublishingStatusRecord.builder()
                        .withStatus(statusItem.getStatus().name())
                        .withStatusMessage(statusItem.getStatusMessage())
                        .withBookId(statusItem.getBookId())
                        .build());
            }
        }
        return recordList;
    }
}
